package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.model.customfield.*;

import java.lang.reflect.Type;

public class CustomFieldResponseAdapter implements JsonSerializer<CustomFieldResponse<?>>, JsonDeserializer<CustomFieldResponse<?>> {

    @Override
    public JsonElement serialize(CustomFieldResponse customFieldResponse, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject json = new JsonObject();
        json.addProperty("fieldId", customFieldResponse.getFieldId());
        json.addProperty("value", customFieldResponse.getStringValue());
        json.addProperty("name", customFieldResponse.getName());
        return json;
    }

    @Override
    public CustomFieldResponse<?> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonElement fieldType = jsonObject.get("type");
        if (fieldType == null) {
            throw new JsonParseException("'type' field expected in the JSON");
        }

        CustomFieldType customFieldType = CustomFieldType.valueOf(fieldType.getAsString());
        CustomFieldResponse<?> response = createCustomField(customFieldType);

        String value = asString(jsonObject, "value");
        response.fromStringValue(value);

        Long fieldId = asLong(jsonObject, "fieldId");
        response.setFieldId(fieldId);

        String fieldName = asString(jsonObject, "name");
        response.setName(fieldName);

        return response;
    }


    private static CustomFieldResponse<?> createCustomField(CustomFieldType customFieldType) {
        switch (customFieldType) {
            case SINGLE_LINE:
                return new SingleLineCustomFieldResponse();
            case MULTI_LINE:
                return new MultiLineCustomFieldResponse();
            case NUMBER:
                return new NumberCustomFieldResponse();
            case DATE:
                return new DateCustomFieldResponse();
            case DROPDOWN:
                return new DropDownCustomFieldResponse();
            default:
                throw new JsonParseException("Unknown Custom Field type " + customFieldType);
        }

    }

    private static String asString(JsonObject jsonObject, String fieldName) {
        JsonElement value = jsonObject.get(fieldName);
        if (value != null) {
            return value.getAsString();
        } else {
            return null;
        }
    }

    private static Long asLong(JsonObject jsonObject, String fieldName) {
        JsonElement value = jsonObject.get(fieldName);
        if (value != null) {
            return value.getAsLong();
        } else {
            return null;
        }
    }

}




