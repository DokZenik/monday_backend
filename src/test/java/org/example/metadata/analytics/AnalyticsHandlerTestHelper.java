package org.example.metadata.analytics;

import org.example.metadata.analytics.models.AnalyticsRequest;
import org.example.metadata.assignment.model.AssignmentCreateRequest;
import org.example.metadata.assignment.model.AssignmentType;
import org.example.metadata.course.model.CourseCategory;
import org.example.metadata.course.model.CourseCreateRequest;
import org.example.metadata.course.model.CourseLevel;
import org.example.metadata.grades.model.GradeCreateRequest;
import org.example.metadata.student.model.StudentCreateRequest;
import org.example.metadata.teachers.model.TeacherCreateRequest;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AnalyticsHandlerTestHelper {

    public StudentCreateRequest getStudentCreateRequest() {
        return new StudentCreateRequest("Alevtina", "7A");
    }

    public TeacherCreateRequest getTeacherCreateRequest() {
        return new TeacherCreateRequest("Alevtina");
    }

    public CourseCreateRequest getCourseCreateRequest(Long creatorId) {
        return new CourseCreateRequest(
                "My course",
                "Course description",
                CourseCategory.MATHEMATICS,
                creatorId,
                List.of(1L, 2L, 3L),
                List.of(4L, 5L, 6L),
                LocalDate.now(),
                LocalDate.now().plusWeeks(12),
                4.75,
                CourseLevel.ADVANCED,
                "/placeholder.svg?height=200&width=300&text=Math",
                "from-blue-500 to-purple-600",
                List.of("Calculus", "Linear Algebra", "Statistics", "Problem Solving"),
                299.0,
                false
        );
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
