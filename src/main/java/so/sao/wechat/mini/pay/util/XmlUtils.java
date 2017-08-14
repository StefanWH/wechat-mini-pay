package so.sao.wechat.mini.pay.util;

import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public final class XmlUtils {

    private XmlUtils() {
    }

    public static void configureXmlMapperForSpeed(XmlMapper xmlMapper) {
        XmlFactory xmlFactory = xmlMapper.getFactory();
        WstxOutputFactory xmlOutputFactory = (WstxOutputFactory) xmlFactory.getXMLOutputFactory();
        xmlOutputFactory.configureForSpeed();
    }
}
