import net.helpscout.api.ApiClient;
import net.helpscout.api.model.Customer;
import net.helpscout.api.model.customer.*;

import java.util.ArrayList;
import java.util.List;

public class CreateCustomer {

    public static void main(String[] args) throws Exception {

        Customer customer = new Customer();
        customer.setFirstName("John"); // Required
        customer.setLastName("Appleseed"); // Required
        customer.setOrganization("Acme, Inc");
        customer.setJobTitle("CEO and Co-Founder");
        customer.setLocation("San Francisco, CA");
        customer.setBackground("I've worked with John before and he's really great.");

        // Address
        // ~~~~~
        Address address = new Address();

        List<String> lines = new ArrayList<String>();
        lines.add("500 Main St");
        lines.add("Suite 23");

        address.setLines(lines);
        address.setCity("San Francisco");
        address.setState("CA");
        address.setPostalCode("94103");
        address.setCountry("US");
        customer.setAddress(address);

        // Phones
        // ~~~~~
        List<PhoneEntry> phones = new ArrayList<PhoneEntry>();

        PhoneEntry phoneWork = new PhoneEntry();
        phoneWork.setValue("800-555-1212");
        phoneWork.setLocation(PhoneEntry.Location.Work.getLabel());

        PhoneEntry phoneHome = new PhoneEntry();
        phoneHome.setValue("800-123-1234");
        phoneHome.setLocation(PhoneEntry.Location.Home.getLabel());

        phones.add(phoneWork);
        phones.add(phoneHome);

        customer.setPhones(phones);

        // Emails: at least one email entry must be specified
        // ~~~~~
        List<EmailEntry> emails = new ArrayList<EmailEntry>();

        EmailEntry emailHome = new EmailEntry();
        emailHome.setValue("john@example.com");
        emailHome.setLocation(EmailEntry.Location.Home.getLabel());

        EmailEntry emailWork = new EmailEntry();
        emailWork.setValue("appleseed@example.com");
        emailWork.setLocation(EmailEntry.Location.Work.getLabel());

        emails.add(emailHome);
        emails.add(emailWork);

        customer.setEmails(emails);

        // Social Profiles
        // ~~~~~
        List<SocialProfileEntry> socialProfiles = new ArrayList<SocialProfileEntry>();

        SocialProfileEntry facebook = new SocialProfileEntry();
        facebook.setValue("https://facebook.com/john.appleseed");
        facebook.setType(SocialProfileEntry.Type.Facebook.getLabel());

        SocialProfileEntry twitter = new SocialProfileEntry();
        twitter.setValue("https://twitter.com/johnappleseed");
        twitter.setType(SocialProfileEntry.Type.Twitter.getLabel());

        socialProfiles.add(facebook);
        socialProfiles.add(twitter);
        customer.setSocialProfiles(socialProfiles);

        // Chats
        // ~~~~~
        List<ChatEntry> chats = new ArrayList<ChatEntry>();

        ChatEntry chatAim = new ChatEntry();
        chatAim.setValue("jappleseed");
        chatAim.setType(ChatEntry.Type.Aim.getLabel());

        ChatEntry chatGTalk = new ChatEntry();
        chatGTalk.setValue("jappleseed@gmail.com");
        chatGTalk.setType(ChatEntry.Type.GTalk.getLabel());

        chats.add(chatAim);
        chats.add(chatGTalk);

        customer.setChats(chats);

        // Websites
        // ~~~~~~
        List<WebsiteEntry> websites = new ArrayList<WebsiteEntry>();

        WebsiteEntry web1 = new WebsiteEntry();
        web1.setValue("http://www.johnappleseed.com");

        WebsiteEntry web2 = new WebsiteEntry();
        web2.setValue("http://www.appleseed.com");

        websites.add(web1);
        websites.add(web2);

        customer.setWebsites(websites);

        // Create the customer
        // ~~~~~
        ApiClient client = ApiClient.getInstance();
        client.setKey("example-key");
        client.createCustomer(customer);
        System.out.println("Customer id: " + customer.getId());
    }
}
