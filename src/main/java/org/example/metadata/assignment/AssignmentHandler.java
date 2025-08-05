package org.example.metadata.assignment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.metadata.assignment.model.AssignmentCreateRequest;
import org.example.metadata.assignment.model.AssignmentResponse;
import org.example.metadata.assignment.model.AssignmentUpdateRequest;
import org.example.metadata.assignment.model.AssignmentsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class AssignmentHandler {

    private final AssignmentService assignmentService;

    @Operation(summary = "Create a new assignment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Assignment created successfully",
                    content = @Content(schema = @Schema(implementation = AssignmentResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<AssignmentResponse> save(@RequestBody AssignmentCreateRequest assignment) {
        return ResponseEntity.ok(assignmentService.create(assignment));
    }

    @Operation(summary = "Get all assignments")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Assignment retrieved successfully",
                    content = @Content(schema = @Schema(implementation = AssignmentsResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<AssignmentsResponse> getAll() {
        return ResponseEntity.ok(assignmentService.getAll());
    }

    @Operation(summary = "Get assignment by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Assignment retrieved successfully",
                    content = @Content(schema = @Schema(implementation = AssignmentResponse.class))),
        @ApiResponse(responseCode = "400", description = "Assignment not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getById(id));
    }

    @Operation(summary = "Delete assignment by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Assignment deleted successfully",
                    content = @Content(schema = @Schema(implementation = AssignmentResponse.class))),
        @ApiResponse(responseCode = "400", description = "Course not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        assignmentService.deleteById(id);
        return ResponseEntity.ok().body(String.format("Assignment with id %d deleted successfully", id));
    }

    @Operation(summary = "Update assignment by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Assignment updated successfully",
                    content = @Content(schema = @Schema(implementation = AssignmentResponse.class))),
        @ApiResponse(responseCode = "400", description = "Course not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponse> updateById(
            @PathVariable Long id,
            @RequestBody AssignmentUpdateRequest assignment) {
        return ResponseEntity.ok(assignmentService.updateById(assignment, id));
    }


}
