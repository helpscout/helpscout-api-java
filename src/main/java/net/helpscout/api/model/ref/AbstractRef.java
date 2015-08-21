package net.helpscout.api.model.ref;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.helpscout.api.cbo.PersonType;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractRef implements PersonRef {
    
	Long id;
	String firstName;
	String lastName;
	String email;
	String phone;
	PersonType type;
}