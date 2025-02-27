package com.academy.backend.timeTable.service;

import com.academy.backend.timeTable.repository.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {

    private final TimeTableRepository timeTableRepository;


}
