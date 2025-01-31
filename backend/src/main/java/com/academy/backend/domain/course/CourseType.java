package com.academy.backend.domain.course;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Table(name = "COURSE_TYPE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseTypeId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer cost;

    @Builder
    public CourseType(Course course, Category category, int cost) {
        this.course = course;
        this.category = category;
        this.cost = cost;
    }
}
