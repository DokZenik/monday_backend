package org.example.metadata.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateRequest {
    private String title;

    private Long teacherId;

    private String groupName;

    public CourseEntity toEntity() {
        return new CourseEntity(null, title, teacherId, groupName);
    }
}
