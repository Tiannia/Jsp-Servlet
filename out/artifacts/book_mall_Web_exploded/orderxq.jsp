<%--
  Created by IntelliJ IDEA.
  User: Tian
  Date: 2021/5/18
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>订单详情</title>
    <link rel="shortcut icon" type="image/x-icon" href="/book_mall/manger/assets/img/icon/favicon.png">
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/myorder.css"/>
</head>
<body><!------------------------------head------------------------------>
<%@include file="Subheader.jsp" %>
<<!------------------------------idea------------------------------>
<div class="address mt">
    <div class="wrapper clearfix"><a href="" class="fl">首页</a><span>/</span><a href="showorderinfo"
                                                                                         class="on">我的订单</a><span>/</span><a
            href="#" class="on">订单详情</a></div>
</div><!------------------------------Bott------------------------------>
<div class="Bott">
    <div class="wrapper clearfix">
        <div class="zuo fl">
            <h3><a href="#"><img src="img/tx.png"/></a>
                <p class="clearfix"><span class="fl">[${name.USER_NAME }]</span><span>[<a href="logout">退出登录</a>]</span></p></h3>
            <div><h4>我的交易</h4>
                <ul>
                    <li><a href="cart.jsp">我的购物车</a></li>
                    <li class="on"><a href="showorderinfo">我的订单</a></li>
                </ul>
                <h4>个人中心</h4>
                <ul>
                    <li><a href="mygxin.jsp">我的中心</a></li>
                    <li><a href="address.jsp">地址管理</a></li>
                </ul>
                <h4>账户管理</h4>
                <ul>
                    <li><a href="mygrxx.jsp">个人信息</a></li>
                    <li><a href="repassword.jsp">修改密码</a></li>
                </ul>
            </div>
        </div>
        <div class="you fl">
            <div class="my clearfix"><h2>订单详情<a href="#">请谨防钓鱼链接或诈骗电话，了解更多&gt;</a></h2>
                <br><br>
                <h3>订单号：<span>${odinfo.order_sn}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;快递公司：<span>${odinfo.shipping_comp_name}</span></h3></div>
            <div class="orderList">
                <div class="orderList1"><h3><c:if test="${odinfo.order_status==0}">待发货</c:if><c:if test="${odinfo.order_status==1}">待收货</c:if>
                    <c:if test="${odinfo.order_status==2}">已发货</c:if></h3>
                    <div class="clearfix">
                        <c:forEach var="de" items="${detaillist}">
                            <p class="fl">
                                    <a href="selectproductview?id=${de.product_id}" class="fl"><img src="images/product/${de.product_filename}" width="120" height="120"/></a>
                                    <a href="selectproductview?id=${de.product_id}">【${de.product_name}】</a><a href="#">¥${de.product_price}×${de.product_cnt}</a>
                            </p>
                        </c:forEach>
                    </div>
                </div>
                <div class="orderList1"><h3>收货信息</h3>
                    <p>姓名：<span>${adinfo.receive_name}</span></p>
                    <p>联系电话：<span>${adinfo.telephone}</span></p>
                    <p>收货地址：<span>${adinfo.province}&nbsp;${adinfo.city}&nbsp;${adinfo.district}&nbsp;${adinfo.address}</span></p>
                    <p>配送区域：<span>${adinfo.send_place}</span></p></div>
                <div class="orderList1"><h3>支付方式及送货时间</h3>
                    <p>支付方式：<span><c:if test="${odinfo.payment_method==1}">支付宝</c:if><c:if test="${odinfo.payment_method==2}">微信支付</c:if>
                                    <c:if test="${odinfo.payment_method==3}">银联支付</c:if><c:if test="${odinfo.payment_method==4}">货到付款</c:if></span></p>
                    <p>发货时间：<span>${odinfo.shipping_time}</span></p></div>
                <div class="orderList1 hei"><h3><strong>商品总价：</strong><span>¥${odinfo.payment_money}</span></h3>
                    <p><strong>运费：</strong><span>¥0（包邮啊芜湖！！！）</span></p>
                    <p><strong>订单金额：</strong><span>¥${odinfo.payment_money}</span></p>
                    <p><strong>实付金额：</strong><span>¥${odinfo.payment_money}（优惠是什么？能吃吗）</span></p></div>
            </div>
        </div>
    </div>
</div>
<<!--返回顶部-->
<div class="gotop"><a href="cart.html">
    <dl>
        <dt><img src="img/gt1.png"/></dt>
        <dd>去购<br/>物车</dd>
    </dl>
</a><a href="#" class="dh">
    <dl>
        <dt><img src="img/gt2.png"/></dt>
        <dd>联系<br/>客服</dd>
    </dl>
</a><a href="mygxin.html">
    <dl>
        <dt><img src="img/gt3.png"/></dt>
        <dd>个人<br/>中心</dd>
    </dl>
</a><a href="#" class="toptop" style="display: none">
    <dl>
        <dt><img src="img/gt4.png"/></dt>
        <dd>返回<br/>顶部</dd>
    </dl>
</a>
    <p>400-800-8200</p></div><!--footer-->
<div class="footer">
    <div class="top">
        <div class="wrapper">
            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot1.png"/></a><span class="fl">7天无理由退货</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot2.png"/></a><span class="fl">15天免费换货</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot3.png"/></a><span class="fl">满599包邮</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot4.png"/></a><span class="fl">手机特色服务</span>
            </div>
        </div>
    </div>
    <p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br/>
        违法和不良信息举报电话：400-800-8200，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p></div>
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
