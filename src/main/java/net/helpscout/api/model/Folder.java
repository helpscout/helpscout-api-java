package net.helpscout.api.model;

import java.util.Date;

public class Folder {
	private Long id;
	private String name;
	private String type;
	private int userId;
	private int totalCount;
	private int activeCount;
	private Date modifiedAt;

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public int getUserId() {
		return userId;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public int getActiveCount() {
		return activeCount;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
}