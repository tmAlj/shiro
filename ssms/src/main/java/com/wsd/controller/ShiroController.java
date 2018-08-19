package com.wsd.controller;

import com.wsd.service.impl.ShiroServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tm on 2018/8/17.
 * shiro认证Controller
 */
@Controller
@RequestMapping("shiro")
public class ShiroController {

    private static final transient Logger log = LoggerFactory.getLogger(ShiroController.class);
    @Autowired
    ShiroServiceImpl ssi;

    /**
     * 登录
     * @param userName  用户名称
     * @param password  用户密码
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request){
        String remember = request.getParameter("remember");
        // 得到一个Subject对象，该对象为访问系统任意用户
        Subject currentUser = SecurityUtils.getSubject();

        // 通过Subject对象的isAuthenticated判断该用户是否已经验证
        if (!currentUser.isAuthenticated()) {
            // 如果该用户未验证，得到用户的账号和密码，使用UsernamePasswordToken的方式生成一个该用户的token凭证
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            // 开启记住我的功能，这里可以通过获取用户的提交的信息，判断是否选择记住我来决定开启或关闭
            try{
                if(remember.equals("on")){
                    token.setRememberMe(true);
                }
            }catch (NullPointerException e){
                token.setRememberMe(false);
            }

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
            } catch (AuthenticationException ae) {
                // AuthenticationException即验证不通过异常，为前面几个异常的父类
            }
        }
        //认证成功后跳转，需配置过滤器
        return "redirect:/welcom.jsp";
    }

    /**
     * 权限注解测试
     * @return
     */
    @RequestMapping("/test")
    public String note(){
        ssi.test();
        return "redirect:/welcom.jsp";
    }
}
