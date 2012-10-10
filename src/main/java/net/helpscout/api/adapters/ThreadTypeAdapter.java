package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.cbo.ThreadType;

import java.lang.reflect.Type;

/**
 * ThreadTypeAdapter -
 *
 * @author briandame@gmail.com
 */
public class ThreadTypeAdapter implements JsonDeserializer<ThreadType>, JsonSerializer<ThreadType> {

	public ThreadType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return ThreadType.findByLabel(json.getAsString());
	}

	public JsonElement serialize(ThreadType type, Type srcType, JsonSerializationContext context) {
		JsonPrimitive json = new JsonPrimitive(type.getLabel());
		return json;
	}
}
