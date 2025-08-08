package org.example.metadata.assignment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.example.metadata.exceptions.MondayException;

public enum AssignmentType {
    TEST ("Test"),
    THEORY ("Theory"),
    EXAM ("Exam"),
    PRE_EXAM ("Pre-exam"),
    RESEARCH ("Research"),
    REPORT ("Report"),
    ESSAY ("Essay"),
    WRITING_ASSIGNMENT ("Writing assignment");


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

        for (AssignmentType assignmentType : AssignmentType.values()) {
            if (assignmentType.type.equalsIgnoreCase(value)) {
                return assignmentType;
            }
        }
        throw new MondayException(String.format("Assigment type %s has not been found", value));
    }

}
