package net.helpscout.api.model;

import java.util.List;

import net.helpscout.api.model.customer.*;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String role;
	private String timezone;
	private String photoUrl;
	private String photoType;
	private String gender;
	private String age;
	private String organization;
	private String jobTitle;
	private String location;
	private String createdAt;
	private String modifiedAt;
	
	private String background;
	private Address address;
	private List<SocialProfileEntry> socialProfiles;
	private List<EmailEntry> emails;
	private List<PhoneEntry> phones;
	private List<ChatEntry> chats;
	private List<WebsiteEntry> websites;

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getRole() {
		return role;
	}

	public String getTimezone() {
		return timezone;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public String getPhotoType() {
		return photoType;
	}

	public String getGender() {
		return gender;
	}

	public String getAge() {
		return age;
	}

	public String getOrganization() {
		return organization;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getLocation() {
		return location;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}
	
	public boolean hasBackground() {
		return background != null;
	}

	public String getBackground() {
		return background;
	}
	
	public boolean hasAddress() {
		return address != null;
	}

	public Address getAddress() {
		return address;
	}

	public boolean hasSocialProfiles() {
		return socialProfiles != null && socialProfiles.size() > 0;
	}
	
	public List<SocialProfileEntry> getSocialProfiles() {
		return socialProfiles;
	}
	
	public boolean hasEmails() {
		return emails != null && emails.size() > 0;
	}

	public List<EmailEntry> getEmails() {
		return emails;
	}
	
	public boolean hasPhones() {
		return phones != null && phones.size() > 0;
	}

	public List<PhoneEntry> getPhones() {
		return phones;
	}
	
	public boolean hasChats() {
		return chats != null && chats.size() > 0;
	}

	public List<ChatEntry> getChats() {
		return chats;
	}
	
	public boolean hasWebsites() {
		return websites != null && websites.size() > 0;
	}

	public List<WebsiteEntry> getWebsites() {
		return websites;
	}
}