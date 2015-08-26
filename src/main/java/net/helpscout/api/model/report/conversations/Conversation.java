package net.helpscout.api.model.report.conversations;

import java.util.Date;
import java.util.List;

import lombok.Data;
import net.helpscout.api.cbo.ConversationType;
import net.helpscout.api.cbo.Status;
import net.helpscout.api.model.report.common.Tag;

@Data
public class Conversation {

    private Integer id;
    private Integer number;
    private ConversationType type;
    private Integer mailboxid;
    private Boolean attachments;
    private String subject;
    private Status status;
    private Integer threadCount;
    private String preview;
    private String customerName;
    private String customerEmail;
    private List<Integer> customerIds;
    private Date modifiedAt;
    private Integer assignedid;
    private List<Tag> tags;
    private String assignedName;
}