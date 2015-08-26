package net.helpscout.api.model.report.common;

import java.util.List;

import lombok.Data;

@Data
public class DatesAndElapsedTimes {

    private List<DateAndElapsedTime> current;
    private List<DateAndElapsedTime> previous;
}