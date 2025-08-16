package org.example.metadata.grades;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.metadata.grades.model.GradeCreateRequest;
import org.example.metadata.grades.model.GradeResponse;
import org.example.metadata.grades.model.GradesResponse;
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
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeHandler {

    private final GradeService gradeService;

    @Operation(summary = "Create a new grade")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Grade created successfully",
                    content = @Content(schema = @Schema(implementation = GradeResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<GradeResponse> save(@Valid @RequestBody GradeCreateRequest gradeCreateRequest) {
        return ResponseEntity.ok(gradeService.create(gradeCreateRequest));
    }

    @Operation(summary = "Get all grades")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Assignment retrieved successfully",
                    content = @Content(schema = @Schema(implementation = GradesResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<GradesResponse> getAll() {
        return ResponseEntity.ok(gradeService.getAll());
    }

    @Operation(summary = "Delete grade by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Grade deleted successfully",
                    content = @Content(schema = @Schema(implementation = GradeResponse.class))),
        @ApiResponse(responseCode = "400", description = "Grade not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        gradeService.deleteById(id);
        return ResponseEntity.ok().body(String.format("Grade with id %d deleted successfully", id));
    }


}
