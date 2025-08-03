package org.example.metadata.analytics;

import org.example.metadata.analytics.models.AnalyticsRequest;
import org.example.metadata.assignment.model.AssignmentCreateRequest;
import org.example.metadata.assignment.model.AssignmentType;
import org.example.metadata.course.model.CourseCreateRequest;
import org.example.metadata.grades.model.GradeCreateRequest;
import org.example.metadata.student.model.StudentCreateRequest;
import org.example.metadata.teachers.model.TeacherCreateRequest;

import java.sql.Date;
import java.time.LocalDateTime;

public class AnalyticsHandlerTestHelper {

    public StudentCreateRequest getStudentCreateRequest() {
        return new StudentCreateRequest("Alevtina", "7A");
    }

    public TeacherCreateRequest getTeacherCreateRequest() {
        return new TeacherCreateRequest("Alevtina");
    }

    public CourseCreateRequest getCourseCreateRequest(Long teacherId) {
        return new CourseCreateRequest("My course", teacherId, "7b");
    }

    public AssignmentCreateRequest getAssignmentCreateRequest(Long courseId) {
        return new AssignmentCreateRequest(
                "My task",
                courseId,
                AssignmentType.HOME_TASK,
                100,
                Date.valueOf(LocalDateTime.now().toLocalDate()));
    }

    public GradeCreateRequest getGradeCreateRequest(Long assignmentId, Long studentId) {
        return new GradeCreateRequest(
                assignmentId,
                studentId,
                68,
                "aboba",
                1L);
    }

    public AnalyticsRequest getAnalyticsRequest(Long courseId, String groupName) {
        return new AnalyticsRequest(courseId, groupName);
    }

}
