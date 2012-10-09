package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.cbo.JsonThreadLocal;
import net.helpscout.api.cbo.PersonType;
import net.helpscout.api.model.ref.CustomerRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;

import java.lang.reflect.Type;

public class PersonRefAdapter implements JsonDeserializer<PersonRef> {
	private GsonBuilder gson;

	public PersonRefAdapter(GsonBuilder gson) {
		gson.registerTypeAdapter(PersonType.class, new PersonTypeAdapter());
		this.gson = gson;
	}

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
