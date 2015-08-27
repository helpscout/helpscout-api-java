package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.cbo.Status;

import java.lang.reflect.Type;

public class StatusAdapter implements JsonDeserializer<Status>, JsonSerializer<Status> {

    public Status deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Status.findByKey(json.getAsString());
    }

    public JsonElement serialize(Status status, Type typeOfT, JsonSerializationContext context) {
        return new JsonPrimitive(status.getKey());
    }
}
