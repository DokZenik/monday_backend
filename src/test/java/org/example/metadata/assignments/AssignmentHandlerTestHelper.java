package org.example.metadata.assignments;

import org.example.metadata.assignment.model.AssignmentCreateRequest;
import org.example.metadata.assignment.model.AttachedFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AssignmentHandlerTestHelper {

    public AssignmentCreateRequest getCreateRequest() {

        List<AttachedFile> files = new ArrayList<>();
        files.add(new AttachedFile("file1_title","file1_url"));
        files.add(new AttachedFile("file2_title","file2_url"));

        return new AssignmentCreateRequest(
                "My task",
                123L,
                456L,
                "Test",
                "Pending",
                "Task Description",
                100,
                null,
                LocalDateTime.now(),
                files);
    }
}
