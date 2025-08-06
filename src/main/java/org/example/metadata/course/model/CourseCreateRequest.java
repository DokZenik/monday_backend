package org.example.metadata.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.metadata.course.CourseMapper;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateRequest {

    private String title;

    private String description;

    private CourseCategory category;

    private Long creatorId;

    private List<Long> teacherIds;

    private List<Long> studentIds;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double rating;

    private CourseLevel level;

    private String thumbnail;

    private String color;

    private List<String> skills;

    private Double price;

    private Boolean published;

    public CourseEntity toEntity() {
        return CourseMapper.toEntity(this);
    }
}
