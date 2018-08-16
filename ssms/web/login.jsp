<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/8/16
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="statics/boostrap/css/bootstrap.css"/>
    <style>
        .tm-container{
            width: 600px;
            margin: 20px auto;
        }
    </style>
</head>
<body>
    <div class="tm-container">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">用户姓名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="firstname" name="userName" placeholder="请输入姓名">
                </div>
            </div>
            <div class="form-group">
                <label for="lastname" class="col-sm-2 control-label">用户密码</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="lastname" name="password" placeholder="请输入密码">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox">请记住我
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-danger">登录</button>
                </div>
            </div>
        </form>
    </div>
    <script type="text/javascript" src="statics/jquery/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="statics/boostrap/js/bootstrap.js"></script>
</body>
</html>
