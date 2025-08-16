package org.example.metadata.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class CourseStudentsRequest {

    private Set<Long> studentIds;
}
