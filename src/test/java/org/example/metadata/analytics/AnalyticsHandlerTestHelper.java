package org.example.metadata.analytics;

import org.example.metadata.analytics.models.AnalyticsRequest;
import org.example.metadata.assignment.model.AssignmentCreateRequest;
import org.example.metadata.assignment.model.AssignmentStatus;
import org.example.metadata.assignment.model.AssignmentType;
import org.example.metadata.course.model.CourseCategory;
import org.example.metadata.assignment.model.AttachedFile;
import org.example.metadata.course.model.CourseCreateRequest;
import org.example.metadata.course.model.CourseLevel;
import org.example.metadata.grades.model.GradeCreateRequest;
import org.example.metadata.student.model.StudentCreateRequest;
import org.example.metadata.teachers.model.TeacherCreateRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

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
                Set.of(1L, 2L, 3L),
                Set.of(4L, 5L, 6L),
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

    public AssignmentCreateRequest getAssignmentCreateRequest(Long courseId, Long teacherId) {

        List<AttachedFile> files = new ArrayList<>();
        files.add(new AttachedFile("file1_title","file1_url"));
        files.add(new AttachedFile("file2_title","file2_url"));

        return new AssignmentCreateRequest(
                "My task",
                courseId,
                teacherId,
                AssignmentType.TEST,
                AssignmentStatus.PENDING,
                "Task Description",
                100,
                LocalDateTime.now(),
                files);

    }

    public GradeCreateRequest getGradeCreateRequest(Long assignmentId, Long studentId) {
        return new GradeCreateRequest(
                assignmentId,
                studentId,
                68,
                "aboba",
                1L,
                1L);
    }

    public AnalyticsRequest getAnalyticsRequest(Long courseId, String groupName) {
        return new AnalyticsRequest(courseId, groupName);
    }

}
