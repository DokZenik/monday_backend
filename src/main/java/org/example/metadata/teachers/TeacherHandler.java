package org.example.metadata.teachers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.metadata.teachers.model.TeacherCreateRequest;
import org.example.metadata.teachers.model.TeacherResponse;
import org.example.metadata.teachers.model.TeachersResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherHandler {

    private final TeacherService teacherService;

    @Operation(summary = "Create a new teacher")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Teacher created successfully",
                    content = @Content(schema = @Schema(implementation = TeacherResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<TeacherResponse> save(@RequestBody TeacherCreateRequest teacher) {
        return ResponseEntity.ok(teacherService.create(teacher));
    }

    @Operation(summary = "Get all teachers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Teachers retrieved successfully",
                    content = @Content(schema = @Schema(implementation = TeacherResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<TeachersResponse> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    @Operation(summary = "Get teacher by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Teacher retrieved successfully",
                    content = @Content(schema = @Schema(implementation = TeacherResponse.class))),
        @ApiResponse(responseCode = "400", description = "Teacher not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @Operation(summary = "Delete teacher by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Teacher deleted successfully",
                    content = @Content(schema = @Schema(implementation = TeacherResponse.class))),
        @ApiResponse(responseCode = "400", description = "Teacher not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);
        return ResponseEntity.ok().body(String.format("Teacher with id %d deleted successfully", id));
    }


}
