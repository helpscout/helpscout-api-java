package net.helpscout.api.model.thread;

import java.util.Calendar;

import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.ref.MailboxRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;

public class BaseLineItem implements LineItem {
	private UserRef assignedTo;
	private Status status;
	private PersonRef createdBy;
	private Calendar createdAt;
	
	private MailboxRef fromMailbox;
	
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
	
	public Calendar getCreatedAt() {
		return createdAt;
	}
	public MailboxRef getFromMailbox() {
		return fromMailbox;
	}
}