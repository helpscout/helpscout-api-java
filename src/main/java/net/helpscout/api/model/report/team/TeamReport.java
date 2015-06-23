package net.helpscout.api.model.report.team;

import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.helpscout.api.model.report.common.Tag;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamReport {

    List<Tag> filterTags;
    TimeRangeStats current;
    TimeRangeStats previous;
    MultipleTimeRangeStats deltas;
    List<UserStats> users;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class TimeRangeStats {

        Date startDate;
        Date endDate;
        Integer customersHelped;
        Integer closed;
        Integer totalReplies;
        Integer totalUsers;
        Integer totalDays;
        Double repliesPerDayPerUser;
        Double repliesPerDay;
        Double resolvedPerDay;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class MultipleTimeRangeStats {

        Double repliesPerDay;
        Double totalUsers;
        Double totalReplies;
        Double customersHelped;
        Double repliesPerDayPerUser;
        Double closed;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class UserStats {

        Double handleTime;
        Integer replies;
        Double happinessScore;
        Integer customersHelped;
        Double previousHandleTime;
        String name;
        Integer previousCustomersHelped;
        Double previousHappinessScore;
        String user;
        Integer previousReplies;
    }
}