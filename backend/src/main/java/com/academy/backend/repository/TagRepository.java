package com.academy.backend.repository;

import com.academy.backend.domain.course.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(

    )
    List<Tag> findTagsByCourseId(Long courseId);
}
