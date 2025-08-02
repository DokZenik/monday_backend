package org.example.metadata.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class AssignmentsResponse {

    @JsonProperty("_embedded")
    Embedded embedded;

    @Value
    private static class Embedded {
        List<AssignmentResponse> courses;
    }

    public AssignmentsResponse(List<AssignmentResponse> courses) {
        this.embedded = new Embedded(courses);
    }
}
