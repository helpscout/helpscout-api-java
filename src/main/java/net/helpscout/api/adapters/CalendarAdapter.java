package net.helpscout.api.adapters;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class CalendarAdapter implements JsonDeserializer<Calendar> {
	private final DateFormat dateFormat;
	
	public CalendarAdapter() {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
	

	@Override
	public synchronized Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
			synchronized (dateFormat) {								
				Calendar cal = Calendar.getInstance();
				cal.setTimeZone(TimeZone.getTimeZone("UTC"));
				try {
					cal.setTime(dateFormat.parse(json.getAsString()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return cal;
			}
		
	}
}
