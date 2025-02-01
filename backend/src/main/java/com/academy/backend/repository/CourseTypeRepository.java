package com.academy.backend.repository;

import com.academy.backend.domain.course.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTypeRepository extends JpaRepository<CourseType, Long> {
    public List<CourseType> findByCourseId(Long courseId);
}
