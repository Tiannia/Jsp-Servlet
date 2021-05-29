<%--
  Created by IntelliJ IDEA.
  User: Tian
  Date: 2021/5/18
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>个人订单</title>
    <link rel="shortcut icon" type="image/x-icon" href="/book_mall/manger/assets/img/icon/favicon.png">
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/myorder.css"/>
</head>
<body><!------------------------------head------------------------------>
<%@include file="Subheader.jsp" %>
<!------------------------------idea------------------------------>
<div class="address mt">
    <div class="wrapper clearfix"><a href="#" class="fl">首页</a><span>/</span><a
            href="mygxin.jsp">个人中心</a><span>/</span><a href="myorderq.jsp" class="on">我的订单</a></div>
</div><!------------------------------Bott------------------------------>
<div class="Bott">
    <div class="wrapper clearfix">
        <div class="zuo fl">
            <h3><a href="#"><img src="img/tx.png"/></a>
                <p class="clearfix"><span class="fl">[${name.USER_NAME }]</span><span>[<a href="logout">退出登录</a>]</span>
                </p></h3>
            <div><h4>我的交易</h4>
                <ul>
                    <li><a href="showcart">我的购物车</a></li>
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
            <div class="my clearfix"><h2 class="fl">我的订单</h2><a href="#" class="fl">请谨防钓鱼链接或诈骗电话，了解更多&gt;</a></div>
            <div class="dlist clearfix">
                <ul class="fl clearfix" id="wa">
                    <li class="on"><a href="#1">全部有效订单</a></li>
                    <li><a href="#2">待收货</a></li>
                    <li><a href="#3">已收货</a></li>
                </ul>
                <form action="#" method="get" class="fr clearfix"><input type="text" name="" id="" value=""
                                                                         placeholder="请输入商品名称、订单号"/><input type="button"
                                                                                                           name="" id=""
                                                                                                           value=""/>
                </form>
            </div>
            <c:forEach var="od" items="${orderlist}">
                <c:if test="${od.order_status == 0}">
                    <div class="dkuang deng"><p class="one">待发货</p>
                        <div class="word clearfix">
                            <ul class="fl clearfix">
                                <li>${od.pay_time}</li>
                                <c:forEach var="ad" items="${addresslist}">
                                    <c:if test="${ad.receive_address_id == od.receive_address_id}">
                                        <li>${ad.receive_name}</li>
                                    </c:if>
                                </c:forEach>
                                <li>订单号：${od.order_sn}</li>
                                <li>支付方式：<c:if test="${od.payment_method==1}">支付宝</c:if><c:if
                                        test="${od.payment_method==2}">微信支付</c:if>
                                    <c:if test="${od.payment_method==3}">银联支付</c:if><c:if
                                            test="${od.payment_method==4}">货到付款</c:if>
                                </li>
                            </ul>
                            <p class="fr">订单金额：<span>￥${od.payment_money}.00</span>元</p></div>
                        <div class="shohou clearfix"><a href="#" class="fl">
                            <c:forEach var="de" items="${info_detail}">
                            <c:if test="${de.key == od.order_id}">
                            <a href="#" class="fl"><img src="images/product/${de.value.product_filename}"/></a>
                            <p class="fl"><a href="#">${de.value.product_name}</a><a href="#">¥${de.value.product_price}×${de.value.product_cnt}</a>
                            </p>
                            </c:if>
                            </c:forEach>
                            <p class="fr"><a href="#">提醒商家</a><a href="showorderdetail?oid=${od.order_id}">订单详情</a></p>
                        </div>
                    </div>
                </c:if>
                <c:if test="${od.order_status == 1}">
                    <div class="dkuang clearfix deng"><p class="one fl">待收货</p>
                        <div class="clearfix">
                            <div class="fl vewwl"><a href="wuliu.html" class="ckwl">查看物流</a>
                                <div class="wuliu"><h4>${od.shipping_comp_name}：${od.order_sn}</h4>
                                    <ul>
                                        <li>
                                            <p>
                                                您的订单正在运输途中，莫着急哦~
                                            </p>
                                        </li>
                                        <li><p>您的订单由第三方卖家拣货完成，待出库交付${od.shipping_comp_name}，运单号为${od.order_sn}</p>
                                            <p>${od.shipping_time}</p></li>
                                        <li>
                                            <p>您提交了订单，请等待第三方卖家系统确认</p>
                                            <p>${od.pay_time}</p>
                                        </li>
                                        <li>以上为最新跟踪信息<a href="wuliu.html">查看全部</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="word clearfix">
                            <ul class="fl clearfix">
                                <li>${od.pay_time}</li>
                                <c:forEach var="ad" items="${addresslist}">
                                    <c:if test="${ad.receive_address_id == od.receive_address_id}">
                                        <li>${ad.receive_name}</li>
                                    </c:if>
                                </c:forEach>
                                <li>订单号：${od.order_sn}</li>
                                <li>支付方式：<c:if test="${od.payment_method==1}">支付宝</c:if><c:if
                                        test="${od.payment_method==2}">微信支付</c:if>
                                    <c:if test="${od.payment_method==3}">银联支付</c:if><c:if
                                            test="${od.payment_method==4}">货到付款</c:if>
                                </li>
                            </ul>
                            <p class="fr">订单金额：<span>￥${od.payment_money}.00</span>元</p></div>
                        <div class="shohou clearfix"><a href="#" class="fl">
                            <c:forEach var="de" items="${info_detail}">
                            <c:if test="${de.key == od.order_id}">
                            <a href="#" class="fl"><img src="images/product/${de.value.product_filename}"/></a>
                            <p class="fl"><a href="#">${de.value.product_name}</a><a href="#">¥${de.value.product_price}×${de.value.product_cnt}</a>
                            </p>
                            </c:if>
                            </c:forEach>
                            <p class="fr"><a href="javascript:MakeSure('你确定？订单号【${od.order_sn}】','comfirmgoods?sn=${od.order_sn}')">确认收货</a><a
                                    href="showorderdetail?oid=${od.order_id}&aid=${od.receive_address_id}">订单详情</a></p>
                        </div>
                    </div>
                </c:if>
                <c:if test="${od.order_status == 2}">
                    <div class="dkuang"><p class="one">已收货</p>
                        <div class="word clearfix">
                            <ul class="fl clearfix">
                                <li>姓名：${od.pay_time}</li>
                                <c:forEach var="ad" items="${addresslist}">
                                    <c:if test="${ad.receive_address_id == od.receive_address_id}">
                                        <li>${ad.receive_name}</li>
                                    </c:if>
                                </c:forEach>
                                <li>订单号：${od.order_sn}</li>
                                <li>支付方式：<c:if test="${od.payment_method==1}">支付宝</c:if><c:if
                                        test="${od.payment_method==2}">微信支付</c:if>
                                    <c:if test="${od.payment_method==3}">银联支付</c:if><c:if
                                            test="${od.payment_method==4}">货到付款</c:if>
                                </li>
                            </ul>
                            <p class="fr">订单金额：<span>￥${od.payment_money}.00</span>元</p></div>
                        <div class="shohou clearfix"><a href="#" class="fl">
                            <c:forEach var="de" items="${info_detail}">
                            <c:if test="${de.key == od.order_id}">
                            <a href="#" class="fl"><img src="images/product/${de.value.product_filename}"/></a>
                            <p class="fl"><a href="#">${de.value.product_name}</a><a href="#">¥${de.value.product_price}×${de.value.product_cnt}</a>
                            </p>
                            </c:if>
                            </c:forEach>
                            <p class="fr"><a href="#">待评价</a><a
                                    href="showorderdetail?oid=${od.order_id}&aid=${od.receive_address_id}">订单详情</a></p>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            <div class="fenye clearfix"><a href="#"><img src="img/zuo.jpg"/></a><a href="#">1</a><a href="#"><img
                    src="img/you.jpg"/></a></div>
        </div>
    </div>
    <script>
        function MakeSure(mess, url) {
            if (confirm(mess)) {
                location.href = url;
            }
        }

        function Delete(mess, url) {
            if (confirm(mess)) {
                location.href = url;
            }
        }

    </script>
</div><!--返回顶部-->
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
        违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p></div>
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
