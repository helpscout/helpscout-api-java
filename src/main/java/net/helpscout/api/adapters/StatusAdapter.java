package net.helpscout.api.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.helpscout.api.cbo.Status;

import java.lang.reflect.Type;

public class StatusAdapter implements JsonDeserializer<Status> {

	public Status deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return Status.findByKey(json.getAsString());
	}
}
