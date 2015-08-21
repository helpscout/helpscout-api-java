package net.helpscout.api.model.thread;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import net.helpscout.api.cbo.ThreadState;
import net.helpscout.api.cbo.ThreadType;
import net.helpscout.api.model.Attachment;

@Data
@EqualsAndHashCode(callSuper=true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AbstractThread extends BaseLineItem implements ConversationThread {
    
	Long id;
	ThreadType type;
	ThreadState state;
	String body;
	List<String> toList;
	List<String> ccList;
	List<String> bccList;
	List<Attachment> attachments;

	public boolean isPublished() {
	    return getState() == ThreadState.Published;
	}

	public boolean isDraft() {
	    return getState() == ThreadState.Draft;
	}

	public boolean isHeldForReview() {
		return getState() == ThreadState.UnderReview;
	}

	public boolean hasAttachments() {
		return getAttachments() != null && getAttachments().size() > 0;
	}
}