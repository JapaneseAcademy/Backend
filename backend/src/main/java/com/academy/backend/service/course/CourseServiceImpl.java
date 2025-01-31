package com.academy.backend.service.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.course.Tag;
import com.academy.backend.domain.member.Member;
import com.academy.backend.dto.request.CourseCreateRequest;
import com.academy.backend.dto.response.CourseCreateResponse;
import com.academy.backend.exception.course.TagNotFoundException;
import com.academy.backend.exception.member.UserMappingException;
import com.academy.backend.exception.member.UserNotFoundException;
import com.academy.backend.repository.CourseRepository;
import com.academy.backend.repository.CourseTagRepository;
import com.academy.backend.repository.MemberRepository;
import com.academy.backend.repository.TagRepository;
import com.academy.backend.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final MemberService memberService;
    private final CourseTagService courseTagService;

    private final CourseRepository courseRepository;
    private final TagRepository tagRepository;

    @Transactional
    public Course saveCourse(Member member, CourseCreateRequest request) {
        Course course = Course.builder()
                .member(member)
                .title(request.getTitle())
                .description(request.getDescription())
                .build();

        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public CourseCreateResponse createCourse(CourseCreateRequest request) {
        Member member = memberService.getMemberById(request.getMemberId());

        Course course = saveCourse(member, request);

        // 입력값에 태그가 주어질 때 존재하지 않는 태그 검증 및 생성
        if (!request.getTags().isEmpty()){
            List<Tag> tags = createTags(request.getTags());
            courseTagService.craeteCourseTag(course, tags);
        }

        return CourseCreateResponse.of(course);
    }

    @Transactional
    private List<Tag> createTags(List<String> tags) {
        List<Tag> savedTags = new ArrayList<>();

        tags.forEach(tag -> {
            Tag savedTag = tagRepository.findByLabel(tag)
                    .orElseGet(() -> tagRepository.save(Tag.builder().label(tag).build())); // 존재하면 가져오고, 없으면 저장
            savedTags.add(savedTag); // 새 리스트에 추가
        });

        return savedTags;
    }
}
