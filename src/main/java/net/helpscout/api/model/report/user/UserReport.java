package net.helpscout.api.model.report.user;

import java.util.Date;
import java.util.List;

import lombok.Data;
import net.helpscout.api.model.report.common.Tag;

@Data
public class UserReport {

    private List<Tag> filterTags;
    private User user;
    private TimeRangeStats current;
    private TimeRangeStats previous;
    private MultipleTimeRangeStats deltas;

    @Data
    public class User {

        private Date createdAt;
        private String photoUrl;
        private Boolean hasPhoto;
        private String name;
        private Integer totalCustomersHelped;
        private Integer id;
    }

    @Data
    public class TimeRangeStats {

        private Date startDate;
        private Date endDate;
        private Integer totalDays;
        private Integer resolved;
        private Integer conversationsCreated;
        private Integer closed;
        private Integer totalReplies;
        private Integer resolvedOnFirstReply;
        private Double percentResolvedOnFirstReply;
        private Double repliesToResolve;
        private Double handleTime;
        private Double happinessScore;
        private Double responseTime;
        private Double resolutionTime;
        private Double repliesPerDay;
        private Integer customersHelped;
        private Integer totalConversations;
        private Double conversationsPerDay;
        private Integer busiestDay;
    }

    @Data
    public class MultipleTimeRangeStats {

        private Double totalConversations;
        private Double customersHelped;
        private Double happinessScore;
        private Double repliesPerDay;
        private Double resolvedOnFirstReply;
        private Double handleTime;
        private Double conversationsPerDay;
        private Double resolved;
        private Double repliesToResolve;
        private Double activeConversations;
        private Double totalReplies;
        private Double closed;
        private Double responseTime;
        private Double resolutionTime;
        private Double conversationsCreated;
    }
}