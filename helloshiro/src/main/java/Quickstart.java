/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simple Quickstart application showing how to use Shiro's API.
 *
 * @since 0.9 RC2
 */
public class Quickstart {

    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);


    public static void main(String[] args) {

        // 通过shiro.ini的方式创建一个securityManager对象，shiro.ini中配置了用户，角色，资源等信息
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        // 将securityManager对象交给SecurityUtils管理，以便和后面的Subject对象产生关系
        SecurityUtils.setSecurityManager(securityManager);

        // 得到一个Subject对象，该对象为访问系统任意用户
        Subject currentUser = SecurityUtils.getSubject();

        // 得到Session对象，和servlet中的Session类似，同时可以给该对象设置属性等
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("===============session=>"+value);
        }

        // 通过Subject对象的isAuthenticated判断该用户是否已经验证
        if (!currentUser.isAuthenticated()) {
            // 如果该用户未验证，得到用户的账号和密码，使用UsernamePasswordToken的方式生成一个该用户的token凭证
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            // 开启记住我的功能，这里可以通过获取用户的提交的信息，判断是否选择记住我来决定开启或关闭
            token.setRememberMe(true);
            try {
                // 通过Subject对象的login来验证用户的身份
                currentUser.login(token);
                // 如果用户身份验证不通过会抛出相应的异常，可以通过抛出的异常来设置返回给前台的信息
            } catch (UnknownAccountException uae) {
                // UnknownAccountException账号不存在异常
                log.info("===============账号不存在异常=>"+token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                // IncorrectCredentialsException密码错误异常
                log.info("===============密码错误异常=>"+token.getPrincipal());
            } catch (LockedAccountException lae) {
                // LockedAccountException账户被锁定异常
                log.info("===============账户被锁定异常=>"+token.getPrincipal());
            }
            // DisabledAccountException帐号被禁用异常
            // ExcessiveAttemptsException登录失败次数过多异常
            // ExpiredCredentialsException凭证过期异常等等...
            catch (AuthenticationException ae) {
                // AuthenticationException即验证不通过异常，为前面几个异常的父类
            }
        }

        // 通过Subject对象的hasRole方法判断当前用户是否拥有某个角色
        // 这里比对的角色，权限都是在shiro.ini中配置的，常用的是从数据库中动态加载
        if (currentUser.hasRole("schwartz")) {
            log.info("===============用户拥有schwartz角色");
        } else {
            log.info("===============用户不拥有schwartz角色");
        }

        // 通过Subject对象的isPermitted方法判断当前用户是否拥有某个权限
        if (currentUser.isPermitted("lightsaber:weild")) {
            log.info("===============用户拥有lightsaber:weild权限");
        } else {
            log.info("===============用户不拥有lightsaber:weild权限");
        }

        //a (very powerful) Instance Level permission:
        // 通过Subject对象的isPermitted方法判断当前用户是否拥有某个权限，相比上面判断，该判断粒度更小
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            log.info("===============用户拥有winnebago:drive:eagle5权限");
        } else {
            log.info("===============用户不拥有winnebago:drive:eagle5权限");
        }

        // 通过Subject对象的logout方法退出登录
        currentUser.logout();

        System.exit(0);
    }
}
