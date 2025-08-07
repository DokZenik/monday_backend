package org.example.metadata.assignment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.metadata.assignment.model.*;
import org.example.metadata.exceptions.MondayException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class AssignmentMapper {

    public static AssignmentEntity createRequestToEntity(AssignmentCreateRequest request) {

        try {
            String files = new ObjectMapper().writeValueAsString(request.getAttachedFiles());
            return new AssignmentEntity(
                    null,
                    request.getTitle(),
                    request.getCourseId(),
                    request.getTeacherId(),
                    request.getType(),
                    request.getStatus(),
                    request.getDescription(),
                    request.getMaxScore(),
                    request.getDueDate(),
                    files);
        } catch (JsonProcessingException e) {
            throw new MondayException("Files can't be saved");
        }
    }

    public static AssignmentEntity updateRequestToEntity(AssignmentUpdateRequest request, AssignmentEntity oldEntity) {

        try {
            String files = new ObjectMapper().writeValueAsString(request.getAttachedFiles());
            return new AssignmentEntity(
                    oldEntity.getId(),
                    request.getTitle(),
                    oldEntity.getCourseId(),
                    oldEntity.getTeacherId(),
                    request.getType(),
                    request.getStatus(),
                    request.getDescription(),
                    request.getMaxScore(),
                    request.getDueDate(),
                    files);
        } catch (JsonProcessingException e) {
            throw new MondayException("Files can't be saved");
        }
    }

    public static AssignmentResponse entityToResponse(AssignmentEntity entity, Integer submissionCount) {


        LocalDateTime now = LocalDateTime.now();

        Duration between = Duration.between(now, entity.getDueDate());
        boolean isNegative = between.isNegative();

        long totalMinutes = Math.abs(between.toMinutes());
        long days = totalMinutes / (60 * 24);
        long hours = (totalMinutes % (60 * 24)) / 60;
        long minutes = totalMinutes % 60;

        String timeRemaining = String.format(
                "%s%d days, %d hours, %d minutes",
                isNegative ? "- " : "",
                days, hours, minutes
        );

        try {
            List<AttachedFile> attachedFiles =
                    new ObjectMapper().readValue(entity.getAttachedFiles(), new TypeReference<>() {
                    });

            return new AssignmentResponse(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getCourseId(),
                    entity.getTeacherId(),
                    entity.getType(),
                    entity.getStatus(),
                    entity.getDescription(),
                    entity.getMaxScore(),
                    entity.getDueDate(),
                    attachedFiles,
                    timeRemaining,
                    submissionCount);

        } catch (JsonProcessingException e) {
            throw new MondayException("Can't deserialize attached files");
        }


    }
}
