package com.wsd.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * Created by tm on 2018/8/18.
 * 自定义md5加密认证realm，实现多realm认证
 */
public class Md5Realm extends AuthenticatingRealm {

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //token类型转化
        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        //获取前台用户信息
        String username = upToken.getUsername();

        // TODO 从数据库中获取用户信息，这里先模拟从数据库获取数据
        Object principal = username;  //从数据中获取用户名称
        Object credentials = "4a95737b032e98a50c056c41f2fa9ec6";  //从数据中获取用户密码,这里的值是通过加密工具类加密所得
        String realmName = "md5Realm";  //自定义
        return new SimpleAuthenticationInfo(principal, credentials, realmName);
    }
}

