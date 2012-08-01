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

import sun.misc.BASE64Encoder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ApiClient {
	private final static String BASE_URL = "https://api.helpscout.net/v1/";
	
	private String apiKey = "";
	
	public static void main(String[] args) {
		ApiClient client = new ApiClient();
		
		Page response = client.getMailboxes();
		
		Mailbox m = client.getMailbox(85);
		
		System.out.println(response);
		
	}
	
	public Mailbox getMailbox(Integer mailboxID) {
		return (Mailbox)getItem("mailboxes/" + mailboxID + ".json", Mailbox.class);
	}
	
	public Mailbox getMailbox(Integer mailboxID, List<String> fields) throws ApiException {
		if (mailboxID == null || mailboxID < 1) {
			throw new ApiException("Invalid mailboxId in getMailbox");		
		}
		if (fields != null && fields.size() > 0) {
			// handle fields
		}

	}
	
	
	public Page getMailboxes() {
		return getPage("mailboxes.json", Mailbox.class);
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
	    return response;
	} 
	
	private String getBase64Encoded(String val) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(val.getBytes());		
	}
}
