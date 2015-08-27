package net.helpscout.api.model.ref;

import net.helpscout.api.cbo.PersonType;

public interface PersonRef {
    
    Long getId();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPhone();
    PersonType getType();

    void setId(Long id);
    void setFirstName(String firstName);
    void setLastName(String lastName);
    void setEmail(String email);
    void setPhone(String phone);
    void setType(PersonType type);
}