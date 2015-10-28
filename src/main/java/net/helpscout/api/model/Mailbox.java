package net.helpscout.api.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import net.helpscout.api.model.customfield.CustomField;

@Getter
@ToString
public class Mailbox {
    private Long id;
    private String name;
    private String slug;
    private String email;
    private Date createdAt;
    private Date modifiedAt;
    private List<CustomField> customFields;
}