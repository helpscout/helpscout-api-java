package net.helpscout.api.cbo;

import com.google.gson.JsonElement;

public class JsonThreadLocal {
	public static final ThreadLocal<JsonElement> instance = new ThreadLocal<JsonElement>();
	
	public static void set(JsonElement user) {
		instance.remove();
		instance.set(user);
	}

	public static void unset() {
		instance.remove();
	}

	public static JsonElement get() {
		return instance.get();
	}
}
