package net.helpscout.api.model.thread;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.helpscout.api.cbo.ActionType;
import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.ref.MailboxRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseLineItem implements LineItem {
    
    UserRef assignedTo;
    Status status;
    PersonRef createdBy;
    Date createdAt;
    MailboxRef fromMailbox;
    ActionType actionType;
    Long actionSourceId;

	public boolean isAssigned() {
	    return getAssignedTo() != null;
	}

	public boolean isActive() {
	    return getStatus() == Status.Active;
	}

	public boolean isPending() {
	    return getStatus() == Status.Pending;
	}

	public boolean isClosed() {
	    return getStatus() == Status.Closed;
	}

	public boolean isSpam() {
	    return getStatus() == Status.Spam;
	}

	public boolean isStatusChange() {
		return status != Status.NoChange;
	}
}