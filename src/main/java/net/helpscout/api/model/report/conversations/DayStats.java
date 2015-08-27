package net.helpscout.api.model.report.conversations;

import lombok.Data;

@Data
public class DayStats {

    private Integer day;
    private Integer hour;
    private Integer count;
}