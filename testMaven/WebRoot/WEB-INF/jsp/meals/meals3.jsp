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
        function saveType(id, selectId){
            var type = $('#' + selectId).val();
            $.ajax({
                url: "${ctx}/meals/saveType",
                type: "POST",
                data: {id:id, type:type},
                success: function(data){
                    // 更新页面中的修改值
                    $('#' + selectId).val(type);
                },
                error: function(){
                    alert('请求成功！');
                }
            });
        }

    </script>

</head>
<body>
<div class="panel admin-panel">
    <form action="${ctx}/meals/findBySql3" id="listform" method="post">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left: 10px;">
                <li>
                    <input type="text" placeholder="请输入餐品名称" name="name" class="input" value="${obj.name}"
                           style="width: 250px;line-height: 17px;display: inline-block" />
                    <a href="javascript:void(0)" onclick="changeSearch()" class="button border-main icon-search">搜索</a>
                </li>
                <li>
                    <a class="button border-main icon-plus-square-o" href="${ctx}/meals/findBySql3?action=1">查看全部餐品</a>
                </li>
                <li>
                    <a class="button border-main icon-plus-square-o" href="${ctx}/meals/delete3">清空</a>
                </li>
            </ul>
        </div>
    </form>

    <table class="table table-hover text-center">
        <tr>
            <th>餐品名称</th>
            <th>餐品主图</th>
            <th>餐品价格</th>
            <th>餐品销量</th>
            <th>所在店名</th>
            <th>餐品轮播图序列</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pagers.datas}" var="data" varStatus="l">
            <tr>
                <td>${data.name}</td>
                <td><img src="${data.url1}" alt="" style="width: 100px;height: 100px;"></td>
                <td>${data.price}</td>
                <td>${data.gmNum}</td>
                <td>${data.yiji.name}</td>
                <td>
                    <div class="field">
                        <select name="type${data.id}" id="type${data.id}" class="input w50">
                            <option value=""></option>
                            <c:forEach begin="1" end="3" var="i">
                                <option value="${i}" ${i == data.type ? 'selected' : ''}>${i}</option>
                            </c:forEach>
                        </select>
                    </div>
                </td>
                <td>
                    <a class="button border-main" href="javascript:void(0)" onclick="saveType(${data.id}, 'type${data.id}')"><span class="icon-edit">确定修改</span></a>
                    <a class="button border-red" href="${ctx}/meals/saveType2?id=${data.id}"><span class="icon-trash-o">删除</span> </a>
                </td>

            </tr>
        </c:forEach>
       <%-- <tr>
            <td colspan="8">
                <div class="pagelist">
                    <!--分页开始-->
                    <pg:pager url="${ctx}/meals/findBySql3" maxIndexPages="5" items="${pagers.total}" maxPageItems="15" export="curPage=pageNumber">
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
        </tr>--%>
    </table>
</div>
<script>
    function changeSearch(){
        $("#listform").submit();
    }
</script>
</body>

</html>