package com.academy.backend.timeTable.repository;

import com.academy.backend.timeTable.domain.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    @Query("SELECT t.id " +
            "FROM TimeTable t " +
            "WHERE t.course.id = :courseId")
    List<Long> findTimeTableIdByCourseId(@Param("courseId") Long courseId);
}
