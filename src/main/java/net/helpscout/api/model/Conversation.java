package net.helpscout.api.model;

import net.helpscout.api.cbo.ConversationType;
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
	private ConversationType type;
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

	public void setId(Long id) {
		this.id = id;
	}

	public ConversationType getType() {
		return type;
	}

	public void setType(ConversationType type) {
		this.type = type;
	}

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public boolean isDraft() {
		return isDraft;
	}

	public void setDraft(boolean draft) {
		isDraft = draft;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public UserRef getOwner() {
		return owner;
	}

	public void setOwner(UserRef owner) {
		this.owner = owner;
	}

	public MailboxRef getMailbox() {
		return mailbox;
	}

	public void setMailbox(MailboxRef mailbox) {
		this.mailbox = mailbox;
	}

	public CustomerRef getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerRef customer) {
		this.customer = customer;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getClosedAt() {
		return closedAt;
	}

	public void setClosedAt(String closedAt) {
		this.closedAt = closedAt;
	}

	public UserRef getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(UserRef closedBy) {
		this.closedBy = closedBy;
	}

	public PersonRef getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(PersonRef createdBy) {
		this.createdBy = createdBy;
	}

	public List<String> getCcList() {
		return ccList;
	}

	public void setCcList(List<String> ccList) {
		this.ccList = ccList;
	}

	public List<String> getBccList() {
		return bccList;
	}

	public void setBccList(List<String> bccList) {
		this.bccList = bccList;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<LineItem> getThreads() {
		return threads;
	}

	public void setThreads(List<LineItem> threads) {
		this.threads = threads;
	}

	public boolean hasCcList() {
		return ccList != null && ccList.size() > 0;
	}

	public boolean hasBccList() {
		return bccList != null && bccList.size() > 0;
	}

	public boolean hasThreads() {
		return threads != null && threads.size() > 0;
	}

	public boolean hasTags() {
		return tags != null && tags.size() > 0;
	}

	public boolean isCreatedByCustomer() {
		return createdBy != null && createdBy instanceof CustomerRef;
	}
}