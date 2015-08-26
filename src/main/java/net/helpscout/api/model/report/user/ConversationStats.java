package net.helpscout.api.model.report.user;

import java.util.List;

import lombok.Data;
import net.helpscout.api.cbo.Status;

@Data
public class ConversationStats {

    private Integer number;
    private Integer responseTime;
    private Integer firstResponseTime;
    private Integer resolveTime;
    private Integer repliesSent;
    private Integer id;
    private Status status;
    private List<Customer> customers;

    @Data
    public class Customer {

        private Integer id;
        private String name;
    }
}