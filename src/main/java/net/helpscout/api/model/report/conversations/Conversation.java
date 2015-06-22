package net.helpscout.api.model.report.conversations;

import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.helpscout.api.cbo.ConversationType;
import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.report.common.Tag;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Conversation {

    Integer id;
    Integer number;
    ConversationType type;
    Integer mailboxid;
    Boolean attachments;
    String subject;
    Status status;
    Integer threadCount;
    String preview;
    String customerName;
    String customerEmail;
    List<Integer> customerIds;
    Date modifiedAt;
    Integer assignedid;
    List<Tag> tags;
    String assignedName;
}