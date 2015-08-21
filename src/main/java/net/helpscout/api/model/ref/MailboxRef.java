package net.helpscout.api.model.ref;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MailboxRef {

    Long id;
	String name;
}