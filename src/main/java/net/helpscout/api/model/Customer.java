package net.helpscout.api.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import net.helpscout.api.model.customer.Address;
import net.helpscout.api.model.customer.ChatEntry;
import net.helpscout.api.model.customer.EmailEntry;
import net.helpscout.api.model.customer.PhoneEntry;
import net.helpscout.api.model.customer.SocialProfileEntry;
import net.helpscout.api.model.customer.WebsiteEntry;

@Data
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

    private Date createdAt;
    private Date modifiedAt;

    private String background;
    private Address address;
    private List<SocialProfileEntry> socialProfiles;
    private List<EmailEntry> emails;
    private List<PhoneEntry> phones;
    private List<ChatEntry> chats;
    private List<WebsiteEntry> websites;

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