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
    <script type="text/javascript" src="${ctx}/resource/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${ctx}/resource/ueditor/ueditor.all.min.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add">
        <strong><span class="icon-pencil-square-o">店员完成上菜</span> </strong>
    </div>
    <div class="body-content">
        <form action="${ctx}/order/exAddo2" method="post" class="form-x" enctype="multipart/form-data">
            <input type="text" name="id" value="${obj.id}" />
            <input type="text" name="uid" value="${obj.uid}" />
            <input type="text" name="lastid" value="${obj.id}" />
            <input type="text" name="count" value="${obj.count}" />
            <input type="text" name="nowrmb" value="${obj.nowrmb}" />
            <input type="text" name="price" value="${obj.price}" />
            <input type="text" name="date" value="${obj.date}" />
            <input type="text" name="date2" value="${obj.date}" />
            <input type="text" name="detel" value="${obj.detel}" />
            <input type="text" name="detel2" value="${obj.detel2}" />
            <input type="text" name="weizhi" value="${obj.weizhi}" />
            <input type="text" name="isDelete" value="${obj.isDelete}" />
            <div class="form-group">
                <div class="label"><label>店员名字：</label></div>
                <div class="field">
                    <select name="empname" class="input w50">
                        <c:forEach items="${types}" var="data" varStatus="l">
                            <option value="${data.id}">${data.employeename}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <%--
            <div class="form-group">
                <div class="label"><label>商品名称：</label></div>
                <div class="field">
                    <input type="text" class="input w50" name="name" data-validate="required:请输入商品名称" value="${obj.name}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label"><label>商品价格：</label></div>
                <div class="field">
                    <input type="text" class="input w50" name="price" data-validate="required:请输入商品价格"  value="${obj.price}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label"><label>商品折扣：</label></div>
                <div class="field">
                    <input type="text" class="input w50" name="zk" data-validate="required:请输入商品折扣"  value="${obj.zk}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label"><label>商品类别：</label></div>
                <div class="field">
                    <select name="categoryIdTwo" class="input w50">
                        <c:forEach items="${types}" var="data" varStatus="l">
                            <option value="${data.id}" ${obj.categoryIdTwo == data.id?"selected":""}>${data.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="label"><label>商品描述：</label></div>
                <div class="field" >
                    <input type="text" class="input w200 h100" name="desccription" data-validate="required:请输入商品描述"  value="${obj.desccription}"/>
                    <div class="tips"></div>
                </div>
            </div>
            --%>

            <div class="form-group">
                <div class="label"></div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>