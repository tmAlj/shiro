package com.wsd.controller;

import com.wsd.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tm on 2018/8/16.
 * 用户Controller
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserServiceImpl ui;

    @RequestMapping("/getUser")
    public void getUser(){
        System.out.println("=============================="+ui.getUser().getName());
    }
}
