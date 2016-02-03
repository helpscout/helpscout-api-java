package net.helpscout.api.model.customfield;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class DropDownCustomFieldResponse extends BaseCustomFieldResponse<Long> {

    public DropDownCustomFieldResponse() {
        super(CustomFieldType.DROPDOWN);
    }

    public DropDownCustomFieldResponse(Long optionId) {
        this();
        setValue(optionId);
    }

    public DropDownCustomFieldResponse(CustomFieldOption customFieldOption) {
        this();
        setValue(customFieldOption.getId());
    }

    @Override
    protected String typedToStringValue(Long value) {
        return String.valueOf(value);
    }

    @Override
    protected Long getTypedValue(String stringValue) {
        if(StringUtils.isBlank(stringValue)) {
            return null;
        } else {
            return NumberUtils.createLong(stringValue);
        }
    }
}
