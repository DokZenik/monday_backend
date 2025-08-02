package org.example.metadata.course.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class CoursesResponse {

    @JsonProperty("_embedded")
    Embedded embedded;

    @Value
    private static class Embedded {
        List<CourseResponse> courses;
    }

    public CoursesResponse(List<CourseResponse> courses) {
        this.embedded = new Embedded(courses);
    }
}
