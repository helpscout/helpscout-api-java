package net.helpscout.api.model.customfield;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateCustomFieldResponse extends BaseCustomFieldResponse<Date> {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter ISO_DATE_FORMAT = DateTimeFormat.forPattern(DATE_TIME_FORMAT).withZoneUTC();

    public DateCustomFieldResponse() {
        super(CustomFieldType.DATE);
    }

    public DateCustomFieldResponse(Date date) {
        this();
        setValue(date);
    }

    @Override
    protected String typedToStringValue(Date value) {
        return ISO_DATE_FORMAT.print(new LocalDate(value));
    }

    @Override
    protected Date getTypedValue(String stringValue) {
        if(StringUtils.isBlank(stringValue)) {
            return null;
        } else {
            return ISO_DATE_FORMAT.parseLocalDate(stringValue).toDate();
        }
    }

}
