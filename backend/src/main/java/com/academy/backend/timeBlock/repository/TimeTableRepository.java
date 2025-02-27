package com.academy.backend.timeBlock.repository;

import com.academy.backend.timeBlock.domain.TimeBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeBlock, Long> {
    public List<TimeBlock> findByCourseId(Long courseId);
}
