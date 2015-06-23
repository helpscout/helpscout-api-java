package net.helpscout.api.model.report.happiness;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.helpscout.api.model.report.common.Tag;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HappinessReport {

    List<Tag> filterTags;
    TimeRangeStats current;
    TimeRangeStats previous;
    MultipleTimeRangeStats deltas;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class TimeRangeStats {

        Double okay;
        Integer notGoodCount;
        Integer totalCustomers;
        Double happinessScore;
        Integer totalCustomersWithRatings;
        Integer ratingsCount;
        Double ratingsPercent;
        Double notGood;
        Double great;
        Integer greatCount;
        Integer okayCount;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class MultipleTimeRangeStats {

        Double okay;
        Double notGoodCount;
        Double happinessScore;
        Double notGood;
        Double great;
        Double greatCount;
        Double okayCount;
    }
}