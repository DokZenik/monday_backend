package org.example.metadata.submission;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.metadata.TestcontainersConfiguration;
import org.example.metadata.grades.model.GradeCreateRequest;
import org.example.metadata.submission.model.SubmissionCreateRequest;
import org.example.metadata.submission.model.SubmissionResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.junit.Assert.*;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubmissonHandlerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final SubmissionHandlerTestHelper helper = new SubmissionHandlerTestHelper();

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @AfterEach
    void cleanUp() {
        jdbc.update("DELETE FROM submissions", new MapSqlParameterSource());
        jdbc.update("DELETE FROM grades", new MapSqlParameterSource());
    }

    @Test
    void submissionApiTest(){
        SubmissionCreateRequest createRequest = helper.getCreateRequest();
        GradeCreateRequest gradeCreateRequest = helper.getGradeCreateRequest();
        ResponseEntity<?> response;
        SubmissionResponse responseBody;

        response = restTemplate.postForEntity(String.format("/assignment/%d/submission", 1L), createRequest, SubmissionResponse.class);
        responseBody = (SubmissionResponse) response.getBody();

        assertNotNull(responseBody);
        assertEquals(createRequest.getText(), responseBody.getText());

        response = restTemplate
                .getForEntity(String.format("/assignment/%d/submission?student_id=%d", responseBody.getAssignmentId(), responseBody.getStudentId()), SubmissionResponse.class);
        responseBody = (SubmissionResponse) response.getBody();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createRequest.getText(), responseBody.getText());
        assertEquals(responseBody.getScore(), null);

        restTemplate.postForEntity("/grades", gradeCreateRequest, JsonNode.class);

        response = restTemplate.getForEntity(String.format("/assignment/%d/submission?student_id=%d", responseBody.getAssignmentId(), responseBody.getStudentId()), SubmissionResponse.class);
        responseBody = (SubmissionResponse) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseBody.getFeedback(), gradeCreateRequest.getFeedback());
        assertEquals(responseBody.getScore(), gradeCreateRequest.getScore());

        response = restTemplate.getForEntity(String.format("/assignment/%d/submission?student_id=%d", responseBody.getAssignmentId()+1L, responseBody.getStudentId()+1L), SubmissionResponse.class);

        assertTrue(response.getStatusCode().is4xxClientError());

        restTemplate.delete(String.format("/assignment/%d/submission?student_id=%d", responseBody.getAssignmentId(), responseBody.getStudentId()));


        response = restTemplate.getForEntity(String.format("/assignment/%d/submission?student_id=%d", responseBody.getAssignmentId(), responseBody.getStudentId()), SubmissionResponse.class);

        assertTrue(response.getStatusCode().is4xxClientError());

    }

}
