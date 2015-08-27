package net.helpscout.api.model.report.common;

import java.util.Date;

import lombok.Data;
import net.helpscout.api.cbo.ConversationType;

@Data
public class Rating {

    private Integer number;
    private Integer id;
    private ConversationType type;
    private Integer threadid;
    private Date threadCreatedAt;
    private Integer ratingId;
    private Integer ratingCustomerId;
    private String ratingComments;
    private Date ratingCreatedAt;
    private String ratingCustomerName;
    private Integer ratingUserId;
    private String ratingUserName;
}