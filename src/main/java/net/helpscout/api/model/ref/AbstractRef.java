package net.helpscout.api.model.ref;

public abstract class AbstractRef implements PersonRef {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;

	public Long getId() {
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
}
