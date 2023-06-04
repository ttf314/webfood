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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(function() {
            // 绑定"充值2"按钮的单击事件
            $('.rechargeBtn').click(function() {
                var id = $(this).data('id'); // 获取用户ID
                var quantity = $(this).closest('tr').find('input[name="quantity"]').val(); // 获取输入框的值
                if (window.confirm('确定为该用户充值 ' + quantity + ' 元吗？')) { // 显示确认框
                    $.ajax({
                        url: '${ctx}/userinfo/update2', // 后台URL
                        type: 'POST', // 请求类型
                        data: { id: id, rmb: quantity }, // 请求参数
                        success: function(result) {
                            window.location.href = '${ctx}/userinfo/findBySql'; // 重定向到新页面
                        }
                    });
                }
            });
        });

        function showUserDetails(userId) {
            // 在这里编写弹窗显示用户详情信息的逻辑
            // 您可以使用 alert()、console.log() 或其他自定义的弹窗方式显示信息
            // 可以通过 AJAX 请求后端接口获取用户详情数据，并在弹窗中显示
            // 以下为示例代码，您可以根据实际需求进行修改

            // 示例：使用 alert() 弹窗显示用户详情信息
            alert('显示用户详情信息，用户ID为：' + userId);
        }
        /*function showUserDetails2(userId, userName, password, phone, rmb ,sex,address,email, createDt) {
            var userDetails = "用户ID: " + userId + "\n" +
                "用户名: " + userName + "\n" +
                "密码: " + password + "\n" +
                "手机号: " + phone + "\n" +
                "账户余额: " + rmb + "\n" +
                "性别: " + sex + "\n" +
                "地址: " + address + "\n" +
                "邮箱: " + email + "\n" +
                "创建日期: " + createDt;
            alert(userDetails);
        }*/
        function showUserDetails2(userId, userName, password, phone, rmb, sex, address, email, createDt) {
            var userDetails = "<span style='font-size: 24px;'>用户ID: </span><span style='font-size: 20px;'>" + userId + "</span><br>" +
                "<span style='font-size: 24px;'>用户名: </span><span style='font-size: 20px;'>" + userName + "</span><br>" +
                "<span style='font-size: 24px;'>密码: </span><span style='font-size: 20;'>" + password + "</span><br>" +

                "<span style='font-size: 24px;'>手机号: </span><span style='font-size: 20px;'>" + phone + "</span><br>" +

                "<span style='font-size: 24px;'>账户余额: </span><span style='font-size: 20px;'>" + rmb + "</span><br>" +
                "<span style='font-size: 24px;'>性别: </span><span style='font-size: 20px;'>" + sex + "</span><br>" +
                "<span style='font-size: 24px;'>地址: </span><span style='font-size: 20px;'>" + address + "</span><br>" +
                "<span style='font-size: 24px;'>邮箱: </span><span style='font-size: 20px;'>" + email + "</span><br>" +
                "<span style='font-size: 24px;'>创建日期: </span><span style='font-size: 20px;'>" + createDt + "</span><br>";

            var modal = document.createElement("div");
            modal.innerHTML = userDetails;
            modal.style.position = "fixed";
            modal.style.top = "50%";
            modal.style.left = "50%";
            modal.style.transform = "translate(-50%, -50%)";
            modal.style.backgroundColor = "#fff";
            modal.style.padding = "20px";
            modal.style.border = "1px solid #ccc";
            modal.style.boxShadow = "0 0 10px rgba(0, 0, 0, 0.3)";
            modal.style.fontFamily = "Arial, sans-serif";

            var closeButton = document.createElement("button");
            closeButton.innerHTML = "关闭";
            closeButton.style.marginTop = "10px";
            closeButton.style.padding = "5px 10px";
            closeButton.style.backgroundColor = "#ccc";
            closeButton.style.border = "none";
            closeButton.style.cursor = "pointer";

            closeButton.addEventListener("click", function() {
                document.body.removeChild(modal);
            });

            modal.appendChild(closeButton);

            document.body.appendChild(modal);
        }



    </script>


</head>
<body>
<div class="panel admin-panel">
    <form action="${ctx}/userinfo/findBySql" id="listform" method="post">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left: 10px;">
                <li>
                    <a class="button border-main icon-plus-square-o" href="${ctx}/userinfo/add">添加用户</a>
                </li>
                <li>
                    <input type="text" placeholder="请输入用户名称" name="userName" class="input" value="${obj.userName}"
                           style="width: 250px;line-height: 17px;display: inline-block" />
                    <a href="javascript:void(0)" onclick="changeSearch()" class="button border-main icon-search">搜索</a>
                </li>
            </ul>
        </div>
    </form>
    <table class="table table-hover text-center">
        <tr>
            <th>编号</th>
            <th>用户名</th>
            <th>密码</th>
            <th>手机号</th>
            <th>账户余额</th>
            <th>创建日期</th>
            <th>充值金额</th>
        </tr>
        <c:forEach items="${pagers.datas}" var="data" varStatus="l">
            <tr>
                <td>${data.id}</td>
                <td>${data.userName}</td>
                <td>${data.passWord}</td>
                <td>${data.phone}</td>
                <td>${data.rmb}</td>
                <td>${data.createDt}</td>
                <td style="width: 100px !important;">
                    <div class="field">
                        <input type="number" name="quantity" class="input w100" min="1" max="999" required>
                    </div>
                </td>

                <td>
                    <a class="button border-main rechargeBtn" data-id="${data.id}"><span class="icon-edit">充值</span> </a>
                    <a class="button border-red" href="${ctx}/userinfo/delete?id=${data.id}"><span class="icon-trash-o">删除用户</span> </a>
                        <%-- <a class="button border-main" href="${ctx}/userinfo/see?id=${data.id}"><span class="icon-trash-o">查看用户详情</span> </a>--%>
                        <%-- <a class="button border-main" href="#" onclick="showUserDetails(${data.id}); return false;"><span class="icon-trash-o">查看用户详情</span> </a>
                         --%><a class="button border-main" href="#" onclick="showUserDetails2(${data.id}, '${data.userName}', '${data.passWord}', '${data.phone}','${data.rmb}', '${data.sex}','${data.address}','${data.email}', '${data.createDt}'); return false;" class="button border-main icon-search">查看用户详情</a>

                </td>

            </tr>
        </c:forEach>
        <tr>
            <td colspan="8">
                <div class="pagelist">
                    <!--分页开始-->
                    <pg:pager url="${ctx}/userinfo/findBySql" maxIndexPages="5" items="${pagers.total}" maxPageItems="15" export="curPage=pageNumber">
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