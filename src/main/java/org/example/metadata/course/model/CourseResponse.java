package org.example.metadata.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseResponse {

    private Long id;

    private String title;

    private Long teacherId;

    private String groupName;
}
