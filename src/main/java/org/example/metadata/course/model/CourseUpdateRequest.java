package org.example.metadata.course.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.metadata.course.CourseMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseUpdateRequest {

    @NotBlank(message = "Title can't be null")
    private String title;

    private String description;

    @NotNull(message = "Category can't be null")
    private CourseCategory category;

    @NotNull(message = "Creator id can't be null")
    @PositiveOrZero(message = "Creator id shouldn't be negative")
    private Long creatorId;

    @NotNull(message = "Teacher ids can't be null")
    private Set<Long> teacherIds;

    private Set<Long> studentIds;

    @NotNull(message = "Start date can't be null")
    @FutureOrPresent(message = "Start date can't be less than today")
    private LocalDate startDate;

    @NotNull(message = "End date can't be null")
    @FutureOrPresent(message = "End date can't be less than today")
    private LocalDate endDate;

    @NotNull(message = "Rating can't be null")
    @PositiveOrZero(message = "Rating shouldn't be negative")
    private Double rating;

    @NotNull(message = "Course level can't be null")
    private CourseLevel level;

    private String thumbnail;

    @NotBlank(message = "Color can't be null")
    private String color;

    @NotEmpty(message = "Skills can't be empty")
    private List<String> skills;

    @NotNull(message = "Price can't be null")
    @PositiveOrZero(message = "Price can't be negative")
    private Double price;

    @NotNull(message = "Published can't be null")
    private Boolean published;

    public CourseEntity toEntity(Long courseId) {
        return CourseMapper.toEntity(this, courseId);
    }
}
