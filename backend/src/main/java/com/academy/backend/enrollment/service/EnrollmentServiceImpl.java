package com.academy.backend.enrollment.service;

import com.academy.backend.config.auth.PrincipalDetailsService;
import com.academy.backend.course.domain.Course;
import com.academy.backend.course.service.CourseService;
import com.academy.backend.enrollment.converter.EnrollmentConverter;
import com.academy.backend.enrollment.domain.Category;
import com.academy.backend.enrollment.domain.Enrollment;
import com.academy.backend.enrollment.dto.request.EnrollmentCreateRequest;
import com.academy.backend.enrollment.dto.response.EnrollmentDetailResponse;
import com.academy.backend.enrollment.dto.response.EnrollmentResponse;
import com.academy.backend.enrollment.repository.EnrollmentRepository;
import com.academy.backend.exception.auth.UserForbiddenException;
import com.academy.backend.exception.enrollment.EnrollmentNotFoundException;
import com.academy.backend.exception.enrollment.UnavailableCategoryException;
import com.academy.backend.member.domain.Member;
import com.academy.backend.member.service.MemberService;
import com.academy.backend.review.domain.Review;
import com.academy.backend.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService{

    private final MemberService memberService;
    private final CourseService courseService;
    private final ReviewService reviewService;

    private final EnrollmentRepository enrollmentRepository;

    @Override
    @Transactional
    public void createEnrollment(EnrollmentCreateRequest request) {
        /***********************************
         * TODO: 결제 관련 api 연결하고 비즈니스 로직 구현해야 함
         ************************************/

        Long memberId = PrincipalDetailsService.getCurrentMemberId();
        Member member = memberService.getMemberById(memberId);

        Course course = courseService.findCourse(request.getCourseId());
        validateCategory(course, request.getCategory());

        saveEnrollment(member, course, request);
    }

    private void validateCategory(Course course, Category category) {
        if (category.equals(Category.LIVE) && !course.getIsLive()
                || category.equals(Category.ONLINE) && !course.getIsOnline()
                || category.equals(Category.RECORDED) && !course.getIsRecorded()) {
            throw new UnavailableCategoryException(course.getId());
        }
    }

    private void saveEnrollment(Member member, Course course, EnrollmentCreateRequest request) {
        Enrollment enrollment = Enrollment.builder()
                .member(member)
                .course(course)
                .category(request.getCategory())
                .paymentAmount(course.getCost())
                .paymentAt(LocalDateTime.now())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();

        enrollmentRepository.save(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getEnrollmentsForUser() {
        Long memberId = PrincipalDetailsService.getCurrentMemberId();

        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentsByMemberId(memberId);

        return enrollments.stream().map(enrollment -> {
            Boolean reviewed = reviewService.getExistsByEnrollmentId(enrollment.getId());
            return EnrollmentConverter.toEnrollmentResponse(enrollment, reviewed);
        }).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EnrollmentDetailResponse getEnrollmentById(Long enrollmentId) {
        // TODO: EnrollmentResponse에 Review 추가
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentNotFoundException(enrollmentId));

        Long memberId = PrincipalDetailsService.getCurrentMemberId();
        if (!enrollment.getMember().getId().equals(memberId)) {
            throw new UserForbiddenException(memberId);
        }

        Review review = reviewService.getReviewByEnrollmentId(enrollmentId);

        return EnrollmentConverter.toEnrollmentDetailResponse(enrollment, review);
    }
}
