package net.helpscout.api.model.customfield;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString(doNotUseGetters = true)
public abstract class BaseCustomFieldResponse<V> implements CustomFieldResponse<V> {

    protected BaseCustomFieldResponse(CustomFieldType customFieldType) {
        this.type = customFieldType;
    }

    private Long fieldId;
    private String name;
    private CustomFieldType type;
    private V value;

    protected abstract String typedToStringValue(V value);

    protected abstract V getTypedValue(String stringValue);

    @Override
    public String getStringValue() {
        return typedToStringValue(value);
    }

    @Override
    public void fromStringValue(String value) {
        this.value = getTypedValue(value);
    }

}
