package com.milotnt.controller;

import com.milotnt.pojo.Admin;
import com.milotnt.pojo.Member;
import com.milotnt.service.AdminService;
import com.milotnt.service.EmployeeService;
import com.milotnt.service.EquipmentService;
import com.milotnt.service.MemberService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EquipmentService equipmentService;
    @ApiOperation("跳转登录界面")
    @GetMapping("/")
    public String toAdminLogin() {
        return "adminLogin";
    }

    Member member1;
    @GetMapping("/toUserLogin")
    public String toUserLogin() {
        return "userLogin";
    }
    @ApiOperation("管理员登录")
    @PostMapping("/adminLogin")
    public ResponseEntity<String> adminLogin(@RequestBody Admin admin) {
        Admin admin1 = adminService.adminLogin(admin);
        if (admin1 != null) {
            Integer memberTotal = memberService.selectTotalCount();
            Integer employeeTotal = employeeService.selectTotalCount();
            Integer humanTotal = memberTotal + employeeTotal;
            Integer equipmentTotal = equipmentService.selectTotalCount();

            // 根据需要构建返回的 JSON 数据，这里假设返回一个成功消息
            String responseBody = "memberTotal: " + memberTotal +
                    ", employeeTotal: " + employeeTotal +
                    ", humanTotal: " + humanTotal +
                    ", equipmentTotal: " + equipmentTotal;
            return ResponseEntity.ok(responseBody);
        }
        return ResponseEntity.badRequest().body("登录失败！");
    }
    @ApiOperation("用户登录")
    @PostMapping("/userLogin")
    public ResponseEntity<String> userLogin(@RequestBody Member member) {
        Member loggedInMember = memberService.userLogin(member);
        if (loggedInMember != null) {
            // 在这里生成 Token，可以使用 Spring Security 或其他的认证机制
            String token = generateToken(loggedInMember); // 这里需要实现生成 Token 的方法

            // 返回 Token 给前端
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().body("登录失败！");
    }

    // ... 其他的方法 ...

    // 这是一个示例方法，实际上你需要根据你的实际情况生成 Token
    public String generateToken(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", member.getMemberAccount());
        claims.put("username", member.getMemberName());
        claims.put("age", member.getMemberAge());
        claims.put("gender", member.getMemberGender());
        claims.put("phone", member.getMemberPhone());
        claims.put("height", member.getMemberHeight());
        claims.put("weight", member.getMemberWeight());
        // 可以添加其他需要保存在 Token 中的信息

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000); // 1天的有效期，单位为毫秒

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .compact();
    }


    @ApiOperation("跳转管理员主界面")
    @GetMapping("/toAdminMain")
    public ResponseEntity<String> toAdminMain() {
        Integer memberTotal = memberService.selectTotalCount();
        Integer employeeTotal = employeeService.selectTotalCount();
        Integer humanTotal = memberTotal + employeeTotal;
        Integer equipmentTotal = equipmentService.selectTotalCount();

        // 根据需要构建返回的 JSON 数据，这里假设返回一些数据信息
        String responseBody = "memberTotal: " + memberTotal +
                ", employeeTotal: " + employeeTotal +
                ", humanTotal: " + humanTotal +
                ", equipmentTotal: " + equipmentTotal;

        return ResponseEntity.ok(responseBody);
    }
    @ApiOperation("跳转用户主界面")
    @GetMapping("/toUserMain")
    public ResponseEntity<Member> toUserMain() {


        return ResponseEntity.ok(member1);
    }
}



