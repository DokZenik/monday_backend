package org.example.metadata.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseResponse {

    private Long id;

    private String title;

    private String description;

    private CourseCategory category;

    private Long creatorId;

    private Set<Long> teacherIds;

    private Set<Long> studentIds;

    private LocalDate startDate;

    private LocalDate endDate;

    private String duration;

    private Double rating;

    private CourseLevel level;

    private String thumbnail;

    private String color;

    private List<String> skills;

    private Double price;

    private Boolean published;
}
