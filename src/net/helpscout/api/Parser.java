package net.helpscout.api;

import java.util.Calendar;

import net.helpscout.api.adapters.CalendarAdapter;
import net.helpscout.api.adapters.PersonRefAdapter;
import net.helpscout.api.adapters.StatusAdapter;
import net.helpscout.api.adapters.ThreadStateAdapter;
import net.helpscout.api.adapters.ThreadsAdapater;
import net.helpscout.api.cbo.JsonThreadLocal;
import net.helpscout.api.cbo.Status;
import net.helpscout.api.cbo.ThreadState;
import net.helpscout.api.model.Conversation;
import net.helpscout.api.model.Customer;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.thread.LineItem;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public final class Parser {
	private final GsonBuilder builder;
	
	private static Parser instance = null;
	
	private Parser() {
		builder = new GsonBuilder();
		builder.registerTypeAdapter(ThreadState.class, new ThreadStateAdapter());
		builder.registerTypeAdapter(Status.class, new StatusAdapter());
		builder.registerTypeAdapter(PersonRef.class, new PersonRefAdapter(builder));
		builder.registerTypeAdapter(LineItem.class, new ThreadsAdapater(builder));		
		builder.registerTypeAdapter(Calendar.class, new CalendarAdapter());		
	}
	
	public synchronized static Parser getInstance() {
		if (instance == null) {			
			instance = new Parser();			
		}
		return instance;
	}
	
	public Conversation getConversation(String json) {		
		JsonElement obj  = (new JsonParser()).parse(json);
		return (Conversation)getObject(obj, Conversation.class);
	}
	
	public Customer getCustomer(String json) {		
		JsonElement obj  = (new JsonParser()).parse(json);
		return (Customer)getObject(obj, Customer.class);
	}	
	
	public Object getObject(JsonElement item, Class<?> clazzType) {						
		JsonThreadLocal.set(item);
		
		Object clazz = builder.create().fromJson(item, clazzType);
				
		JsonThreadLocal.unset();
		
		return clazz;
	}
}
