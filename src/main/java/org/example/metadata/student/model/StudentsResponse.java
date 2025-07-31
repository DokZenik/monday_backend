package org.example.metadata.student.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class StudentsResponse {

    @JsonProperty("_embedded")
    Embedded embedded;

    @Value
    private static class Embedded {
        List<StudentResponse> students;
    }

    public StudentsResponse(List<StudentResponse> students) {
        this.embedded = new Embedded(students);
    }
}
