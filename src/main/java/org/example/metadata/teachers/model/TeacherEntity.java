package org.example.metadata.teachers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("teachers")
public class TeacherEntity {

    @Id
    private Long id;

    @Column("full_name")
    private String fullName;


    public TeacherResponse toResponse() {
        return new TeacherResponse(id, fullName);
    }
}
