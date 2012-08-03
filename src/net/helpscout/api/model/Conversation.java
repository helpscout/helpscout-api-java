package net.helpscout.api.model;

import java.util.List;

public class Conversation {
	private int id;
	private int folderId;
	private boolean draft;
	private int number;
	private int ownerId;
	private int mailboxId;
	private int customerId;
	private int threadCount;
	private String status;
	private String subject;
	private String preview;
	private int createdBy;
	private String createdAt;
	private String modifiedAt;
	private String closedAt;
	private int closedBy;
	
	private List<String> ccList;
	private List<String> bccList;
	private List<String> tags;
	private List<Thread> threads;
	
	public int getId() {
		return id;
	}
	public int getFolderId() {
		return folderId;
	}
	public boolean isDraft() {
		return draft;
	}
	public int getNumber() {
		return number;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public int getMailboxId() {
		return mailboxId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public int getThreadCount() {
		return threadCount;
	}
	public String getStatus() {
		return status;
	}
	public String getSubject() {
		return subject;
	}
	public String getPreview() {
		return preview;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public String getModifiedAt() {
		return modifiedAt;
	}
	public String getClosedAt() {
		return closedAt;
	}
	public int getClosedBy() {
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
	public List<Thread> getThreads() {
		return threads;
	}
}