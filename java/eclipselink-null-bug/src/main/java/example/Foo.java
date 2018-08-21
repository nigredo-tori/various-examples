package example;

import java.util.UUID;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Foo
{
    @Id
    private int id;

    @Convert(converter = UUIDConverter.class)
    private UUID uid;

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public UUID getUid() {
        return uid;
    }
}
