package org.example.metadata.analytics.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnalyticsRequest {

    @NotNull(message = "Course id can't be null")
    @PositiveOrZero(message = "Course id shouldn't be negative")
    private Long courseId;

    @NotBlank(message = "Group name can't be null")
    private String groupName;
}
