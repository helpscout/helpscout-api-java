package net.helpscout.api.adapters;

import java.lang.reflect.Type;

import net.helpscout.api.cbo.Status;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class StatusAdapter implements JsonDeserializer<Status> {
	
	@Override
	public Status deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return Status.findByKey(json.getAsString());		
	}	
}
