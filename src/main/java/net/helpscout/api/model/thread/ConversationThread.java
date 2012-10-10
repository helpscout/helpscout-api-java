package net.helpscout.api.model.thread;

import net.helpscout.api.cbo.Status;
import net.helpscout.api.cbo.ThreadState;
import net.helpscout.api.cbo.ThreadType;
import net.helpscout.api.model.Attachment;
import net.helpscout.api.model.ref.MailboxRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;

import java.util.Calendar;
import java.util.List;

public interface ConversationThread {
	public boolean isPublished();
	public boolean isDraft();
	public boolean isHeldForReview();
	public boolean hasAttachments();
	public ThreadType getType();
	public ThreadState getState();
	public String getBody();
	public List<String> getToList();
	public List<String> getCcList();
	public List<String> getBccList();
	public List<Attachment> getAttachments();
	public boolean isAssigned();
	public boolean isActive();
	public boolean isPending();
	public boolean isClosed();
	public boolean isSpam();
	public boolean isStatusChange();
	public UserRef getAssignedTo();
	public Status getStatus();
	public PersonRef getCreatedBy();
	public Calendar getCreatedAt();
	public MailboxRef getFromMailbox();
}
