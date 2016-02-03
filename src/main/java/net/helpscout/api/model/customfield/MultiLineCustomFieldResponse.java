package net.helpscout.api.model.customfield;

import lombok.ToString;

@ToString(callSuper = true)
public class MultiLineCustomFieldResponse extends BaseCustomFieldResponse<String> {

    public MultiLineCustomFieldResponse() {
        super(CustomFieldType.MULTI_LINE);
    }

    public MultiLineCustomFieldResponse(String value) {
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
