package net.helpscout.api.model;

import net.helpscout.api.model.customer.*;

import java.util.Calendar;
import java.util.List;

public class Customer {
	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String age;

	private String jobTitle;
	private String location;
	private String organization;

	private String photoUrl;
	private String photoType;

	private Calendar createdAt;
	private Calendar modifiedAt;

	private String background;
	private Address address;
	private List<SocialProfileEntry> socialProfiles;
	private List<EmailEntry> emails;
	private List<PhoneEntry> phones;
	private List<ChatEntry> chats;
	private List<WebsiteEntry> websites;

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
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

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public Calendar getModifiedAt() {
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public void setModifiedAt(Calendar modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setSocialProfiles(List<SocialProfileEntry> socialProfiles) {
		this.socialProfiles = socialProfiles;
	}

	public void setEmails(List<EmailEntry> emails) {
		this.emails = emails;
	}

	public void setPhones(List<PhoneEntry> phones) {
		this.phones = phones;
	}

	public void setChats(List<ChatEntry> chats) {
		this.chats = chats;
	}

	public void setWebsites(List<WebsiteEntry> websites) {
		this.websites = websites;
	}
}