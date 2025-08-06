package org.example.metadata.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.metadata.course.CourseMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table("courses")
public class CourseEntity {

    @Id
    private Long id;

    @Column("title")
    private String title;

    @Column("description")
    private String description;

    @Column("category")
    private CourseCategory category;

    @Column("creator_id")
    private Long creatorId;

    @Column("teacher_ids")
    private String teacherIds;

    @Column("student_ids")
    private String studentIds;

    @Column("start_date")
    private LocalDate startDate;

    @Column("end_date")
    private LocalDate endDate;

    @Column("rating")
    private Double rating;

    @Column("level")
    private CourseLevel level;

    @Column("thumbnail")
    private String thumbnail;

    @Column("color")
    private String color;

    @Column("skills")
    private String skills;

    @Column("price")
    private Double price;

    @Column("published")
    private Boolean published;


    public CourseResponse toResponse() {
        return CourseMapper.toResponse(this);
    }
}
