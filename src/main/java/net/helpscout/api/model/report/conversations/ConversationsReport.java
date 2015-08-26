package net.helpscout.api.model.report.conversations;

import java.util.Date;
import java.util.List;

import lombok.Data;
import net.helpscout.api.model.report.common.Tag;

@Data
public class ConversationsReport {

    private List<Tag> filterTags;
    private DayStats busiestDay;
    private Integer busyTimeStart;
    private Integer busyTimeEnd;
    private TimeRangeStats current;
    private TimeRangeStats previous;
    private MultipleTimeRangeStats delta;
    private TagStats tags;
    private CustomerStats customers;
    private SavedReplyStats replies;
    private WorkflowStats workflows;

    @Data
    public class TimeRangeStats {

        private Date startDate;
        private Date endDate;
        private Integer totalConversations;
        private Integer conversationsCreated;
        private Integer newConversations;
        private Integer customers;
        private Integer conversationsPerDay;
    }

    @Data
    public class MultipleTimeRangeStats {

        private Double newConversations;
        private Double totalConversations;
        private Double customers;
        private Double conversationsCreated;
        private Double conversationsPerDay;
    }

    @Data
    public class TagStats {

        private Integer count;
        private List<TagStat> top;
    }

    @Data
    public class TagStat {

        private String name;
        private Integer id;
        private Integer count;
        private Integer previousCount;
        private Double percent;
        private Double previousPercent;
        private Double deltaPercent;
    }

    @Data
    public class CustomerStats {

        private Integer count;
        private List<CustomerStat> top;
    }

    @Data
    public class CustomerStat {

        private String name;
        private Integer id;
        private Integer count;
        private Double previousCount;
        private Double percent;
        private Double previousPercent;
        private Double deltaPercent;
    }

    @Data
    public class SavedReplyStats {

        private Integer count;
        private List<SavedReplyStat> replies;
    }

    @Data
    public class SavedReplyStat {

        private String name;
        private Integer id;
        private Integer mailboxId;
        private Integer count;
        private Integer previousCount;
        private Double percent;
        private Double previousPercent;
        private Double deltaPercent;
    }

    @Data
    public class WorkflowStats {

        private Integer count;
        private List<WorkflowStat> top;
    }

    @Data
    public class WorkflowStat {

        private String name;
        private Integer id;
        private Integer count;
        private Integer previousCount;
        private Double percent;
        private Double previousPercent;
        private Double deltaPercent;
    }
}