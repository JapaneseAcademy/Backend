package com.academy.backend.course.repository;

import com.academy.backend.course.domain.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {

    List<Description> findDescriptionsByCourseId(Long courseId);
}
