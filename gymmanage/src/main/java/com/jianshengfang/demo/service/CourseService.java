package com.jianshengfang.demo.service;

import com.jianshengfang.demo.dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    // 添加其他业务方法...

}
