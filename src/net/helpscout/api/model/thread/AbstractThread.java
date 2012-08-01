package net.helpscout.api.model.thread;

import net.helpscout.api.model.thread.LineItem;
import net.helpscout.api.model.Attachment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

interface ConversationThread {
	public boolean isPublished();
	public boolean isDraft();
	public boolean isHeldForReview();
	public boolean hasAttachments();
	public String getState();
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
	public int getAssignedTo();
	public String getStatus();
	public int getCreatedBy();
	public int getFromMailboxId();
}

public class AbstractThread extends LineItem implements ConversationThread {
	private int id;
	private String state;
	private String body;
	private List<String> toList;
	private List<String> ccList;
	private List<String> bccList;
	private List<Attachment> attachments;
	
	public boolean isPublished() {
		return this.state == "published";
	}
	public boolean isDraft() {
		return this.state == "draft";
	}
	public boolean isHeldForReview() {
		return this.state == "underreview";
	}
	public boolean hasAttachments() {
		return this.attachments != null && this.attachments.size() > 0;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public List<String> getToList() {
		return toList;
	}
	public void setToList(List<String> toList) {
		this.toList = toList;
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
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
}