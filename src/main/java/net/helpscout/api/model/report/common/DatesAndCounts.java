package net.helpscout.api.model.report.common;

import java.util.List;

import lombok.Data;

@Data
public class DatesAndCounts {

    private List<DateAndCount> current;
    private List<DateAndCount> previous;
}