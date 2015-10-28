package net.helpscout.api.model.customfield;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomFieldOption {
    private Long id;
    private String label;
    private Integer order;
}
