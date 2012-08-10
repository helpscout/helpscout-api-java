helpscout-api-java
=================
Java Wrapper for the Help Scout API. More information on our developer site: [http://developer.helpscout.net](http://developer.helpscout.net).

Requirements
---------------------
* JDE 1.6
* [google-gson](http://code.google.com/p/google-gson/)

Example Usage
---------------------
<pre><code>
import net.helpscout.api;
import java.util.ArrayList;
import java.util.List;

public class TestingAPI {

  public static void main(String[] args) throws ApiException {
  	ApiClient client = new ApiClient("your-api-key-here");
		
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

Built-in Methods
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

### Attachments
* getAttachmentData(Integer attachmentID)

### Customers
* getCustomers()
* getCustomer(Integer customerID)

### Users
* getUsers()
* getUsersForMailbox(Integer mailboxID)
* getUser(Integer userID)