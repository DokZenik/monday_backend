package org.example.metadata.stundets;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.metadata.TestcontainersConfiguration;
import org.example.metadata.student.model.StudentCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentsHandlerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final StudentHandlerTestHelper helper = new  StudentHandlerTestHelper();


    @Test
    void shouldReturnStudents() {
        StudentCreateRequest createRequest = helper.getCreateRequest();

        ResponseEntity<StudentCreateRequest> response = restTemplate
                .postForEntity("/students", createRequest, StudentCreateRequest.class);

        StudentCreateRequest body = response.getBody();

        assertNotNull(body);
        assertEquals(createRequest.getFullName(), body.getFullName());
        assertEquals(createRequest.getGroupName(), body.getGroupName());

        ResponseEntity<JsonNode> responseJson = restTemplate.getForEntity("/students", JsonNode.class);
        JsonNode json = responseJson.getBody();

        assertEquals(HttpStatus.OK, responseJson.getStatusCode());
        assertEquals(json.get("_embedded").size(), 1);
        assertEquals(json.get("_embedded").get("students").size(), 1);
        assertEquals(json.get("_embedded").get("students").get(0).get("fullName").asText(), createRequest.getFullName());
    }

}
