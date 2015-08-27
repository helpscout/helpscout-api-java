package net.helpscout.api.adapters;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    private final DateFormat dateFormat;

    public DateAdapter() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public JsonElement serialize(Date src, Type srcType, JsonSerializationContext context) {
        synchronized (dateFormat) {
            return new JsonPrimitive(dateFormat.format(src));
        }
    }

    public synchronized Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        synchronized (dateFormat) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                cal.setTime(dateFormat.parse(json.getAsString()));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return cal.getTime();
        }
    }
}
