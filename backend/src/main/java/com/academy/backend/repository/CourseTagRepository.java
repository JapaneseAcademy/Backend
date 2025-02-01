package com.academy.backend.repository;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.course.CourseTag;
import com.academy.backend.domain.course.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTagRepository extends JpaRepository<CourseTag, Long> {
    @Query("SELECT ct.tag FROM CourseTag ct WHERE ct.course.id = :courseId")
    List<Tag> findTagsByCourseId(@Param("courseId") Long courseId);
}
