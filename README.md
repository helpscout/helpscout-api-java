Help Scout Java Wrapper
=======================
Java Wrapper for the Help Scout API. More information on our developer site: [http://developer.helpscout.net](http://developer.helpscout.net).

See the [Changelog](https://github.com/helpscout/helpscout-api-java/blob/master/CHANGELOG.md) for details on latest release.

Requirements
---------------------
* JDE 1.6
* [google-gson](http://code.google.com/p/google-gson/)
* [commons-codec](http://commons.apache.org/proper/commons-codec/)

Example Usage: API
---------------------
<pre><code>
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
	if (mailboxes) {
	      // do something
	}

	Mailbox mailbox = client.getMailbox(85);
	if (mailbox) {
		String mailboxName = mailbox.getName();
		List<Folder> folders = mailbox.getFolders();
	}

	Customer c = client.getCustomer(customer-id-here);
	if (c.hasSocialProfiles()) {
		List<SocialProfileEntry> profiles = c.getSocialProfiles();
		// do something
	}
  }
}
</code></pre>

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


Example Usage: Webhooks
------------------------
<pre><code>
Webhook webhook = new Webhook('secret-key-here', httpRequest);
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
</code></pre>
