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
        <strong><span class="icon-pencil-square-o">店员信息修改</span> </strong>
    </div>
    <div class="body-content">
        <form action="${ctx}/inbound/exUpdate" method="post" class="form-x" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${obj.id}" />
            <div class="form-group">
                <div class="label"><label>名称：</label></div>
                <div class="field">
                    <input type="text" class="input w50" name="employeename" data-validate="required:请输入雇员名称 " value="${obj.goods_name}" />
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label"><label>详情：</label></div>
                <div class="field">
                    <script type="text/plain" id="remark_txt_1" name="ms" style="width: 100%;height: 300px;">${obj.detel}</script>
                    <script type="text/javascript">
                        var editor = UE.getEditor('remark_txt_1');
                        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
                        UE.Editor.prototype.getActionUrl = function (action) {
                            if(action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadvideo'){
                                return '${ctx}/ueditor/saveFile';
                            }else{
                                return this._bkGetActionUrl.call(this,action);
                            }
                        }
                    </script>
                    <div class="tips"></div>
                </div>
            </div>
            <%--<div class="form-group">
                <div class="label"><label>描述：</label></div>
                <div class="field">
                    <script type="text/plain" id="remark_txt_1" name="description" style="width: 100%;height: 300px;"></script>
                    <script type="text/javascript">
                        var editor = UE.getEditor('remark_txt_1');
                        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
                        UE.Editor.prototype.getActionUrl = function (action) {
                            if(action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadvideo'){
                                return '${ctx}/ueditor/saveFile';
                            }else{
                                return this._bkGetActionUrl.call(this,action);
                            }
                        }
                    </script>
                    <div class="tips"></div>
                </div>
            </div>--%>

            <%--<div class="form-group">
                <div class="label"></div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit">提交</button>
                </div>
            </div>--%>
        </form>
    </div>
</div>
</body>
</html>