package com.wsd.service.impl;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

/**
 * Created by tm on 2018/8/19.
 * 权限注解测试service
 */
@Service("ShiroServiceImpl")
public class ShiroServiceImpl {

    //只有admin角色才能访问该方法，其他角色访问将会抛出异常
    @RequiresRoles({"admin"})
    public void test(){
        System.out.println("xxxxxxxxxxxxxxxxxxxx");
    }
}
