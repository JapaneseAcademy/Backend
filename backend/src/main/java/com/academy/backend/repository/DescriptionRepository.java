package com.academy.backend.repository;

import com.academy.backend.domain.course.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {

    List<Description> findDescriptionsByCourseId(Long courseId);
}
