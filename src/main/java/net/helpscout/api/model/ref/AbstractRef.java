package net.helpscout.api.model.ref;

import net.helpscout.api.cbo.CreatedByType;

public abstract class AbstractRef implements PersonRef {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private CreatedByType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CreatedByType getType() {
		return type;
	}

	public void setType(CreatedByType type) {
		this.type = type;
	}
}
