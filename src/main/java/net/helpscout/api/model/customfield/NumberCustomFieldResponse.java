package net.helpscout.api.model.customfield;

import lombok.ToString;
import org.apache.commons.lang3.math.NumberUtils;

@ToString(callSuper = true)
public class NumberCustomFieldResponse extends BaseCustomFieldResponse<Number> {

    public NumberCustomFieldResponse() {
        super(CustomFieldType.NUMBER);
    }

    public NumberCustomFieldResponse(Number number) {
        this();
        setValue(number);
    }

    @Override
    protected String typedToStringValue(Number value) {
        return String.valueOf(value);
    }

    @Override
    protected Number getTypedValue(String stringValue) {
        return NumberUtils.createNumber(stringValue);
    }
}
