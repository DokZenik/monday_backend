package org.example.metadata.grades;

import org.example.metadata.grades.model.GradeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends CrudRepository<GradeEntity, Long> {
    Optional<GradeEntity> findById(Long id);
}
