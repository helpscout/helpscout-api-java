package net.helpscout.api.model;

import java.util.Date;

import lombok.Data;

@Data
public class SearchConversation {

    private Long id;
    private Long number;
    private Long mailboxId;
    private String subject;
    private String status;
    private int threadCount;
    private String preview;
    private String customerName;
    private String customerEmail;
    private Date modifiedAt;
    private Float score;
}