package net.helpscout.api.model.ref;

import net.helpscout.api.cbo.PersonType;

public interface PersonRef {
	public Long getId();
	public String getFirstName();
	public String getLastName();
	public String getEmail();
	public PersonType getType();

	public void setId(Long id);
	public void setFirstName(String firstName);
	public void setLastName(String lastName);
	public void setEmail(String email);
	public void setType(PersonType type);
}
