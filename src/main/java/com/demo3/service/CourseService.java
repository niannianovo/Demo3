package com.demo3.service;

import com.demo3.pojo.Course;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface CourseService {
    List<Course> getCourseList();
    Course getById(Integer id);
    Course getByName(String name);
    int addCourse(Course course,MultipartFile file);
    int deleteCourse(Integer id);
    int updateCourse(Course course,MultipartFile file);
}
