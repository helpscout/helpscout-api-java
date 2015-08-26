package net.helpscout.api.model.thread;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.helpscout.api.cbo.ThreadState;
import net.helpscout.api.cbo.ThreadType;
import net.helpscout.api.model.Attachment;

@Data
@EqualsAndHashCode(callSuper=true)
public class AbstractThread extends BaseLineItem implements ConversationThread {
    
    private Long id;
    private ThreadType type;
    private ThreadState state;
    private String body;
    private List<String> toList;
    private List<String> ccList;
    private List<String> bccList;
    private List<Attachment> attachments;

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