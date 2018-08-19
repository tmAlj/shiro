package com.wsd.base;

import java.util.LinkedHashMap;

/**
 * Created by tm on 2018/8/19.
 * 拦截器工厂类，用于构造拦截集合
 */
public class FilterChainDefinitionMapBuilder {
    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        // TODO 从数据库去获取，与配置文件中一致
        map.put("/statics/**", "anon");
        map.put("/login.jsp", "anon");
        map.put("/welcom.jsp", "user");
        map.put("/logout", "logout");
        map.put("/user.jsp", "authc,roles[user]");
        map.put("/admin.jsp", "authc,roles[admin]");
        return map;
    }
}
