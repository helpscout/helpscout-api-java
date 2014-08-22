Help Scout Java Wrapper
=======================
Java Wrapper for the Help Scout API. More information on our developer site: [http://developer.helpscout.net](http://developer.helpscout.net).

Version 1.3.13 Released
---------------------
Please see the [Changelog](https://github.com/helpscout/helpscout-api-java/blob/master/CHANGELOG.md) for details.

Requirements
---------------------
* JDE 1.6
* [google-gson](http://code.google.com/p/google-gson/)

Example Usage: API
---------------------
```java
import net.helpscout.api;
import java.util.ArrayList;
import java.util.List;

public class TestingAPI {

  public static void main(String[] args) throws ApiException {
    ApiClient client = ApiClient.getInstance();
    client.setKey("your-api-key-here");

    List<String> fields = new ArrayList<String>();
    fields.add("name");
    fields.add("email");
    Page mailboxes = client.getMailboxes(fields);
    if (mailboxes != null) {
      // do something
    }

    Long mailboxId = 123456L;
    Mailbox mailbox = client.getMailbox(mailboxId);
    if (mailbox != null) {
      String mailboxName = mailbox.getName();
      Page folders = client.getFolders(mailbox.getId());
    }

    Long customerId = 123456L;
    Customer c = client.getCustomer(customerId);
    if (c.hasSocialProfiles()) {
      List<SocialProfileEntry> profiles = c.getSocialProfiles();
      // do something
    }
  }
}
```

Field Selectors
---------------------
Field selectors are given as a list of Strings. When field selectors are used, the appropriate object is created with the fields provided.

ApiClient Methods
--------------------
Each method also has a duplicate that allows you to pass in a list of Strings to specify desired fields (see Field Selectors).

### Mailboxes
* getMailboxes()
* getMailbox(Integer mailboxID)

### Folders
* getFolders(Integer mailboxID)

### Conversations
* getConversationsForFolder(Integer mailboxID, Integer folderID)
* getConversationsForMailbox(Integer mailboxID)
* getConversationsForCustomerByMailbox(Integer mailboxID, Integer customerID)
* getConversation(Integer conversationID)
* createConversation(Conversation conversation)
* createConversationThread(Long conversationId, ConversationThread thread)
* updateConversation(Conversation conversation)
* deleteConversation(Long id)

### Attachments
* getAttachmentData(Integer attachmentID)
* createAttachment(Attachment attachment)
* deleteAttachment(Long id)

### Customers
* getCustomers()
* getCustomer(Integer customerID)
* createCustomer(Customer customer)
* updateCustomer(Customer customer)

### Users
* getUsers()
* getUsersForMailbox(Integer mailboxID)
* getUser(Integer userID)


Example Usage: Webhooks
------------------------
```java
String secretKey = "secret-key-here";
Webhook webhook = new Webhook(secretKey, httpRequest);
if (webhook.isValid()) {
  String event = webhook.getEventType();

  if (webhook.isConversationEvent()) {
    Conversation convo = webhook.getConversation();
    // do something
  } else if (webhook.isCustomerEvent()) {
    Customer customer = webhook.getCustomer();
    // do something
  }
}
```
