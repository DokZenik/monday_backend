package org.example.metadata.assignment;

import org.example.metadata.assignment.model.AssignmentEntity;
import org.example.metadata.assignment.model.AssignmentStatus;
import org.example.metadata.assignment.model.AssignmentType;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends CrudRepository<AssignmentEntity, Long> {
    Optional<AssignmentEntity> findById(Long id);

    List<AssignmentEntity> findAllByCourseId(Long courseId);

    @Modifying
    @Query("UPDATE assignments SET " +
            "title = :title, " +
            "course_id = :courseId, " +
            "teacher_id = :teacherId, " +
            "type = :type, " +
            "status = :status, " +
            "description = :description, " +
            "max_score = :maxScore, " +
            "due_date = :dueDate, " +
            "attached_files = :attachedFiles " +
            "WHERE id = :id")
    int updateAssignment(@Param("id") Long id,
                         @Param("title") String title,
                         @Param("courseId") Long courseId,
                         @Param("teacherId") Long teacherId,
                         @Param("type") AssignmentType type,
                         @Param("status") AssignmentStatus status,
                         @Param("description") String description,
                         @Param("maxScore") Integer maxScore,
                         @Param("dueDate") LocalDateTime dueDate,
                         @Param("attachedFiles") String attachedFiles);
}
