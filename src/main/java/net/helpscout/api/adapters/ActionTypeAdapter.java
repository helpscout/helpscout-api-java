package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.cbo.ActionType;

import java.lang.reflect.Type;

/**
 * ActionTypeAdapter -
 *
 * @author briandame@gmail.com
 */
public class ActionTypeAdapter implements JsonDeserializer<ActionType>, JsonSerializer<ActionType> {

    public ActionType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return ActionType.findByLabel(json.getAsString());
    }

    public JsonElement serialize(ActionType actionType, Type typeOfT, JsonSerializationContext context) {
        return new JsonPrimitive(actionType.getLabel());
    }
}
