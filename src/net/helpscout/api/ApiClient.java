package net.helpscout.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.helpscout.api.model.Mailbox;
import net.helpscout.api.model.Folder;
import net.helpscout.api.model.Conversation;
import net.helpscout.api.model.Customer;
import net.helpscout.api.model.User;

import sun.misc.BASE64Encoder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ApiClient {
	private final static String BASE_URL = "https://api.helpscout.net/v1/";
	private String apiKey = "";
	
	public ApiClient() {
		// no param constructor
	}
	
	public ApiClient(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public void setKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public static void main(String[] args) throws ApiException {
		ApiClient client = new ApiClient("75ecf26bd0c9cbf294292d6c9e27dcfce928c11a");
		
		List<String> fields = new ArrayList<String>();
		fields.add("name");
		//fields.add("email");
		/*
		Page response = client.getMailboxes(fields);
		Mailbox m = client.getMailbox(85, fields);
		System.out.println(response.getItems());
		System.out.println(m.getEmail());
		Page response = client.getUsers();
		User u = client.getUser(3465, fields);
		Page response2 = client.getUsersByMailbox(85, fields);
		System.out.println(response2.getItems());
		System.out.println(u.getEmail());
		Page response = client.getFolders(85);
		System.out.println(response.getItems());
		Page response2 = client.getFolders(85, fields);
		System.out.println(response2.getItems());
		*/
		
		Page response = client.getCustomers();
		System.out.println(response.getItems());
	}
	
	public Page getConversationsByMailbox(Integer mailboxID) {
		return getPage("mailboxes/" + mailboxID + "/conversations.json", Conversation.class);
	}
	
	public Mailbox getMailbox(Integer mailboxID) {
		return (Mailbox)getItem("mailboxes/" + mailboxID + ".json", Mailbox.class);
	}
	
	public Mailbox getMailbox(Integer mailboxID, List<String> fields) throws ApiException {
		if (mailboxID == null || mailboxID < 1) {
			throw new ApiException("Invalid mailboxId in getMailbox");		
		}
		String url = setFields("mailboxes/" + mailboxID + ".json", fields);
		return (Mailbox)getItem(url, Mailbox.class);
	}
	
	public Page getMailboxes() {
		return getPage("mailboxes.json", Mailbox.class);
	}
	
	public Page getMailboxes(List<String> fields) throws ApiException {
		String url = setFields("mailboxes.json", fields);
		return getPage(url, Mailbox.class);
	}
	
	public Page getFolders(Integer mailboxId) {
		return getPage("mailboxes/" + mailboxId + "/folders.json", Folder.class);
	}
	
	public Page getFolders(Integer mailboxId, List<String> fields) throws ApiException {
		String url = setFields("mailboxes/" + mailboxId + "/folders.json", fields);
		return getPage(url, Folder.class);
	}
	
	public Page getCustomers() {
		return getPage("customers.json", Customer.class);
	}
	
	public Page getCustomers(List<String> fields) throws ApiException {
		String url = setFields("customers.json", fields);
		return getPage(url, Customer.class);
	}

	public User getUser(Integer userID) {
		return (User)getItem("users/" + userID + ".json", User.class);
	}
	
	public User getUser(Integer userID, List<String> fields) throws ApiException {
		if (userID == null || userID < 1) {
			throw new ApiException("Invalid userId in getUser");		
		}
		String url = setFields("users/" + userID + ".json", fields);
		return (User)getItem(url, User.class);
	}
	
	public Page getUsers() {
		return getPage("users.json", User.class);
	}
	
	public Page getUsers(List<String> fields) throws ApiException {
		String url = setFields("users.json", fields);
		return getPage(url, User.class);
	}
	
	public Page getUsersByMailbox(Integer mailboxId) {
		return getPage("mailboxes/" + mailboxId + "/users.json", User.class);
	}
	
	public Page getUsersByMailbox(Integer mailboxId, List<String> fields) throws ApiException {
		String url = setFields("mailboxes/" + mailboxId + "/users.json", fields);
		return getPage(url, User.class);
	}
	
	private String setFields(String url, List<String> fields) {
		if (fields != null && fields.size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(url + "?fields=");
		    String sep = "";
		    Iterator<String> iterator = fields.iterator();
		    while (iterator.hasNext()) {
		    	sb.append(sep).append(iterator.next());
		        sep = ",";
		    }
		    url = sb.toString();
		}
		return url;
	}
	
	private Object getItem(String url, Class<?> clazzType) {
		String json = callServer(url);
		JsonElement obj = (new JsonParser()).parse(json);
		JsonElement elem  = obj.getAsJsonObject().get("item");
		
		Gson gson = new Gson();
		return gson.fromJson(elem, clazzType);
	}
	
	private Page getPage(String url, Class<?> clazzType) {
		String json = callServer(url);
				
		JsonElement obj = (new JsonParser()).parse(json);
				
		Set<Map.Entry<String, JsonElement>> set = obj.getAsJsonObject().entrySet();

		Page p = new Page();
		
		Iterator<Map.Entry<String, JsonElement>> elem = set.iterator();
		while(elem.hasNext()) {
			Map.Entry<String, JsonElement> a = elem.next();
			String key      = a.getKey();
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
			if (key.equals("items")) {
				p.setItems(getPageItems(val, clazzType));				
			}									
		}
		return p;						
	}
	
	private ArrayList<Object> getPageItems(JsonElement elem, Class<?> clazzType) {
		Gson gson = new Gson();
		
		JsonArray ar = elem.getAsJsonArray();
		ArrayList<Object> col = new ArrayList<Object>(ar.size());
		
		for(JsonElement e : ar) {
			Object o = gson.fromJson(e, clazzType);
			if (o != null) {
				col.add(o);
			}
		}
		return col;
	}
	
	private String callServer(String url) {
		System.out.println(url);
		HttpURLConnection conn = null;
		
		String response = null;
		
	    try { 
	        URL aUrl = new URL(BASE_URL + url); 

	        conn = (HttpURLConnection) aUrl.openConnection(); 
	        
	        conn.setInstanceFollowRedirects(false); 
	        conn.setRequestMethod("GET"); 

	        conn.setRequestProperty("Content-Type", "application/json"); 
	        conn.setRequestProperty("Accept", "application/json"); 
	        conn.setRequestProperty("Authorization", "Basic " + getBase64Encoded(apiKey + ":x"));
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	     
	        StringBuilder sb = new StringBuilder();
	        
	    	String line;	    		
	    	while ((line = br.readLine()) != null) {
	    		sb.append(line);
	    	}
	     	    		
	    	conn.getResponseCode(); 
	    	conn.disconnect(); 
	    	
	    	response = sb.toString();
	    	
	    } catch(Exception e) { 
	        throw new RuntimeException(e); 
	    } finally {
	    	if (conn != null) {
	    		conn.disconnect(); 
	    	}	    	
	    }
	    System.out.println(response); // Testing
	    return response;
	} 
	
	private String getBase64Encoded(String val) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(val.getBytes());		
	}
}
