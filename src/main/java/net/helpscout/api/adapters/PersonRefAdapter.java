package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.cbo.JsonThreadLocal;
import net.helpscout.api.cbo.PersonType;
import net.helpscout.api.model.ref.CustomerRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.TeamRef;
import net.helpscout.api.model.ref.UserRef;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

public class PersonRefAdapter implements JsonDeserializer<PersonRef> {
    private GsonBuilder gson;

    public PersonRefAdapter(GsonBuilder gson) {
        gson.registerTypeAdapter(PersonType.class, new PersonTypeAdapter());
        this.gson = gson;
    }

    public PersonRef deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement element = JsonThreadLocal.get();
        String type = element.getAsJsonObject().get("type").getAsString();
        Class<? extends PersonRef> personRefClass = getPersonRefClass(type);
        return gson.create().fromJson(json, personRefClass);
    }


    private static Class<? extends PersonRef> getPersonRefClass(String personTypeString) {
        if (StringUtils.isEmpty(personTypeString)) {
            return UserRef.class;
        }
        PersonType personType = PersonType.findByLabel(personTypeString.trim());
        switch (personType) {
            case User:
                return UserRef.class;
            case Customer:
                return CustomerRef.class;
            case Team:
                return TeamRef.class;
            default:
                return UserRef.class;
        }

    }
}
