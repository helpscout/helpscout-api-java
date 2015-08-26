package net.helpscout.api.model.report.team;

import java.util.Date;
import java.util.List;

import lombok.Data;
import net.helpscout.api.model.report.common.Tag;

@Data
public class TeamReport {

    private List<Tag> filterTags;
    private TimeRangeStats current;
    private TimeRangeStats previous;
    private MultipleTimeRangeStats deltas;
    private List<UserStats> users;

    @Data
    public class TimeRangeStats {

        private Date startDate;
        private Date endDate;
        private Integer customersHelped;
        private Integer closed;
        private Integer totalReplies;
        private Integer totalUsers;
        private Integer totalDays;
        private Double repliesPerDayPerUser;
        private Double repliesPerDay;
        private Double resolvedPerDay;
    }

    @Data
    public class MultipleTimeRangeStats {

        private Double repliesPerDay;
        private Double totalUsers;
        private Double totalReplies;
        private Double customersHelped;
        private Double repliesPerDayPerUser;
        private Double closed;
    }

    @Data
    public class UserStats {

        private Double handleTime;
        private Integer replies;
        private Double happinessScore;
        private Integer customersHelped;
        private Double previousHandleTime;
        private String name;
        private Integer previousCustomersHelped;
        private Double previousHappinessScore;
        private String user;
        private Integer previousReplies;
    }
}