package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.cbo.ConversationType;

import java.lang.reflect.Type;

/**
 * ConversationTypeAdapter -
 *
 * @author briandame@gmail.com
 */
public class ConversationTypeAdapter implements JsonDeserializer<ConversationType>, JsonSerializer<ConversationType> {

	public ConversationType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return ConversationType.findByLabel(json.getAsString());
	}

	public JsonElement serialize(ConversationType type, Type typeOfT, JsonSerializationContext context) {
		return new JsonPrimitive(type.getLabel());
	}
}
