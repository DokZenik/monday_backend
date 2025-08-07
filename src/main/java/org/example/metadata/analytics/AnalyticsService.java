package org.example.metadata.analytics;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.RequiredArgsConstructor;
import org.example.metadata.analytics.models.AnalyticsDTO;
import org.example.metadata.analytics.models.AnalyticsRequest;
import org.example.metadata.analytics.models.AnalyticsResponse;
import org.example.metadata.analytics.models.RowItem;
import org.example.metadata.assignment.AssignmentService;
import org.example.metadata.assignment.model.AssignmentResponse;
import org.example.metadata.course.CourseService;
import org.example.metadata.course.model.CourseResponse;
import org.example.metadata.grades.model.GradeResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    private final AssignmentService assignmentService;

    private final CourseService courseService;

    public AnalyticsResponse getAnalytics(AnalyticsRequest request) {
        List<AssignmentResponse> assignments = assignmentService.getAllByCourseId(request.getCourseId());
        CourseResponse course = courseService.getById(request.getCourseId());

        List<AnalyticsDTO> grades = analyticsRepository.fetchGroupedGrades(request);

        List<RowItem> list = grades.stream().map(grade -> new RowItem(grade.getStudentId(),
                grade.getStudentName(),
                getGrades(grade.getGrades(), grade.getStudentId()))).toList();

        return new AnalyticsResponse(
                request.getCourseId(),
                course.getTitle(),
                assignments,
                list
        );
    }

    private List<GradeResponse> getGrades(ArrayNode arrayNode, Long studentId) {
        List<GradeResponse> grades = new ArrayList<>();

        for (JsonNode node : arrayNode) {
            grades.add(new GradeResponse(
                    node.get("grade_id").asLong(),
                    node.get("assignment_id").asLong(),
                    studentId,
                    node.get("score").asInt(),
                    node.get("feedback").asText(),
                    node.get("teacher_id").asLong(),
                    node.get("graded_at").asLong()
            ));
        }

        return grades;
    }

}
