package net.helpscout.api.model;

import java.util.Calendar;

public class Mailbox {
	private Long id;
	private String name;
	private String slug;
	private String email;
	private Calendar createdAt;
	private Calendar modifiedAt;

	public Long getId() {
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
	public Calendar getCreatedAt() {
		return createdAt;
	}
	public Calendar getModifiedAt() {
		return modifiedAt;
	}
}
