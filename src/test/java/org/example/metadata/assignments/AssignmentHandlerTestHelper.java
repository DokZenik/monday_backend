package org.example.metadata.assignments;

import org.example.metadata.assignment.model.AssignmentCreateRequest;
import org.example.metadata.assignment.model.AssignmentType;

import java.sql.Date;
import java.time.LocalDateTime;

public class AssignmentHandlerTestHelper {

    public AssignmentCreateRequest getCreateRequest() {
        return new AssignmentCreateRequest(
                "My task",
                1L,
                AssignmentType.HOME_TASK,
                100,
                Date.valueOf(LocalDateTime.now().toLocalDate()));
    }
}
