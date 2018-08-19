package com.wsd.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * Created by tm on 2018/8/17.
 * 自定义加盐加密认证realm
 */
public class SaltRealm extends AuthenticatingRealm {

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

        //加盐测试用户
        Object credentials = null;
        if ("wsd2".equals(username)) {
            credentials = "b65f57fd1dcbafdb1c05295356a41edf";  //这里的值是通过加密工具类加密所得
        } else if ("wsd3".equals(username)) {
            credentials = "2d68497b78547e6b0bc6a6c1bb22fb44";
        }

        // TODO 从数据库中获取用户信息，这里先模拟从数据库获取数据
        String realmName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        Object principal = username;  //从数据中获取用户名称
        return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
    }
}
