package org.example.metadata.student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.metadata.student.model.StudentCreateRequest;
import org.example.metadata.student.model.StudentEntity;
import org.example.metadata.student.model.StudentResponse;
import org.example.metadata.student.model.StudentsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentHandler {

    private final StudentService studentService;

    @Operation(summary = "Create a new student")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student created successfully",
                    content = @Content(schema = @Schema(implementation = StudentResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<StudentResponse> save(@RequestBody StudentCreateRequest student) {
        return ResponseEntity.ok(studentService.create(student));
    }

    @Operation(summary = "Get all students")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Students retrieved successfully",
                    content = @Content(schema = @Schema(implementation = StudentResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<StudentsResponse> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @Operation(summary = "Get student by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student retrieved successfully",
                    content = @Content(schema = @Schema(implementation = StudentResponse.class))),
        @ApiResponse(responseCode = "400", description = "Student not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getById(id));
    }


}
