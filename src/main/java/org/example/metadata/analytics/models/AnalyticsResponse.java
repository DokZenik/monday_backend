package org.example.metadata.analytics.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.metadata.assignment.model.AssignmentResponse;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnalyticsResponse {

    private Long courseId;

    private String courseTitle;

    private List<AssignmentResponse> assignments;

    private List<RowItem> grades;
}
