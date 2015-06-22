package net.helpscout.api.model.report.productivity;

import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import net.helpscout.api.model.report.common.Tag;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductivityReport {

    List<Tag> filterTags;
    TimeRangeStats current;
    TimeRangeStats previous;
    MultipleTimeRangeStats deltas;
    ProductivityStats responseTime;
    ProductivityStats handleTime;
    ProductivityStats firstResponseTime;
    RepliesToResolveStats repliesToResolve;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class TimeRangeStats {

        Date startDate;
        Date endDate;
        Integer totalConversations;
        Double resolutionTime;
        Double repliesToResolve;
        Double responseTime;
        Double firstResponseTime;
        Integer resolved;
        Integer resolvedOnFirstReply;
        Integer closed;
        Integer repliesSent;
        Integer handleTime;
        Double percentResolvedOnFirstReply;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class MultipleTimeRangeStats {

        Double firstResponseTime;
        Double handleTime;
        Double repliesSent;
        Double responseTime;
        Double totalConversations;
        Double repliesToResolve;
        Double closed;
        Double resolvedOnFirstReply;
        Double resolutionTime;
        Double resolved;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class ProductivityStats {

        Integer count;
        Integer previousCount;
        List<DrillDownStats> ranges;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class RepliesToResolveStats {

        Integer count;
        Integer previousCount;
        List<RepliesToResolveDrillDownStats> ranges;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class DrillDownStats {

        Integer id;
        Integer count;
        Integer previousCount;
        Double percent;
        Double previousPercent;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    public class RepliesToResolveDrillDownStats extends DrillDownStats {

        Double resolutionTime;
    }
}