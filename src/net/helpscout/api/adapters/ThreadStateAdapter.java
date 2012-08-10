package net.helpscout.api.adapters;

import java.lang.reflect.Type;

import net.helpscout.api.cbo.ThreadState;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ThreadStateAdapter implements JsonDeserializer<ThreadState> {
	
	@Override
	public ThreadState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return ThreadState.findByKey(json.getAsString());		
	}	
}
