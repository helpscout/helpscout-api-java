package net.helpscout.api.model;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchConversation {

    Long id;
    Long number;
    Long mailboxId;
    String subject;
    String status;
    int threadCount;
    String preview;
    String customerName;
    String customerEmail;
    Date modifiedAt;
    Float score;
}