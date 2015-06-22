package net.helpscout.api.adapters;

import java.lang.reflect.Type;
import java.util.Date;

import lombok.RequiredArgsConstructor;
import net.helpscout.api.model.report.common.DateAndCount;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

@RequiredArgsConstructor
public class DateAndCountDeserializer implements JsonDeserializer<DateAndCount> {
    
    private final GsonBuilder gson;
    
    public DateAndCount deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        
        DateAndCount stat = new DateAndCount();
        stat.setDate(extractDate(json.getAsJsonObject()));
        stat.setCount(extractCount(json.getAsJsonObject()));
        
        return stat;
    }
    
    protected int extractCount(JsonObject json) {
        // The API refers to the count by various names depending on context.
        if(json.has("count"))     return json.get("count").getAsInt();
        if(json.has("customers")) return json.get("customers").getAsInt();
        if(json.has("replies"))   return json.get("replies").getAsInt();
        if(json.has("resolved"))  return json.get("resolved").getAsInt();
        
        throw new JsonParseException("Unable to parse the count for " + json);
    }
    
    protected Date extractDate(JsonObject json) {
        // The API refers to the date by various names depending on context.
        if(json.has("start")) return getDateByName(json, "start");
        if(json.has("date"))  return getDateByName(json, "date");
        
        throw new JsonParseException("Unable to parse the date for " + json);
    }
    
    protected Date getDateByName(JsonObject json, String name) {
        JsonElement date = json.get(name);
        return gson.create().fromJson(date, Date.class);
    }
}