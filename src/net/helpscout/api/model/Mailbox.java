package net.helpscout.api.model;

public class Mailbox {
	private int id;
	private String name;
	private String slug;
	private String email;
	private String createdAt;
	private String modifiedAt;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSlug() {
		return slug;
	}
	public String getEmail() {
		return email;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public String getModifiedAt() {
		return modifiedAt;
	}
}
