package com.academy.backend.repository;

import com.academy.backend.domain.enrollment.Enrollment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("SELECT e " +
            "FROM Enrollment e " +
            "JOIN FETCH e.member m " +
            "WHERE m.loginId = :loginId")
    List<Enrollment> findEnrollmentsByLoginId(@Param("loginId") String loginId);
}
