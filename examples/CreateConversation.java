import net.helpscout.api.ApiClient;
import net.helpscout.api.cbo.ConversationType;
import net.helpscout.api.cbo.Status;
import net.helpscout.api.cbo.ThreadType;
import net.helpscout.api.model.Attachment;
import net.helpscout.api.model.Conversation;
import net.helpscout.api.model.ref.CustomerRef;
import net.helpscout.api.model.ref.MailboxRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;
import net.helpscout.api.model.thread.ConversationThread;
import net.helpscout.api.model.thread.LineItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateConversation {

	public static void main(String[] args) throws Exception {

		// The mailbox associated with the conversation
		MailboxRef mailbox = new MailboxRef();
		mailbox.setId(123L);

		// The customer associated with the conversation
		CustomerRef customer = new CustomerRef();
		customer.setId(12345L);
		customer.setEmail("customer@example.com");

		Conversation conversation = new Conversation();
		conversation.setSubject("I need help!"); // Required
		conversation.setMailbox(mailbox); // Required
		conversation.setCustomer(customer); // Required
		conversation.setType(ConversationType.Email); // Not required - defaults to email

		// A conversation must have at least one thread
		ConversationThread thread = new net.helpscout.api.model.thread.Customer();
		thread.setType(ThreadType.Customer); // Required
		thread.setBody("Hello. I need some help."); // Required
		thread.setStatus(Status.Active); // Required
		thread.setCreatedAt(new Date()); // Not required - defaults to current UTC time

		// Created by: required
		PersonRef createdBy = new CustomerRef();
		createdBy.setId(12345L);
		thread.setCreatedBy(createdBy); // Required

		// Assigned to: not required - defaults to 'anyone'
		UserRef assignedTo = new UserRef();
		assignedTo.setId(100L);
		thread.setAssignedTo(assignedTo);

		// Cc: list of emails to Cc
		List<String> ccList = new ArrayList<String>();
		ccList.add("foo@example.com");
		thread.setCcList(ccList);

		// Bcc: list of emails to Bcc
		List<String> bccList = new ArrayList<String>();
		bccList.add("bar@example.com");
		thread.setBccList(bccList);

		// Attachments: attachments must be sent to the API before they can
		// be used when creating a thread. Use the hash value returned when
		// creating the attachment to associate it with a ticket.
		List<Attachment> attachments = new ArrayList<Attachment>();

		Attachment attachment = new Attachment();
		attachment.setHash("j894hg93gh9egh934gh34g8hjhvbdjvhbweg3");
		attachments.add(attachment);
		thread.setAttachments(attachments);

		List<LineItem> threads = new ArrayList<LineItem>();
		threads.add((LineItem)thread);
		conversation.setThreads(threads);

		ApiClient client = ApiClient.getInstance();
		client.setKey("example-key");
		client.createConversation(conversation);

		System.out.println("Conversation id: " + conversation.getId());
	}
}
