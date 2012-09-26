package net.helpscout.api.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.helpscout.api.cbo.ThreadState;

import java.lang.reflect.Type;

public class ThreadStateAdapter implements JsonDeserializer<ThreadState> {

	public ThreadState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return ThreadState.findByKey(json.getAsString());
	}
}
