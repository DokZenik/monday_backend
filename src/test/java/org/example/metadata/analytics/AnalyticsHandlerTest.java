package org.example.metadata.analytics;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.metadata.TestcontainersConfiguration;
import org.example.metadata.assignment.model.AssignmentResponse;
import org.example.metadata.course.model.CourseResponse;
import org.example.metadata.grades.model.GradeResponse;
import org.example.metadata.student.model.StudentResponse;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnalyticsHandlerTest {

    private AnalyticsHandlerTestHelper helper = new AnalyticsHandlerTestHelper();

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @AfterEach
    void cleanUp() {
        jdbc.update("DELETE FROM assignments", new MapSqlParameterSource());
        jdbc.update("DELETE FROM teachers", new MapSqlParameterSource());
        jdbc.update("DELETE FROM courses", new MapSqlParameterSource());
        jdbc.update("DELETE FROM grades", new MapSqlParameterSource());
        jdbc.update("DELETE FROM students", new MapSqlParameterSource());
    }

    @Test
    void analyticsTest() {

        ResponseEntity<?> response;

        response = restTemplate
                .postForEntity("/students", helper.getStudentCreateRequest(), StudentResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        StudentResponse studentResponse = (StudentResponse) response.getBody();

        response = restTemplate
                .postForEntity("/teachers", helper.getTeacherCreateRequest(), TeacherResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        TeacherResponse teacherResponse = (TeacherResponse) response.getBody();

        response = restTemplate
                .postForEntity("/courses", helper.getCourseCreateRequest(teacherResponse.getId()), CourseResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        CourseResponse courseResponse = (CourseResponse) response.getBody();

        response = restTemplate
                .postForEntity("/assignments", helper.getAssignmentCreateRequest(courseResponse.getId()), AssignmentResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        AssignmentResponse assignmentResponse = (AssignmentResponse) response.getBody();

        response = restTemplate
                .postForEntity("/grades", helper.getGradeCreateRequest(assignmentResponse.getId(), studentResponse.getId()), GradeResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        response = restTemplate
                .postForEntity("/analytics", helper.getAnalyticsRequest(courseResponse.getId(), studentResponse.getGroupName()), JsonNode.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}
