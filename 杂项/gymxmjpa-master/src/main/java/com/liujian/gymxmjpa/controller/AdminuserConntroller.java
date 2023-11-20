package com.liujian.gymxmjpa.controller;



import com.liujian.gymxmjpa.dao.AdminuserDao;
import com.liujian.gymxmjpa.entity.Adminuser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 管理员登录Controller控制层
 */
@Controller
@RequestMapping("/")
public class AdminuserConntroller {
    @Autowired
    private AdminuserDao adminuserDao;

    /**
     * @Description: 输入端口号直接跳转登录界面
     */
    @RequestMapping("/")
    public String beforeLogin(){
        return "login";
    }

    /**
     * @Description: 管理员登录验证方法
     */
    @RequestMapping("/dl/yz")
    public String login(String username, String password, HttpSession httpSession, Model model){
        // 获取当前 Subject，用于认证登录
        Subject subject = SecurityUtils.getSubject();
        // 创建用于登录的用户名密码令牌
        UsernamePasswordToken userToken = new UsernamePasswordToken(username, DigestUtils.md5Hex(password));
        try {
            // 进行登录验证
            subject.login(userToken);
            // 从数据库中查找管理员用户
            Adminuser a = adminuserDao.findByAdminNameAndAdminPassword(username, DigestUtils.md5Hex(password));
            // 将用户信息存储在 Session 中
            httpSession.setAttribute("user", a);
            return "WEB-INF/jsp/index";
        } catch (UnknownAccountException e) {
            // 处理用户名或密码错误的异常
            model.addAttribute("msg", "用户名或密码错误，请重新输入");
            return "login";
        }
    }

    /**
     * @Description: 退出登录后清楚session
     */
    @RequestMapping("/logout")
    public String logout() {
        // 获取当前 Subject，执行退出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

    /**
     * @Description: 跳转到修改密码界面
     */
    @RequestMapping("/updPassword")
    public String updPassword() {
        return "WEB-INF/jsp/updPassword";
    }

    /**
     * @Description: 修改密码
     */
    @RequestMapping("/upd/updPassword")
    public String updPasswordConfirm(String oldPassword, String newPassword, String newPasswordAgain, HttpSession httpSession, Model model) {
        // 定义密码的正则表达式模式
        Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!.%*#?&])[A-Za-z\\d$@$!.%*#?&]{8,}$");
        // 对新密码进行匹配验证
        Matcher m = p.matcher(newPassword);
        // 如果新密码不符合要求，则返回错误信息
        if (!m.matches()) {
            model.addAttribute("msg", "新密码最少为8位并且包含字母、数字和特殊字符");
            return "WEB-INF/jsp/updPassword";
        }
        // 如果两次输入的新密码不一致，则返回错误信息
        if (!newPassword.equals(newPasswordAgain)) {
            model.addAttribute("msg", "两次输入的新密码不一致，请重新输入");
            return "WEB-INF/jsp/updPassword";
        }
        // 从 Session 中获取管理员用户信息
        Adminuser adminuser = (Adminuser) httpSession.getAttribute("user");
        // 如果获取到了管理员用户
        if (null != adminuser) {
            // 如果原密码不正确，则返回错误信息
            if (!adminuser.getAdminPassword().equals(DigestUtils.md5Hex(oldPassword))) {
                model.addAttribute("msg", "原密码不正确，请重新输入");
                return "WEB-INF/jsp/updPassword";
            }
            // 更新密码并返回登录页面
            adminuserDao.updPassword(adminuser.getAdminId(), DigestUtils.md5Hex(newPassword));
        }
        // 执行退出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }
}
