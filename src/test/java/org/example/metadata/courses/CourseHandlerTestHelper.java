package org.example.metadata.courses;

import org.example.metadata.course.model.CourseCreateRequest;

public class CourseHandlerTestHelper {

    public CourseCreateRequest getCreateRequest() {
        return new CourseCreateRequest("My course", 1L, "7b");
    }
}
