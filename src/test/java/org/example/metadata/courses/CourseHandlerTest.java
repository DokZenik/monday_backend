package org.example.metadata.courses;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.metadata.TestcontainersConfiguration;
import org.example.metadata.course.model.CourseCreateRequest;
import org.example.metadata.course.model.CourseResponse;
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
class CourseHandlerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final CourseHandlerTestHelper helper = new CourseHandlerTestHelper();

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @AfterEach
    void cleanUp() {
        jdbc.update("DELETE FROM courses", new MapSqlParameterSource());
    }


    @Test
    void coursesApiTest() {
        CourseCreateRequest createRequest = helper.getCreateRequest();
        ResponseEntity<?> response;

        response = restTemplate
                .postForEntity("/courses", createRequest, CourseResponse.class);

        CourseResponse responseBody = (CourseResponse) response.getBody();

        assertNotNull(responseBody);
        assertEquals(createRequest.getTitle(), responseBody.getTitle());

        response = restTemplate.getForEntity("/courses", JsonNode.class);
        JsonNode json = (JsonNode) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(json.get("_embedded").size(), 1);
        assertEquals(json.get("_embedded").get("courses").size(), 1);
        assertEquals(json.get("_embedded").get("courses").get(0).get("title").asText(), createRequest.getTitle());

        response = restTemplate.getForEntity("/courses/" + responseBody.getId() + 1, CourseResponse.class);

        assertTrue(response.getStatusCode().is4xxClientError());

        response = restTemplate.getForEntity("/courses/" + responseBody.getId(), CourseResponse.class);

        responseBody = (CourseResponse) response.getBody();

        assertNotNull(responseBody);
        assertEquals(createRequest.getTitle(), responseBody.getTitle());


        restTemplate.delete("/courses/" + responseBody.getId());

        response = restTemplate.getForEntity("/courses/" + responseBody.getId(), JsonNode.class);

        assertTrue(response.getStatusCode().is4xxClientError());

        json = (JsonNode) response.getBody();

        assertEquals(String.format("Course with id %d not found", responseBody.getId()), json.get("message").asText());


    }

}
