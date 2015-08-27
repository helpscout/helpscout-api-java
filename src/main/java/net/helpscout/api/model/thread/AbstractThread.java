package net.helpscout.api.model.thread;

import java.util.ArrayList;
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
    private List<String> toList = new ArrayList<String>();
    private List<String> ccList = new ArrayList<String>();
    private List<String> bccList = new ArrayList<String>();
    private List<Attachment> attachments = new ArrayList<Attachment>();
    
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
        return !getAttachments().isEmpty();
    }
}