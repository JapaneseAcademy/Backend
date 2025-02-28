package com.academy.backend.course.converter;

import com.academy.backend.course.domain.Course;
import com.academy.backend.course.domain.Description;
import com.academy.backend.course.domain.Tag;
import com.academy.backend.course.dto.response.CourseDetailResponse;
import com.academy.backend.exception.course.CourseMappingException;
import com.academy.backend.timeTable.dto.response.TimeTableResponse;

import java.util.List;

public class CourseConverter {

    public static CourseDetailResponse toCourseDetailResponse(Course course, List<Description> descriptions, List<TimeTableResponse> timeTables, List<Tag> tags) {
        try {
            List<String> descriptionImageUrls = descriptions.stream()
                    .map(Description::getImageUrl).toList();

            List<String> tagLabels = tags.stream()
                    .map(Tag::getLabel).toList();

            return CourseDetailResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .cost(course.getCost())
                    .startDate(course.getStartDate())
                    .endDate(course.getEndDate())
                    .mainImageUrl(course.getMainImageUrl())
                    .isLive(course.getIsLive())
                    .isOnline(course.getIsOnline())
                    .isRecorded(course.getIsRecorded())
                    .descriptions(descriptionImageUrls)
                    .timeTables(timeTables)
                    .tags(tagLabels)
                    .build();
        } catch (Exception e) {
            throw new CourseMappingException();
        }
    }
}
