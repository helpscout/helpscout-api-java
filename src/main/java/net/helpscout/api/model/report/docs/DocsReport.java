package net.helpscout.api.model.report.docs;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocsReport {

    TimeRangeStats current;
    TimeRangeStats previous;
    List<SearchStats> popularSearches;
    List<SearchStats> failedSearches;
    List<ArticleStats> topArticles;
    List<ArticleStats> topCategories;
    DeltaStats deltas;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class TimeRangeStats {

        Integer visitors;
        Double browseAction;
        Double sentAnEmailResult;
        Double foundAnAnswerResult;
        Double searchAction;
        Double failedResult;
        Double docsViewedPerVisit;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class SearchStats {

        Integer count;
        String id;
        Integer results;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class ArticleStats {

        Integer count;
        String id;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class DeltaStats {

        Double failedResult;
        Double docsViewedPerVisit;
        Double foundAnAnswerResult;
        Double visitors;
        Double browseAction;
        Double searchAction;
        Double sentAnEmailResult;
    }
}