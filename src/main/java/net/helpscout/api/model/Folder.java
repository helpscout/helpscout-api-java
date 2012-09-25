package net.helpscout.api.model;

import java.util.Calendar;

public class Folder {
	private Long id;
	private String name;
	private String type;
	private int userId;
	private int totalCount;
	private int activeCount;
	private Calendar modifiedAt;

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
	public Calendar getModifiedAt() {
		return modifiedAt;
	}
}