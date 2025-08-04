package org.example.metadata.assignment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.example.metadata.exceptions.MondayException;

import java.util.HashMap;
import java.util.Map;

public enum AssignmentStatus {

    COMPLETED ("Completed"),
    PENDING ("Pending"),
    OVERDUE ("Overdue");

    private static final Map<String, AssignmentStatus> STATUS_MAP = new HashMap<>();

    static {
        for (AssignmentStatus assignmentStatus : values()) {
            STATUS_MAP.put(assignmentStatus.status, assignmentStatus);
        }
    }

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
        AssignmentStatus type = STATUS_MAP.get(value);
        if (type == null) {
            throw new MondayException("Unknown assignment status: " + value);
        }
        return type;
    }
}
