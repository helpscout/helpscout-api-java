package net.helpscout.api.model.report.user;

import java.util.List;

import lombok.Data;
import net.helpscout.api.model.report.common.Tag;

@Data
public class UserHappiness {

    private List<Tag> filterTags;
    private TimeRangeStats current;
    private TimeRangeStats previous;
    private MultipleTimeRangeStats deltas;

    @Data
    public class TimeRangeStats {

        private Double great;
        private Double okay;
        private Double notGood;
        private Double greatCount;
        private Integer okayCount;
        private Integer notGoodCount;
        private Integer totalCustomers;
        private Integer totalCustomersWithRatings;
        private Integer ratingsCount;
        private Double ratingsPercent;
        private Double happinessScore;
    }

    @Data
    public class MultipleTimeRangeStats {

        private Double great;
        private Double okay;
        private Double notGood;
        private Double greatCount;
        private Double okayCount;
        private Double notGoodCount;
        private Double happinessScore;
    }
}