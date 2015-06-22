package net.helpscout.api.model.report.user;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.helpscout.api.cbo.Status;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationStats {

    Integer number;
    Integer responseTime;
    Integer firstResponseTime;
    Integer resolveTime;
    Integer repliesSent;
    Integer id;
    Status status;
    List<Customer> customers;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class Customer {

        Integer id;
        String name;
    }
}