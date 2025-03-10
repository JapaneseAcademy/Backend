package com.academy.backend.enrollment.repository;

import com.academy.backend.course.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(

    )
    List<Tag> findTagsByCourseId(Long courseId);
}
