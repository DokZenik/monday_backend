package org.example.metadata.courses;

import org.example.metadata.course.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CourseHandlerTestHelper {

    public CourseCreateRequest getCreateRequest() {
        return new CourseCreateRequest(
                "My course",
                "Course description",
                CourseCategory.MATHEMATICS,
                1L,
                Set.of(1L, 2L, 3L),
                Set.of(4L, 5L, 6L),
                LocalDate.now(),
                LocalDate.now().plusWeeks(12),
                4.75,
                CourseLevel.ADVANCED,
                "/placeholder.svg?height=200&width=300&text=Math",
                "from-blue-500 to-purple-600",
                List.of("Calculus", "Linear Algebra", "Statistics", "Problem Solving"),
                299.0,
                false
                );
    }

    public CourseUpdateRequest getUpdateRequest() {
        return new CourseUpdateRequest(
                "My course updated",
                "Course description",
                CourseCategory.MATHEMATICS,
                1L,
                Set.of(1L, 2L, 3L),
                Set.of(4L, 5L, 6L),
                LocalDate.now(),
                LocalDate.now().plusWeeks(12),
                5.0,
                CourseLevel.ADVANCED,
                "/placeholder.svg?height=200&width=300&text=Math",
                "from-blue-500 to-purple-600",
                List.of("Calculus", "Linear Algebra", "Statistics", "Game theory"),
                599.00,
                true
        );
    }

    public CourseStudentsRequest getStudentsRequest(){
        return new CourseStudentsRequest(Set.of(4L, 6L, 7L));
    }
}
