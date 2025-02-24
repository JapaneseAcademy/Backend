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

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r " +
            "FROM Review r " +
            "LEFT JOIN r.enrollment e " +
            "LEFT JOIN FETCH r.reviewImages ri " +
            "WHERE e.course.id = :courseId " +
            "AND r.isVisible = true")
    Page<Review> findByCourseId(@Param("courseId") Long courseId, Pageable pageable);

//    @Query("SELECT new com.academy.backend.review.dto.response.BestReviewResponse" +
//            "(c.title, r.title, r.review, r.name, ri.imageUrl, r.isAnonymous, r.createdAt)")
//    List<BestReviewResponse> findBestReviews();

    boolean existsByEnrollmentId(Long enrollmentId);

    Page<Review> findAll(Pageable pageable);
}
