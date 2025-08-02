package org.example.metadata.grades;

import org.example.metadata.grades.model.GradeCreateRequest;

public class GradeHandlerTestHelper {

    public GradeCreateRequest getCreateRequest() {
        return new GradeCreateRequest(
                1L,
                1L,
                68,
                "aboba",
                1L);
    }
}
