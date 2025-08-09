package org.example.metadata.teachers.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCreateRequest {

    @NotBlank(message = "Full name can't be null")
    private String fullName;

    public TeacherEntity toEntity() {
        return new TeacherEntity(null, fullName);
    }
}
