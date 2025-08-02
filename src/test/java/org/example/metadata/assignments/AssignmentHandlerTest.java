package org.example.metadata.assignments;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.metadata.TestcontainersConfiguration;
import org.example.metadata.assignment.model.AssignmentCreateRequest;
import org.example.metadata.assignment.model.AssignmentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AssignmentHandlerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final AssignmentHandlerTestHelper helper = new AssignmentHandlerTestHelper();


    @Test
    void assignmentApiTest() {
        AssignmentCreateRequest createRequest = helper.getCreateRequest();
        ResponseEntity<?> response;

        response = restTemplate
                .postForEntity("/assignments", createRequest, AssignmentResponse.class);

        AssignmentResponse responseBody = (AssignmentResponse) response.getBody();

        assertNotNull(responseBody);
        assertEquals(createRequest.getTitle(), responseBody.getTitle());

        response = restTemplate.getForEntity("/assignments", JsonNode.class);
        JsonNode json = (JsonNode) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(json.get("_embedded").size(), 1);
        assertEquals(json.get("_embedded").get("courses").size(), 1);
        assertEquals(json.get("_embedded").get("courses").get(0).get("title").asText(), createRequest.getTitle());

        response = restTemplate.getForEntity("/assignments/" + responseBody.getId() + 1, AssignmentResponse.class);

        assertTrue(response.getStatusCode().is4xxClientError());

        response = restTemplate.getForEntity("/assignments/" + responseBody.getId(), AssignmentResponse.class);

        responseBody = (AssignmentResponse) response.getBody();

        assertNotNull(responseBody);
        assertEquals(createRequest.getTitle(), responseBody.getTitle());


        restTemplate.delete("/assignments/" + responseBody.getId());

        response = restTemplate.getForEntity("/assignments/" + responseBody.getId(), JsonNode.class);

        assertTrue(response.getStatusCode().is4xxClientError());

        json = (JsonNode) response.getBody();

        assertEquals("Assignment with id 1 not found", json.get("message").asText());


    }

}
