package net.helpscout.api.model.customfield;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SingleLineCustomFieldResponse extends BaseCustomFieldResponse<String> {

    public SingleLineCustomFieldResponse() {
        super(CustomFieldType.SINGLE_LINE);
    }

    public SingleLineCustomFieldResponse(String value) {
        this();
        setValue(value);
    }

    @Override
    protected String typedToStringValue(String value) {
        return value;
    }

    @Override
    protected String getTypedValue(String stringValue) {
        return stringValue;
    }

}
