package net.helpscout.api.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.helpscout.api.cbo.ConversationType;
import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.customfield.CustomFieldResponse;
import net.helpscout.api.model.ref.*;
import net.helpscout.api.model.thread.LineItem;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {
    
    private Long id;
    private ConversationType type;
    private Long folderId;
    private boolean isDraft;
    private Long number;
    private Source source;
    private MailboxUserRef owner;
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
    private List<? extends CustomFieldResponse> customFields;

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