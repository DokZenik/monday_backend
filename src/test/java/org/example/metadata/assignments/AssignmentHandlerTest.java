package org.example.metadata.assignments;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.metadata.TestcontainersConfiguration;
import org.example.metadata.assignment.model.AssignmentCreateRequest;
import org.example.metadata.assignment.model.AssignmentResponse;
import org.example.metadata.assignment.model.AssignmentUpdateRequest;
import org.example.metadata.submission.model.SubmissionCreateRequest;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AssignmentHandlerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final AssignmentHandlerTestHelper helper = new AssignmentHandlerTestHelper();

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @AfterEach
    void cleanUp() {
        jdbc.update("DELETE FROM assignments", new MapSqlParameterSource());
    }

    @Test
    void assignmentApiTest() {
        AssignmentCreateRequest createRequest = helper.getCreateRequest();
        AssignmentUpdateRequest updateRequest = helper.getUpdateRequest();
        SubmissionCreateRequest submissionCreateRequest = helper.getSubmissionCreateRequest();
        AssignmentCreateRequest invalidCreateRequest = helper.getInvalidCreateRequest();
        AssignmentUpdateRequest invalidUpdateRequest = helper.getInvalidUpdateRequest();
        ResponseEntity<?> response;

        response = restTemplate
                .postForEntity("/assignments", createRequest, AssignmentResponse.class);

        AssignmentResponse responseBody = (AssignmentResponse) response.getBody();

        assertNotNull(responseBody);
        assertEquals(createRequest.getTitle(), responseBody.getTitle());
        assertEquals(Integer.valueOf(0), responseBody.getSubmissions());

        restTemplate.postForEntity(String.format("/assignment/%d/submission", responseBody.getId()), submissionCreateRequest, JsonNode.class);
        restTemplate.postForEntity(String.format("/assignment/%d/submission", responseBody.getId()), submissionCreateRequest, JsonNode.class);

        response = restTemplate
                .postForEntity("/assignments", invalidCreateRequest, JsonNode.class);

        JsonNode json = (JsonNode) response.getBody();

        assertNotNull(response);
        assertNotNull(json);
        assertTrue(response.getStatusCode().is4xxClientError());
        assertEquals("Due date can't be null", json.get("message").asText());

        response = restTemplate.getForEntity("/assignments", JsonNode.class);
        json = (JsonNode) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(json.get("_embedded").size(), 1);
        assertEquals(json.get("_embedded").get("courses").size(), 1);
        assertEquals(json.get("_embedded").get("courses").get(0).get("title").asText(), createRequest.getTitle());
        assertEquals(json.get("_embedded").get("courses").get(0).get("submissions").asInt(), 2);

        response = restTemplate.getForEntity("/assignments/" + responseBody.getId() + 1, JsonNode.class);

        assertTrue(response.getStatusCode().is4xxClientError());

        response = restTemplate.getForEntity("/assignments/" + responseBody.getId(), JsonNode.class);

        json = (JsonNode) response.getBody();

        assertNotNull(json);
        assertEquals(createRequest.getTitle(), json.get("title").asText());

        restTemplate.put(String.format("/assignments/%d", json.get("id").asLong()), updateRequest);

        response = restTemplate.getForEntity("/assignments/" + json.get("id").asLong(), JsonNode.class);
        json = (JsonNode) response.getBody();
        assertNotNull(json);
        assertEquals(updateRequest.getTitle(), json.get("title").asText());

        restTemplate.put(String.format("/assignments/%d", json.get("id").asLong()), invalidUpdateRequest);

        response = restTemplate.getForEntity("/assignments/" + json.get("id").asLong(), JsonNode.class);
        assertNotNull(response);
        assertNotNull(json);
        assertEquals(HttpStatus.OK, response.getStatusCode());


        restTemplate.delete("/assignments/" + responseBody.getId());

        response = restTemplate.getForEntity("/assignments/" + responseBody.getId(), JsonNode.class);

        assertTrue(response.getStatusCode().is4xxClientError());

        json = (JsonNode) response.getBody();

        assertEquals(String.format("Assignment with id %d not found", responseBody.getId()), json.get("message").asText());


    }

}
