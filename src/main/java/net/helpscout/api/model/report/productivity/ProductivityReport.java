package net.helpscout.api.model.report.productivity;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.helpscout.api.model.report.common.Tag;

@Data
public class ProductivityReport {

    private List<Tag> filterTags;
    private TimeRangeStats current;
    private TimeRangeStats previous;
    private MultipleTimeRangeStats deltas;
    private ProductivityStats responseTime;
    private ProductivityStats handleTime;
    private ProductivityStats firstResponseTime;
    private RepliesToResolveStats repliesToResolve;

    @Data
    public class TimeRangeStats {

        private Date startDate;
        private Date endDate;
        private Integer totalConversations;
        private Double resolutionTime;
        private Double repliesToResolve;
        private Double responseTime;
        private Double firstResponseTime;
        private Integer resolved;
        private Integer resolvedOnFirstReply;
        private Integer closed;
        private Integer repliesSent;
        private Integer handleTime;
        private Double percentResolvedOnFirstReply;
    }

    @Data
    public class MultipleTimeRangeStats {

        private Double firstResponseTime;
        private Double handleTime;
        private Double repliesSent;
        private Double responseTime;
        private Double totalConversations;
        private Double repliesToResolve;
        private Double closed;
        private Double resolvedOnFirstReply;
        private Double resolutionTime;
        private Double resolved;
    }

    @Data
    public class ProductivityStats {

        private Integer count;
        private Integer previousCount;
        private List<DrillDownStats> ranges;
    }

    @Data
    public class RepliesToResolveStats {

        private Integer count;
        private Integer previousCount;
        private List<RepliesToResolveDrillDownStats> ranges;
    }

    @Data
    public class DrillDownStats {

        private Integer id;
        private Integer count;
        private Integer previousCount;
        private Double percent;
        private Double previousPercent;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    public class RepliesToResolveDrillDownStats extends DrillDownStats {

        private Double resolutionTime;
    }
}