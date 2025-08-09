package org.example.metadata.teachers;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.metadata.TestcontainersConfiguration;
import org.example.metadata.teachers.model.TeacherCreateRequest;
import org.example.metadata.teachers.model.TeacherResponse;
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
class TeacherHandlerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final TeacherHandlerTestHelper helper = new TeacherHandlerTestHelper();

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @AfterEach
    void cleanUp() {
        jdbc.update("DELETE FROM teachers", new MapSqlParameterSource());
    }

    @Test
    void teachersApiTest() {
        TeacherCreateRequest createRequest = helper.getCreateRequest();
        TeacherCreateRequest invalidCreateRequest = helper.getInvalidCreateRequest();
        ResponseEntity<?> response;
        JsonNode json;

        response = restTemplate
                .postForEntity("/teachers", createRequest, TeacherResponse.class);

        TeacherResponse responseBody = (TeacherResponse) response.getBody();

        assertNotNull(responseBody);
        assertEquals(createRequest.getFullName(), responseBody.getFullName());

        response = restTemplate
                .postForEntity("/teachers", invalidCreateRequest, JsonNode.class);

        json = (JsonNode) response.getBody();

        assertNotNull(json);
        assertTrue(response.getStatusCode().is4xxClientError());
        assertEquals("Full name can't be null", json.get("message").asText());


        response = restTemplate.getForEntity("/teachers", JsonNode.class);
        json = (JsonNode) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(json.get("_embedded").size(), 1);
        assertEquals(json.get("_embedded").get("teachers").size(), 1);
        assertEquals(json.get("_embedded").get("teachers").get(0).get("fullName").asText(), createRequest.getFullName());

        response = restTemplate.getForEntity("/teachers/" + responseBody.getId() + 1, TeacherResponse.class);

        assertTrue(response.getStatusCode().is4xxClientError());

        response = restTemplate.getForEntity("/teachers/" + responseBody.getId(), TeacherResponse.class);

        responseBody = (TeacherResponse) response.getBody();

        assertNotNull(responseBody);
        assertEquals(createRequest.getFullName(), responseBody.getFullName());


        restTemplate.delete("/teachers/" + responseBody.getId());

        response = restTemplate.getForEntity("/teachers/" + responseBody.getId(), JsonNode.class);

        assertTrue(response.getStatusCode().is4xxClientError());

        json = (JsonNode) response.getBody();

        assertEquals(String.format("Teacher with id %d not found", responseBody.getId()), json.get("message").asText());


    }

}
