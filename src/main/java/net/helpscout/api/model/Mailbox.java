package net.helpscout.api.model;

import java.util.Date;

public class Mailbox {
	private Long id;
	private String name;
	private String slug;
	private String email;
	private Date createdAt;
	private Date modifiedAt;

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
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	@Override
	public String toString() {
		return "Mailbox [id=" + id + ", name=" + name + ", slug=" + slug + ", email=" + email + ", createdAt="
				+ createdAt + ", modifiedAt=" + modifiedAt + "]";
	}

}
