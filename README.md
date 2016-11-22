Help Scout Java Wrapper
=======================
Java Wrapper for the Help Scout API. More information can be found on our [developer site](http://developer.helpscout.net).

Version 1.6.2 Released
----------------------
Please see the [Changelog](https://github.com/helpscout/helpscout-api-java/blob/master/CHANGELOG.md) for details.

Requirements
---------------------
* Java 1.7 or higher
* [Commons Codec](http://commons.apache.org/proper/commons-codec/)
* [Commons Lang](http://commons.apache.org/proper/commons-lang/)
* [Commons IO](http://commons.apache.org/proper/commons-io/)
* [GSON](https://github.com/google/gson/)
* [SLF4J](http://www.slf4j.org/)
* [Joda Time](http://www.joda.org/joda-time/)

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
* getMailbox(Long mailboxID)

### Folders
* getFolders(Long mailboxID)

### Conversations
* getConversationsForFolder(Long mailboxID, Long folderID)
* getConversationsForMailbox(Long mailboxID)
* getConversationsForCustomerByMailbox(Long mailboxID, Long customerID)
* getConversation(Long conversationID)
* createConversation(Conversation conversation)
* createConversationThread(Long conversationId, ConversationThread thread)
* updateConversation(Conversation conversation)
* deleteConversation(Long id)

### Attachments
* getAttachmentData(Long attachmentID)
* createAttachment(Attachment attachment)
* deleteAttachment(Long id)

### Customers
* getCustomers()
* getCustomer(Long customerID)
* createCustomer(Customer customer)
* updateCustomer(Customer customer)

### Users
* getUsers()
* getUsersForMailbox(Long mailboxID)
* getUser(Long userID)

### Teams
* getTeams()
* getTeam()
* getTeamMembers()


Example Usage: Webhooks
------------------------
```java
String secretKey = "secret-key-here";
Webhook webhook = new Webhook(secretKey, httpRequest);
if (webhook.isValid()) {
  WebhookEventType event = webhook.getEventType();

  if (event.isConversationEvent()) {
    Conversation convo = webhook.getConversation();
    // do something
  } else if (event.isCustomerEvent()) {
    Customer customer = webhook.getCustomer();
    // do something
  }
}
```

Contributing
------------
All contributions are welcomed and reviewed. Please submit all pull requests against the [develop branch](https://github.com/helpscout/helpscout-api-java/tree/develop).