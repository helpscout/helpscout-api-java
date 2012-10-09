package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.cbo.CreatedByType;

import java.lang.reflect.Type;

/**
 * CreatedByTypeAdapter -
 *
 * @author briandame@gmail.com
 */
public class CreatedByTypeAdapter implements JsonDeserializer<CreatedByType>, JsonSerializer<CreatedByType> {

	public CreatedByType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return CreatedByType.findByLabel(json.getAsString());
	}

	public JsonElement serialize(CreatedByType type, Type typeOfT, JsonSerializationContext context) {
		return new JsonPrimitive(type.getLabel());
	}
}
