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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "descriptionId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    @Column(nullable = false)
    private String imageUrl;

    @Builder
    public Description(Course course, String imageUrl) {
        this.course = course;
        this.imageUrl = imageUrl;
    }
}
