package com.academy.backend.course.converter;

import com.academy.backend.course.domain.Course;
import com.academy.backend.course.domain.Description;
import com.academy.backend.course.domain.Tag;
import com.academy.backend.course.dto.response.CourseResponse;
import com.academy.backend.timeBlock.converter.TimeBlockConverter;
import com.academy.backend.timeBlock.domain.TimeBlock;
import com.academy.backend.timeBlock.dto.response.TimeBlockResponse;
import com.academy.backend.exception.course.CourseMappingException;

import java.util.List;

public class CourseConverter {

    public static CourseResponse toCourseResponse(Course course, List<Description> descriptions, List<TimeBlock> timeBlocks, List<Tag> tags) {
        try {
            List<String> descriptionImageUrls = descriptions.stream()
                    .map(Description::getImageUrl).toList();

            List<String> tagLabels = tags.stream()
                    .map(Tag::getLabel).toList();

            List<TimeBlockResponse> timeBlockRespons = timeBlocks.stream()
                    .map(TimeBlockConverter::toTimeTableResponse).toList();

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
                    .timeTables(timeBlockRespons)
                    .tags(tagLabels)
                    .build();
        } catch (Exception e) {
            throw new CourseMappingException();
        }
    }
}
