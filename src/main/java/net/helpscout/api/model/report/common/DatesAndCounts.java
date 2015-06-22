package net.helpscout.api.model.report.common;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DatesAndCounts {

    List<DateAndCount> current;
    List<DateAndCount> previous;
}