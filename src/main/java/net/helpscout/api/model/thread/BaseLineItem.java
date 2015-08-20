package net.helpscout.api.model.thread;

import net.helpscout.api.cbo.ActionType;
import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.ref.MailboxRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;

import java.util.Date;

public class BaseLineItem implements LineItem {
	private UserRef assignedTo;
	private Status status;
	private PersonRef createdBy;
	private Date createdAt;
	private MailboxRef fromMailbox;
    private ActionType actionType;
    private Long actionSourceId;

	public boolean isAssigned() {
		return this.assignedTo != null;
	}

	public boolean isActive() {
		return this.status == Status.Active;
	}

	public boolean isPending() {
		return this.status == Status.Pending;
	}

	public boolean isClosed() {
		return this.status == Status.Closed;
	}

	public boolean isSpam() {
		return this.status == Status.Spam;
	}

	public UserRef getAssignedTo() {
		return assignedTo;
	}

	public Status getStatus() {
		return status;
	}

	public boolean isStatusChange() {
		return status != Status.NoChange;
	}

	public PersonRef getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setFromMailbox(MailboxRef fromMailbox) {
		this.fromMailbox = fromMailbox;
	}

	public MailboxRef getFromMailbox() {
		return fromMailbox;
	}

	public void setAssignedTo(UserRef assignedTo) {
		this.assignedTo = assignedTo;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setCreatedBy(PersonRef person) {
		this.createdBy = person;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Long getActionSourceId() {
        return actionSourceId;
    }

    public void setActionSourceId(Long actionSourceId) {
        this.actionSourceId = actionSourceId;
    }

	@Override
	public String toString() {
		return "BaseLineItem [assignedTo=" + assignedTo + ", status=" + status + ", createdBy=" + createdBy
				+ ", createdAt=" + createdAt + ", fromMailbox=" + fromMailbox + ", actionType=" + actionType
				+ ", actionSourceId=" + actionSourceId + "]";
	}

}