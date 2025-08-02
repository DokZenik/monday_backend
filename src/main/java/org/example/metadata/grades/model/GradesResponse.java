package org.example.metadata.grades.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class GradesResponse {

    @JsonProperty("_embedded")
    Embedded embedded;

    @Value
    private static class Embedded {
        List<GradeResponse> grades;
    }

    public GradesResponse(List<GradeResponse> grades) {
        this.embedded = new Embedded(grades);
    }
}
