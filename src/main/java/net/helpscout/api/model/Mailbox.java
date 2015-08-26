package net.helpscout.api.model;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Mailbox {

    private Long id;
    private String name;
    private String slug;
    private String email;
    private Date createdAt;
    private Date modifiedAt;
}