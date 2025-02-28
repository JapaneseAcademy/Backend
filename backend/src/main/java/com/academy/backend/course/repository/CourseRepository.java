package com.academy.backend.course.repository;

import com.academy.backend.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c " +
            "FROM Course c " +
            "WHERE c.isActive = true and c.id = :courseId")
    Optional<Course> findById(Long courseId);

    @Query("SELECT c " +
            "FROM Course c " +
            "WHERE c.isActive = true")
    List<Course> findAll();
}
