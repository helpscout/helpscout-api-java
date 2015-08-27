package net.helpscout.api.model.report.happiness;

import java.util.List;

import lombok.Data;
import net.helpscout.api.model.report.common.Tag;

@Data
public class HappinessReport {

    private List<Tag> filterTags;
    private TimeRangeStats current;
    private TimeRangeStats previous;
    private MultipleTimeRangeStats deltas;

    @Data
    public class TimeRangeStats {

        private Double okay;
        private Integer notGoodCount;
        private Integer totalCustomers;
        private Double happinessScore;
        private Integer totalCustomersWithRatings;
        private Integer ratingsCount;
        private Double ratingsPercent;
        private Double notGood;
        private Double great;
        private Integer greatCount;
        private Integer okayCount;
    }

    @Data
    public class MultipleTimeRangeStats {

        private Double okay;
        private Double notGoodCount;
        private Double happinessScore;
        private Double notGood;
        private Double great;
        private Double greatCount;
        private Double okayCount;
    }
}