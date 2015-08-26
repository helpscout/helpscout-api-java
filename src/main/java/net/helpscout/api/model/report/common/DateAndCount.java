package net.helpscout.api.model.report.common;

import java.util.Date;

import lombok.Data;

@Data
public class DateAndCount {

    private Date date;
    private Integer count;
}