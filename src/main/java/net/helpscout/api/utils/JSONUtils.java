package net.helpscout.api.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.helpscout.api.Page;
import net.helpscout.api.Parser;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * @Author: ivan
 * Date: 28.04.16
 * Time: 0:26
 */
public class JSONUtils {

    public static <T> Page<T> objectToPage(JsonObject obj, Class<T> clazzType) {
        Set<Map.Entry<String, JsonElement>> set = obj.entrySet();

        Page<T> p = new Page<T>();

        for (Map.Entry<String, JsonElement> a : set) {
            String key = a.getKey();
            JsonElement val = a.getValue();

            if (key.equals("page")) {
                p.setPage(val.getAsInt());
                continue;
            }
            if (key.equals("pages")) {
                p.setPages(val.getAsInt());
                continue;
            }
            if (key.equals("count")) {
                p.setCount(val.getAsInt());
                continue;
            }
            if (key.equals("items") || key.equalsIgnoreCase("results")) {
                p.setItems(getPageItems(val, clazzType));
            }
        }
        return p;
    }

    public static <T> ArrayList<T> getPageItems(JsonElement elem, Class<T> clazzType) {
        JsonArray ar = elem.getAsJsonArray();
        ArrayList<T> col = new ArrayList<T>(ar.size());
        for (JsonElement e : ar) {
            T o = (T) Parser.getInstance().getObject(e, clazzType);
            if (o != null) {
                col.add(o);
            }
        }
        return col;
    }
}
