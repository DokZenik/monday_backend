package org.example.metadata.analytics;

import lombok.RequiredArgsConstructor;
import org.example.metadata.analytics.models.AnalyticsDTO;
import org.example.metadata.analytics.models.AnalyticsRequest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnalyticsRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<AnalyticsDTO> fetchGroupedGrades(AnalyticsRequest request) {
        String sql = """
                select grades.student_id,
                       s.full_name as fullname,
                       json_agg(json_build_object(
                               'assignment_id', assignment_id,
                               'score', score,
                               'graded_at', graded_at,
                               'feedback', feedback,
                               'teacher_id', grades.teacher_id,
                               'grade_id', grades.id
                                )) AS student_grades
                from grades
                         left join assignments a on grades.assignment_id = a.id
                         left join students s on grades.student_id = s.id
                where a.course_id = :courseId
                  and group_name = :groupName
                group by grades.student_id, s.full_name;
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("courseId", request.getCourseId())
                .addValue("groupName", request.getGroupName());

        return jdbcTemplate.query(sql, params, new AnalyticsMapper());
    }
}
