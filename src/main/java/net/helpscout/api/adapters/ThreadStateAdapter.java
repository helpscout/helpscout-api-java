package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.cbo.ThreadState;

import java.lang.reflect.Type;

public class ThreadStateAdapter implements JsonDeserializer<ThreadState>, JsonSerializer<ThreadState> {

    public ThreadState deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return ThreadState.findByKey(json.getAsString());
    }

    public JsonElement serialize(ThreadState threadState, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(threadState.getKey());
    }
}
