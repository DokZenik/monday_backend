package org.example.metadata.submission;

import org.example.metadata.assignment.model.AttachedFile;
import org.example.metadata.grades.model.GradeCreateRequest;
import org.example.metadata.submission.model.SubmissionCreateRequest;

import java.util.ArrayList;
import java.util.List;

public class SubmissionHandlerTestHelper {

    public SubmissionCreateRequest getCreateRequest() {

        List<AttachedFile> files = new ArrayList<>();
        files.add(new AttachedFile("file1_title","file1_url"));
        files.add(new AttachedFile("file2_title","file2_url"));

        return new SubmissionCreateRequest(
                123L,
                "Submission Text",
                files);
    }
    public GradeCreateRequest getGradeCreateRequest() {
        return new GradeCreateRequest(
                1L,
                123L,
                80,
                "Lox ebanii",
                3L,
                321231L
        );
    }
}
