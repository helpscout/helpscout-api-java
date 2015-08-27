package net.helpscout.api.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonFormatter {

    private JsonParser parser = new JsonParser();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String format(String rawJson) {
        JsonElement el = parser.parse(rawJson);
        return gson.toJson(el);
    }
}