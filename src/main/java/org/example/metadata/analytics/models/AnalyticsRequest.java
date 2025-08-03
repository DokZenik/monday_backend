package org.example.metadata.analytics.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnalyticsRequest {

    private Long courseId;

    private String groupName;
}
