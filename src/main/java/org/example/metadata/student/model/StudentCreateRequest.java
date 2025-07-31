package org.example.metadata.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequest {
    private String fullName;

    private String groupName;

    public StudentEntity toEntity() {
        return new  StudentEntity(null, fullName, groupName);
    }
}
