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
    <form action="${ctx}/xingxi/upinfo" method="POST">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left: 10px;">
        <input type="text" placeholder="请输入传达给商家的信息" name="xingxi" class="input" value=" " style="width: 450px;line-height: 17px;display: inline-block" />

                <button type="submit" class="button border-main icon-search" style="width: 150px;">发表</button>

            </ul>
        </div>
    </form>
<%--
    <form action="${ctx}/xingxi/upinfo" id="listform" method="post">
        <div class="padding border-bottom">
            <ul class="search" style="padding-left: 10px;">
                <li>
                    <input type="text" placeholder="请输入传达给商家的信息" name="xingxi" class="input" value=""
                           style="width: 250px;line-height: 17px;display: inline-block" />
                    <a href="javascript:void(0)" onclick="changeSearch()" class="button border-main icon-search">发表</a>
                </li>
            </ul>
        </div>
    </form>--%>
    </div>
</body>

</html>