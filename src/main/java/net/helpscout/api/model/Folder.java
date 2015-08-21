package net.helpscout.api.model;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Folder {
    
	Long id;
	String name;
	String type;
	int userId;
	int totalCount;
	int activeCount;
	Date modifiedAt;
}