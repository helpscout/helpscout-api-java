package net.helpscout.api.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class MailboxUser {

    public enum UserType {
        team, user
    }
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String timezone;
    private String photoUrl;
    private Date createdAt;
    private Date modifiedAt;
    private UserType type;
}