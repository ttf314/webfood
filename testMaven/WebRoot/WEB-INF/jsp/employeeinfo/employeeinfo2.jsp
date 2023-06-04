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
    <script>
        $(function() {
            // 绑定"充值2"按钮的单击事件
            $('.rechargeBtn').click(function() {
                var id = $(this).data('id'); // 获取用户ID
                var rmb2 = $(this).closest('tr').find('input[name="rmb2"]').val(); // 获取输入框的值
                if (window.confirm('确定给该店员加成？ ' + rmb2 + ' 元吗？')) { // 显示确认框
                    $.ajax({
                        url: '${ctx}/employeeinfo/update3', // 后台URL
                        type: 'POST', // 请求类型
                        data: { id: id, rmb2: rmb2 }, // 请求参数
                        success: function(result) {
                            window.location.href = '${ctx}/employeeinfo/findBySql2'; // 重定向到新页面
                        }
                    });
                }
            });
        });
    </script>
</head>
<body>
<div class="panel admin-panel">
    <form action="${ctx}/employeeinfo/findBySql2" id="listform" method="post">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left: 10px;">
                <li>
                    <input type="text" placeholder="请输入店员名称" name="employeename" class="input" value="${obj.employeename}"
                           style="width: 250px;line-height: 17px;display: inline-block" />
                    <a href="javascript:void(0)" onclick="changeSearch()" class="button border-main icon-search">搜索</a>
                </li>
            </ul>
        </div>
    </form>
    <table class="table table-hover text-center">
        <tr>
            <th>顾员编号</th>
            <th>顾员名称</th>
            <th>脸照片</th>
            <th>每天薪资</th>
            <th>加成</th>
            <th>考勤日期</th>
            <th>考勤有效次数</th>
            <th>奖励</th>
            <th>预估薪资</th>
        </tr>
        <c:forEach items="${pagers.datas}" var="data" varStatus="l">
            <tr>
                <td>${data.id}</td>
                <td>${data.employeename}</td>
                <td><img src="${data.faceurl}" alt="" style="width: 100px;height: 100px;"></td>
                <td>${data.rmb}</td>
                <td>${data.rmb2}</td>
                <td>
                    <c:forEach items="${fn:split(data.data2, '#')}" var="date">
                        ${date}<br>
                    </c:forEach>
                </td>
                <td>${(data.rmb3-data.rmb2)/data.rmb}</td>
                <td style="width: 100px !important;">
                    <div class="field">
                        <input type="number" name="rmb2" class="input w100" min="1" max="999" required>
                    </div>
                </td>
                <td>${data.rmb3}</td>
                <td>
                    <a class="button border-main rechargeBtn" data-id="${data.id}"><span class="icon-edit">确定</span> </a>
                 </td>
            </tr>
        </c:forEach>

    <tr>
            <td colspan="8">
                <div class="pagelist">
                    <!--分页开始-->
                    <pg:pager url="${ctx}/employeeinfo/findBySql2" maxIndexPages="5" items="${pagers.total}" maxPageItems="15" export="curPage=pageNumber">
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