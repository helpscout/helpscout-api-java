import net.helpscout.api.ApiClient;
import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.Conversation;
import net.helpscout.api.model.ref.MailboxRef;
import net.helpscout.api.model.ref.UserRef;

import java.util.ArrayList;
import java.util.List;

public class UpdateConversation {

	public static void main(String[] args) throws Exception {

		ApiClient client = ApiClient.getInstance();
		client.setKey("example-key");

		Conversation conversation = client.getConversation(1L);
		if (conversation == null) {
			throw new Exception("Conversation not found");
		}

		conversation.setSubject("I need help");
		conversation.setStatus(Status.Pending);

		// Change the owner of the conversation
		UserRef owner = new UserRef();
		owner.setId(100L);
		conversation.setOwner(owner);

		// Change the mailbox of the conversation
		MailboxRef mailbox = new MailboxRef();
		mailbox.setId(1L);
		conversation.setMailbox(mailbox);

		// Update the conversation tags. Existing tags not
		// in this list will be deleted.
		List<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		conversation.setTags(tags);

		client.updateConversation(conversation);
	}
}
