package net.helpscout.api.model;

import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.helpscout.api.cbo.ConversationType;
import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.ref.CustomerRef;
import net.helpscout.api.model.ref.MailboxRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;
import net.helpscout.api.model.thread.LineItem;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Conversation {
    
    Long id;
    ConversationType type;
    Long folderId;
    boolean isDraft;
    Long number;
    Source source;
    UserRef owner;
    MailboxRef mailbox;
    CustomerRef customer;
    int threadCount;
    Status status;
    String subject;
    String preview;
    Date createdAt;
    Date modifiedAt;
    String closedAt;
    UserRef closedBy;
    PersonRef createdBy;
    List<String> ccList;
    List<String> bccList;
    List<String> tags;
    List<LineItem> threads;

	public boolean hasCcList() {
	    return getCcList() != null && !getCcList().isEmpty();
	}

	public boolean hasBccList() {
	    return getBccList() != null && !getBccList().isEmpty();
	}

	public boolean hasThreads() {
	    return getThreads() != null && !getThreads().isEmpty();
	}

	public boolean hasTags() {
	    return getTags() != null && !getTags().isEmpty();
	}

	public boolean isCreatedByCustomer() {
	    return getCreatedBy() != null && getCreatedBy() instanceof CustomerRef;
	}
}