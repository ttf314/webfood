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
        <strong><span class="icon-pencil-square-o">店员信息修改</span></strong>
    </div>
    <div class="body-content">
        <form action="${ctx}/employeeinfo/exUpdate" method="post" class="form-x" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${obj.id}" />
            <div class="form-group">
                <div class="label"><label>雇员名称：</label></div>
                <div class="field">
                    <input type="text" class="input w50" name="employeename" value="${obj.employeename}" readonly />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label"><label>脸主图：</label></div>
                <div class="field">
                    <div class="tips">
                        <img src="${obj.faceurl}" alt="${obj.faceurl}" style="width: 200px; height: 200px;">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="label"><label>健康证主图：</label></div>
                <div class="field">
                    <div class="tips">
                        <img src="${obj.healthcertificateurl}" alt="${obj.healthcertificateurl}" style="width: 200px; height: 200px;">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="label"><label>雇员手机号：</label></div>
                <div class="field">
                    <input type="text" class="input w50" name="phone" value="${obj.phone}" readonly />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label"><label>雇员地址：</label></div>
                <div class="field">
                    <input type="text" class="input w50" name="address" value="${obj.address}" readonly />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label"><label>雇员薪资：</label></div>
                <div class="field">
                    <input type="text" class="input w50" name="rmb" value="${obj.rmb}" readonly />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label"><label>描述：</label></div>
                <div class="field">
                    <div class="tips">${obj.detel}</div>
                </div>
            </div>
            <%--  <div class="form-group">
                  <div class="label"></div>
                  <div class="field">
                      <button class="button bg-main icon-check-square-o" type="submit" disabled>提交</button>
                  </div>
              </div>--%>
        </form>
    </div>
</div>
</body>

</html>