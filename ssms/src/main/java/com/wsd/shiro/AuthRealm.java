package com.wsd.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tm on 2018/8/18.
 * 自定义认证授权realm
 */
public class AuthRealm extends AuthorizingRealm {
    //授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 首先 principals中获取登录信息，在多realm的场景中获取getPrimaryPrincipal的时候，是有顺序的，跟我们之前的配置相关，
        Object principal = principalCollection.getPrimaryPrincipal();
        // 根据登录信息来获取角色或权限
        // TODO 由于没有从注册流程做起，账户的账号/密码没有存在数据库中，这里模拟从数据库读取登录用户的角色权限数据，
        // TODO 如果是用户名称为admin则设置user、admin角色，其他用户设置user角色
        Set<String> roles = new HashSet<String>();
        roles.add("user");
        if ("admin".equals(principal)) {
            roles.add("admin");
        }
        return  new SimpleAuthorizationInfo(roles);
    }
    //认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //token类型转化
        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        //获取前台用户信息
        String username = upToken.getUsername();

        // TODO 从数据库中获取用户信息，这里先模拟从数据库获取数据
        Object principal = username;  //从数据中获取用户名称
        Object credentials = "123456";  //从数据中获取用户密码
        String realmName = getName();  //自定义
        return new SimpleAuthenticationInfo(principal, credentials, realmName);
    }
}
