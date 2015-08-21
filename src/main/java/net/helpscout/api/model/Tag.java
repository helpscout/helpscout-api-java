package net.helpscout.api.model;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tag {

    Long id;
    String slug;
    String tag;
    Integer count;
    String color;
    Date createdAt;
    Date modifiedAt;
}