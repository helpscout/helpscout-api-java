package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.cbo.ThreadState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

public class ThreadStateAdapter implements JsonDeserializer<ThreadState>, JsonSerializer<ThreadState> {

	final static Logger log = LoggerFactory.getLogger(ThreadStateAdapter.class);

	public ThreadState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return ThreadState.findByKey(json.getAsString());
	}

	public JsonElement serialize(ThreadState threadState, Type srcType, JsonSerializationContext context) {
		JsonPrimitive json = new JsonPrimitive(threadState.getKey());
		log.debug("BKD => Thread state: " + json.toString());
		return json;
	}
}
