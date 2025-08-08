package org.example.metadata.assignment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AssignmentStatus {

    COMPLETED("Completed"),
    PENDING("Pending"),
    OVERDUE("Overdue");


    private final String status;

    AssignmentStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    @JsonCreator
    public static AssignmentStatus fromString(String value) {
        return AssignmentStatus.valueOf(value.toUpperCase());
    }
}
