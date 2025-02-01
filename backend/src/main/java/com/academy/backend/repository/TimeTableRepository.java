package com.academy.backend.repository;

import com.academy.backend.domain.timetable.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
    public List<TimeTable> findByCourseId(Long courseId);
}
