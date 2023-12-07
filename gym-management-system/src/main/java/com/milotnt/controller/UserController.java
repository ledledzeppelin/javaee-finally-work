package com.milotnt.controller;

import com.milotnt.pojo.ClassOrder;
import com.milotnt.pojo.ClassTable;
import com.milotnt.pojo.Member;
import com.milotnt.service.ClassOrderService;
import com.milotnt.service.ClassTableService;
import com.milotnt.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ClassTableService classTableService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ClassOrderService classOrderService;


    @ApiOperation("更新用户信息")
    @PutMapping("/updateInfo")
    public ResponseEntity<Void> updateUserInformation( @RequestBody Member member) {

        memberService.updateMemberByMemberAccount(member);
        return ResponseEntity.ok().build();
    }
    @ApiOperation("用户课程")
    @GetMapping("/userClass")
    public ResponseEntity<List<ClassOrder>> getUserClass(@RequestBody Member member) {

        Integer memberAccount = member.getMemberAccount();
        List<ClassOrder> classOrderList = classOrderService.selectClassOrderByMemberAccount(memberAccount);
        return ResponseEntity.ok(classOrderList);
    }
    @ApiOperation("删除用户课程")
    @DeleteMapping("/delUserClass/{classOrderId}")
    public ResponseEntity<Void> deleteUserClass(@PathVariable Integer classOrderId) {
        classOrderService.deleteByClassOrderId(classOrderId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("查找课程")
    @GetMapping("/applyClass")
    public ResponseEntity<List<ClassTable>> getUserApplyClass(@RequestBody Member member) {

        List<ClassTable> classList = classTableService.findAll();
        return ResponseEntity.ok(classList);
    }
    @ApiOperation("加入课程")
    @PostMapping("/applyClass/{classId}")
    public ResponseEntity<Void> userApplyClass(@PathVariable Integer classId,@RequestBody Member member) {
        ClassTable classTable = classTableService.selectByClassId(classId);

        Integer classId1 = classTable.getClassId();
        String className = classTable.getClassName();
        String coach = classTable.getCoach();
        String classBegin = classTable.getClassBegin();
        String memberName = member.getMemberName();
        Integer memberAccount = member.getMemberAccount();
        ClassOrder classOrder = new ClassOrder(classId1, className, coach, memberName, memberAccount, classBegin);
        Integer memberAccount1 = member.getMemberAccount();
        ClassOrder classOrder1 = classOrderService.selectMemberByClassIdAndMemberAccount(classId1, memberAccount1);
        if (classOrder1 == null) {
            classOrderService.insertClassOrder(classOrder);
        }
        return ResponseEntity.ok().build();
    }
}


