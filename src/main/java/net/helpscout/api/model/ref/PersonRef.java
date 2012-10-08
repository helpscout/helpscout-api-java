package net.helpscout.api.model.ref;

public interface PersonRef {

	public Long getId();
	public String getFirstName();
	public String getLastName();
	public String getEmail();

	public void setId(Long id);
	public void setFirstName(String firstName);
	public void setLastName(String lastName);
	public void setEmail(String email);
}
