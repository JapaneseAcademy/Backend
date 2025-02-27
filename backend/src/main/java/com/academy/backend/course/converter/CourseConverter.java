package com.academy.backend.course.converter;

import com.academy.backend.course.domain.Course;
import com.academy.backend.course.domain.Description;
import com.academy.backend.course.domain.Tag;
import com.academy.backend.course.dto.response.CourseResponse;
import com.academy.backend.timeTable.converter.TimeTableConverter;
import com.academy.backend.timeTable.dto.response.TimeTableResponse;
import com.academy.backend.exception.course.CourseMappingException;
import com.academy.backend.timeTable.domain.TimeTable;

import java.util.List;

public class CourseConverter {

    public static CourseResponse toCourseResponse(Course course, List<Description> descriptions, List<TimeTable> timeTables, List<Tag> tags) {
        try {
            List<String> descriptionImageUrls = descriptions.stream()
                    .map(Description::getImageUrl).toList();

            List<String> tagLabels = tags.stream()
                    .map(Tag::getLabel).toList();

            List<TimeTableResponse> timeTableResponses = timeTables.stream()
                    .map(TimeTableConverter::toTimeTableResponse).toList();

            return CourseResponse.builder()
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
                    .timeTables(timeTableResponses)
                    .tags(tagLabels)
                    .build();
        } catch (Exception e) {
            throw new CourseMappingException();
        }
    }
}
