package net.helpscout.api;

import com.google.gson.*;
import net.helpscout.api.adapters.*;
import net.helpscout.api.cbo.*;
import net.helpscout.api.exception.*;
import net.helpscout.api.extractors.HashExtractor;
import net.helpscout.api.extractors.IdExtractor;
import net.helpscout.api.utils.EncodeUtils;
import net.helpscout.api.utils.JSONUtils;
import net.helpscout.api.model.*;
import net.helpscout.api.model.Customer;
import net.helpscout.api.model.customer.SearchCustomer;
import net.helpscout.api.model.customfield.CustomFieldResponse;
import net.helpscout.api.model.report.common.DatesAndCounts;
import net.helpscout.api.model.report.common.DatesAndElapsedTimes;
import net.helpscout.api.model.report.common.Rating;
import net.helpscout.api.model.report.conversations.ConversationsReport;
import net.helpscout.api.model.report.conversations.DayStats;
import net.helpscout.api.model.report.docs.DocsReport;
import net.helpscout.api.model.report.happiness.HappinessReport;
import net.helpscout.api.model.report.productivity.ProductivityReport;
import net.helpscout.api.model.report.team.TeamReport;
import net.helpscout.api.model.report.user.ConversationStats;
import net.helpscout.api.model.report.user.UserHappiness;
import net.helpscout.api.model.report.user.UserReport;
import net.helpscout.api.model.thread.*;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.text.MessageFormat.format;
import static net.helpscout.api.utils.ParamsUtils.getCustomerSearchParams;
import static net.helpscout.api.utils.ParamsUtils.setFields;
import static net.helpscout.api.utils.ParamsUtils.setParams;

public class ApiClient {

    private final static String DEFAULT_BASE_URL = "https://api.helpscout.net/v1/";
    private final static String METHOD_GET = "GET";
    private final static String METHOD_POST = "POST";
    private final static String METHOD_PUT = "PUT";
    private final static String METHOD_DELETE = "DELETE";

    private final static int HTTP_STATUS_OK = 200;
    private final static int HTTP_STATUS_CREATED = 201;

    private String apiKey = "";
    private String baseUrl = DEFAULT_BASE_URL;

    private static final ResultExtractor<Long> idExtractor = new IdExtractor();
    private static final ResultExtractor<String> hashExtractor = new HashExtractor();

    private static ApiClient instance = new ApiClient();

    private ApiClient() {}

    public static ApiClient getInstance() {
        return instance;
    }

    public void setKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Gets the mailbox with the specified id.
     *
     * @param mailboxID
     * @return Mailbox
     * @throws ApiException
     * @deprecated use {@link #getMailbox(Long)}
     */
    public Mailbox getMailbox(Integer mailboxID) throws ApiException {
        return getMailbox(Long.valueOf(mailboxID));
    }

    /**
     * Gets the mailbox with the specified id.
     *
     * @param mailboxID
     * @return Mailbox
     * @throws ApiException
     */
    public Mailbox getMailbox(Long mailboxID) throws ApiException {
        return (Mailbox)getItem("mailboxes/" + mailboxID + ".json", Mailbox.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the mailbox with the specified id.
     *
     * @param mailboxID
     * @param fields
     * @return Mailbox
     * @throws ApiException
     * @deprecated use {@link #getMailbox(Long, List)}
     */
    public Mailbox getMailbox(Integer mailboxID, List<String> fields)
            throws ApiException {
        return getMailbox(Long.valueOf(mailboxID), fields);
    }

    /**
     * Gets the mailbox with the specified id.
     *
     * @param mailboxID
     * @param fields
     * @return Mailbox
     * @throws ApiException
     */
    public Mailbox getMailbox(Long mailboxID, List<String> fields) throws ApiException {
        if (mailboxID == null || mailboxID < 1) {
            throw new ApiException("Invalid mailboxId in getMailbox");
        }
        String url = setFields("mailboxes/" + mailboxID + ".json", fields);
        return getItem(url, Mailbox.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of mailboxes.
     *
     * @return Page
     * @throws ApiException
     */
    public Page<Mailbox> getMailboxes() throws ApiException {
        return getPage("mailboxes.json", Mailbox.class, HTTP_STATUS_OK);
    }

    /**
     * Get the first page of mailboxes.
     *
     * @param fields
     * @return Page
     * @throws ApiException
     */
    public Page<Mailbox> getMailboxes(List<String> fields) throws ApiException {
        String url = setFields("mailboxes.json", fields);
        return getPage(url, Mailbox.class, HTTP_STATUS_OK);
    }

    /**
     * Gets a list of mailboxes.
     *
     * @param queryParams
     * @return Page
     * @throws ApiException
     */
    public Page<Mailbox> getMailboxes(Map<String, String> queryParams) throws ApiException {
        return getPage("mailboxes.json", queryParams, Mailbox.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of folders for the specified mailbox.
     *
     * @param mailboxId
     * @return Page
     * @throws ApiException
     * @deprecated use {@link #getFolders(Long)}
     */
    public Page<Folder> getFolders(Integer mailboxId) throws ApiException {
        return getFolders(Long.valueOf(mailboxId));
    }

    /**
     * Gets the first page of folders for the specified mailbox.
     *
     * @param mailboxId
     * @return Page
     * @throws ApiException
     */
    public Page<Folder> getFolders(Long mailboxId) throws ApiException {
        return getPage("mailboxes/" + mailboxId + "/folders.json", Folder.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of folders for the specified mailbox.
     *
     * @param mailboxId
     * @param fields
     * @return Page
     * @throws ApiException
     * @deprecated use {@link #getFolders(Long, List)}
     */
    public Page<Folder> getFolders(Integer mailboxId, List<String> fields) throws ApiException {
        return getFolders(Long.valueOf(mailboxId), fields);
    }

    /**
     * Gets the first page of folders for the specified mailbox.
     *
     * @param mailboxId
     * @param fields
     * @return Page
     * @throws ApiException
     */
    public Page<Folder> getFolders(Long mailboxId, List<String> fields) throws ApiException {
        String url = setFields("mailboxes/" + mailboxId + "/folders.json", fields);
        return getPage(url, Folder.class, HTTP_STATUS_OK);
    }

    /**
     * Gets a page of folders for the specified mailbox.
     *
     * @param mailboxId
     * @param queryParams
     * @return Page
     * @throws ApiException
     * @deprecated use {@link #getFolders(Long, Map)}
     */
    public Page<Folder> getFolders(Integer mailboxId, Map<String, String> queryParams) throws ApiException {
        return getFolders(Long.valueOf(mailboxId), queryParams);
    }

    /**
     * Gets a page of folders for the specified mailbox.
     *
     * @param mailboxId
     * @param queryParams
     * @return Page
     * @throws ApiException
     */
    public Page<Folder> getFolders(Long mailboxId, Map<String, String> queryParams) throws ApiException {
        return getPage("mailboxes/" + mailboxId + "/folders.json", queryParams, Folder.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of conversations for the specified mailbox and
     * folder.
     *
     * @param mailboxID
     * @param folderID
     * @return Page
     * @throws ApiException
     * @deprecated use {@link #getConversationsForFolder(Long, Long)}
     */
    public Page<Conversation> getConversationsForFolder(Integer mailboxID, Integer folderID) throws ApiException {
        return getConversationsForFolder(Long.valueOf(mailboxID), Long.valueOf(folderID));
    }

    /**
     * Gets the first page of conversations for the specified mailbox and
     * folder.
     *
     * @param mailboxID
     * @param folderID
     * @return Page
     * @throws ApiException
     */
    public Page<Conversation> getConversationsForFolder(Long mailboxID, Long folderID) throws ApiException {
        return getPage("mailboxes/" + mailboxID + "/folders/" + folderID + "/conversations.json", Conversation.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of conversations for the specified mailbox and
     * folder.
     *
     * @param mailboxID
     * @param folderID
     * @param fields
     * @return Page
     * @throws ApiException
     * @deprecated use {@link #getConversationsForFolder(Long, Long, List)}
     */
    public Page<Conversation> getConversationsForFolder(Integer mailboxID, Integer folderID, List<String> fields) throws ApiException {
        return getConversationsForFolder(Long.valueOf(mailboxID), Long.valueOf(folderID), fields);
    }

    /**
     * Gets the first page of conversations for the specified mailbox and
     * folder.
     *
     * @param mailboxID
     * @param folderID
     * @param fields
     * @return Page
     * @throws ApiException
     */
    public Page<Conversation> getConversationsForFolder(Long mailboxID, Long folderID, List<String> fields) throws ApiException {
        String url = setFields("mailboxes/" + mailboxID + "/folders/" + folderID + "/conversations.json", fields);
        return getPage(url, Conversation.class, HTTP_STATUS_OK);
    }

    /**
     * Gets a page of conversations for the specified mailbox and folder.
     *
     * @param mailboxID
     * @param folderID
     * @param queryParams
     * @return Page
     * @throws ApiException
     * @deprecated use {@link #getConversationsForFolder(Long, Long, Map)}
     */
    public Page<Conversation> getConversationsForFolder(Integer mailboxID, Integer folderID, Map<String, String> queryParams) throws ApiException {
        return getConversationsForFolder(Long.valueOf(mailboxID), Long.valueOf(folderID), queryParams);
    }

    /**
     * Gets a page of conversations for the specified mailbox and folder.
     *
     * @param mailboxID
     * @param folderID
     * @param queryParams
     * @return Page
     * @throws ApiException
     */
    public Page<Conversation> getConversationsForFolder(Long mailboxID, Long folderID, Map<String, String> queryParams) throws ApiException {
        return getPage("mailboxes/" + mailboxID + "/folders/" + folderID + "/conversations.json", queryParams, Conversation.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of conversations for the specified mailbox.
     *
     * @param mailboxID
     * @return Page
     * @throws ApiException
     * @deprecated use {@link #getConversationsForMailbox(Long)}
     */
    public Page<Conversation> getConversationsForMailbox(Integer mailboxID) throws ApiException {
        return getConversationsForMailbox(Long.valueOf(mailboxID));
    }

    /**
     * Gets the first page of conversations for the specified mailbox.
     *
     * @param mailboxID
     * @return Page
     * @throws ApiException
     */
    public Page<Conversation> getConversationsForMailbox(Long mailboxID) throws ApiException {
        return getPage("mailboxes/" + mailboxID + "/conversations.json", Conversation.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of conversations for the specified mailbox.
     *
     * @param mailboxID
     * @param fields
     * @return Page
     * @throws ApiException
     * @deprecated use {@link #getConversationsForMailbox(Long, List)}
     */
    public Page<Conversation> getConversationsForMailbox(Integer mailboxID, List<String> fields) throws ApiException {
        return getConversationsForMailbox(Long.valueOf(mailboxID), fields);
    }

    /**
     * Gets the first page of conversations for the specified mailbox.
     *
     * @param mailboxID
     * @param fields
     * @return Page
     * @throws ApiException
     */
    public Page<Conversation> getConversationsForMailbox(Long mailboxID, List<String> fields) throws ApiException {
        String url = setFields("mailboxes/" + mailboxID + "/conversations.json", fields);
        return getPage(url, Conversation.class, HTTP_STATUS_OK);
    }

    /**
     * Gets a page of conversations for the specified mailbox.
     *
     * @param mailboxID
     * @param queryParams
     * @return Page
     * @throws ApiException
     * @deprecated use {@link #getConversationsForMailbox(Long, Map)}
     */
    public Page<Conversation> getConversationsForMailbox(Integer mailboxID, Map<String, String> queryParams) throws ApiException {
        return getConversationsForMailbox(Long.valueOf(mailboxID), queryParams);
    }

    /**
     * Gets a page of conversations for the specified mailbox.
     *
     * @param mailboxID
     * @param queryParams
     * @return Page
     * @throws ApiException
     */
    public Page<Conversation> getConversationsForMailbox(Long mailboxID, Map<String, String> queryParams) throws ApiException {
        return getPage("mailboxes/" + mailboxID + "/conversations.json", queryParams, Conversation.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of conversations for the specified mailbox and
     * customer.
     *
     * @param mailboxID
     * @param customerID
     * @return Page
     * @throws ApiException
     * @deprecated use {@link #getConversationsForCustomerByMailbox(Long, Long)}
     */
    public Page<Conversation> getConversationsForCustomerByMailbox(Integer mailboxID, Integer customerID) throws ApiException {
        return getConversationsForCustomerByMailbox(Long.valueOf(mailboxID), Long.valueOf(customerID));
    }

    /**
     * Gets the first page of conversations for the specified mailbox and
     * customer.
     *
     * @param mailboxID
     * @param customerID
     * @return Page
     * @throws ApiException
     */
    public Page<Conversation> getConversationsForCustomerByMailbox(Long mailboxID, Long customerID) throws ApiException {
        return getPage("mailboxes/" + mailboxID + "/customers/" + customerID + "/conversations.json", Conversation.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of conversations for the specified mailbox and
     * customer.
     *
     * @param mailboxID
     * @param customerID
     * @param fields
     * @return Page
     * @throws ApiException
     * @deprecated use
     *             {@link #getConversationsForCustomerByMailbox(Long, Long, List)}
     */
    public Page<Conversation> getConversationsForCustomerByMailbox(Integer mailboxID, Integer customerID, List<String> fields) throws ApiException {
        return getConversationsForCustomerByMailbox(Long.valueOf(mailboxID), Long.valueOf(customerID), fields);
    }

    /**
     * Gets the first page of conversations for the specified mailbox and
     * customer.
     *
     * @param mailboxID
     * @param customerID
     * @param fields
     * @return Page
     * @throws ApiException
     */
    public Page<Conversation> getConversationsForCustomerByMailbox(Long mailboxID, Long customerID, List<String> fields) throws ApiException {
        String url = setFields("mailboxes/" + mailboxID + "/customers/" + customerID + "/conversations.json", fields);
        return getPage(url, Conversation.class, HTTP_STATUS_OK);
    }

    /**
     * Gets a page of conversations for the specified mailbox and customer.
     *
     * @param mailboxID
     * @param customerID
     * @param queryParams
     * @return Page
     * @throws ApiException
     * @deprecated use
     *             {@link #getConversationsForCustomerByMailbox(Long, Long, Map)}
     */
    public Page<Conversation> getConversationsForCustomerByMailbox(Integer mailboxID, Integer customerID, Map<String, String> queryParams) throws ApiException {
        return getConversationsForCustomerByMailbox(Long.valueOf(mailboxID), Long.valueOf(customerID), queryParams);
    }

    /**
     * Gets a page of conversations for the specified mailbox and customer.
     *
     * @param mailboxID
     * @param customerID
     * @param queryParams
     * @return Page
     * @throws ApiException
     */
    public Page<Conversation> getConversationsForCustomerByMailbox(Long mailboxID, Long customerID, Map<String, String> queryParams) throws ApiException {
        return getPage("mailboxes/" + mailboxID + "/customers/" + customerID + "/conversations.json", queryParams, Conversation.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the conversation with the specified id.
     *
     * @param conversationID
     * @return Conversation
     * @throws ApiException
     */
    public Conversation getConversation(Long conversationID) throws ApiException {
        return getItem("conversations/" + conversationID + ".json", Conversation.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the conversation with the specified id.
     *
     * @param conversationID
     * @param fields
     * @return Conversation
     * @throws ApiException
     * @deprecated use {@link #getConversation(Long, List)}
     */
    public Conversation getConversation(Integer conversationID, List<String> fields) throws ApiException {
        return getConversation(Long.valueOf(conversationID), fields);
    }

    /**
     * Gets the conversation with the specified id.
     *
     * @param conversationID
     * @param fields
     * @return Conversation
     * @throws ApiException
     */
    public Conversation getConversation(Long conversationID, List<String> fields) throws ApiException {
        if (conversationID == null || conversationID < 1) {
            throw new ApiException("Invalid conversationId in getConversation");
        }
        String url = setFields("conversations/" + conversationID + ".json", fields);
        return getItem(url, Conversation.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the thread source for the specified conversation and thread.
     *
     * @param conversationID
     * @param threadID
     * @return String
     * @throws ApiException
     * @deprecated use {@link #getThreadSource(Long, Long)}
     */
    public String getThreadSource(Integer conversationID, Integer threadID) throws ApiException {
        return getThreadSource(Long.valueOf(conversationID), Long.valueOf(threadID));
    }

    /**
     * Gets the thread source for the specified conversation and thread.
     *
     * @param conversationID
     * @param threadID
     * @return String
     * @throws ApiException
     */
    public String getThreadSource(Long conversationID, Long threadID) throws ApiException {
        if (conversationID == null || conversationID < 1) {
            throw new ApiException("Invalid conversationID in getThreadSource");
        }
        if (threadID == null || threadID < 1) {
            throw new ApiException("Invalid threadID in getThreadSource");
        }
        String url = "conversations/" + conversationID + "/thread-source/" + threadID + ".json";
        String json;
        try {
            json = doGet(url, HTTP_STATUS_OK);
        } catch(RuntimeException e) {
            if (e.getCause() instanceof NotFoundException) {
                json = null;
            } else {
                throw e;
            }
        }
        if (json != null) {
            JsonElement obj = parseJson(url, json);
            JsonElement elem  = obj.getAsJsonObject().get("item");
            return new String(EncodeUtils.getDecoded(elem.getAsJsonObject().get("data").getAsString()));
        } 
        return null;
    }

    private JsonElement parseJson(String url, String json) {
        LoggerFactory.getLogger(getClass()).trace("{}: {}", url, json);
        JsonElement obj = (new JsonParser()).parse(json);
        return obj;
    }

    /**
     * Gets the attachment data for the specified attachment id.
     *
     * @param attachmentID
     * @return String
     * @throws ApiException
     * @deprecated use {@link #getAttachmentData(Long)}
     */
    public String getAttachmentData(Integer attachmentID) throws ApiException {
        return getAttachmentData(Long.valueOf(attachmentID));
    }

    /**
     * Gets the attachment data for the specified attachment id.
     *
     * @param attachmentID
     * @return String
     * @throws ApiException
     */
    public String getAttachmentData(Long attachmentID) throws ApiException {
        return new String(getAttachmentBinaryData(attachmentID));
    }

    /**
     * Gets the attachment data for the specified attachment id.
     *
     * @param attachmentID
     * @return String
     * @throws ApiException
     */
    public byte[] getAttachmentBinaryData(Long attachmentID) throws ApiException {
        if (attachmentID == null || attachmentID < 1) {
            throw new ApiException("Invalid attachmentID in getAttachmentData");
        }
        String url = "attachments/" + attachmentID + "/data.json";
        String json = doGet(url, HTTP_STATUS_OK);
        JsonElement obj = parseJson(url, json);
        JsonElement elem  = obj.getAsJsonObject().get("item");
        return EncodeUtils.getDecoded(elem.getAsJsonObject().get("data").getAsString());
    }

    /**
     * Gets a page of all tags.
     *
     * @param queryParams
     * @return Page
     * @throws ApiException
     */
    public Page<Tag> getTags(Map<String, String> queryParams) throws ApiException {
        return getPage("tags.json", queryParams, Tag.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of customers.
     *
     * @return Page
     * @throws ApiException
     */
    public Page<Customer> getCustomers() throws ApiException {
        return getCustomers((Integer) null);
    }

    /**
     * Gets a page of customers.
     *
     * @param page
     * @return Page
     * @throws ApiException
     */
    public Page<Customer> getCustomers(Integer page) throws ApiException {
        if (page != null) {
            return getPage("customers.json?page=" + page, Customer.class, HTTP_STATUS_OK);
        } else {
            return getPage("customers.json", Customer.class, HTTP_STATUS_OK);
        }
    }

    /**
     * Gets the first page of customers.
     *
     * @param fields
     * @return Page
     * @throws ApiException
     */
    public Page<Customer> getCustomers(List<String> fields) throws ApiException {
        String url = setFields("customers.json", fields);
        return getPage(url, Customer.class, HTTP_STATUS_OK);
    }

    /**
     * Gets a page of customers.
     *
     * @param mailboxId
     * @param page
     * @param fields
     * @return Page
     * @throws ApiException
     */
    public Page<Customer> getCustomersForMailbox(Long mailboxId, Integer page, List<String> fields) throws ApiException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append("mailboxes/").append(mailboxId).append("/customers.json");
        if (page != null) {
            sbUrl.append("?page=").append(page);
        }
        String url = setFields(sbUrl.toString(), fields);
        return getPage(url, Customer.class, HTTP_STATUS_OK);
    }

    /**
     * Searches for the first page of customers using any combination of email,
     * firstName, lastName.
     *
     * @param email
     * @param firstName
     * @param lastName
     * @return Page
     * @throws ApiException
     */
    public Page<Customer> searchCustomers(String email, String firstName, String lastName) throws ApiException {
        return searchCustomers(email, firstName, lastName, null, null);
    }

    /**
     * Returns a page of customers by searching on any combination of email,
     * firstName, lastName.
     *
     * @param email
     * @param firstName
     * @param lastName
     * @param page
     * @param fields
     * @return Page
     * @throws ApiException
     */
    public Page<Customer> searchCustomers(String email, String firstName, String lastName, Integer page, List<String> fields) throws ApiException {
        Map<String, String> params = getCustomerSearchParams(email, firstName, lastName, page);
        String url = setFields("customers.json", fields);
        return getPage(url, params, Customer.class, HTTP_STATUS_OK);
    }

    /**
     * Returns a page of SearchConversation objects by searching using the
     * query.
     *
     * @param query
     * @param sortField
     * @param sortOrder
     * @param page
     * @return Page
     * @throws ApiException
     */
    public Page<SearchConversation> searchConversations(String query, String sortField, String sortOrder, Integer page) throws ApiException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("query", query);
        if (sortField != null && sortField.trim().length() > 0) {
            params.put("sortField", sortField);
        }
        if (sortOrder != null && sortOrder.trim().length() > 0) {
            params.put("sortOrder", sortOrder);
        }
        params.put("page", String.valueOf(page));
        return getPage("search/conversations.json", params, SearchConversation.class, HTTP_STATUS_OK);
    }

    /**
     * Returns a page of SearchCustomer objects by searching using the query.
     *
     * @param query
     * @param sortField
     * @param sortOrder
     * @param page
     * @return Page
     * @throws ApiException
     */
    public Page<SearchCustomer> searchCustomers(String query, String sortField, String sortOrder, Integer page) throws ApiException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("query", query);
        if (sortField != null && sortField.trim().length() > 0) {
            params.put("sortField", sortField);
        }
        if (sortOrder != null && sortOrder.trim().length() > 0) {
            params.put("sortOrder", sortOrder);
        }
        params.put("page", String.valueOf(page));
        return getPage("search/customers.json", params, SearchCustomer.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the customer with the specified id.
     *
     * @param customerId
     * @return Customer
     * @throws ApiException
     */
    public Customer getCustomer(Long customerId) throws ApiException {
        return getItem("customers/" + customerId + ".json", Customer.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the customer with the specified id.
     *
     * @param customerId
     * @param fields
     * @return Customer
     * @throws ApiException
     */
    public Customer getCustomer(Long customerId, List<String> fields) throws ApiException {
        if (customerId == null || customerId < 1) {
            throw new ApiException("Invalid customerId in getCustomer");
        }
        String url = setFields("customers/" + customerId + ".json", fields);
        return getItem(url, Customer.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the user associated with the API key used to make the request.
     *
     * @return User
     * @throws ApiException
     */
    public User getUserMe() throws ApiException {
        return getItem("users/me.json", User.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the user with the specified id.
     *
     * @param userID
     * @return User
     * @throws ApiException
     * @deprecated use {@link #getUser(Long)}
     */
    public MailboxUser getUser(Integer userID) throws ApiException {
        return getUser(Long.valueOf(userID));
    }

    /**
     * Gets the user with the specified id.
     *
     * @param userID
     * @return User
     * @throws ApiException
     */
    public MailboxUser getUser(Long userID) throws ApiException {
        return getItem("users/" + userID + ".json", MailboxUser.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the user with the specified id.
     *
     * @param teamID
     * @return User
     * @throws ApiException
     */
    public Team getTeam(Long teamID) throws ApiException {
        return getItem("teams/" + teamID + ".json", Team.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the user with the specified id.
     *
     * @param userID
     * @param fields
     * @return User
     * @throws ApiException
     * @deprecated use {@link #getUser(Long, List)}
     */
    public MailboxUser getUser(Integer userID, List<String> fields) throws ApiException {
        return getUser(Long.valueOf(userID), fields);
    }

    /**
     * Gets the user with the specified id.
     *
     * @param userID
     * @param fields
     * @return User
     * @throws ApiException
     */
    public MailboxUser getUser(Long userID, List<String> fields) throws ApiException {
        return getMailboxUser(MailboxUser.class, "users", userID, fields);
    }

    /**
     * Gets the user with the specified id.
     *
     * @param teamID
     * @param fields
     * @return User
     * @throws ApiException
     */
    public Team getTeam(Long teamID, List<String> fields) throws ApiException {
        return getMailboxUser(Team.class, "teams", teamID, fields);
    }

    private <T extends MailboxUser> T getMailboxUser(Class<T> userClass, String baseUrl, Long mailboxUserId, List<String> fields) throws ApiException {
        if (mailboxUserId == null || mailboxUserId < 1) {
            throw new ApiException("Invalid identifier, it must be larger than 1, but was " + mailboxUserId);
        }
        String url = format("{0}/{1}.json", baseUrl, mailboxUserId);
        url = setFields(url, fields);
        return getItem(url, userClass, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of users.
     *
     * @return Page
     * @throws ApiException
     */
    public Page<MailboxUser> getUsers() throws ApiException {
        return getPage("users.json", MailboxUser.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of teams.
     *
     * @return Page of teams
     * @throws ApiException
     */
    public Page<Team> getTeams() throws ApiException {
        return getPage("teams.json", Team.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of users.
     *
     * @param fields
     * @return Page
     * @throws ApiException
     */
    public Page<MailboxUser> getUsers(List<String> fields) throws ApiException {
        String url = setFields("users.json", fields);
        return getPage(url, MailboxUser.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of teams.
     *
     * @param fields
     * @return Page
     * @throws ApiException
     */
    public Page<Team> getTeams(List<String> fields) throws ApiException {
        String url = setFields("teams.json", fields);
        return getPage(url, Team.class, HTTP_STATUS_OK);
    }

    /**
     * Gets a page of teams.
     *
     * @param queryParams
     * @return Page of teams
     * @throws ApiException
     */
    public Page<MailboxUser> getUsers(Map<String, String> queryParams) throws ApiException {
        return getPage("users.json", queryParams, MailboxUser.class, HTTP_STATUS_OK);
    }

    /**
     * Gets a page of teams.
     *
     * @param queryParams
     * @return Page of teams
     * @throws ApiException
     */
    public Page<Team> getTeams(Map<String, String> queryParams) throws ApiException {
        return getPage("teams.json", queryParams, Team.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of users for the specified mailbox.
     *
     * @param mailboxId
     * @return Page
     * @throws ApiException
     */
    public Page<User> getTeamMembers(Long mailboxId) throws ApiException {
        return getPage("teams/" + mailboxId + "/members.json", User.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of users for the specified mailbox.
     *
     * @param mailboxId
     * @return Page
     * @throws ApiException
     * @deprecated use {@link #getUsersForMailbox(Long)}
     */
    public Page<MailboxUser> getUsersForMailbox(Integer mailboxId) throws ApiException {
        return getUsersForMailbox(Long.valueOf(mailboxId));
    }

    /**
     * Gets the first page of users for the specified mailbox.
     *
     * @param mailboxId
     * @return Page
     * @throws ApiException
     */
    public Page<MailboxUser> getUsersForMailbox(Long mailboxId) throws ApiException {
        return getPage("mailboxes/" + mailboxId + "/users.json", MailboxUser.class, HTTP_STATUS_OK);
    }

    /**
     * Gets the first page of users for the specified mailbox.
     *
     * @param mailboxId
     * @param fields
     * @return Page
     * @throws ApiException
     * @deprecated use {@link #getUsersForMailbox(Long, List)}
     */
    public Page<MailboxUser> getUsersForMailbox(Integer mailboxId, List<String> fields) throws ApiException {
        return getUsersForMailbox(Long.valueOf(mailboxId), fields);
    }

    /**
     * Gets the first page of users for the specified mailbox.
     *
     * @param mailboxId
     * @param fields
     * @return Page
     * @throws ApiException
     */
    public Page<MailboxUser> getUsersForMailbox(Long mailboxId, List<String> fields) throws ApiException {
        String url = setFields("mailboxes/" + mailboxId + "/users.json", fields);
        return getPage(url, MailboxUser.class, HTTP_STATUS_OK);
    }

    /**
     * Gets a page of users for the specified mailbox.
     *
     * @param mailboxId
     * @param queryParams
     * @return Page
     * @throws ApiException
     * @deprecated use {@link #getUsersForMailbox(Long, Map)}
     */
    public Page<MailboxUser> getUsersForMailbox(Integer mailboxId, Map<String, String> queryParams) throws ApiException {
        return getUsersForMailbox(Long.valueOf(mailboxId), queryParams);
    }

    /**
     * Gets a page of users for the specified mailbox.
     *
     * @param mailboxId
     * @param queryParams
     * @return Page
     * @throws ApiException
     */
    public Page<MailboxUser> getUsersForMailbox(Long mailboxId, Map<String, String> queryParams) throws ApiException {
        return getPage("mailboxes/" + mailboxId + "/users.json", queryParams, MailboxUser.class, HTTP_STATUS_OK);
    }

    /**
     * Creates a new customer.
     *
     * @param customer
     * @throws ApiException
     */
    public void createCustomer(Customer customer) throws ApiException {
        String json = new Gson().toJson(customer);
        Long id = doPost("customers.json", json, HTTP_STATUS_CREATED, idExtractor);
        customer.setId(id);
    }

    /**
     * Updates an existing customer.
     *
     * @param customer
     * @throws ApiException
     */
    public void updateCustomer(Customer customer) throws ApiException {
        GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String json = builder.create().toJson(customer, Customer.class);
        doPut("customers/" + customer.getId() + ".json", json, HTTP_STATUS_OK);
    }

    /**
     * Creates a new conversations.
     *
     * @param conversation
     * @throws ApiException
     */
    public void createConversation(Conversation conversation) throws ApiException {
        createConversation(conversation, false);
    }

    /**
     * Creates a new conversation.
     *
     * @param conversation
     * @param imported
     * @throws ApiException
     */
    public void createConversation(Conversation conversation, boolean imported) throws ApiException {
        GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .registerTypeAdapter(ThreadState.class, new ThreadStateAdapter())
                .registerTypeAdapter(Status.class, new StatusAdapter())
                .registerTypeAdapter(PersonType.class, new PersonTypeAdapter())
                .registerTypeAdapter(ThreadType.class, new ThreadTypeAdapter())
                .registerTypeAdapter(ConversationType.class, new ConversationTypeAdapter())
                .registerTypeAdapter(CustomFieldResponse.class, new CustomFieldResponseAdapter());

        String json = builder.create().toJson(conversation);

        StringBuilder url = new StringBuilder("conversations.json");
        if (imported) {
            url.append("?imported=true");
        }

        Long id = doPost(url.toString(), json, HTTP_STATUS_CREATED, idExtractor);
        conversation.setId(id);
    }

    /**
     * Creates a new thread on the specified conversation.
     *
     * @param conversationId
     * @param thread
     * @throws ApiException
     */
    public void createConversationThread(Long conversationId, ConversationThread thread) throws ApiException {
        createConversationThread(conversationId, thread, false);
    }

    /**
     * Creates a new thread on the specified conversation.
     *
     * @param conversationId
     * @param thread
     * @param imported
     * @throws ApiException
     */
    public void createConversationThread(Long conversationId, ConversationThread thread, boolean imported) throws ApiException {
        try {
            setThreadProperties(thread);

            GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    .registerTypeAdapter(ThreadState.class, new ThreadStateAdapter())
                    .registerTypeAdapter(Status.class, new StatusAdapter())
                    .registerTypeAdapter(PersonType.class, new PersonTypeAdapter());

            String json = builder.create().toJson(thread);

            StringBuilder url = new StringBuilder("conversations/").append(conversationId).append(".json");
            if (imported) {
                url.append("?imported=true");
            }

            doPost(url.toString(), json, HTTP_STATUS_CREATED, idExtractor);
        } catch (Exception ex) {
            throw new ApiException(ex.getMessage());
        }
    }

    /**
     * Update the body text of the specified thread.
     *
     * @param conversationId
     * @param threadId
     * @param text
     * @return
     * @throws ApiException
     */
    public void updateConversationThreadText(Long conversationId, Long threadId, String text) throws ApiException {
        Map<String, String> threadBody = new HashMap<String, String>();
        threadBody.put("body", text);

        GsonBuilder builder = new GsonBuilder();
        String json = builder.create().toJson(threadBody);

        doPut("conversations/" + conversationId + "/threads/" + threadId + ".json", json, HTTP_STATUS_OK);
    }

    /**
     * Deletes the specified conversation.
     *
     * @param id
     * @throws ApiException
     */
    public void deleteConversation(Long id) throws ApiException {
        String url = "conversations/" + id + ".json";
        doDelete(url, HTTP_STATUS_OK);
    }

    /**
     * Updates the specified conversation.
     *
     * @param conversation
     * @throws ApiException
     */
    public void updateConversation(Conversation conversation) throws ApiException {
        GsonBuilder builder = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .registerTypeAdapter(ThreadState.class, new ThreadStateAdapter())
                .registerTypeAdapter(Status.class, new StatusAdapter())
                .registerTypeAdapter(CustomFieldResponse.class, new CustomFieldResponseAdapter());

        String json = builder.create().toJson(conversation, Conversation.class);
        doPut("conversations/" + conversation.getId() + ".json", json, HTTP_STATUS_OK);
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
        String hash = doPost("attachments.json", json, HTTP_STATUS_CREATED, hashExtractor);
        attachment.setHash(hash);
    }

    /**
     * Deletes an attachment.
     *
     * @param id
     * @throws ApiException
     */
    public void deleteAttachment(Long id) throws ApiException {
        String url = "attachments/" + id + ".json";
        doDelete(url, HTTP_STATUS_OK);
    }

    /**
     * Deletes a note thread.
     *
     * @param threadId
     * @throws ApiException
     */
    public void deleteNote(Long threadId) throws ApiException {
        String url = "notes/" + threadId + ".json";
        doDelete(url, HTTP_STATUS_OK);
    }

    /**
     * Finds the first page of workflows associated with the specified mailbox
     * id.
     *
     * @param mailboxId
     *            the id of the mailbox
     * @return a Page of Workflow objects
     * @throws ApiException
     */
    public Page<Workflow> getWorkflows(Long mailboxId) throws ApiException {
        return getPage("mailboxes/" + mailboxId + "/workflows.json", Workflow.class, HTTP_STATUS_OK);
    }

    /**
     * Get a page of workflows for the specified mailbox.
     *
     * @param mailboxId
     * @param queryParams
     * @return Page
     * @throws ApiException
     */
    public Page<Workflow> getWorkflows(Long mailboxId, Map<String, String> queryParams) throws ApiException {
        return getPage("mailboxes/" + mailboxId + "/workflows.json", queryParams, Workflow.class, HTTP_STATUS_OK);
    }

    /**
     * Runs the specified manual workflow on the specified ticket.
     *
     * @param id
     * @param ticketId
     * @throws ApiException
     */
    public void runManualWorkflow(Long id, Long ticketId) throws ApiException {
        doPost("workflows/" + id + "/conversations/" + ticketId + ".json", null, HTTP_STATUS_OK, idExtractor);
    }

    /**
     * Runs the specified manual workflow on the specified tickets.
     *
     * @param id
     * @param ticketIds
     * @throws ApiException
     */
    public void runManualWorkflow(Long id, Collection<Long> ticketIds) throws ApiException {
        JsonElement tickets = new Gson().toJsonTree(ticketIds);
        JsonObject obj = new JsonObject();
        obj.add("conversationIds", tickets);
        String json = new Gson().toJson(obj);
        doPost("workflows/" + id + "/conversations.json", json, HTTP_STATUS_OK, idExtractor);
    }
    
    public ConversationsReport getConversationsReport(Map<String, String> queryParams) throws ApiException {
        String url = setParams("reports/conversations.json", queryParams);
        return getObject(url, ConversationsReport.class);
    }

    public List<DayStats> getBusiestTimeOfDayReport(Map<String, String> queryParams) throws ApiException {
        String url = setParams("reports/conversations/busy-times.json", queryParams);
        String json = doGet(url, HTTP_STATUS_OK);
        JsonElement busyTimes = (new JsonParser()).parse(json);

        return JSONUtils.getPageItems(busyTimes, DayStats.class);
    }

    public DatesAndCounts getNewConversationsReport(Map<String, String> queryParams) throws ApiException {
        String url = setParams("reports/conversations/new.json", queryParams);
        return getObject(url, DatesAndCounts.class);
    }

    public Page<net.helpscout.api.model.report.conversations.Conversation> getConversationsDrillDown(Map<String, String> queryParams) throws ApiException {
        String url = setParams("reports/conversations/drilldown.json", queryParams);
        return getPage(url, net.helpscout.api.model.report.conversations.Conversation.class, "conversations");
    }

    public Page<net.helpscout.api.model.report.conversations.Conversation> getConversationsDrillDownByField(Map<String, String> queryParams) throws ApiException {
        String url = setParams("reports/conversations/fields-drilldown.json", queryParams);
        return getPage(url, net.helpscout.api.model.report.conversations.Conversation.class, "conversations");
    }

    public Page<net.helpscout.api.model.report.conversations.Conversation> getNewConversationsDrillDown(Map<String, String> queryParams) throws ApiException {
        String url = setParams("reports/conversations/new-drilldown.json", queryParams);
        return getPage(url, net.helpscout.api.model.report.conversations.Conversation.class, "conversations");
    }

    public DocsReport getDocsReport(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/docs.json", queryParams);
        return getObject(url, DocsReport.class);
    }
    
    public HappinessReport getHappinessReport(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/happiness.json", queryParams);
        return getObject(url, HappinessReport.class);
    }
    
    public Page<Rating> getHappinessRatings(Map<String, String> queryParams) throws ApiException {
        String url = setParams("reports/happiness/ratings.json", queryParams);
        return getPage(url, queryParams, Rating.class, HTTP_STATUS_OK);
    }
    
    public ProductivityReport getProductivityReport(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/productivity.json", queryParams);
        return getObject(url, ProductivityReport.class);
    }
    
    public DatesAndElapsedTimes getFirstResponseTimes(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/productivity/first-response-time.json", queryParams);
        return getObject(url, DatesAndElapsedTimes.class);
    }
    
    public DatesAndCounts getRepliesSent(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/productivity/replies-sent.json", queryParams);
        return getObject(url, DatesAndCounts.class);
    }
    
    public DatesAndCounts getResolved(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/productivity/resolved.json", queryParams);
        return getObject(url, DatesAndCounts.class);
    }
    
    public DatesAndElapsedTimes getResolutionTimes(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/productivity/resolution-time.json", queryParams);
        return getObject(url, DatesAndElapsedTimes.class);
    }
    
    public DatesAndElapsedTimes getResponseTime(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/productivity/response-time.json", queryParams);
        return getObject(url, DatesAndElapsedTimes.class);
    }
    
    public Page<net.helpscout.api.model.report.conversations.Conversation> getProductivityDrillDown(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/productivity/drilldown.json", queryParams);
        return getPage(url, net.helpscout.api.model.report.conversations.Conversation.class, "conversations");
    }
    
    public TeamReport getTeamReport(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/team.json", queryParams);
        return getObject(url, TeamReport.class);
    }
    
    public DatesAndCounts getTeamCustomersHelped(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/team/customers-helped.json", queryParams);
        return getObject(url, DatesAndCounts.class);
    }
    
    public Page<net.helpscout.api.model.report.conversations.Conversation> getTeamDrillDown(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/team/drilldown.json", queryParams);
        return getPage(url, net.helpscout.api.model.report.conversations.Conversation.class, "conversations");
    }
    
    public UserReport getUserReport(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/user.json", queryParams);
        return getObject(url, UserReport.class);
    }
    
    public Page<ConversationStats> getUserConversationHistory(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/user/conversation-history.json", queryParams);
        return getPage(url, queryParams, ConversationStats.class, HTTP_STATUS_OK);
    }
    
    public DatesAndCounts getUserCustomersHelped(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/user/customers-helped.json", queryParams);
        return getObject(url, DatesAndCounts.class);
    }
    
    public DatesAndCounts getUserReplies(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/user/replies.json", queryParams);
        return getObject(url, DatesAndCounts.class);
    }
    
    public DatesAndCounts getUserResolutions(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/user/resolutions.json", queryParams);
        return getObject(url, DatesAndCounts.class);
    }
    
    public UserHappiness getUserHappinessReport(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/user/happiness.json", queryParams);
        return getObject(url, UserHappiness.class);
    }
    
    public Page<Rating> getUserRatings(Map<String,String> queryParams) throws ApiException {
        String url = setParams("reports/user/ratings.json", queryParams);
        return getPage(url, queryParams, Rating.class, HTTP_STATUS_OK);
    }

    public Page<net.helpscout.api.model.report.conversations.Conversation> getUserDrillDown(Map<String, String> queryParams) throws ApiException {
        String url = setParams("reports/user/drilldown.json", queryParams);
        return getPage(url, net.helpscout.api.model.report.conversations.Conversation.class, "conversations");
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

    private <T> T getObject(String url, Class<T> clazzType) throws ApiException {
        String json = doGet(url, HTTP_STATUS_OK);
        return Parser.getInstance().getObject(json, clazzType);     
    }

    private <T> T getItem(String url, Class<T> clazzType, int expectedCode) throws ApiException {
        String json = doGet(url, expectedCode);
        JsonElement obj = parseJson(url, json);
        JsonElement item = obj.getAsJsonObject().get("item");

        return Parser.getInstance().getObject(item, clazzType);
    }
    
    private <T> Page<T> getPage(String url, Class<T> clazzType, int expectedCode) throws ApiException {
        return getPage(url, null, clazzType, expectedCode);
    }
    
    private <T> Page<T> getPage(String url, Class<T> clazzType, String wrapperObjectName) throws ApiException {
        String json = doGet(url, HTTP_STATUS_OK);

        JsonObject outerObj = parseJson(url, json).getAsJsonObject();
        JsonObject innerObj = outerObj.get(wrapperObjectName).getAsJsonObject();
        
        return JSONUtils.objectToPage(innerObj, clazzType);
    }

    private <T> Page<T> getPage(String url, Map<String,String> params, Class<T> clazzType, int expectedCode) throws ApiException {
        url = setParams(url, params);
        String json = doGet(url, HTTP_STATUS_OK);
        JsonElement obj = parseJson(url, json);
        
        return JSONUtils.objectToPage(obj.getAsJsonObject(), clazzType);
    }
    
    private <T> T doPost(String url, String requestBody, int expectedCode, ResultExtractor<T> extractor) throws ApiException {

        try (HTTPConnectionWrapper conn = new HTTPConnectionWrapper(apiKey, baseUrl + url, METHOD_POST, expectedCode, requestBody)) {
            return extractor.extract(conn);
        } catch(ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void doPut(String url, String requestBody, int expectedCode) throws ApiException {

        try (HTTPConnectionWrapper conn = new HTTPConnectionWrapper(apiKey, baseUrl + url, METHOD_PUT, expectedCode, requestBody)) {
        } catch(ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private String doGet(String url, int expectedCode) throws ApiException {
        String response    = null;
        try (HTTPConnectionWrapper conn = new HTTPConnectionWrapper(apiKey, baseUrl + url, METHOD_GET, expectedCode)) {
            response = conn.getResponse();
        } catch(ApiException e) {
            throw e;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private void doDelete(String url, int expectedCode) throws ApiException {
        try (HTTPConnectionWrapper conn = new HTTPConnectionWrapper(apiKey, baseUrl + url, METHOD_DELETE, expectedCode)) {
        } catch(ApiException e) {
            throw e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
