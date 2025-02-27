package com.academy.backend.course.service;

import com.academy.backend.common.service.CommonService;
import com.academy.backend.common.service.S3Service;
import com.academy.backend.course.converter.CourseConverter;
import com.academy.backend.course.domain.Course;
import com.academy.backend.course.domain.Description;
import com.academy.backend.course.domain.Tag;
import com.academy.backend.course.dto.request.CourseCreateRequest;
import com.academy.backend.course.dto.response.CourseResponse;
import com.academy.backend.course.repository.CourseRepository;
import com.academy.backend.exception.course.CourseNotFoundException;
import com.academy.backend.member.domain.Member;
import com.academy.backend.timeBlock.domain.TimeBlock;
import com.academy.backend.timeBlock.service.TimeBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CommonService commonService;
    private final TimeBlockService timeBlockService;
    private final TagService tagService;
    private final DescriptionService descriptionService;
    private final S3Service s3Service;

    private final CourseRepository courseRepository;

    private static final String path = "courses/";

    @Transactional
    private Course saveCourse(Member member, CourseCreateRequest request, String mainImageUrl) {
        Course course = Course.builder()
                .member(member)
                .title(request.getTitle())
                .cost(request.getCost())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .mainImageUrl(mainImageUrl)
                .isLive(request.getIsLive())
                .isOnline(request.getIsOnline())
                .isRecorded(request.getIsRecorded())
                .build();

        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void createCourse(CourseCreateRequest request, MultipartFile mainImage) {

        // TODO: 관리자 ID로 수정 필요
        Member member = commonService.getMemberByMemberId(1L);
        String mainImageUrl = s3Service.uploadImage(mainImage, path);
        Course course = saveCourse(member, request, mainImageUrl);

        // 입력값에 태그가 주어질 때 태그 생성
        if (!request.getTags().isEmpty()){
            tagService.createTags(course, request.getTags());
        }

        // 강의 설명 생성
        descriptionService.createDescription(course, request.getDescriptions());

        // 수업 시간표 생성
        timeBlockService.createTimeBlock(course, request.getTimetables());
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponse getCourse(Long courseId) {
        Course course = findCourse(courseId);
        List<TimeBlock> timeBlocks = timeBlockService.getTimeBlocksByCourse(course);
        List<Description> descriptions = descriptionService.getDescriptionsByCourse(course);
        List<Tag> tags = tagService.getTagsByCourse(course);

        return CourseConverter.toCourseResponse(course, descriptions, timeBlocks, tags);
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
