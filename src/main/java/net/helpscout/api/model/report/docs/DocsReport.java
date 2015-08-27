package net.helpscout.api.model.report.docs;

import java.util.List;

import lombok.Data;

@Data
public class DocsReport {

    private TimeRangeStats current;
    private TimeRangeStats previous;
    private List<SearchStats> popularSearches;
    private List<SearchStats> failedSearches;
    private List<ArticleStats> topArticles;
    private List<ArticleStats> topCategories;
    private DeltaStats deltas;

    @Data
    public class TimeRangeStats {

        private Integer visitors;
        private Double browseAction;
        private Double sentAnEmailResult;
        private Double foundAnAnswerResult;
        private Double searchAction;
        private Double failedResult;
        private Double docsViewedPerVisit;
    }

    @Data
    public class SearchStats {

        private Integer count;
        private String id;
        private Integer results;
    }

    @Data
    public class ArticleStats {

        private Integer count;
        private String id;
    }

    @Data
    public class DeltaStats {

        private Double failedResult;
        private Double docsViewedPerVisit;
        private Double foundAnAnswerResult;
        private Double visitors;
        private Double browseAction;
        private Double searchAction;
        private Double sentAnEmailResult;
    }
}