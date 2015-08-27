import net.helpscout.api.ApiClient;
import net.helpscout.api.model.Customer;
import net.helpscout.api.model.customer.EmailEntry;

import java.util.ArrayList;
import java.util.List;

public class UpdateCustomer {

    public static void main(String[] args) throws Exception {

        ApiClient client = ApiClient.getInstance();
        client.setKey("example-key");

        Customer customer = client.getCustomer(1L);
        if (customer == null) {
            throw new Exception("Customer not found");
        }

        customer.setFirstName("John");
        customer.setLastName("Appleseed");

        // Update emails. This example can be used for other customer
        // entries as well (social profiles, websites, chats, phones)
        // ~~~~~
        for (EmailEntry email : customer.getEmails()) {
            if (email.getValue().equalsIgnoreCase("john@example.com")) {
                // Update an email
                email.setValue("test@example.com");
            } else if (email.getValue().equalsIgnoreCase("appleseed@example.com")) {
                // Delete an email by prefixing the id with a minus sign
                email.setId(-1L);
            }
        }

        // Add a new email
        EmailEntry newEmail = new EmailEntry();
        newEmail.setValue("test2@example.com");
        newEmail.setLocation(EmailEntry.Location.Home.getLabel());
        customer.getEmails().add(newEmail);

        // Update address
        // ~~~~~
        List<String> lines = new ArrayList<String>();
        lines.add("100 Maple St");
        lines.add("Suite 22");

        customer.getAddress().setLines(lines);
        customer.getAddress().setCity("Los Angeles");
        customer.getAddress().setState("CA");

        client.updateCustomer(customer);
    }
}
