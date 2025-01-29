package com.academy.backend.service.course;

import com.academy.backend.domain.Course;
import com.academy.backend.domain.member.Member;
import com.academy.backend.dto.request.CourseCreateRequest;
import com.academy.backend.dto.response.CourseCreateResponse;
import com.academy.backend.exception.member.UserNotFoundException;
import com.academy.backend.repository.CourseRepository;
import com.academy.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public CourseCreateResponse createCourse(CourseCreateRequest request) {
        Long memberId = request.getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new UserNotFoundException(memberId)
        );

        Course course = Course.builder()
                .member(member)
                .title(request.getTitle())
                .description(request.getDescription())
                .build();

        Course result = courseRepository.save(course);

        return CourseCreateResponse.of(result);
    }
}
