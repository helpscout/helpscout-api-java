package net.helpscout.api.model;

public class Folder {
	private int id;
	private String name;
	private String type;
	private int userId;
	private int totalCount;
	private int activeCount;
	private String modifiedAt;
	
	public int getId() {
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
	public String getModifiedAt() {
		return modifiedAt;
	}
}