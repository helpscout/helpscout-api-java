package net.helpscout.api.adapters;

import java.lang.reflect.Type;

import net.helpscout.api.cbo.JsonThreadLocal;
import net.helpscout.api.model.ref.CustomerRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class PersonRefAdapter implements JsonDeserializer<PersonRef> {
	private GsonBuilder gson;
	
	public PersonRefAdapter(GsonBuilder gson) {
		this.gson = gson;		
	}
	
	@Override
	public PersonRef deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		
		JsonElement element = JsonThreadLocal.get();
		JsonElement source = element.getAsJsonObject().get("source");
				
		if (source != null && !(source instanceof JsonNull)) {
			String via = source.getAsJsonObject().get("via").getAsString();
			if ("customer".equals(via)) {
				return gson.create().fromJson(json, CustomerRef.class);	
			}
		}
		return gson.create().fromJson(json, UserRef.class);		
	}	
}
