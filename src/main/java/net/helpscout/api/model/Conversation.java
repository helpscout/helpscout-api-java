package net.helpscout.api.model;

import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.ref.CustomerRef;
import net.helpscout.api.model.ref.MailboxRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;
import net.helpscout.api.model.thread.LineItem;

import java.util.Date;
import java.util.List;

public class Conversation {
	private Long id;
	private Long folderId;
	private boolean isDraft;
	private Long number;
	private Source source;

	private UserRef owner;
	private MailboxRef mailbox;
	private CustomerRef customer;
	private int threadCount;
	private Status status;
	private String subject;
	private String preview;
	private Date createdAt;
	private Date modifiedAt;
	private String closedAt;
	private UserRef closedBy;

	private PersonRef createdBy;

	private List<String> ccList;
	private List<String> bccList;
	private List<String> tags;

	private List<LineItem> threads;

	public Long getId() {
		return id;
	}
	public Long getFolderId() {
		return folderId;
	}
	public boolean isDraft() {
		return isDraft;
	}
	public Long getNumber() {
		return number;
	}
	public UserRef getOwner() {
		return owner;
	}
	public MailboxRef getMailbox() {
		return mailbox;
	}
	public CustomerRef getCustomer() {
		return customer;
	}
	public int getThreadCount() {
		return threadCount;
	}
	public Status getStatus() {
		return status;
	}
	public String getSubject() {
		return subject;
	}
	public String getPreview() {
		return preview;
	}
	public Source getSource() {
		return source;
	}

	public PersonRef getCreatedBy() {
		return createdBy;
	}

	public boolean isCreatedByCustomer() {
		return createdBy != null && createdBy instanceof CustomerRef;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public String getClosedAt() {
		return closedAt;
	}

	public UserRef getClosedBy() {
		return closedBy;
	}

	public boolean hasCcList() {
		return ccList != null && ccList.size() > 0;
	}
	public List<String> getCcList() {
		return ccList;
	}
	public boolean hasBccList() {
		return bccList != null && bccList.size() > 0;
	}
	public List<String> getBccList() {
		return bccList;
	}
	public boolean hasTags() {
		return tags != null && tags.size() > 0;
	}
	public List<String> getTags() {
		return tags;
	}
	public boolean hasThreads() {
		return threads != null && threads.size() > 0;
	}

	public List<LineItem> getThreads() {
		return threads;
	}
}