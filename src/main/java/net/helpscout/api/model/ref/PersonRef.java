package net.helpscout.api.model.ref;

import net.helpscout.api.cbo.PersonType;

public interface PersonRef {

	public int getId();
	public String getFirstName();
	public String getLastName();
	public String getEmail();
	public PersonType getType();
}
