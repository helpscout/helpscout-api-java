package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.cbo.WorkflowStatus;

import java.lang.reflect.Type;

public class WorkflowStatusAdapter implements JsonDeserializer<WorkflowStatus>, JsonSerializer<WorkflowStatus> {

    public WorkflowStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return WorkflowStatus.findByKey(json.getAsString());
    }

    public JsonElement serialize(WorkflowStatus status, Type typeOfT, JsonSerializationContext context) {
        return new JsonPrimitive(status.getKey());
    }
}
