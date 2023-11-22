package com.jianshengfang.demo.service;

import com.jianshengfang.demo.dao.CoachDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachService {

    @Autowired
    private CoachDao coachDao;
}
