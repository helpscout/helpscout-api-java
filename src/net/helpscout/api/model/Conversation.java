package net.helpscout.api.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
	// private Source source;
	/*
	private List<String> ccList;
	private List<String> bccList;
	private List<String> tags;
	private List<Thread> threads;
	*/

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFolderId() {
		return folderId;
	}
	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}
	public boolean isDraft() {
		return draft;
	}
	public void setDraft(boolean draft) {
		this.draft = draft;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getMailboxId() {
		return mailboxId;
	}
	public void setMailboxId(int mailboxId) {
		this.mailboxId = mailboxId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getThreadCount() {
		return threadCount;
	}
	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public String getClosedAt() {
		return closedAt;
	}
	public void setClosedAt(String closedAt) {
		this.closedAt = closedAt;
	}
	public int getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(int closedBy) {
		this.closedBy = closedBy;
	}
	/*
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
	public List<Thread> getThreads() {
		return threads;
	}
	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}
	*/
}