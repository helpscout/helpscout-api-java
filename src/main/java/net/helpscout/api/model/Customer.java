package net.helpscout.api.model;

import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.helpscout.api.model.customer.Address;
import net.helpscout.api.model.customer.ChatEntry;
import net.helpscout.api.model.customer.EmailEntry;
import net.helpscout.api.model.customer.PhoneEntry;
import net.helpscout.api.model.customer.SocialProfileEntry;
import net.helpscout.api.model.customer.WebsiteEntry;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    
	Long id;
	String firstName;
	String lastName;
	String gender;
	String age;

	String jobTitle;
	String location;
	String organization;

	String photoUrl;
	String photoType;

	Date createdAt;
	Date modifiedAt;

	String background;
	Address address;
	List<SocialProfileEntry> socialProfiles;
	List<EmailEntry> emails;
	List<PhoneEntry> phones;
	List<ChatEntry> chats;
	List<WebsiteEntry> websites;

	public boolean hasBackground() {
		return getBackground() != null;
	}

	public boolean hasAddress() {
	    return getAddress() != null;
	}

	public boolean hasSocialProfiles() {
	    return getSocialProfiles() != null && !getSocialProfiles().isEmpty();
	}

	public boolean hasEmails() {
	    return getEmails() != null && !getEmails().isEmpty();
	}

	public boolean hasPhones() {
	    return getPhones() != null && !getPhones().isEmpty();
	}

	public boolean hasChats() {
	    return getChats() != null && !getChats().isEmpty();
	}

	public boolean hasWebsites() {
	    return getWebsites() != null && !getWebsites().isEmpty();
	}
}