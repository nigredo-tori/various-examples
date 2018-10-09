package example;

import java.math.BigDecimal;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CustomBigDecimalAdapter extends XmlAdapter<String, BigDecimal>{

    private final String prefix = "custom:";

    @Override
    public BigDecimal unmarshal(String v) throws Exception {
        return new BigDecimal(v.substring(prefix.length()));
    }

    @Override
    public String marshal(BigDecimal v) throws Exception {
        return prefix + v.toPlainString();
    }

}
