package org.example.metadata.assignments;

import org.example.metadata.assignment.model.*;

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
                AssignmentType.TEST,
                AssignmentStatus.PENDING,
                "Task Description",
                100,
                LocalDateTime.now(),
                files);
    }

    public AssignmentUpdateRequest getUpdateRequest() {

        List<AttachedFile> files = new ArrayList<>();
        files.add(new AttachedFile("file1_title","file1_url"));
        files.add(new AttachedFile("file2_title","file2_url"));

        return new AssignmentUpdateRequest(
                "My updated task",
                AssignmentType.TEST,
                AssignmentStatus.PENDING,
                "Task Description",
                100,
                LocalDateTime.now(),
                files);
    }
}
