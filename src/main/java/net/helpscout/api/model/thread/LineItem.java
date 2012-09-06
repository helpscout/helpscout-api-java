package net.helpscout.api.model.thread;

import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.ref.MailboxRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;

public interface LineItem {
	
	public boolean isAssigned();
	
	public boolean isActive();
	
	public boolean isPending();
	
	public boolean isClosed();
	
	public boolean isSpam();
	
	public UserRef getAssignedTo();
	
	public Status getStatus();
		
	public boolean isStatusChange();
		
	public PersonRef getCreatedBy();
		
	public MailboxRef getFromMailbox();	
}