package example;

import java.io.StringWriter;
import java.math.BigDecimal;
import javax.xml.bind.*;

class Main {
    public static void main(String[] args) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Foo.class);

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty("eclipselink.media-type", "application/json");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Foo foo = new Foo();
            foo.noAdapter = new BigDecimal("111111111111111111111111111.11111111111111111");
            foo.customAdapter = new BigDecimal("111111111111111111111111111.11111111111111111");
            foo.customAdapterNoType = new BigDecimal("111111111111111111111111111.11111111111111111");

            StringWriter sw = new StringWriter();
            marshaller.marshal(foo, sw);
            String result = sw.getBuffer().toString();

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
