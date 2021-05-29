<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="admin_menu.jsp" %>
<title>图书菜单</title>
<!--/sidebar-->
<div class="main-wrap">
    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font"></i><a href="../manger/admin_index.jsp">首页</a><span
                class="crumb-step">&gt;</span><span class="crumb-name">订单管理</span></div>
    </div>
    <div class="result-wrap">
        <form action="#" id="myform" method="post">
            <div class="result-title">
            </div>
            <div class="result-content">
                <table class="result-tab" width="100%">
                    <tr>
                        <th>订单编号</th>
                        <th>用户ID</th>
                        <th>快递单号</th>
                        <th>下单时间</th>
                        <th>发货状态</th>
                        <th>发货时间</th>
                        <th>收货时间</th>
                        <th>操作</th>
                    </tr>
                    <c:if test="${fn:length(orderlist)==0}">
                        <div style="margin:200px 400px; position:absolute; font-size:40px;  background: linear-gradient(to right, lightpink, deeppink);
        -webkit-background-clip: text; color: transparent;">
                            <h2>
                                没有订单信息~0V0
                            </h2>
                        </div>
                    </c:if>
                    <c:forEach var="o" items="${orderlist}">
                        <tr>
                            <td>${o.order_sn}</td>
                            <td>${o.user_id}</td>
                            <td>${o.shipping_sn}</td>
                            <td>${o.pay_time}</td>
                            <td><c:if test="${o.order_status == 0}">未发货 </c:if><c:if
                                    test="${o.order_status == 1}">已发货 </c:if>
                                <c:if test="${o.order_status == 2}">已收货 </c:if></td>
                            <td>
                                <c:if test="${o.order_status == 0 }">
                                    等待您的发货哦~
                                </c:if>
                                <c:if test="${o.order_status > 0}">
                                    ${o.shipping_time}
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${o.order_status == 0}">
                                    等待您的发货哦~
                                </c:if>
                                <c:if test="${o.order_status == 1}">
                                    快递还在运输中
                                </c:if>
                                <c:if test="${o.order_status == 2}">
                                    ${o.receive_time}
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${o.order_status == 0}">
                                    <a href="javascript:Deliver('你确定要发货吗？订单号【${o.order_sn}】','/book_mall/manage/admin_dodeliver?sn=${o.order_sn}')">发货</a>
                                </c:if>
                                <c:if test="${o.order_status == 2}">
                                    <a href="javascript:Delete('你确定要删除订单【${o.order_sn}】吗？删除后用户将失去订单信息，请谨慎操作！','/book_mall/manage/admin_doorderdelete?sn=${o.order_sn}')">删除</a>
                                </c:if>

                            </td>
                        </tr>

                    </c:forEach>


                    <script>
                        function Deliver(mess, url) {
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
                </table>
            </div>
        </form>
    </div>
</div>
<!--/main-->
</div>
</body>
</html>>