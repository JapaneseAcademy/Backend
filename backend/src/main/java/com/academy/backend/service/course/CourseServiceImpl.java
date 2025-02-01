package com.academy.backend.service.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.course.CourseType;
import com.academy.backend.domain.course.Tag;
import com.academy.backend.domain.member.Member;
import com.academy.backend.domain.timetable.TimeTable;
import com.academy.backend.dto.request.CourseCreateRequest;
import com.academy.backend.dto.response.course.CourseCreateResponse;
import com.academy.backend.dto.response.course.CourseResponse;
import com.academy.backend.exception.course.CourseNotFoundException;
import com.academy.backend.repository.CourseRepository;
import com.academy.backend.repository.TagRepository;
import com.academy.backend.service.member.MemberService;
import com.academy.backend.service.timetable.TimeTableService;
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
    private final CourseTypeService courseTypeService;
    private final TimeTableService timeTableService;

    private final CourseRepository courseRepository;
    private final TagRepository tagRepository;

    @Override
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
            courseTagService.createCourseTag(course, tags);
        }

        // 수업 시간표 생성
        timeTableService.createTimeTable(course, request.getTimetables());

        // 수업 유형 별 비용 생성
        courseTypeService.createCourseType(course, request.getCourseTypes());

        return CourseCreateResponse.of(course);
    }

    @Transactional
    private List<Tag> createTags(List<String> tags) {
        List<Tag> savedTags = new ArrayList<>();

        tags.forEach(tag -> {
            Tag saved = tagRepository.findByLabel(tag)
                    .orElseGet(() -> tagRepository.save(Tag.builder().label(tag).build())); // 존재하면 가져오고, 없으면 저장
            savedTags.add(saved);
        });

        return savedTags;
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponse getCourse(Course course) {
        List<TimeTable> timeTables = timeTableService.getTimeTablesByCourse(course);
        List<CourseType> courseTypes = courseTypeService.getCourseTypesByCourse(course);
        List<Tag> tags = courseTagService.getTagsByCourse(course);

        return CourseResponse.of(course, timeTables, courseTypes, tags);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> getCourse(course))
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
    public void deleteCourse(Long courseId) {
        Course course = findCourse(courseId);
        courseRepository.delete(course);
    }
}
