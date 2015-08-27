package net.helpscout.api.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import net.helpscout.api.cbo.ConversationType;
import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.ref.CustomerRef;
import net.helpscout.api.model.ref.MailboxRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;
import net.helpscout.api.model.thread.LineItem;

@Data
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

    public boolean hasCcList() {
        return getCcList() != null && !getCcList().isEmpty();
    }

    public boolean hasBccList() {
        return getBccList() != null && !getBccList().isEmpty();
    }

    public boolean hasThreads() {
        return getThreads() != null && !getThreads().isEmpty();
    }

    public boolean hasTags() {
        return getTags() != null && !getTags().isEmpty();
    }

    public boolean isCreatedByCustomer() {
        return getCreatedBy() != null && getCreatedBy() instanceof CustomerRef;
    }
}