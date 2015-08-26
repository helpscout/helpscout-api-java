package net.helpscout.api.model.thread;

import java.util.Date;

import lombok.Data;
import net.helpscout.api.cbo.ActionType;
import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.ref.MailboxRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;

@Data
public class BaseLineItem implements LineItem {
    
    private UserRef assignedTo;
    private Status status;
    private PersonRef createdBy;
    private Date createdAt;
    private MailboxRef fromMailbox;
    private ActionType actionType;
    private Long actionSourceId;

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