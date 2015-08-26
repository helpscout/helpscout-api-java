package net.helpscout.api.model;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Folder {
    
    private Long id;
    private String name;
    private String type;
    private int userId;
    private int totalCount;
    private int activeCount;
    private Date modifiedAt;
}