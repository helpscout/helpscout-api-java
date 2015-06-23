package net.helpscout.api.model.report.conversations;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DayStats {

    Integer day;
    Integer hour;
    Integer count;
}