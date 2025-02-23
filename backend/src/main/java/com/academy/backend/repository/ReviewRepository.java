package com.academy.backend.repository;

import com.academy.backend.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r JOIN FETCH r.enrollment e WHERE e.course.id = :courseId")
    List<Review> findByCourseId(@Param("courseId") Long courseId);
}
