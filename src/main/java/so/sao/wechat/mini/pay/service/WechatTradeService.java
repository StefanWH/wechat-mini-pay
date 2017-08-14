package so.sao.wechat.mini.pay.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;


import so.sao.wechat.mini.pay.config.WechatProperties;
import so.sao.wechat.mini.pay.errorcode.MicropayErrorCode;
import so.sao.wechat.mini.pay.errorcode.PaymentErrorCode;
import so.sao.wechat.mini.pay.exception.ShopRuntimeException;
import so.sao.wechat.mini.pay.exception.WechatException;
import so.sao.wechat.mini.pay.mapper.JsonMapper;
import so.sao.wechat.mini.pay.request.*;
import so.sao.wechat.mini.pay.response.*;
import so.sao.wechat.mini.pay.util.WechatUtils;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;


public class WechatTradeService {

    private RestTemplate wechatPaymentRestTemplate;

    private JsonMapper wechatPaymentXmlMapper;

    private WechatProperties wechatProperties;


    /**
     * 发送小程序支付接口
     * */
    public WechatResign pay(WechatUnifiedOrderRequest request){
        request.setSpbillCreateIp(wechatProperties.getClientIp());//42.159.145.190(todo..............)
        //request.setNotifyUrl(wechatProperties.getNotifyUrl());//接受微信通知的回掉地址（todo.........）
//        request.setSign(WechatUtils.sign(request, paymentPay));//设置sign
        request.setNonceStr(generateNonceString());//设置随机字符串
        String paymentKey=request.getPaymentKey();
        String appid=request.getAppid();

        UnifiedOrderResponse response = executeRequest("/pay/unifiedorder", request, UnifiedOrderResponse.class);
        //如果业务结果正确(需对返回的预付单信息进行再次签名prepay_id)
        if(response.getResultCode()== WechatResultCode.SUCCESS){
            //todo.....更改订单支付状态为预支付(支付结果待确认)（order_transaction）

            //获得预支付id，进行再签名
            String prepayId=response.getPrepayId();
            WechatResign resign=reSign(prepayId, paymentKey, appid);

            return resign;
        }else{
            MicropayErrorCode errorCode = MicropayErrorCode.valueOf(response.getErrCode());
            throw new WechatException(errorCode.getStatus(), errorCode, response.getErrCodeDes());
        }

    }

    /**
     * 对数据进行重签名返回前端（由前端调用微信支付接口）
     * */
    private WechatResign reSign(String prepayId, String paymentKey, String appId){
        WechatResign resign=new WechatResign();
        resign.setAppId(appId);
        resign.setNonceStr(generateNonceString());
        resign.setTimeStamp(getSecondsCount());
        resign.setPackagee("prepay_id="+prepayId);
        resign.setSignType("MD5");
        resign.setPaySign(WechatUtils.sign(resign, paymentKey));

        return resign;
    }

    /**
     * 计算当前时间到1970年1月1日00:00:00的秒数（用于微信小程序支付再签名）
     * */
    private String getSecondsCount(){
        Long count=System.currentTimeMillis() / 1000;
        return count.toString();
    }


    /**
     * 微信刷卡支付查询订单状态
     * */
    public OrderQueryResponse queryOrderByTransactionId(String transactionId) {

        WechatOrderQueryRequest request = new WechatOrderQueryRequest();
        request.setTransactionId(transactionId);
        return queryOrder(request);
    }

    /**
     * 关闭微信订单
     * */
    public OrderCloseResponse closeOrderByOrderId(String orderId, String appId, String mchId, String paymentKey){
        WechatCloseOrderRequest request = new WechatCloseOrderRequest();
        request.setOutTradeNo(orderId);
        request.setAppid(appId);
        request.setMchId(mchId);
        request.setPaymentKey(paymentKey);
        request.setNonceStr(generateNonceString());//设置随机字符串
        return closeOrder(request);
    }

    /**
     *  发送微信后台关闭订单
     * */
    private OrderCloseResponse closeOrder(WechatCloseOrderRequest request){
        return executeRequest("/pay/closeorder", request, OrderCloseResponse.class);
    }

    /**
     * 微信小程序查询微信端订单状态
     * */
    public OrderQueryResponse queryOrderByTransactionId(String transactionId, String appId, String mchId, String paymentKey, String orderId) {

        WechatOrderQueryRequest request = new WechatOrderQueryRequest();
        request.setOutTradeNo(transactionId);
        //request.setTransactionId(transactionId);//目前微信支付成功后小程序收不到微信的第三方流水id
        request.setPaymentKey(paymentKey);
        request.setMchId(mchId);
        request.setAppid(appId);
        request.setNonceStr(generateNonceString());//设置随机字符串
        return queryOrder(request);
    }

    /**
     * 发送查询微信订单状态
     * */
    private OrderQueryResponse queryOrder(WechatOrderQueryRequest request) {
        return executeRequest("/pay/orderquery", request, OrderQueryResponse.class);
    }


    /**
     * 微信退款
     * 微信退款需要双向服务器证书验证todo。。。
     * */
    public RefundResponse refund(WechatRefundRequest request) {
        //设置退款请求参数
        request.setAppid(wechatProperties.getMircoAppId());
        request.setMchId(wechatProperties.getMircoProgramMchId());
        request.setPaymentKey(wechatProperties.getMircoPaymentKey());
        request.setOpUserId(request.getMchId());
        request.setNonceStr(generateNonceString());
        setBaseRequestParams(request);
        //todo....验证双向证书
        try {
            //设置证书验证
            CloseableHttpClient httpclient = setApiclientCert();
            HttpPost httpPost  = new HttpPost(wechatProperties.getPaymentHost()+"/secapi/pay/refund");
            //将参数转化为xml格式
            String requestStr = wechatPaymentXmlMapper.toJson(request);
            //设置post参数
            StringEntity se = new StringEntity(requestStr);
            httpPost.setEntity(se);
            //发送请求
            CloseableHttpResponse response = httpclient.execute(httpPost);
            String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            return wechatPaymentXmlMapper.fromJson(jsonStr,RefundResponse.class);
        } catch (Exception e) {
            throw new ShopRuntimeException("发起退款失败");
        }

    }

    private CloseableHttpClient setApiclientCert() throws Exception {
        //指定读取证书格式为PKCS12
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //读取本机存放的PKCS12证书文件
        try (InputStream instream = wechatProperties.getMircoProgramCertFile().getInputStream()) {
            //指定PKCS12的密码(商户ID)
            keyStore.load(instream, wechatProperties.getMircoProgramMchId().toCharArray());
        }
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, wechatProperties.getMircoProgramMchId().toCharArray()).build();
        //指定TLS版本
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,new String[] { "TLSv1" },null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        //设置httpclient的SSLSocketFactory

        return HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();

    }

    public UnifiedOrderResponse placeUnifiedOrder(WechatUnifiedOrderRequest request) {
        request.setTradeType("APP");
        request.setSpbillCreateIp(wechatProperties.getClientIp());
        request.setNotifyUrl(wechatProperties.getNotifyUrl());
        return executeRequest("/pay/unifiedorder", request, UnifiedOrderResponse.class);
    }

    public String downloadBill(WechatDownloadBillRequest request) {
        return executeRequest("/pay/downloadbill", request);
    }

//    public WechatTransferResponse trasfer(WechatTransferRequest request) {
//        return executeRequest("/mmpaymkttransfers/promotion/transfers", request, WechatTransferResponse.class);
//    }

    /**
     * 微信通知小程序支付结果时进行签名验证
     * */
    public String getSign(WechatPaymentRequest request, String paymentKey) {
        request.setSign(null);
        return WechatUtils.sign(request, paymentKey);
    }

    private void setBaseRequestParams(WechatPaymentRequest request) {
        //小程序支付相关参数已在c端设置过不在这里进行统一处理在这只进行签名，刷卡支付在此设置支付参数及签名
        if(request.getAppid() != null && !request.getAppid().equals("")) {
            //WechatUnifiedOrderRequest wechatUnifiedOrderRequest=(WechatUnifiedOrderRequest)request;
            String paymentKey=request.getPaymentKey();
            request.setPaymentKey(null);
            request.setSign(WechatUtils.sign(request, paymentKey));
        }else{
            request.setAppid(wechatProperties.getPaymentAppId());
            request.setNonceStr(generateNonceString());
            request.setMchId(wechatProperties.getMchId());
            request.setSign(WechatUtils.sign(request, wechatProperties.getPaymentKey()));
        }
    }

    private String executeRequest(String url, WechatPaymentRequest request) {
        setBaseRequestParams(request);
        return wechatPaymentRestTemplate.postForObject(url, request, String.class);
    }

    private <T extends WechatResponse> T executeRequest(String url, WechatRequest request, Class<T> responseType) {
        //发送接口获取返回结果
        String responseStr = executeRequest(url, (WechatPaymentRequest)request);
        //将返回的xml格式的结果转化为json形式
        T response = wechatPaymentXmlMapper.fromJson(responseStr, responseType);
        //判断返回状态如果不成功取得错误信息
        if (response.getReturnCode() != WechatResultCode.SUCCESS) {
            throw new WechatException(HttpStatus.INTERNAL_SERVER_ERROR, PaymentErrorCode.UNKNOWN_ERROR, response.getReturnMsg());
        }
        return response;
    }

    public String generateNonceString() {
        return RandomStringUtils.randomAlphanumeric(32);
    }

    public void setWechatPaymentRestTemplate(RestTemplate wechatPaymentRestTemplate) {
        this.wechatPaymentRestTemplate = wechatPaymentRestTemplate;
    }

    public void setWechatPaymentXmlMapper(JsonMapper wechatPaymentXmlMapper) {
        this.wechatPaymentXmlMapper = wechatPaymentXmlMapper;
    }

    public void setWechatProperties(WechatProperties wechatProperties) {
        this.wechatProperties = wechatProperties;
    }

}
