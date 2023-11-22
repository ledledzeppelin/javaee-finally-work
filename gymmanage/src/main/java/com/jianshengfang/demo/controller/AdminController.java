package com.jianshengfang.demo.controller;

import com.jianshengfang.demo.dao.AdminDao;
import com.jianshengfang.demo.entity.Adminuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private  AdminDao admindao;



    @PostMapping("/login")
    public ResponseEntity<Adminuser> adminLogin(@PathVariable String password, @PathVariable String name) {
        Adminuser loggedInAdmin = admindao.findByNameAndPassword(name,password);

        if (loggedInAdmin != null) {
            return ResponseEntity.ok(loggedInAdmin);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    // Other admin-related endpoints
}

