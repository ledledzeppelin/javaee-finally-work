package com.jianshengfang.demo.service;

import com.jianshengfang.demo.dao.UserDao;
import com.jianshengfang.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userdao;

    public User registerUser(String username, String password,String account, String phonenumber) {
        User newUser = new User();
        newUser.setName(username);
        newUser.setPassword(password);
        newUser.setAccount(account);
        newUser.setPhoneNumber(phonenumber);

        // 进行一些验证或其他必要的逻辑...

        return userdao.save(newUser);
    }

    public User loginUser(String username, String password) {
        // 通过用户名和密码验证用户
        User user = userdao.findByAccountAndPassword(username, password);
        if (user != null) {
            // 登录成功
            return user;
        } else {
            // 登录失败
            throw new IllegalArgumentException("Invalid username or password");
        }
    }



    // 其他可能的方法...
}
