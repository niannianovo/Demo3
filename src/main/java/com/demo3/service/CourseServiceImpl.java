package com.demo3.service;

import com.demo3.mapper.CourseMapper;
import com.demo3.pojo.Course;
import com.demo3.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getCourseList() {
        return courseMapper.selectAll();
    }

    @Override
    public Course getById(Integer id) {
        return courseMapper.getById(id);
    }

    @Override
    public Course getByName(String name) {
        return courseMapper.getByName(name);
    }

    @Override
    public int addCourse(Course course,MultipartFile file) {
        String filePath;
        String fileName;
        String originalFileName = file.getOriginalFilename();
        if(!"".equals(originalFileName)){
            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            fileName = UUID.randomUUID().toString()+suffix;
        } else {
            fileName="nophoto.png";
        }
        filePath = Constants.IMG_PATH+fileName;
        File saveFile = new File(filePath);
        try {
            file.transferTo(saveFile);
            course.setImg(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return courseMapper.add(course);
    }

    @Override
    public int deleteCourse(Integer id) {
        return courseMapper.delete(id);
    }

    @Override
    public int updateCourse(Course course,MultipartFile file) {
        String filePath;
        String fileName;
        String originalFileName = file.getOriginalFilename();
        if(!"".equals(originalFileName)){
            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            fileName = UUID.randomUUID().toString()+suffix;
        } else {
            fileName="nophoto.png";
        }
        filePath = Constants.IMG_PATH+fileName;
        File saveFile = new File(filePath);
        try {
            file.transferTo(saveFile);
            course.setImg(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return courseMapper.update(course);
    }
}
