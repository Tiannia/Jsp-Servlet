<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="shortcut icon" type="image/x-icon" href="/book_mall/manger/assets/img/icon/favicon.png">
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script src=js/jquery-1.12.4.min.js></script>
    <script src="js/function.js"></script>

    <style>
        .reg P .error {
            display: inline-block;
            color: red;
            height: 20px;
            line-heigth: 25px;
            padding-left: 5px;
            margin-left: 0px;
        }
    </style>

    <style>
        .reg P .correct {
            display: inline-block;
            color: green;
            height: 20px;
            line-heigth: 25px;
            padding-left: 5px;
            margin-left: 0px;
        }
    </style>
</head>
<body><!-------------------reg-------------------------->
<div class="reg">
    <form action="register" method="post" onsubmit="return checkForm(this)"><h1 style="text-align: center"><a
            href="register.jsp">
        <!--  img src="学习猿地"-->用户注册</a></h1>
        <p><input type="text" name="userName" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)"
                  placeholder="用户ID"><span class="error">Input here!</span></p>
        <p><input type="text" name="name" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)"
                  placeholder="姓名"><span></span></p>
        <p><input type="password" name="passWord" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)"
                  placeholder="密码"><span></span></p>
        <p><input type="password" name="rePassWord" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)"
                  placeholder="确认密码"><span></span></p>
        <p>
            <input style="width:15px; height:15px" type="radio" name="sex" value="T" checked="checked">男
            <input style="width:15px; height:15px" type="radio" name="sex" value="F">女
        </p>
        <p>
            <input type="date" name="birthday" value="2020-02-02" placeholder="出生日期"><span></span></p>
        <p><input type="text" name="email" value="" placeholder="邮箱" onfocus="FocusItem(this)" onblur="CheckItem(this)">
            <span></span></p>
        <p><input type="text" name="mobile" value="" placeholder="手机号" onfocus="FocusItem(this)"
                  onblur="CheckItem(this)">
            <span></span></p>
        <p><input type="text" name="address" value="" placeholder="地址"><span></span></p>
        <p><input class="code" type="text" name="code" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)"
                  placeholder="验证码">
            <img src="getcode" alt="看不清？换一张" onclick="change(this)"><span class="error"></span></p>
        <p><input type="submit" name="" value="注册"></p>
        <p>完成此注册，即表明您同意了我们的
            <a href="#">
                <使用条款和隐私策略>
            </a>
        </p>
        <p class="txt"><a href="login.jsp"><span id="changetologin">已有账号登录</span></a></p>
        <!--<a href="#" class="off"><img src="img/temp/off.png"></a>--></form>
</div>
</body>
</html>
