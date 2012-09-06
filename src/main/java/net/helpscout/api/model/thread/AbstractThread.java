package net.helpscout.api.model.thread;

import net.helpscout.api.cbo.ThreadState;
import net.helpscout.api.model.Attachment;

import java.util.List;

public class AbstractThread extends BaseLineItem implements ConversationThread {
	private int id;
	private ThreadState state;
	private String body;
	private List<String> toList;
	private List<String> ccList;
	private List<String> bccList;
	private List<Attachment> attachments;
	
	public boolean isPublished() {
		return this.state == ThreadState.Published;
	}
	public boolean isDraft() {
		return this.state == ThreadState.Draft;
	}
	public boolean isHeldForReview() {
		return this.state == ThreadState.UnderReview;
	}
	public boolean hasAttachments() {
		return this.attachments != null && this.attachments.size() > 0;
	}
	public int getId() {
		return id;
	}
	
	public ThreadState getState() {
		return state;
	}
	public String getBody() {
		return body;
	}
	public List<String> getToList() {
		return toList;
	}
	public List<String> getCcList() {
		return ccList;
	}
	public List<String> getBccList() {
		return bccList;
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}
}