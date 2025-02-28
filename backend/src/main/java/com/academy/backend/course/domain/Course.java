package com.academy.backend.course.domain;

import com.academy.backend.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private Integer cost;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, length = 2048)
    private String mainImageUrl;

    @Column(nullable = false)
    private Boolean isFeatured = false;

    @Column(nullable = false)
    private Boolean isLive;

    @Column(nullable = false)
    private Boolean isOnline;

    @Column(nullable = false)
    private Boolean isRecorded;

    private int studentCount = 0;

    @Builder
    public Course(Member member, String title, Integer cost, LocalDate startDate, LocalDate endDate, String mainImageUrl, Boolean isLive, Boolean isOnline, Boolean isRecorded) {
        this.member = member;
        this.title = title;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mainImageUrl = mainImageUrl;
        this.isLive = isLive;
        this.isOnline = isOnline;
        this.isRecorded = isRecorded;
    }

    public void addStudent() {
        this.studentCount++;
    }

    public void subStudent() {
        this.studentCount--;
    }
}
