package org.example.metadata.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("courses")
public class CourseEntity {

    @Id
    private Long id;

    @Column("title")
    private String title;

    @Column("teacher_id")
    private Long teacherId;

    @Column("group_name")
    private String groupName;

    public CourseResponse toResponse() {
        return new CourseResponse(id, title, teacherId,  groupName);
    }
}
