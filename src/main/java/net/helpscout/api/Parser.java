package net.helpscout.api;

import java.util.Date;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import net.helpscout.api.adapters.*;
import net.helpscout.api.cbo.*;
import net.helpscout.api.model.Conversation;
import net.helpscout.api.model.Customer;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.report.common.DateAndCount;
import net.helpscout.api.model.thread.LineItem;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public final class Parser {
	private final GsonBuilder builder;

	private static Parser instance = null;

	private Parser() {
		builder = new GsonBuilder();
		builder.registerTypeAdapter(ConversationType.class, new ConversationTypeAdapter());
		builder.registerTypeAdapter(ThreadType.class, new ThreadTypeAdapter());
		builder.registerTypeAdapter(ThreadState.class, new ThreadStateAdapter());
		builder.registerTypeAdapter(Status.class, new StatusAdapter());
        builder.registerTypeAdapter(ActionType.class, new ActionTypeAdapter());
        builder.registerTypeAdapter(Date.class, new DateAdapter());
        builder.registerTypeAdapter(WorkflowStatus.class, new WorkflowStatusAdapter());
        builder.registerTypeAdapter(WorkflowType.class, new WorkflowTypeAdapter());
		builder.registerTypeAdapter(PersonRef.class, new PersonRefAdapter(builder));
		builder.registerTypeAdapter(LineItem.class, new ThreadsAdapater(builder));
		builder.registerTypeAdapter(DateAndCount.class, new DateAndCountDeserializer(builder));
	}

	public synchronized static Parser getInstance() {
		if (instance == null) {
			instance = new Parser();
		}
		return instance;
	}

	public Conversation getConversation(String json) {
		JsonElement obj  = (new JsonParser()).parse(json);
		return getObject(obj, Conversation.class);
	}

	public Customer getCustomer(String json) {
		JsonElement obj  = (new JsonParser()).parse(json);
		return getObject(obj, Customer.class);
	}
	
	public <T> T getObject(String json, Class<T> clazzType) {
	    JsonElement obj  = new JsonParser().parse(json);
	    return getObject(obj, clazzType);
	}

	public <T> T getObject(JsonElement item, Class<T> clazzType) {
		JsonThreadLocal.set(item);
		try {
			return builder.create().fromJson(item, clazzType);
		} finally {
			JsonThreadLocal.unset();
		}
	}
}
