package com.academy.backend.timeTable.dto.response;


import com.academy.backend.timeBlock.dto.response.TimeBlockResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TimeTableResponse {

    List<TimeBlockResponse> timeBlocks;
}
