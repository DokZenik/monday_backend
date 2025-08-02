package org.example.metadata.stundets;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.metadata.TestcontainersConfiguration;
import org.example.metadata.student.model.StudentCreateRequest;
import org.example.metadata.student.model.StudentResponse;
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
class StudentsHandlerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final StudentHandlerTestHelper helper = new  StudentHandlerTestHelper();


    @Test
    void studentsApiTest() {
        StudentCreateRequest createRequest = helper.getCreateRequest();
        ResponseEntity<?> response;

        response = restTemplate
                .postForEntity("/students", createRequest, StudentResponse.class);

        StudentResponse responseBody = (StudentResponse) response.getBody();

        assertNotNull(responseBody);
        assertEquals(createRequest.getFullName(), responseBody.getFullName());
        assertEquals(createRequest.getGroupName(), responseBody.getGroupName());

        response = restTemplate.getForEntity("/students", JsonNode.class);
        JsonNode json = (JsonNode) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(json.get("_embedded").size(), 1);
        assertEquals(json.get("_embedded").get("students").size(), 1);
        assertEquals(json.get("_embedded").get("students").get(0).get("fullName").asText(), createRequest.getFullName());

        response = restTemplate.getForEntity("/students/" + responseBody.getId() + 1, StudentResponse.class);

        assertTrue(response.getStatusCode().is4xxClientError());

        response = restTemplate.getForEntity("/students/" + responseBody.getId(), StudentResponse.class);

        responseBody = (StudentResponse) response.getBody();

        assertNotNull(responseBody);
        assertEquals(createRequest.getFullName(), responseBody.getFullName());
        assertEquals(createRequest.getGroupName(), responseBody.getGroupName());


        restTemplate.delete("/students/" + responseBody.getId());

        response = restTemplate.getForEntity("/students/" + responseBody.getId(), JsonNode.class);

        assertTrue(response.getStatusCode().is4xxClientError());

        json = (JsonNode) response.getBody();

        assertEquals("Student with id 1 not found", json.get("message").asText());


    }

}
