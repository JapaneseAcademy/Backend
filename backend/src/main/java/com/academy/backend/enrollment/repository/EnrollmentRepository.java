package com.academy.backend.enrollment.repository;

import com.academy.backend.enrollment.domain.Enrollment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("SELECT e " +
            "FROM Enrollment e " +
            "JOIN FETCH e.member m " +
            "WHERE m.loginId = :loginId")
    List<Enrollment> findEnrollmentsByLoginId(@Param("loginId") String loginId);
}
