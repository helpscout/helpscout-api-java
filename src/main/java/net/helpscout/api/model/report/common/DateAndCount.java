package net.helpscout.api.model.report.common;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DateAndCount {

    Date date;
    Integer count;
}