package net.helpscout.api.model.customfield;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomField {
    private Long id;
    private String fieldName;
    private CustomFieldType fieldType;
    private boolean required;
    private Integer order;
    private List<CustomFieldOption> options;
}
