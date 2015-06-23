package net.helpscout.api.model.report.common;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tag {

    String name;
    Long id;
    String color;
}