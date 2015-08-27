package net.helpscout.api.model.thread;

import net.helpscout.api.cbo.ActionType;
import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.ref.MailboxRef;
import net.helpscout.api.model.ref.PersonRef;
import net.helpscout.api.model.ref.UserRef;

import java.util.Date;

public interface LineItem {

    boolean isAssigned();
    boolean isActive();
    boolean isPending();
    boolean isClosed();
    boolean isSpam();
    boolean isStatusChange();
    UserRef getAssignedTo();
    Status getStatus();
    PersonRef getCreatedBy();
    MailboxRef getFromMailbox();
    ActionType getActionType();
    Long getActionSourceId();

    void setAssignedTo(UserRef assignedTo);
    void setStatus(Status status);
    void setCreatedBy(PersonRef person);
    void setFromMailbox(MailboxRef mailbox);
    void setCreatedAt(Date createdAt);
    void setActionType(ActionType actionType);
    void setActionSourceId(Long actionSourceId);
}