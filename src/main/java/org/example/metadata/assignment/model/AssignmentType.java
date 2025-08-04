package org.example.metadata.assignment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.example.metadata.exceptions.MondayException;

import java.util.HashMap;
import java.util.Map;

public enum AssignmentType {
    TEST ("Test"),
    THEORY ("Theory"),
    EXAM ("Exam"),
    PRE_EXAM ("Pre-exam"),
    RESEARCH ("Research"),
    REPORT ("Report"),
    ESSAY ("Essay"),
    WRITING_ASSIGNMENT ("Writing assignment");

    private static final Map<String, AssignmentType> TYPE_MAP = new HashMap<>();

    static {
        for (AssignmentType assignmentType : values()) {
            TYPE_MAP.put(assignmentType.type, assignmentType);
        }
    }

    private final String type;

    AssignmentType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }


    @JsonCreator
    public static AssignmentType fromString(String value) {
        AssignmentType type = TYPE_MAP.get(value);
        if (type == null) {
            throw new MondayException("Unknown assignment type: " + value);
        }
        return type;
    }

}
