package com.academy.backend.domain.review;

import com.academy.backend.domain.enrollment.Enrollment;
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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollmentId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Enrollment enrollment;

    @Column(length = 500)
    private String review;

    private Boolean isFeatured = false;
    private Boolean isAnonymous;
    private Boolean isVisible = true;

    @Builder
    public Review(Enrollment enrollment, String review, Boolean isAnonymous) {
        this.enrollment = enrollment;
        this.review = review;
        this.isAnonymous = isAnonymous;
    }
}
