package org.example.metadata.teachers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class TeachersResponse {

    @JsonProperty("_embedded")
    Embedded embedded;

    @Value
    private static class Embedded {
        List<TeacherResponse> teachers;
    }

    public TeachersResponse(List<TeacherResponse> teachers) {
        this.embedded = new Embedded(teachers);
    }
}
