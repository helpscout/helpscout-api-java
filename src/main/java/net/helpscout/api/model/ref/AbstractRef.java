package net.helpscout.api.model.ref;

import net.helpscout.api.cbo.PersonType;

public abstract class AbstractRef implements PersonRef {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private PersonType type;

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public PersonType getType() {
		return type;
	}
}
