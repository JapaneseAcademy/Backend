package com.academy.backend.review.repository;

import com.academy.backend.review.domain.Review;
import com.academy.backend.review.dto.response.BestReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r " +
            "FROM Review r " +
            "LEFT JOIN r.enrollment e " +
            "LEFT JOIN FETCH r.reviewImages ri " +
            "WHERE e.course.id = :courseId " +
            "AND r.isVisible = true")
    Page<Review> findByCourseId(@Param("courseId") Long courseId, Pageable pageable);

    boolean existsByEnrollmentId(Long enrollmentId);

    Page<Review> findAll(Pageable pageable);

    @Query("SELECT r " +
            "FROM Review r " +
            "LEFT JOIN FETCH r.reviewImages ri " +
            "WHERE r.enrollment.id = :enrollmentId")
    Optional<Review> findByEnrollmentId(@Param("enrollmentId") Long enrollmentId);
}
