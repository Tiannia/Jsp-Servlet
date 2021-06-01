<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>order</title>
    <link rel="shortcut icon" type="image/x-icon" href="/book_mall/manger/assets/img/icon/favicon.png">
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/proList.css"/>
    <link rel="stylesheet" type="text/css" href="css/mygxin.css"/>
</head>
<body><!----------------------------------------order------------------>
<%@include file="Subheader.jsp" %>
<div class="order cart mt"><!-----------------site------------------->
    <div class="site"><p class="wrapper clearfix"><span class="fl">订单确认</span><img class="top"
                                                                                   src="img/temp/cartTop02.png"></p>
    </div><!-----------------orderCon------------------->
        <div class="orderCon wrapper clearfix">
            <div class="orderL fl"><!--------h3----------------><h3>收件信息<a href="#" class="fr">新增地址</a></h3>
                <!--------addres---------------->
                <div class="addres clearfix">
                    <div class="addre fl on" name="${defad.receive_address_id}">
                        <div class="tit clearfix"><p class="fl">${defad.receive_name} <span
                                class="default">[默认地址]</span>
                        </p>
                            <p class="fr"><a href="#">删除</a><span>|</span><a href="#" class="edit">添加</a></p></div>
                        <div class="addCon">
                            <p>详细地址：${defad.province}&nbsp;${defad.city}&nbsp;${defad.district}&nbsp;${defad.address}</p>
                            <p>配送区域：${defad.send_place}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮政编码：${defad.zipcode}</p>
                            <p>收货电话：${defad.telephone}</p></div>
                    </div>
                    <c:forEach var="ar" items="${ addresslist }">
                        <c:if test="${ar.receive_address_id != defad.receive_address_id}">
                            <div class="addre fl" name="${ar.receive_address_id}">
                                <div class="tit clearfix"><p class="fl">${ar.receive_name}</p>
                                    <p class="fr"><a href="#" class="setDefault">设为默认</a><span>|</span><a
                                            href="#">删除</a><span>|</span><a href="#" class="edit">编辑</a></p></div>
                                <div class="addCon">
                                    <p>详细地址：${ar.province}&nbsp;${ar.city}&nbsp;${ar.district}&nbsp;${ar.address}</p>
                                    <p>配送区域：${ar.send_place}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮政编码：${ar.zipcode}</p>
                                    <p>收货电话：${ar.telephone}</p></div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
                <h3>支付方式</h3><!--------way---------------->
                <div class="way clearfix"><img class="on" src="img/temp/way01.jpg" name="zhifubao"><img src="img/temp/way02.jpg" name="weixin"><img
                        src="img/temp/way03.jpg" name="yinlian"><img src="img/temp/way04.jpg" name="daofu"></div>
                <h3>选择快递</h3><!--------dis---------------->
                <div class="dis clearfix"><span
                        class="on" name="shunfeng">顺风快递</span><span name="baishi">百世汇通</span>
                        <span name="yuantong">圆通快递</span>
                    <span name="shentong">中通快递</span>
                </div>
            </div>
            <div class="orderR fr">
                <div class="msg"><h3>订单内容<a href="showcart" class="fr">返回购物车</a></h3><!--------ul---------------->

                    <c:forEach var="rs" items="${requestScope.shoplist }">
                        <ul class="clearfix">
                            <li class="fl"><img width="100" height="100" src="images/product/${rs.cart_p_filename }">
                            </li>
                            <li class="fl"><p>${rs.cart_p_name }</p>
                                <p>产品分类：【未设计】</p>
                                <p>数量：${rs.cart_quantity }</p></li>
                            <li class="fr">￥${rs.cart_p_price * rs.cart_quantity}.00</li>
                        </ul>

                    </c:forEach>


                </div><!--------tips---------------->
                <div class="tips"><p><span class="fl">商品金额：</span><span class="fr">￥${totalprice}.00</span></p>
                    <p><span class="fl">优惠金额：</span><span class="fr">￥0.00</span></p>
                    <p><span class="fl">运费：</span><span class="fr">免运费</span></p></div>
                <!--------tips count---------------->
                <div class="count tips"><p><span class="fl">合计：</span><span class="fr">￥${totalprice}.00</span></p>
                </div><!--<input type="button" name="" value="去支付">--> <a href="javascript:topay()" class="pay">现在支付</a>
            </div>
        </div>
</div>


<script>
    function topay() {
        var str = "";
        var obj = document.getElementsByClassName("on");//获取"on"类
        for(var i=0;i<obj.length - 1;i++){
            if(i == 0) str += obj[i].getAttribute("name");
            else str += ',' + obj[i].getAttribute("name");
        }
        console.log(str);
        //定位到该地址
        location.href = "addorder?money=${totalprice}&eids=${eids}&addinfo=" + str;
    }

</script>


<!--编辑弹框--><!--遮罩-->
<div class="mask"></div>
<div class="adddz editAddre">
    <!-- 传给地址表单 -->
    <form action="add_address?id=${name.USER_ID}&eids=${eids}" method="post">
        <input type="text" placeh older=${name.USER_NAME} name="receive_name" class="on"/>
        <input type="text" placeholder="手机号" name="telephone"/>
        <div class="city"><select name="province" onchange="change(this)">
            <option value="0" selected="selected"> 省份/自治区</option>
            <c:forEach var="pro" items="${province_list}">
                <option value="${pro.id}">${pro.name}</option>
            </c:forEach>
        </select><select id="op1" name="city" onchange="change(this)">
            <option value="0" selected="selected">城市/地区</option>
        </select><select id="op2" name="district">
            <option value="0" selected="selected">区/县</option>
        </select><select name="send_place">
            <option value="0" selected="selected">配送区域</option>
            <option value="1">学校</option>
            <option value="2">家庭</option>
            <option value="3">公司</option>
        </select></div>
        <textarea name="address" rows="" cols="" placeholder="详细地址"></textarea><input name="zipcode" type="text"
                                                                                      placeholder="邮政编码"/>
        <div class="bc"><input type="submit" value="保存"/><input type="button" value="取消"/></div>
    </form>
</div>
<script>
    function change(obj) {
        switch ($(obj).attr('name')) {
            case "province":
                $.ajax({
                    type: "get",
                    dataType: "text",
                    url: "areagetcity?province=" + encodeURI($(obj).val()) + "&" + new Date().getTime(),
                    success: function (msg) {
                        var jsonStr = new String(msg);
                        var json = $.parseJSON(jsonStr);
                        var jslength = 0;
                        var objopt = document.getElementById("op1");
                        objopt.options.length = 0;
                        document.getElementById("op1").options.add(new Option('城市/地区', '0'));
                        for (var c in json) {
                            document.getElementById("op1").options.add(new Option(json[c].name, json[c].id));
                        }
                    },
                    error: function (msg) {
                        alert(msg);
                    }
                });
                break;
            case "city":
                $.ajax({
                    type: "get",
                    dataType: "text",
                    url: "areagetdistrict?city=" + encodeURI($(obj).val()) + "&" + new Date().getTime(),
                    success: function (msg) {
                        var jsonStr = new String(msg);
                        var json = $.parseJSON(jsonStr);
                        var jslength = 0;
                        var objopt = document.getElementById("op2");
                        objopt.options.length = 0;
                        document.getElementById("op2").options.add(new Option('区/县', '0'));
                        for (var c in json) {
                            document.getElementById("op2").options.add(new Option(json[c].name, json[c].id));
                        }
                    },
                    error: function (msg) {
                        alert(msg);
                    }
                });
                break;
        }
    }
</script>


<!--返回顶部-->
<div class="gotop"><a href="showcart">
    <dl>
        <dt><img src="img/gt1.png"/></dt>
        <dd>去购<br/>物车</dd>
    </dl>
</a><a href="#" class="dh">
    <dl>
        <dt><img src="img/gt2.png"/></dt>
        <dd>联系<br/>客服</dd>
    </dl>
</a><a href="mygxin.jsp">
    <dl>
        <dt><img src="img/gt3.png"/></dt>
        <dd>个人<br/>中心</dd>
    </dl>
</a><a href="#" class="toptop" style="display: none;">
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
<script src="js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>