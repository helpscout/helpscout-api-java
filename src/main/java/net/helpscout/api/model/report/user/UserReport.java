package net.helpscout.api.model.report.user;

import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.helpscout.api.model.report.common.Tag;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserReport {

    List<Tag> filterTags;
    User user;
    TimeRangeStats current;
    TimeRangeStats previous;
    MultipleTimeRangeStats deltas;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class User {

        Date createdAt;
        String photoUrl;
        Boolean hasPhoto;
        String name;
        Integer totalCustomersHelped;
        Integer id;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class TimeRangeStats {

        Date startDate;
        Date endDate;
        Integer totalDays;
        Integer resolved;
        Integer conversationsCreated;
        Integer closed;
        List<Rating> ratings;
        Integer totalReplies;
        Integer resolvedOnFirstReply;
        Double percentResolvedOnFirstReply;
        Double repliesToResolve;
        Double handleTime;
        Double happinessScore;
        Double responseTime;
        Double resolutionTime;
        Double repliesPerDay;
        Integer customersHelped;
        Integer totalConversations;
        Double conversationsPerDay;
        Integer busiestDay;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class MultipleTimeRangeStats {

        Double totalConversations;
        Double customersHelped;
        Double happinessScore;
        Double repliesPerDay;
        Double resolvedOnFirstReply;
        Double handleTime;
        Double conversationsPerDay;
        Double resolved;
        Double repliesToResolve;
        Double activeConversations;
        Double totalReplies;
        Double closed;
        Double responseTime;
        Double resolutionTime;
        Double conversationsCreated;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class Rating {

        Double repliesSent;
        Double firstResponseTime;
        Double resolveTime;
        String ratingId;
        Double responseTime;
    }
}