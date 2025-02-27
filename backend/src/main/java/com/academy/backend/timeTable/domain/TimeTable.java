package com.academy.backend.timeTable.domain;

import com.academy.backend.course.domain.Course;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeTableId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    @Builder
    public TimeTable(Course course) {
        this.course = course;
    }
}
