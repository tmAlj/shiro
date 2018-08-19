<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/8/16
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入shiro标签库--%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>欢迎</title>
</head>
    <%--shiro标签principal的使用--%>
    <h1>恭喜您：<shiro:principal/>，您登录成功啦！</h1>
    <%--shiro标签hasRole的使用--%>
    <shiro:hasRole name="admin">
    <a href="admin.jsp">进入admin</a>
    </shiro:hasRole>
    <shiro:hasRole name="user">
    <a href="user.jsp">进入user</a>
    </shiro:hasRole>
    <a href="shiro/test">权限注解测试</a>
    <a href="logout">退出登录</a>
<body>
</body>
</html>
