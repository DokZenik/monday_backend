package org.example.metadata.teachers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCreateRequest {
    private String fullName;

    public TeacherEntity toEntity() {
        return new TeacherEntity(null, fullName);
    }
}
