package net.helpscout.api.model.customfield;

public interface CustomFieldResponse<V> {

    Long getFieldId();
    void setFieldId(Long id);

    String getName();
    void setName(String name);

    V getValue();
    void setValue(V value);

    String getStringValue();
    void fromStringValue(String value);

    CustomFieldType getType();
}
