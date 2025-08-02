package org.example.metadata.assignment;

import org.example.metadata.assignment.model.AssignmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentRepository extends CrudRepository<AssignmentEntity, Long> {
    Optional<AssignmentEntity> findById(Long id);
}
