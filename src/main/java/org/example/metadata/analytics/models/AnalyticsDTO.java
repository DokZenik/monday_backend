package org.example.metadata.analytics.models;

import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnalyticsDTO {
    private Long studentId;

    private String studentName;

    private ArrayNode grades;
}
