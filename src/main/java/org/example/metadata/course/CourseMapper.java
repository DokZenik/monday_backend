package org.example.metadata.course;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.metadata.course.model.CourseCreateRequest;
import org.example.metadata.course.model.CourseEntity;
import org.example.metadata.course.model.CourseResponse;
import org.example.metadata.course.model.CourseUpdateRequest;
import org.example.metadata.exceptions.MondayException;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class CourseMapper {

    public static CourseEntity toEntity(CourseCreateRequest request) {

        ObjectMapper mapper = new ObjectMapper();

        try {


            return CourseEntity.builder()
                    .title(request.getTitle())
                    .description(request.getDescription())
                    .category(request.getCategory())
                    .creatorId(request.getCreatorId())
                    .teacherIds(mapper.writeValueAsString(request.getTeacherIds()))
                    .studentIds(mapper.writeValueAsString(request.getStudentIds()))
                    .startDate(request.getStartDate())
                    .endDate(request.getEndDate())
                    .rating(request.getRating())
                    .level(request.getLevel())
                    .thumbnail(request.getThumbnail())
                    .color(request.getColor())
                    .skills(mapper.writeValueAsString(request.getSkills()))
                    .price(request.getPrice())
                    .published(request.getPublished())
                    .build();

        } catch (JsonProcessingException e) {
            throw new MondayException("Can't create course entity");
        }
    }

    public static CourseEntity toEntity(CourseUpdateRequest request, Long courseId) {

        ObjectMapper mapper = new ObjectMapper();

        try {


            return CourseEntity.builder()
                    .id(courseId)
                    .title(request.getTitle())
                    .description(request.getDescription())
                    .category(request.getCategory())
                    .creatorId(request.getCreatorId())
                    .teacherIds(mapper.writeValueAsString(request.getTeacherIds()))
                    .studentIds(mapper.writeValueAsString(request.getStudentIds()))
                    .startDate(request.getStartDate())
                    .endDate(request.getEndDate())
                    .rating(request.getRating())
                    .level(request.getLevel())
                    .thumbnail(request.getThumbnail())
                    .color(request.getColor())
                    .skills(mapper.writeValueAsString(request.getSkills()))
                    .price(request.getPrice())
                    .published(request.getPublished())
                    .build();

        } catch (JsonProcessingException e) {
            throw new MondayException("Can't create course entity");
        }
    }

    public static CourseResponse toResponse(CourseEntity entity) {

        ObjectMapper mapper = new ObjectMapper();

        try {

            List<Long> teacherIds = mapper.readValue(entity.getTeacherIds(), new TypeReference<>() { });
            List<Long> studentIds = mapper.readValue(entity.getStudentIds(), new TypeReference<>() { });
            List<String> skills = mapper.readValue(entity.getSkills(), new TypeReference<>() { });

            Long duration = ChronoUnit.DAYS.between(entity.getStartDate(), entity.getEndDate()) / 7;

            return CourseResponse.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .description(entity.getDescription())
                    .category(entity.getCategory())
                    .creatorId(entity.getCreatorId())
                    .teacherIds(teacherIds)
                    .studentIds(studentIds)
                    .startDate(entity.getStartDate())
                    .endDate(entity.getEndDate())
                    .duration(String.format("%d weeks", duration))
                    .rating(entity.getRating())
                    .level(entity.getLevel())
                    .thumbnail(entity.getThumbnail())
                    .color(entity.getColor())
                    .skills(skills)
                    .price(entity.getPrice())
                    .published(entity.getPublished())
                    .build();

        } catch (JsonProcessingException e) {
            throw new MondayException("Can't create course entity");
        }
    }
}
