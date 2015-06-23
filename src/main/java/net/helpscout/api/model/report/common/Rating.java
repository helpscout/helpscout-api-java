package net.helpscout.api.model.report.common;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.helpscout.api.cbo.ConversationType;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rating {

    Integer number;
    Integer id;
    ConversationType type;
    Integer threadid;
    Date threadCreatedAt;
    Integer ratingId;
    Integer ratingCustomerId;
    String ratingComments;
    Date ratingCreatedAt;
    String ratingCustomerName;
    Integer ratingUserId;
    String ratingUserName;
}