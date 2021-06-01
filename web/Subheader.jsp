<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="head ding">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top"><h1 class="fl">
            <a href="indexselect"><img src="img/logo3.png"/> </a></h1>

            <div class="fr clearfix" id="top1">
                <p class="fl">
                    <c:if test="${isLogin != 1 }">

                        <a href="login.jsp" id="login">登录</a><a href="register.jsp" id="reg">注册</a>
                    </c:if>

                    <c:if test="${isLogin == 1}">

                         你好吖：<a href="login.jsp" id="login"> ${name.USER_NAME }</a>
                    </c:if>
                    <c:if test="${isAdminLogin  == 1 || name.USER_STATUS == 2}">
                        <a href="manger/admin_index.jsp" id="login">进入后台管理</a>
                    </c:if>
                </p>

                <form action="#" method="get" class="fl"><input type="text" placeholder="热门搜索：Python"/><input
                        type="button"/></form>
                <div class="btn fl clearfix"><a href="mygxin.jsp"><img src="img/grzx.png"/></a><a href="#" class="er1">

                    <img src="img/ewm.png"/></a>
                    <a href="showcart"> <img src="img/gwc.png"/></a>
                    <p><a href="#"><img src="img/smewm.png"/></a></p></div>
            </div>
        </div>
        <ul class="clearfix" id="bott">
            <li><a href="indexselect">回到首页</a></li>
            <c:forEach var="f" items="${flist }">

                <li><a href="selectproductlist?fid=${f.CATE_ID }">${f.CATE_NAME }</a>
                    <div class="sList2">
                        <div class="clearfix">

                            <c:forEach var="c" items="${clist }">

                                <c:if test="${f.CATE_ID == c.CATE_PARENT_ID }">
                                    <a href="selectproductlist?cid=${c.CATE_ID }">${c.CATE_NAME }</a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </li>
            </c:forEach>


        </ul>
    </div>
</div>