package net.helpscout.api.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.helpscout.api.cbo.ThreadType;

import java.lang.reflect.Type;

/**
 * ThreadTypeAdapter -
 *
 * @author briandame@gmail.com
 */
public class ThreadTypeAdapter implements JsonDeserializer<ThreadType> {

	public ThreadType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return ThreadType.findByLabel(json.getAsString());
	}
}
