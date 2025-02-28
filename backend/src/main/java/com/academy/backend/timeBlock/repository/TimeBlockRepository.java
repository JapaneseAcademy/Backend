package com.academy.backend.timeBlock.repository;

import com.academy.backend.timeBlock.domain.TimeBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeBlockRepository extends JpaRepository<TimeBlock, Long> {

    @Query("SELECT t " +
            "FROM TimeBlock t " +
            "WHERE t.timeTable.id = :timeTableId")
    List<TimeBlock> findByTimeTableId(Long timeTableId);
}
