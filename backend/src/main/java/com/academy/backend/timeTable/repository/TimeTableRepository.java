package com.academy.backend.timeTable.repository;

import com.academy.backend.timeTable.domain.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
}
