package com.academy.backend.domain.enrollment;

import com.academy.backend.domain.BaseTimeEntity;
import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Enrollment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollmentId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private Integer paymentAmount;

    @Column(nullable = false)
    private LocalDateTime paymentAt;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Builder
    public Enrollment(Member member, Course course, Category category, Integer paymentAmount, LocalDateTime paymentAt, LocalDate startDate, LocalDate endDate) {
        this.member = member;
        this.course = course;
        this.category = category;
        this.paymentAmount = paymentAmount;
        this.paymentAt = paymentAt;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
