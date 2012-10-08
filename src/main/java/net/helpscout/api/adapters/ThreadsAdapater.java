package net.helpscout.api.adapters;

import com.google.gson.*;
import net.helpscout.api.cbo.JsonThreadLocal;
import net.helpscout.api.model.thread.*;

import java.lang.reflect.Type;

public class ThreadsAdapater implements JsonDeserializer<LineItem> {
	private GsonBuilder gson;

	public ThreadsAdapater(GsonBuilder gson) {
		this.gson = gson;
	}

	public LineItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();

		JsonThreadLocal.set(json);

		LineItem item = null;

		String theType = obj.get("type").getAsString();
		if ("lineitem".equals(theType)) {
			item = gson.create().fromJson(json, BaseLineItem.class);
		} else if ("message".equals(theType)) {
			item = gson.create().fromJson(json, Message.class);
		} else if ("customer".equals(theType)) {
			item = gson.create().fromJson(json, Customer.class);
		} else if ("note".equals(theType)) {
			item = gson.create().fromJson(json, Note.class);
		} else if ("forwardparent".equals(theType)) {
			item = gson.create().fromJson(json, ForwardParent.class);
		} else if ("forwardchild".equals(theType)) {
			item = gson.create().fromJson(json, ForwardChild.class);
		}

		JsonThreadLocal.unset();
		return item;
	}
}
