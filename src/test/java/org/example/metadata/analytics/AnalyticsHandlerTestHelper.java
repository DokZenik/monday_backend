package org.example.metadata.analytics;

import org.example.metadata.analytics.models.AnalyticsRequest;
import org.example.metadata.assignment.model.AssignmentCreateRequest;
import org.example.metadata.assignment.model.AssignmentStatus;
import org.example.metadata.assignment.model.AssignmentType;
import org.example.metadata.assignment.model.AttachedFile;
import org.example.metadata.course.model.CourseCreateRequest;
import org.example.metadata.grades.model.GradeCreateRequest;
import org.example.metadata.student.model.StudentCreateRequest;
import org.example.metadata.teachers.model.TeacherCreateRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
