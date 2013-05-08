package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.cbo.WorkflowType;

import java.lang.reflect.Type;

/**
 * WorkflowTypeAdapter -
 *
 * @author briandame@gmail.com
 */
public class WorkflowTypeAdapter implements JsonDeserializer<WorkflowType>, JsonSerializer<WorkflowType> {

    public WorkflowType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return WorkflowType.findByKey(json.getAsString());
    }

    public JsonElement serialize(WorkflowType type, Type typeOfT, JsonSerializationContext context) {
        return new JsonPrimitive(type.getKey());
    }
}
