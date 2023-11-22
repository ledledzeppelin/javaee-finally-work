package com.jianshengfang.demo.controller;

import com.jianshengfang.demo.dao.CourseDao;
import com.jianshengfang.demo.entity.Course;
import com.jianshengfang.demo.service.CoachService;
import com.jianshengfang.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coaches")
public class CoachController {

 @Autowired
 private CoachService coachService;

 @Autowired
 private CourseService courseService;

    // 添加其他控制器方法...
    @PostMapping("/startCourse")
    public ResponseEntity<String> startCourse(@RequestBody Course course) {
     String result = courseService.startCourse(course);
     if ("success".equals(result)) {
      return ResponseEntity.ok("Course started successfully.");
     } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
     }
    }

}
