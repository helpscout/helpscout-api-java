package net.helpscout.api.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.helpscout.api.cbo.ConversationType;

import java.lang.reflect.Type;

/**
 * ConversationTypeAdapter -
 *
 * @author briandame@gmail.com
 */
public class ConversationTypeAdapter implements JsonDeserializer<ConversationType> {

	public ConversationType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return ConversationType.findByLabel(json.getAsString());
	}
}
