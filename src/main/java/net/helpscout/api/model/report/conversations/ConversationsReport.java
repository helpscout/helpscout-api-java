package net.helpscout.api.model.report.conversations;

import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.helpscout.api.model.report.common.Tag;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationsReport {

    List<Tag> filterTags;
    DayStats busiestDay;
    Integer busyTimeStart;
    Integer busyTimeEnd;
    TimeRangeStats current;
    TimeRangeStats previous;
    MultipleTimeRangeStats delta;
    TagStats tags;
    CustomerStats customers;
    SavedReplyStats replies;
    WorkflowStats workflows;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class TimeRangeStats {

        Date startDate;
        Date endDate;
        Integer totalConversations;
        Integer conversationsCreated;
        Integer newConversations;
        Integer customers;
        Integer conversationsPerDay;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class MultipleTimeRangeStats {

        Double newConversations;
        Double totalConversations;
        Double customers;
        Double conversationsCreated;
        Double conversationsPerDay;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class TagStats {

        Integer count;
        List<TagStat> top;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class TagStat {

        String name;
        Integer id;
        Integer count;
        Integer previousCount;
        Double percent;
        Double previousPercent;
        Double deltaPercent;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class CustomerStats {

        Integer count;
        List<CustomerStat> top;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class CustomerStat {

        String name;
        Integer id;
        Integer count;
        Double previousCount;
        Double percent;
        Double previousPercent;
        Double deltaPercent;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class SavedReplyStats {

        Integer count;
        List<SavedReplyStat> replies;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class SavedReplyStat {

        String name;
        Integer id;
        Integer mailboxId;
        Integer count;
        Integer previousCount;
        Double percent;
        Double previousPercent;
        Double deltaPercent;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class WorkflowStats {

        Integer count;
        List<WorkflowStat> top;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class WorkflowStat {

        String name;
        Integer id;
        Integer count;
        Integer previousCount;
        Double percent;
        Double previousPercent;
        Double deltaPercent;
    }
}