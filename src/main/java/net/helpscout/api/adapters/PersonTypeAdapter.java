package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.cbo.PersonType;

import java.lang.reflect.Type;

/**
 * PersonTypeAdapter -
 *
 * @author briandame@gmail.com
 */
public class PersonTypeAdapter implements JsonDeserializer<PersonType>, JsonSerializer<PersonType> {

	public PersonType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return PersonType.findByLabel(json.getAsString());
	}

	public JsonElement serialize(PersonType type, Type typeOfT, JsonSerializationContext context) {
		return new JsonPrimitive(type.getLabel());
	}
}
