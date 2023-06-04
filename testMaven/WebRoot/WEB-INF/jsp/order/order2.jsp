<%@page language="java" contentType="text/html; character=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>管理员后台</title>
    <link rel="stylesheet" href="${ctx}/resource/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/resource/css/admin.css">
    <script src="${ctx}/resource/js/jquery.js"></script>
    <script src="${ctx}/resource/js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <form action="${ctx}/order/findBySql2" id="listform" method="post">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left: 10px;">
                <li>
                    <input type="text" placeholder="请输入需要查找的用户ID" name="no" class="input" value="${obj.no}"
                           style="width: 250px;line-height: 17px;display: inline-block" />
                    <a href="javascript:void(0)" onclick="changeSearch()" class="button border-main icon-search">搜索</a>
                </li>
                <li>
                    <a class="button border-main icon-plus-square-o" href="${ctx}/order/findBySql">查看没交付的订单</a>
                </li>
                <li>
                    <a class="button border-main icon-plus-square-o" href="${ctx}/order/findBySql3">查看删除订单</a>
                </li>
            </ul>
        </div>
    </form>

    <table class="table table-hover text-center">
        <tr>
            <th>编号</th>
            <th>用户编号</th>
            <th>价格</th>
            <th>详情</th>
            <th>详情2</th>
            <th>位置</th>
            <th>结账时间</th>
            <th>出货时间</th>
        </tr>
        <c:forEach items="${pagers.datas}" var="data" varStatus="l">
            <tr>
                <td>${data.lastid}</td>
                <td>${data.uid}</td>
                <td>${data.price}￥</td>
                <td>${data.detel}</td>
                <td>${data.detel2}</td>
                <td>${data.weizhi}</td>
                <td>${data.date}</td>
                <td>${data.date2}</td>
                <td>
                    <a class="button border-red" href="${ctx}/order/up2?id=${data.id}"><span class="icon-trash-o">已经完成需要删除</span> </a>
                </td>

            </tr>
        </c:forEach>
        <tr>
            <td colspan="8">
                <div class="pagelist">
                    <!--分页开始-->
                    <pg:pager url="${ctx}/order/findBySql" maxIndexPages="5" items="${pagers.total}" maxPageItems="15" export="curPage=pageNumber">
                        <pg:last>
                            共${pagers.total}记录，共${pageNumber}页，
                        </pg:last>
                        当前第${curPage}页
                        <pg:first>
                            <a href="${pageUrl}">首页</a>
                        </pg:first>
                        <pg:prev>
                            <a href="${pageUrl}">上一页</a>
                        </pg:prev>
                        <pg:pages>
                            <c:choose>
                                <c:when test="${curPage eq pageNumber}">
                                    <font color="red">[${pageNumber}]</font>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageUrl}">${pageNumber}</a>
                                </c:otherwise>
                            </c:choose>
                        </pg:pages>
                        <pg:next>
                            <a href="${pageUrl}">下一页</a>
                        </pg:next>
                        <pg:last>
                            <c:choose>
                                <c:when test="${curPage eq pageNumber}">
                                    <font color="red">尾页</font>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageUrl}">尾页</a>
                                </c:otherwise>
                            </c:choose>
                        </pg:last>
                    </pg:pager>
                </div>
            </td>
        </tr>
    </table>
</div>
<script>
    function changeSearch(){
        $("#listform").submit();
    }
</script>
</body>

</html>