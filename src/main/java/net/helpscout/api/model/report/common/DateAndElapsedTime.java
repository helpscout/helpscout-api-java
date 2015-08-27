package net.helpscout.api.model.report.common;

import java.util.Date;

import lombok.Data;

@Data
public class DateAndElapsedTime {

    private Date date;
    private Double time;
}