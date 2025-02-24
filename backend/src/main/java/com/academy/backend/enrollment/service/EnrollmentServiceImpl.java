package com.academy.backend.enrollment.service;

import com.academy.backend.config.jwt.JwtProvider;
import com.academy.backend.course.domain.Course;
import com.academy.backend.enrollment.domain.Category;
import com.academy.backend.enrollment.domain.Enrollment;
import com.academy.backend.exception.enrollment.UnavailableCategoryException;
import com.academy.backend.member.domain.Member;
import com.academy.backend.enrollment.dto.request.EnrollmentCreateRequest;
import com.academy.backend.enrollment.dto.response.EnrollmentResponse;
import com.academy.backend.enrollment.repository.EnrollmentRepository;
import com.academy.backend.course.service.CourseService;
import com.academy.backend.member.service.MemberService;
import com.academy.backend.auth.service.AuthService;
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
    private final AuthService authService;

    private final EnrollmentRepository enrollmentRepository;

    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public void createEnrollment(String header, EnrollmentCreateRequest request) {
        /***********************************
         * TODO: 결제 관련 api 연결하고 비즈니스 로직 구현해야 함
         ************************************/

        // 사용자 식별
        String token = authService.extractToken(header);
        String loginId = jwtProvider.getLoginIdFromAccessToken(token);
        Member member = memberService.getMemberByLoginId(loginId);

        Course course = courseService.findCourse(request.getCourseId());
        validateCategory(course, request.getCategory());

        // 수강 정보 등록
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
    public List<EnrollmentResponse> getEnrollments(String header) {
        String token = authService.extractToken(header);
        String loginId = jwtProvider.getLoginIdFromAccessToken(token);

        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentsByLoginId(loginId);

        return enrollments.stream().map(EnrollmentResponse::of).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EnrollmentResponse getEnrollmentById(Long enrollmentId) {
        // TODO: 사용자 검증 로직 필요
        // TODO: EnrollmentResponse에 Review 추가
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(
                () -> new RuntimeException("there is no enrollment with id : " + enrollmentId));

        return EnrollmentResponse.of(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public Enrollment getEnrollmentEntityById(Long enrollmentId) {
        return enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id " + enrollmentId));
    }
}
