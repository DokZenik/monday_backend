package org.example.metadata.submission;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.metadata.assignment.model.AssignmentResponse;
import org.example.metadata.submission.model.SubmissionCreateRequest;
import org.example.metadata.submission.model.SubmissionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assignment/{id}/submission")
@RequiredArgsConstructor
public class SubmissionHandler {

    private final SubmissionService submissionService;

    @Operation(summary = "Create a new submission")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Submission created successfully",
                    content = @Content(schema = @Schema(implementation = SubmissionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    ResponseEntity<SubmissionResponse> save(@RequestBody SubmissionCreateRequest request, @PathVariable("id") Long id) {
        return ResponseEntity.ok(submissionService.create(request, id));
    }

    @Operation(summary = "Get submission by student id and assignment id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Submission retrieved successfully",
                    content = @Content(schema = @Schema(implementation = AssignmentResponse.class))),
        @ApiResponse(responseCode = "400", description = "Submission not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    ResponseEntity<SubmissionResponse> get(@PathVariable("id") Long id, @RequestParam("student_id") Long studentId) {
        return ResponseEntity.ok(submissionService.findByAssignmentIdAndStudentId(id, studentId));
    }

    @Operation(summary = "Delete submission by student id and assignment id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Submission deleted successfully",
                    content = @Content(schema = @Schema(implementation = AssignmentResponse.class))),
        @ApiResponse(responseCode = "400", description = "Submission not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping
    ResponseEntity<String> delete(@PathVariable("id") Long id, @RequestParam("student_id") Long studentId) {
        submissionService.deleteByAssignmentIdAndStudentId(id, studentId);
        return ResponseEntity.ok().body(
                String.format("Submission with student id %d and assignment id %d deleted", id, studentId));
    }
}
