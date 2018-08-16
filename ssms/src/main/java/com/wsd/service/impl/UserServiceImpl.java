package com.wsd.service.impl;

import com.wsd.dao.UserDao;
import com.wsd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tm on 2018/7/19.
 * 用户service类
 */
@Service("UserServiceImpl")
public class UserServiceImpl {
    @Autowired
    UserDao ud;
    public User getUser(){
        return ud.queryUser();
    }
}
