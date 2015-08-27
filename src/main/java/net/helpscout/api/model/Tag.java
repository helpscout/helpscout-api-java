package net.helpscout.api.model;

import java.util.Date;

import lombok.Data;

@Data
public class Tag {

    private Long id;
    private String slug;
    private String tag;
    private Integer count;
    private String color;
    private Date createdAt;
    private Date modifiedAt;
}