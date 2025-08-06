package org.example.metadata.course;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.metadata.course.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseHandler {

    private final CourseService courseService;

    @Operation(summary = "Create a new course")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Course created successfully",
                    content = @Content(schema = @Schema(implementation = CourseResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<CourseResponse> save(@RequestBody CourseCreateRequest course) {
        return ResponseEntity.ok(courseService.create(course));
    }

    @Operation(summary = "Update a course")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Course updated successfully",
                    content = @Content(schema = @Schema(implementation = CourseResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> update(@RequestBody CourseUpdateRequest course, @PathVariable Long id) {
        return ResponseEntity.ok(courseService.update(course, id));
    }

    @Operation(summary = "Enroll students to the course")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Students enrolled successfully",
                    content = @Content(schema = @Schema(implementation = CourseResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/attach/{id}")
    public ResponseEntity<CourseResponse> attach(@RequestBody CourseStudentsRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(courseService.attach(request, id));
    }

    @Operation(summary = "Remove students from the course")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Students removed successfully",
                    content = @Content(schema = @Schema(implementation = CourseResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/detach/{id}")
    public ResponseEntity<CourseResponse> detach(@RequestBody CourseStudentsRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(courseService.detach(request, id));
    }

    @Operation(summary = "Get all courses")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Courses retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CoursesResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<CoursesResponse> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @Operation(summary = "Get course by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Course retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CourseResponse.class))),
        @ApiResponse(responseCode = "400", description = "Course not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @Operation(summary = "Delete course by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Course deleted successfully",
                    content = @Content(schema = @Schema(implementation = CourseResponse.class))),
        @ApiResponse(responseCode = "400", description = "Course not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity.ok().body(String.format("Course with id %d deleted successfully", id));
    }


}
