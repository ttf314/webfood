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
    <script src="${ctx}/resource/js/jquery.js"></script>
   <%-- <script>
        $(function(){
            setInterval(getData, 5000); // 每5秒请求一次数据
        });

        function getData() {
            $.ajax({
                url: "${ctx}/order/getData", // 后台数据接口地址
                success: function(data) {
                    // 更新订单列表
                    $("#orderList").html(data);
                }
            });
        }
    </script>--%>

</head>
<body>
<div class="panel admin-panel">
    <form action="${ctx}/order/findBySql" id="listform" method="post">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left: 10px;">
                <li>
                    <a class="button border-main icon-plus-square-o" href="${ctx}/order/add">添加订单</a>
                </li>
                <li>
                    <input type="text" placeholder="请输入需要查找的用户ID" name="no" class="input" value="${obj.no}"
                           style="width: 250px;line-height: 17px;display: inline-block" />
                    <a href="javascript:void(0)" onclick="changeSearch()" class="button border-main icon-search">搜索</a>
                </li>
                <li>
                    <a class="button border-main icon-plus-square-o" href="${ctx}/order/findBySql2">查看已经交付订单</a>
                </li>


            </ul>
        </div>
    </form>

    <table class="table table-hover text-center">
        <tr>
            <th>编号</th>
            <th>用户编号</th>
            <th>出货数</th>
            <%--<th>餐品名</th>--%>
            <th>详情</th>
            <th>位置</th>
            <th>日期</th>

        </tr>
        <c:forEach items="${pagers.datas}" var="data" varStatus="l">
            <tr id="order${data.id}">
                <td>${data.id}</td>
                <td>${data.uid}</td>
                <td>${data.count}</td>
                <td>${data.detel}</td>
                <td>${data.weizhi}</td>
                <td>${data.date}</td>
                <td>
                    <a class="button border-red" href="${ctx}/order/add?id=${data.id}"><span class="icon-trash-o">去确认完成</span> </a>
                </td>
                <td>
                    <a class="button border-red" href="${ctx}/order/exAdd?id=${data.id}"><span>完成</span> </a>
                </td>
            </tr>
        </c:forEach>
        <script>
            function printRow(rowId) {
                // 获取当前行的数据
                var row = document.getElementById(rowId);
                var cells = row.getElementsByTagName('td');
                var data = [];
                for (var i = 0; i < cells.length; i++) {
                    data.push(cells[i].textContent);
                }
                // 发送 POST 请求到后台
                var xhr = new XMLHttpRequest();
                xhr.open('POST', '/print', true);
                xhr.setRequestHeader('Content-Type', 'application/json');
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        // 下载链接
                        var downloadUrl = xhr.responseText;
                        // 在新窗口中打开下载链接
                        window.open(downloadUrl, '_blank');
                    }
                };
                xhr.send(JSON.stringify(data));
            }

            $(function(){
                setInterval(getData, 5000); // 每5秒请求一次数据
            });

            function getData() {
                $.ajax({
                    url: "${ctx}/order/getData", // 后台数据接口地址
                    success: function(data) {
                        // 更新订单列表
                        $("#orderList").html(data);
                    }
                });
            }
            function getData() {
                $.ajax({
                    url: "${ctx}/order/getData", // 后台数据接口地址
                    success: function(data) {
                        // 更新订单列表
                        $("#orderList").html(data);

                        // 播放声音
                        var audio = new Audio("path/to/sound.mp3");
                        audio.play();
                    }
                });
            }

        </script>

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