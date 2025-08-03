package org.example.metadata.analytics.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.metadata.grades.model.GradeResponse;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RowItem {

    private Long studentId;

    private String studentName;

    private List<GradeResponse> grades;

}
