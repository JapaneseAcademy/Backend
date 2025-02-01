package com.academy.backend.service.course;

import com.academy.backend.domain.course.Course;
import com.academy.backend.domain.course.CourseType;
import com.academy.backend.dto.request.CourseTypeRequest;
import com.academy.backend.repository.CourseTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseTypeServiceImpl implements CourseTypeService {

    private final CourseTypeRepository courseTypeRepository;


    @Override
    @Transactional
    public List<CourseType> createCourseType(Course course, List<CourseTypeRequest> requests) {
        List<CourseType> courseTypes = new ArrayList<>();

        requests.forEach(request -> {
            CourseType courseType = CourseType.builder()
                    .course(course)
                    .category(request.getCategory())
                    .cost(request.getCost())
                    .build();
            CourseType saved = courseTypeRepository.save(courseType);
            courseTypes.add(saved);
        });

        return courseTypes;
    }
}
