package net.helpscout.api.model.ref;

public abstract class AbstractRef implements PersonRef {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
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
}
