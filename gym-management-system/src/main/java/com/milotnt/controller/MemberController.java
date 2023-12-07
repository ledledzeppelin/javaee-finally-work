package com.milotnt.controller;

import com.milotnt.pojo.Member;
import com.milotnt.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("显示所有会员")
    @GetMapping("/selMember")
    public ResponseEntity<List<Member>> selectMember() {
        List<Member> memberList = memberService.findAll();
        return ResponseEntity.ok(memberList);
    }
    @ApiOperation("添加会员")
    @PostMapping("/addMember")
    public ResponseEntity<Void> addMember(@RequestBody Member member) {
        // 生成会员账号
        Random random = new Random();
        String account1 = "2021";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);

        // 初始密码
        String password = "123456";

        // 获取当前日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);

        Integer nextClass = member.getCardClass();

        // 设置会员信息
        member.setMemberAccount(account);
        member.setMemberPassword(password);
        member.setCardTime(nowDay);
        member.setCardNextClass(nextClass);

        // 插入会员信息
        memberService.insertMember(member);

        return ResponseEntity.ok().build();
    }
    @ApiOperation("删除会员")
    @DeleteMapping("/delMember/{memberAccount}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer memberAccount) {
        memberService.deleteByMemberAccount(memberAccount);
        return ResponseEntity.ok().build();
    }
    @ApiOperation("更新会员")
    @PutMapping("/updateMember")
    public ResponseEntity<Void> updateMember(@RequestBody Member member) {
        memberService.updateMemberByMemberAccount(member);
        return ResponseEntity.ok().build();
    }
    @ApiOperation("跳转更新会员姐界面")
    @GetMapping("/toUpdateMember/{memberAccount}")
    public ResponseEntity<List<Member>> toUpdateMember(@PathVariable Integer memberAccount) {
        List<Member> memberList = memberService.selectByMemberAccount(memberAccount);
        return ResponseEntity.ok(memberList);
    }
    @ApiOperation("返回会员卡查询页面的视图名称")
    @GetMapping("/toSelByCard")
    public String toSelectByCardId() {
        // 返回会员卡查询页面的视图名称
        return "selectByMemberAccount";
    }
    @ApiOperation("查询会员")
    @GetMapping("/selByCard/{memberAccount}")
    public ResponseEntity<List<Member>> selectByCardId(@PathVariable Integer memberAccount) {
        List<Member> memberList = memberService.selectByMemberAccount(memberAccount);
        if (memberList != null && !memberList.isEmpty()) {
            return ResponseEntity.ok(memberList);
        } else {
            // 如果会员信息为空，则返回状态码404
            return ResponseEntity.notFound().build();
        }
    }
}

