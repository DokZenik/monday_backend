package org.example.metadata.courses;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.metadata.TestcontainersConfiguration;
import org.example.metadata.course.model.CourseCreateRequest;
import org.example.metadata.course.model.CourseResponse;
import org.example.metadata.course.model.CourseStudentsRequest;
import org.example.metadata.course.model.CourseUpdateRequest;
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

import java.util.Set;

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
        CourseCreateRequest invalidCreateRequest = helper.getInvalidCreateRequest();
        CourseUpdateRequest updateRequest = helper.getUpdateRequest();
        CourseUpdateRequest invalidUpdateRequest = helper.getInvalidUpdateRequest();
        CourseStudentsRequest studentsRequest = helper.getStudentsRequest();
        ResponseEntity<?> response;
        ResponseEntity<?> invalidResponse;

        response = restTemplate
                .postForEntity("/courses", createRequest, CourseResponse.class);

        CourseResponse responseBody = (CourseResponse) response.getBody();

        assertNotNull(responseBody);
        assertEquals(createRequest.getTitle(), responseBody.getTitle());

        invalidResponse = restTemplate
                .postForEntity("/courses",invalidCreateRequest , JsonNode.class);
        JsonNode json = (JsonNode) invalidResponse.getBody();

        assertTrue(invalidResponse.getStatusCode().is4xxClientError());

        assertEquals("Price can't be negative", json.get("message").asText());

        response = restTemplate.getForEntity("/courses", JsonNode.class);
        json = (JsonNode) response.getBody();

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

        restTemplate.put("/courses/" + responseBody.getId(), updateRequest);

        response = restTemplate.getForEntity("/courses/" + responseBody.getId(), CourseResponse.class);

        responseBody = (CourseResponse) response.getBody();

        assertNotNull(responseBody);
        assertEquals(updateRequest.getTitle(), responseBody.getTitle());
        assertEquals(updateRequest.getRating(), responseBody.getRating());
        assertEquals(updateRequest.getSkills(), responseBody.getSkills());

        restTemplate.put("/courses/" + responseBody.getId(), invalidUpdateRequest);

        response = restTemplate.getForEntity("/courses/" + responseBody.getId(), CourseResponse.class);

        responseBody = (CourseResponse) response.getBody();
        assertNotNull(responseBody);
        assertEquals(updateRequest.getStartDate(), responseBody.getStartDate());

        responseBody = restTemplate.patchForObject("/courses/attach/" + responseBody.getId(), studentsRequest, CourseResponse.class);

        assertNotNull(responseBody);
        assertEquals(responseBody.getStudentIds(), Set.of(4L, 5L, 6L, 7L));

        responseBody = restTemplate.patchForObject("/courses/detach/" + responseBody.getId(), studentsRequest, CourseResponse.class);

        assertNotNull(responseBody);
        assertEquals(responseBody.getStudentIds(), Set.of(5L));

        restTemplate.delete("/courses/" + responseBody.getId());

        response = restTemplate.getForEntity("/courses/" + responseBody.getId(), JsonNode.class);

        assertTrue(response.getStatusCode().is4xxClientError());

        json = (JsonNode) response.getBody();

        assertEquals(String.format("Course with id %d not found", responseBody.getId()), json.get("message").asText());


    }

}
