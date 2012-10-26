package net.helpscout.api.model.thread;

import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.ref.MailboxRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;

import java.util.Date;

public interface LineItem {

	public boolean isAssigned();

	public boolean isActive();

	public boolean isPending();

	public boolean isClosed();

	public boolean isSpam();

	public void setAssignedTo(UserRef assignedTo);

	public UserRef getAssignedTo();

	public void setStatus(Status status);

	public Status getStatus();

	public boolean isStatusChange();

	public void setCreatedBy(PersonRef person);

	public PersonRef getCreatedBy();

	public void setFromMailbox(MailboxRef mailbox);

	public MailboxRef getFromMailbox();

	public void setCreatedAt(Date createdAt);
}