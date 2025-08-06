package org.example.metadata.submission;

import org.example.metadata.submission.model.SubmissionEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubmissionRepository extends CrudRepository<SubmissionEntity, Long> {


    @Query(value =
            "SELECT * " +
            "FROM submissions " +
            "WHERE assignment_id = :assignmentId AND student_id = :studentId " +
            "ORDER BY submitted_at DESC " +
            "LIMIT 1")
    Optional<SubmissionEntity> findFirst(
            @Param("assignmentId") Long assignmentId,
            @Param("studentId") Long studentId
    );

    Integer countByAssignmentId(Long assignmentId);


    void deleteByAssignmentIdAndStudentId(Long assignmentId, Long studentId);
}
