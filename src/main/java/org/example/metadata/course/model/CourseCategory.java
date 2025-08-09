package org.example.metadata.course.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.example.metadata.exceptions.MondayException;

public enum CourseCategory {

    MATHEMATICS("Mathematics");

    private final String category;

    CourseCategory(String category) {
        this.category = category;
    }

    @JsonValue
    public String getCategory() {
        return category;
    }

    @JsonCreator
    public static CourseCategory fromString(String value) {
        for (CourseCategory courseCategory : CourseCategory.values()) {
            if (courseCategory.category.equalsIgnoreCase(value)) {
                return courseCategory;
            }
        }
        throw new MondayException(String.format("Course category %s has not been found", value));
    }
}
