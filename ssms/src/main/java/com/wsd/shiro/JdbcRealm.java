package com.wsd.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * Created by tm on 2018/8/17.
 * 用户自定义认证Realm
 */
public class JdbcRealm extends AuthenticatingRealm {

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //token类型转化
        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        //获取前台用户信息
        String username = upToken.getUsername();
        // TODO 从数据库中获取用户信息，这里先模拟从数据库获取数据

        // 使用数据库中的用户信息与前台传入的用户信息比对，如果用户不存在，抛出异常
        if("wsd".equals(username)) {
            throw new UnknownAccountException("==================用户不存在");
        }
        // 用户被锁定是，抛出异常
        if("wsd1".equals(username)) {
            throw new LockedAccountException("==================用户被锁定");
        }

        // TODO 从数据库中获取用户信息，这里先模拟从数据库获取数据
        Object principal = username;  //从数据中获取用户名称
        Object credentials = "123456";  //从数据中获取用户密码
        String realmName = "jdbcRealm";  //自定义
        return new SimpleAuthenticationInfo(principal, credentials, realmName);
    }
}
