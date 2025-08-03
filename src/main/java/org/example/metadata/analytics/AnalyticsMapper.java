package org.example.metadata.analytics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.example.metadata.analytics.models.AnalyticsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalyticsMapper implements RowMapper<AnalyticsDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public AnalyticsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            return new  AnalyticsDTO(
                    rs.getLong("student_id"),
                    rs.getString("fullname"),
                    (ArrayNode) objectMapper.readTree(rs.getString("student_grades"))
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
