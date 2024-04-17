package pizza.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ProductTypesDeserializer extends JsonDeserializer<ProductTypes> {
    @Override
    public ProductTypes deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        String value = jsonParser.getValueAsString();
        try {
            return ProductTypes.valueOf(value);
        } catch (IllegalArgumentException e) {
            return null; // Возвращаем null для недопустимых значений
        }
    }
}
