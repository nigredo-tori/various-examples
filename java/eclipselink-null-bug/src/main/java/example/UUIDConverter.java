package example;

import java.util.UUID;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UUIDConverter implements AttributeConverter<UUID, UUID> {
    @Override
    public UUID convertToDatabaseColumn(UUID x) {
        return x;
    }

    @Override
    public UUID convertToEntityAttribute(UUID x) {
        return x;
    }
}
