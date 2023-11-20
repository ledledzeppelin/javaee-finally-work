package com.jianshengfang.demo.controller;

import com.jianshengfang.demo.entity.User;
import com.jianshengfang.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
// 假设有一个名为UserService的用户服务类

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService; // 自动注入用户服务类


    @RequestMapping("/")
    public String login() {
        return "login";
    }
    // 显示登录页面
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // 返回登录页面的视图名，比如login.jsp
    }

    // 处理用户登录请求
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        // 调用UserService进行登录验证
        User user =userService.loginUser(username, password);
        if (user!=null) {
            // 登录成功，执行相应操作，比如设置Session等
            return "redirect:/dashboard"; // 重定向到仪表板或其他页面
        } else {
            // 登录失败，返回登录页面并显示错误信息
            model.addAttribute("error", "Invalid username or password");
            return "login"; // 返回登录页面的视图名
        }
    }

    // 显示注册页面
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // 返回注册页面的视图名，比如register.jsp
    }

    // 处理用户注册请求
    @PostMapping("/register")
    public String register(@RequestParam("name") String name,@RequestParam("password") String password,
                           @RequestParam("account") String account,@RequestParam("phoneNumber") String phonenumber ,Model model) {
        // 调用UserService进行用户注册
        if (userService.registerUser(name, password,account,phonenumber)!=null ){
            // 注册成功，可以执行相关操作，比如跳转到登录页面
            return "redirect:/user/login"; // 重定向到登录页面
        } else {
            // 注册失败，返回注册页面并显示错误信息
            model.addAttribute("error", "Username already exists");
            return "register"; // 返回注册页面的视图名
        }
    }
}
