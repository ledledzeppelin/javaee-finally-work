package com.jianshengfang.demo.service;

import com.jianshengfang.demo.dao.LogDao;
import com.jianshengfang.demo.entity.Log;
import com.jianshengfang.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogService {

    @Autowired
    private  LogDao logRepository;




    public void recordUserAction(User user, String actionDescription) {
        Log log = new Log();
        log.setUser(user);
        log.setDescription(actionDescription);
        log.setBeginDate(new Date());
        // You can set other relevant attributes as needed
        logRepository.save(log);
    }
}
