package org.example.metadata.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("students")
public class StudentEntity {

    @Id
    private Long id;

    @Column("full_name")
    private String fullName;

    @Column("group_name")
    private String groupName;

    public StudentResponse toResponse() {
        return new StudentResponse(id, fullName, groupName);
    }
}
