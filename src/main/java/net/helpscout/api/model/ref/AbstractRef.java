package net.helpscout.api.model.ref;

import lombok.Data;
import net.helpscout.api.cbo.PersonType;

@Data
public abstract class AbstractRef implements PersonRef {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private PersonType type;
}