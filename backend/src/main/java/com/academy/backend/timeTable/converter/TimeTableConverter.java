package com.academy.backend.timeTable.converter;

import com.academy.backend.exception.course.TimeTableMappingException;
import com.academy.backend.timeBlock.converter.TimeBlockConverter;
import com.academy.backend.timeBlock.domain.TimeBlock;
import com.academy.backend.timeBlock.dto.response.TimeBlockResponse;
import com.academy.backend.timeTable.dto.response.TimeTableResponse;

import java.util.List;

public class TimeTableConverter {

    public static TimeTableResponse toTimeTableResponse(List<TimeBlock> timeBlocks) {
        List<TimeBlockResponse> timeBlockResponses = timeBlocks.stream()
                .map(TimeBlockConverter::toTimeBlockResponse).toList();

        try {
            return TimeTableResponse.builder()
                    .timeBlocks(timeBlockResponses)
                    .build();
        } catch (Exception e) {
            throw new TimeTableMappingException();
        }
    }

}
