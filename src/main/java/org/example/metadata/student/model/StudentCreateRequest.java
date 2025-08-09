package org.example.metadata.student.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequest {

    @NotBlank(message = "Full name can't be null")
    private String fullName;

    @NotBlank(message = "Group name can't be null")
    private String groupName;

    public StudentEntity toEntity() {
        return new StudentEntity(null, fullName, groupName);
    }
}
