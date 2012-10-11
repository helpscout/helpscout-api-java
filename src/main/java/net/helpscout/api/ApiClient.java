package net.helpscout.api;

import com.google.gson.*;
import net.helpscout.api.adapters.*;
import net.helpscout.api.cbo.PersonType;
import net.helpscout.api.cbo.Status;
import net.helpscout.api.cbo.ThreadState;
import net.helpscout.api.cbo.ThreadType;
import net.helpscout.api.exception.*;
import net.helpscout.api.model.*;
import net.helpscout.api.model.Customer;
import net.helpscout.api.model.thread.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class ApiClient {

	// final static Logger log = LoggerFactory.getLogger(ApiClient.class);

	// private final static String BASE_URL = "https://api.helpscout.net/v1/";
	private final static String BASE_URL = "http://localhost:9000/v1/";
	private final static String METHOD_GET = "GET";
	private final static String METHOD_POST = "POST";
	private final static String METHOD_PUT = "PUT";
	private final static String METHOD_DELETE = "DELETE";

	private String apiKey = "";

	private static ApiClient instance = null;

	private ApiClient() {
	}

	public synchronized static ApiClient getInstance() {
		if (instance == null) {
			synchronized (BASE_URL) {
				if (instance == null) {
					instance = new ApiClient();
				}
			}
		}
		return instance;
	}

	public void setKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Mailbox getMailbox(Integer mailboxID) throws ApiException {
		return (Mailbox)getItem("mailboxes/" + mailboxID + ".json", Mailbox.class, 200);
	}

	public Mailbox getMailbox(Integer mailboxID, List<String> fields) throws ApiException {
		if (mailboxID == null || mailboxID < 1) {
			throw new ApiException("Invalid mailboxId in getMailbox");
		}
		String url = setFields("mailboxes/" + mailboxID + ".json", fields);
		return (Mailbox)getItem(url, Mailbox.class, 200);
	}

	public Page getMailboxes() throws ApiException {
		return getPage("mailboxes.json", Mailbox.class, 200);
	}

	public Page getMailboxes(List<String> fields) throws ApiException {
		String url = setFields("mailboxes.json", fields);
		return getPage(url, Mailbox.class, 200);
	}

	public Page getFolders(Integer mailboxId) throws ApiException {
		return getPage("mailboxes/" + mailboxId + "/folders.json", Folder.class, 200);
	}

	public Page getFolders(Integer mailboxId, List<String> fields) throws ApiException {
		String url = setFields("mailboxes/" + mailboxId + "/folders.json", fields);
		return getPage(url, Folder.class, 200);
	}

	public Page getConversationsForFolder(Integer mailboxID, Integer folderID) throws ApiException {
		return getPage("mailboxes/" + mailboxID + "/folders/" + folderID + "/conversations.json", Conversation.class, 200);
	}

	public Page getConversationsForFolder(Integer mailboxID, Integer folderID, List<String> fields) throws ApiException {
		String url = setFields("mailboxes/" + mailboxID + "/folders/" + folderID + "/conversations.json", fields);
		return getPage(url, Conversation.class, 200);
	}

	public Page getConversationsForMailbox(Integer mailboxID) throws ApiException {
		return getPage("mailboxes/" + mailboxID + "/conversations.json", Conversation.class, 200);
	}

	public Page getConversationsForMailbox(Integer mailboxID, List<String> fields) throws ApiException {
		String url = setFields("mailboxes/" + mailboxID + "/conversations.json", fields);
		return getPage(url, Conversation.class, 200);
	}

	public Page getConversationsForCustomerByMailbox(Integer mailboxID, Integer customerID) throws ApiException {
		return getPage("mailboxes/" + mailboxID + "/customers/" + customerID + "/conversations.json", Conversation.class, 200);
	}

	public Page getConversationsForCustomerByMailbox(Integer mailboxID, Integer customerID, List<String> fields) throws ApiException {
		String url = setFields("mailboxes/" + mailboxID + "/customers/" + customerID + "/conversations.json", fields);
		return getPage(url, Conversation.class, 200);
	}

	public Conversation getConversation(Long conversationID) throws ApiException {
		return (Conversation)getItem("conversations/" + conversationID + ".json", Conversation.class, 200);
	}

	public Conversation getConversation(Integer conversationID, List<String> fields) throws ApiException {
		if (conversationID == null || conversationID < 1) {
			throw new ApiException("Invalid conversationId in getConversation");
		}
		String url = setFields("conversations/" + conversationID + ".json", fields);
		return (Conversation)getItem(url, Conversation.class, 200);
	}

	public String getAttachmentData(Integer attachmentID) throws ApiException {
		if (attachmentID == null || attachmentID < 1) {
			throw new ApiException("Invalid attachmentID in getAttachmentData");
		}
		String json = doGet("attachments/" + attachmentID + "/data.json", 200);
		JsonElement obj = (new JsonParser()).parse(json);
		JsonElement elem  = obj.getAsJsonObject().get("item");
		return getDecoded(elem.getAsJsonObject().get("data").getAsString());
	}

	public Page getCustomers() throws ApiException {
		return getCustomers(new Integer(null));
	}

	public Page getCustomers(Integer page) throws ApiException {
		if (page != null) {
			return getPage("customers.json?page=" + page, Customer.class, 200);
		} else {
			return getPage("customers.json", Customer.class, 200);
		}
	}

	public Page getCustomers(List<String> fields) throws ApiException {
		String url = setFields("customers.json", fields);
		return getPage(url, Customer.class, 200);
	}

	public Page searchCustomers(String email, String firstName, String lastName) throws ApiException {
		return searchCustomers(email, firstName, lastName, null, null);
	}

	public Page searchCustomers(String email, String firstName, String lastName, Integer page,
			List<String> fields) throws ApiException {
		StringBuilder url = new StringBuilder();
		url.append(setFields("customers.json", fields));
		addSearchParams(url, email, firstName, lastName, page);
		return getPage(url.toString(), Customer.class, 200);
	}

	public Customer getCustomer(Long customerId) throws ApiException {
		return (Customer)getItem("customers/" + customerId + ".json", Customer.class, 200);
	}

	public Customer getCustomer(Long customerId, List<String> fields) throws ApiException {
		if (customerId == null || customerId < 1) {
			throw new ApiException("Invalid customerId in getCustomer");
		}
		String url = setFields("customers/" + customerId + ".json", fields);
		return (Customer)getItem(url, Customer.class, 200);
	}

	public User getUser(Integer userID) throws ApiException {
		return (User)getItem("users/" + userID + ".json", User.class, 200);
	}

	public User getUser(Integer userID, List<String> fields) throws ApiException {
		if (userID == null || userID < 1) {
			throw new ApiException("Invalid userId in getUser");
		}
		String url = setFields("users/" + userID + ".json", fields);
		return (User)getItem(url, User.class, 200);
	}

	public Page getUsers() throws ApiException {
		return getPage("users.json", User.class, 200);
	}

	public Page getUsers(List<String> fields) throws ApiException {
		String url = setFields("users.json", fields);
		return getPage(url, User.class, 200);
	}

	public Page getUsersForMailbox(Integer mailboxId) throws ApiException {
		return getPage("mailboxes/" + mailboxId + "/users.json", User.class, 200);
	}

	public Page getUsersForMailbox(Integer mailboxId, List<String> fields) throws ApiException {
		String url = setFields("mailboxes/" + mailboxId + "/users.json", fields);
		return getPage(url, User.class, 200);
	}

	public void createCustomer(Customer customer) throws ApiException {
		String json = new Gson().toJson(customer);
		Long id = (Long)doPost("customers.json", json, 201);
		customer.setId(id);
	}

	public void updateCustomer(Customer customer) throws ApiException {
		GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String json = builder.create().toJson(customer, Customer.class);
		doPut("customers/" + customer.getId() + ".json", json, 200);
	}

	public void createConversation(Conversation conversation) throws ApiException {
		GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
				.registerTypeAdapter(ThreadState.class, new ThreadStateAdapter())
				.registerTypeAdapter(Status.class, new StatusAdapter())
				.registerTypeAdapter(PersonType.class, new PersonTypeAdapter())
				.registerTypeAdapter(ThreadType.class, new ThreadTypeAdapter());
		builder.registerTypeAdapter(LineItem.class, new ThreadsAdapater(builder));

		String json = builder.create().toJson(conversation);
		Long id = (Long)doPost("conversations.json", json, 201);
		conversation.setId(id);
	}

	public void createConversationThread(Long conversationId, ConversationThread thread) throws ApiException {
		try {
			setThreadProperties(thread);

			GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
					.registerTypeAdapter(ThreadState.class, new ThreadStateAdapter())
					.registerTypeAdapter(Status.class, new StatusAdapter())
					.registerTypeAdapter(PersonType.class, new PersonTypeAdapter());

			String json = builder.create().toJson(thread);
			Long id = (Long)doPost("conversations/" + conversationId + ".json", json, 201);
			thread.setId(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApiException(ex.getMessage());
		}
	}

	public void deleteConversation(Long id) throws ApiException {
		String url = "conversations/" + id + ".json";
		doDelete(url, 200);
	}

	public void updateConversation(Conversation conversation) throws ApiException {
		GsonBuilder builder = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
				.registerTypeAdapter(ThreadState.class, new ThreadStateAdapter())
				.registerTypeAdapter(Status.class, new StatusAdapter());

		String json = builder.create().toJson(conversation, Conversation.class);
		doPut("conversations/" + conversation.getId() + ".json", json, 200);
	}

	/**
	 * Uploads an attachment to Help Scout and assigns a hash value to the attachment.
	 * Once the hash has been set for an attachment, the attachment can be included
	 * when creating a new thread.
	 *
	 * @param attachment the attachment to be created
	 * @throws ApiException
	 */
	public void createAttachment(Attachment attachment) throws ApiException {
		String json = new Gson().toJson(attachment);
		String hash = (String)doPost("attachments.json", json, 201);
		attachment.setHash(hash);
	}

	public void deleteAttachment(Long id) throws ApiException {
		String url = "attachments/" + id + ".json";
		doDelete(url, 200);
	}

	private void setThreadProperties(ConversationThread thread) {
		AbstractThread theThread = (AbstractThread)thread;

		// Set the type of thread
		if (theThread.getClass().isAssignableFrom(BaseLineItem.class)) {
			thread.setType(ThreadType.LineItem);
		} else if (theThread.getClass().isAssignableFrom(Message.class)) {
			thread.setType(ThreadType.Message);
		} else if (theThread.getClass().isAssignableFrom(Note.class)) {
			thread.setType(ThreadType.Note);
		} else if (theThread.getClass().isAssignableFrom(Customer.class)) {
			thread.setType(ThreadType.Customer);
		} else if (theThread.getClass().isAssignableFrom(ForwardParent.class)) {
			thread.setType(ThreadType.ForwardParent);
		} else if (theThread.getClass().isAssignableFrom(ForwardChild.class)) {
			thread.setType(ThreadType.ForwardChild);
		} else if (theThread.getClass().isAssignableFrom(Chat.class)) {
			thread.setType(ThreadType.Chat);
		}
	}

	private String setFields(String url, List<String> fields) {
		if (fields != null && fields.size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(url);
			if (url.indexOf("?") > 0) {
				sb.append("&");
			} else {
				sb.append("?");
			}
			sb.append("fields=");

		    String sep = "";
			for (String field : fields) {
				sb.append(sep).append(field);
				sep = ",";
			}
		    url = sb.toString();
		}
		return url;
	}

	private void addSearchParams(StringBuilder url, String email, String firstName, String lastName, Integer page) {
		if (email != null && email.trim().length() > 0) {
			if (url.toString().indexOf("?") > 0) {
				url.append("&");
			} else {
				url.append("?");
			}
			url.append("email=").append(email.trim());
		}

		if (firstName != null && firstName.trim().length() > 0) {
			if (url.toString().indexOf("?") > 0) {
				url.append("&");
			} else {
				url.append("?");
			}
			url.append("firstName=").append(firstName.trim());
		}

		if (lastName != null && lastName.trim().length() > 0) {
			if (url.toString().indexOf("?") > 0) {
				url.append("&");
			} else {
				url.append("?");
			}
			url.append("lastName=").append(lastName.trim());
		}

		if (page != null) {
			if (url.toString().indexOf("?") > 0) {
				url.append("&");
			} else {
				url.append("?");
			}
			url.append("page=").append(page);
		}
	}

	private Object getItem(String url, Class<?> clazzType, int expectedCode) throws ApiException {
		String json = doGet(url, expectedCode);
		JsonElement obj  = (new JsonParser()).parse(json);
		JsonElement item = obj.getAsJsonObject().get("item");

		return Parser.getInstance().getObject(item, clazzType);
	}

	private Page getPage(String url, Class<?> clazzType, int expectedCode) throws ApiException {
		String json = doGet(url, 200);
		JsonElement obj = (new JsonParser()).parse(json);

		Set<Map.Entry<String, JsonElement>> set = obj.getAsJsonObject().entrySet();

		Page p = new Page();

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

	private Object doPost(String url, String requestBody, int expectedCode) throws ApiException {
		HttpURLConnection conn = null;
		try {
		    conn = getConnection(apiKey, url, METHOD_POST);

			if (requestBody != null) {
				conn.setDoOutput(true);
				OutputStream output = null;
				try {
					output = conn.getOutputStream();
					output.write(requestBody.getBytes("UTF-8"));
				} finally {
					if (output != null) {
						try { output.close(); } catch (IOException ignored) {}
					}
				}
			}
			conn.connect();
			checkStatusCode(conn, expectedCode);

			if (url.equals("attachments.json")) {
				return getAttachmentHashFromPost(conn);
			} else {
				return getIdFromPost(conn);
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			close(conn);
		}
	}

	private Long getIdFromPost(HttpURLConnection conn) {
		String location = conn.getHeaderField("LOCATION");
		if (location != null && location.trim().length() > 0) {
			return new Long(location.substring(location.lastIndexOf("/") + 1, location.lastIndexOf(".")));
		} else {
			return null;
		}
	}

	private String getAttachmentHashFromPost(HttpURLConnection conn) throws RuntimeException {
		String hash = null;
		BufferedReader br = null;
		String response;
		try {
			br = new BufferedReader(new InputStreamReader((getInputStream(conn)), Charset.forName("UTF8")));
			response = getResponse(br);
			JsonElement obj  = (new JsonParser()).parse(response);
			JsonElement item = obj.getAsJsonObject().get("item");
			hash = item.getAsJsonObject().get("hash").getAsString();
		}  catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(br);
			close(conn);
		}
		return hash;
	}

	private void doPut(String url, String requestBody, int expectedCode) throws ApiException {
		HttpURLConnection conn = null;
		try {
			conn = getConnection(apiKey, url, METHOD_PUT);
			if (requestBody != null) {
				conn.setDoOutput(true);
				OutputStream output = null;
				try {
					output = conn.getOutputStream();
					output.write(requestBody.getBytes("UTF-8"));
				} finally {
					if (output != null) {
						try { output.close(); } catch (IOException ignored) {}
					}
				}
			}
			conn.connect();
			checkStatusCode(conn, expectedCode);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close(conn);
		}
	}

	private String doGet(String url, int expectedCode) throws ApiException {
		HttpURLConnection conn = null;
		BufferedReader br  = null;
		String response    = null;
		try {
			conn = getConnection(apiKey, url, METHOD_GET);
			conn.connect();
			checkStatusCode(conn, expectedCode);

			br = new BufferedReader(new InputStreamReader((getInputStream(conn)), Charset.forName("UTF8")));
			response = getResponse(br);
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(br);
			close(conn);
		}
		return response;
	}

	private void doDelete(String url, int expectedCode) throws ApiException {
		HttpURLConnection conn = null;
		try {
			conn = getConnection(apiKey, url, METHOD_DELETE);
			conn.connect();
			checkStatusCode(conn, expectedCode);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			close(conn);
		}
	}

	private HttpURLConnection getConnection(String apiKey, String url, String method) throws Exception {
		URL aUrl = new URL(BASE_URL + url);

		HttpURLConnection conn = (HttpURLConnection) aUrl.openConnection();

		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod(method);

		if (!method.equalsIgnoreCase(METHOD_DELETE)) {
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
		}
		conn.setRequestProperty("Authorization", "Basic " + getEncoded(apiKey + ":x"));
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
		return conn;
	}

	private void checkStatusCode(HttpURLConnection conn, int expectedCode) throws ApiException, IOException {
		int code = conn.getResponseCode();
		if (code == expectedCode) {
			return;
		}
		switch(code) {
			case 400:
				throw new InvalidFormatException("The request was not formatted correctly");
			case 401:
				throw new InvalidApiKeyException("Invalid API key");
			case 402:
				throw new ApiKeySuspendedException("API key suspended");
			case 403:
				throw new AccessDeniedException("Access denied");
			case 404:
				throw new NotFoundException("Resource not found");
			case 405:
				throw new InvalidMethodException("Invalid method type");
			case 429:
				throw new ThrottleRateException("Throttle limit reached. Too many requests");
			case 500:
				throw new ServerException("Application error or server error");
			case 503:
				throw new ServiceUnavailableException("Service Temporarily Unavailable");
			default:
				throw new ApiException("API key suspended");
		}
	}

	private String getResponse(BufferedReader reader) throws IOException {
		StringBuilder sb = new StringBuilder();

    	String line;
    	while ((line = reader.readLine()) != null) {
    		sb.append(line);
    	}

    	return sb.toString();
	}

	private void close(HttpURLConnection conn) {
		if (conn != null) {
			try {
				conn.disconnect();
			} catch (Exception e) {
				// ignore
			}
		}
	}
	private void close(BufferedReader reader) {
		if (reader != null) {
    		try {
    			reader.close();
			} catch (IOException e) {
				// ignore
			}
    	}
	}

	private InputStream getInputStream(HttpURLConnection conn) throws IOException {
        String encoding = conn.getContentEncoding();

		InputStream inputStream = null;

		//create the appropriate stream wrapper based on
		//the encoding type
		if (encoding != null) {
			if (encoding.equalsIgnoreCase("gzip")) {
				inputStream = new GZIPInputStream(conn.getInputStream());
			} else if (encoding.equalsIgnoreCase("deflate")) {
				inputStream = new InflaterInputStream(conn.getInputStream(), new Inflater(true));
			}
		}
		if (inputStream == null) {
			inputStream = conn.getInputStream();
		}
		return inputStream;
	}

	private String getEncoded(String val) {
		return (new BASE64Encoder()).encode(val.getBytes());
	}

	private String getDecoded(String val) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			return new String(decoder.decodeBuffer(val));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
