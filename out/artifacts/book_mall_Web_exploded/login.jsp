<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="shortcut icon" type="image/x-icon" href="/book_mall/manger/assets/img/icon/favicon.png">
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
</head>
<body><!-------------------login-------------------------->
<div class="login">
    <form action="login" method="post"><h1><a href="login.jsp">用户登陆</a></h1>
        <!-- p style="color: #62c462; font-style: normal; font-size: 20px">用户登陆</p> -->
        <p><input type="text" name="userName" value="" placeholder="账号" required = "required"></p>
        <p><input type="password" name="passWord" value="" placeholder="密码" required = "required"></p>
        <p><input type="submit" name="" value="登  录"></p>
        <p class="txt"><a class="" href="register.jsp">免费注册</a><a href="forget.html">忘记密码？</a></p>
        <div class="msg-warn hide"><b></b>荆溪白石出，天寒红叶稀。</div></form>
</div>
</body>
</html>