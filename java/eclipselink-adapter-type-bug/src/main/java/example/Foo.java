package example;

import java.math.BigDecimal;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(name = "foo_t")
public class Foo {
    @XmlElement(required = true, type = String.class)
    @XmlSchemaType(name = "decimal")
    public BigDecimal noAdapter;

    // Custom BigDecimal adapter
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(CustomBigDecimalAdapter.class)
    @XmlSchemaType(name = "decimal")
    public BigDecimal customAdapter;

    // Custom BigDecimal adapter, no schematype.
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(CustomBigDecimalAdapter.class)
    public BigDecimal customAdapterNoType;
}
