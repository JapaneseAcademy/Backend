package com.academy.backend.service.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.course.Description;
import com.academy.backend.domain.course.Tag;
import com.academy.backend.domain.member.Member;
import com.academy.backend.domain.timetable.TimeTable;
import com.academy.backend.dto.request.CourseCreateRequest;
import com.academy.backend.dto.response.course.CourseCreateResponse;
import com.academy.backend.dto.response.course.CourseResponse;
import com.academy.backend.exception.course.CourseNotFoundException;
import com.academy.backend.repository.CourseRepository;
import com.academy.backend.service.member.MemberService;
import com.academy.backend.service.timetable.TimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final MemberService memberService;
    private final TimeTableService timeTableService;
    private final TagService tagService;
    private final DescriptionService descriptionService;

    private final CourseRepository courseRepository;

    @Transactional
    private Course saveCourse(Member member, CourseCreateRequest request) {
        Course course = Course.builder()
                .member(member)
                .title(request.getTitle())
                .cost(request.getCost())
                .build();

        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public CourseCreateResponse createCourse(CourseCreateRequest request) {
        // 관리자 ID로 수정 필요
        Member member = memberService.getMemberById(1L);
        Course course = saveCourse(member, request);

        // 입력값에 태그가 주어질 때 태그 생성
        if (!request.getTags().isEmpty()){
            tagService.createTags(course, request.getTags());
        }

        // 강의 설명 생성
        descriptionService.createDescription(course, request.getDescriptions());

        // 수업 시간표 생성
        timeTableService.createTimeTable(course, request.getTimetables());

        return CourseCreateResponse.of(course);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponse getCourse(Long courseId) {
        Course course = findCourse(courseId);
        List<TimeTable> timeTables = timeTableService.getTimeTablesByCourse(course);
        List<Description> descriptions = descriptionService.getDescriptionsByCourse(course);
        List<Tag> tags = tagService.getTagsByCourse(course);

        return CourseResponse.of(course, descriptions, timeTables, tags);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> getCourse(course.getId()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Course findCourse(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(
                () -> new CourseNotFoundException(courseId)
        );
    }

    @Override
    @Transactional
    public void deleteCourse(Long courseId) {
        Course course = findCourse(courseId);
        courseRepository.delete(course);
    }
}
