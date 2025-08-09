package org.example.metadata.course.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.example.metadata.exceptions.MondayException;

public enum CourseLevel {
    ADVANCED("Advanced");

    private final String level;

    CourseLevel(String level) {
        this.level = level;
    }

    @JsonValue
    public String getLevel() {
        return level;
    }

    @JsonCreator
    public static CourseLevel fromString(String value) {
        for (CourseLevel courseLevel : CourseLevel.values()) {
            if (courseLevel.level.equalsIgnoreCase(value)) {
                return courseLevel;
            }
        }
        throw new MondayException(String.format("Course level %s has not been found", value));
    }



}
