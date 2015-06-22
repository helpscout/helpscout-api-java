package net.helpscout.api.model.report.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserHappiness {

    TimeRangeStats current;
    TimeRangeStats previous;
    MultipleTimeRangeStats deltas;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class TimeRangeStats {

        Double great;
        Double okay;
        Double notGood;
        Double greatCount;
        Integer okayCount;
        Integer notGoodCount;
        Integer totalCustomers;
        Integer totalCustomersWithRatings;
        Integer ratingsCount;
        Double ratingsPercent;
        Double happinessScore;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class MultipleTimeRangeStats {

        Double great;
        Double okay;
        Double notGood;
        Double greatCount;
        Double okayCount;
        Double notGoodCount;
        Double happinessScore;
    }
}