package net.helpscout.api.model;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mailbox {

    Long id;
    String name;
    String slug;
    String email;
    Date createdAt;
    Date modifiedAt;
}