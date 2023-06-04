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
    <style>
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 50%;
        }
    </style>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add">
        <strong><span class="icon-pencil-square-o">店员信息修改</span></strong>
    </div>
    <div class="body-content">
        <a class="button border-main" href="${ctx}/userinfo/see?id=${data.id}"><span class="icon-trash-o">查看用户详情</span></a>
        <div id="myModal" class="modal">
            <div class="modal-content">
                <form action="${ctx}/userinfo/exUpdate" method="post" class="form-x" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${obj.id}" />
                    <div class="form-group">
                        <div class="label"><label>编号：</label></div>
                        <div class="field">
                            <input type="text" class="input w50" name="employeename" value="${obj.id}" readonly />
                            <div class="tips"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="label"><label>名称：</label></div>
                        <div class="field">
                            <input type="text" class="input w50" name="employeename" value="${obj.userName}" readonly />
                            <div class="tips"></div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label"><label>密码：</label></div>
                        <div class="field">
                            <input type="text" class="input w50" name="employeename" value="${obj.passWord}" readonly />
                            <div class="tips"></div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label"><label>手机号：</label></div>
                        <div class="field">
                            <input type="text" class="input w50" name="employeename" value="${obj.phone}" readonly />
                            <div class="tips"></div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label"><label>性别：</label></div>
                        <div class="field">
                            <input type="text" class="input w50" name="employeename" value="${obj.sex}" readonly />
                            <div class="tips"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="label"><label>地址：</label></div>
                        <div class="field">
                            <input type="text" class="input w50" name="employeename" value="${obj.address}" readonly />
                            <div class="tips"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="label"><label>邮箱：</label></div>
                        <div class="field">
                            <input type="text" class="input w50" name="employeename" value="${obj.email}" readonly />
                            <div class="tips"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="label"><label>创建日期：</label></div>
                        <div class="field">
                            <input type="text" class="input w50" name="employeename" value="${obj.createDt}" readonly />
                            <div class="tips"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="label"><label>余额：</label></div>
                        <div class="field">
                            <input type="text" class="input w50" name="employeename" value="${obj.rmb}" readonly />
                            <div class="tips"></div>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
<script>
    // 获取模态框元素
    var modal = document.getElementById("myModal");

    // 获取打开模态框的链接元素
    var link = document.querySelector("a.button");

    // 获取关闭模态框的按钮元素
    var closeButton = document.querySelector(".modal .button");

    // 点击链接打开模态框
    link.onclick = function(event) {
        event.preventDefault();
        modal.style.display = "block";
    };
    // 点击关闭按钮关闭模态框
    closeButton.onclick = function() {
        modal.style.display = "none";
    };

    // 在模态框外部点击关闭模态框
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
</script>
</body>
</html>

    // 点击关闭按钮关闭
    <%--
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
            <strong><span class="icon-pencil-square-o">用户信息</span></strong>
        </div>
        <div class="body-content">
            <form action="${ctx}/userinfo/exUpdate" method="post" class="form-x" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${obj.id}" />
                <div class="form-group">
                    <div class="label"><label>编号：</label></div>
                    <div class="field">
                        <input type="text" class="input w50" name="employeename" value="${obj.id}" readonly />
                        <div class="tips"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label>名称：</label></div>
                    <div class="field">
                        <input type="text" class="input w50" name="employeename" value="${obj.userName}" readonly />
                        <div class="tips"></div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="label"><label>密码：</label></div>
                    <div class="field">
                        <input type="text" class="input w50" name="employeename" value="${obj.passWord}" readonly />
                        <div class="tips"></div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="label"><label>手机号：</label></div>
                    <div class="field">
                        <input type="text" class="input w50" name="employeename" value="${obj.phone}" readonly />
                        <div class="tips"></div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="label"><label>性别：</label></div>
                    <div class="field">
                        <input type="text" class="input w50" name="employeename" value="${obj.sex}" readonly />
                        <div class="tips"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label>地址：</label></div>
                    <div class="field">
                        <input type="text" class="input w50" name="employeename" value="${obj.address}" readonly />
                        <div class="tips"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label>邮箱：</label></div>
                    <div class="field">
                        <input type="text" class="input w50" name="employeename" value="${obj.email}" readonly />
                        <div class="tips"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label>创建日期：</label></div>
                    <div class="field">
                        <input type="text" class="input w50" name="employeename" value="${obj.createDt}" readonly />
                        <div class="tips"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label>余额：</label></div>
                    <div class="field">
                        <input type="text" class="input w50" name="employeename" value="${obj.rmb}" readonly />
                        <div class="tips"></div>
                    </div>
                </div>

            </form>
        </div>
    </div>
    </body>

    </html>--%>
