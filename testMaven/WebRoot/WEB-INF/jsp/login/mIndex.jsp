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
<body style="background-color: #f2f9fd">
    <div class="header bg-main">
        <div class="logo margin-big-left fadein-top">
            <h1>管理员后台</h1>
        </div>
    </div>
    <div class="leftnav">
        <div class="leftnav-title"><strong><span class="icon-list">列表</span> </strong></div>
        <h2><span class="icon-user"></span>改密 </h2>
        <ul style="display: block">
            <li><a href="${ctx}/itemCategory/findBySql" target="right"><span class="icon-caret-right"></span>商家管理</a> </li>
            <li><a href="${ctx}/meals/findBySql" target="right"><span class="icon-caret-right"></span>餐品管理</a> </li>
            <li><a href="${ctx}/order/findBySql" target="right"><span class="icon-caret-right"></span>订单管理</a> </li>
            <li><a href="${ctx}/userinfo/findBySql" target="right"><span class="icon-caret-right"></span>用户管理</a> </li>
            <li ><a href="${ctx}/employeeinfo/findBySql" target="right"><span class="icon-caret-right"></span>店员管理</a> </li>
            <li><a href="${ctx}/evaluates/findBySql" target="right"><span class="icon-caret-right"></span>评价管理</a> </li>
            <li><a href="${ctx}/question/findBySql" target="right"><span class="icon-caret-right"></span>聊天管理</a> </li>
            <li><a href="${ctx}/meals/findBySql3?action=0" target="right"><span class="icon-caret-right"></span>轮播图管理</a> </li>
            <li><a href="${ctx}/xingxi/findBySql" target="right"><span class="icon-caret-right"></span>横幅信息管理</a> </li>
        </ul>
    </div>
    <ul class="bread">
        <li><a href="${ctx}/itemCategory/charts" target="right" class="icon-home">首页</a> </li>
        <li><a href="mtuichu.html">退出</a> </li>
    </ul>
    <div class="admin">
        <iframe scrolling="auto" rameborder="0" src="${ctx}/itemCategory/charts" name="right" width="100%" height="100%"></iframe>
    </div>
</body>
</html>