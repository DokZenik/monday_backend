package org.example.metadata.grades;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.metadata.TestcontainersConfiguration;
import org.example.metadata.grades.model.GradeCreateRequest;
import org.example.metadata.grades.model.GradeResponse;
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

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GradeHandlerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final GradeHandlerTestHelper helper = new GradeHandlerTestHelper();

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @AfterEach
    void cleanUp() {
        jdbc.update("DELETE FROM grades", new MapSqlParameterSource());
    }

    @Test
    void gradeApiTest() {
        GradeCreateRequest createRequest = helper.getCreateRequest();
        ResponseEntity<?> response;

        response = restTemplate
                .postForEntity("/grades", createRequest, GradeResponse.class);

        GradeResponse responseBody = (GradeResponse) response.getBody();

        assertNotNull(responseBody);
        assertEquals(createRequest.getFeedback(), responseBody.getFeedback());

        response = restTemplate.getForEntity("/grades", JsonNode.class);
        JsonNode json = (JsonNode) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(json.get("_embedded").size(), 1);
        assertEquals(json.get("_embedded").get("grades").size(), 1);
        assertEquals(json.get("_embedded").get("grades").get(0).get("feedback").asText(), createRequest.getFeedback());

        restTemplate.delete("/grades/" + responseBody.getId());

        response = restTemplate.getForEntity("/grades", JsonNode.class);

        json = (JsonNode) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(json.get("_embedded").size(), 1);

    }

}
