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
    <%--<script>
        var goodsSelect = document.getElementById("goodsSelect");
        var goodsInput = document.getElementById("goodsInput");
        // 当输入框的值发生改变时，更新下拉框的选中值
        goodsInput.addEventListener("input", function() {
            goodsSelect.value = this.value;
        });
        // 提交表单时，判断输入框是否有值，如果有就使用输入框的值，否则使用下拉框中选中的值
        document.getElementById("myForm").addEventListener("submit", function() {
            var goodsName = goodsInput.value.trim();
            if (goodsName !== "") {
                goodsSelect.disabled = true; // 禁用下拉框
                goodsSelect.value = goodsName;
            }
        });
    </script>--%>
    <script>
        var goodsSelect = document.getElementById("goodsSelect");
        var goodsInput = document.getElementById("goodsInput");
        // 当输入框的值发生改变时，更新下拉框的选中值
        goodsInput.addEventListener("input", function() {
            goodsSelect.value = this.value;
        });
        // 提交表单时，判断输入框是否有值，如果有就使用输入框的值，否则使用下拉框中选中的值
        document.getElementById("myForm").addEventListener("submit", function() {
            var goodsName = goodsInput.value.trim();
            if (goodsName !== "") {
                goodsSelect.disabled = true; // 禁用下拉框
                goodsSelect.value = goodsName;
            }
        });
    </script>

</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add">
        <strong><span class="icon-pencil-square-o">进货</span> </strong>
    </div>
    <div class="body-content">
        <form action="${ctx}/inbound/exAdd" method="post" class="form-x" enctype="multipart/form-data">
            <%--<div class="form-group">
                <div class="label"><label>商品名称：</label></div>
                <div class="field">
                    <input type="text" class="input w50" name="name" data-validate="required:请输入商品名称" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label"><label>商品价格：</label></div>
                <div class="field">
                    <input type="text" class="input w50" name="price" data-validate="required:请输入商品价格" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label"><label>商品折扣：</label></div>
                <div class="field">
                    <input type="text" class="input w50" name="zk" data-validate="required:请输入商品折扣" />
                    <div class="tips"></div>
                </div>
            </div>--%>
                <div class="form-group">
                    <div class="label"><label>商品类别：</label></div>
                    <div class="field">
                        <select name="goods_id" class="input w50">
                            <c:forEach items="${types3}" var="data" varStatus="l">
                                <option value="${data.name}">${data.name}</option>
                            </c:forEach>
                        </select>
                    </div>


                    <%--<div class="form-group">
                        <div class="label"><label>商品名称：</label></div>
                        <div class="field">
                            <select id="goodsSelect" name="goods_name" class="input w50">
                                <option value=" ">请选择商品</option> <!-- 添加默认选项 -->
                                <c:forEach items="${types}" var="data" varStatus="l">
                                    <option value="${data.name}">${data.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>--%>
                    <div class="form-group">
                        <div class="label"><label>商品名称：</label></div>
                        <div class="field">
                            <select id="goodsSelect" name="goods_name" class="input w50">
                                <option value="">请选择商品</option>
                                <c:forEach items="${types}" var="data" varStatus="l">
                                    <option value="${data.name}">${data.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="label"><label>是进其他商品在这里输入：</label></div>
                        <div class="field">
                            <input type="text" id="goodsInput" name="goods_name" class="input w50">
                        </div>
                    </div>



                    <div class="form-group">
                    <div class="label"><label>买入东西的店员名字：</label></div>
                    <div class="field">
                        <select name="operator" class="input w50">
                            <c:forEach items="${types2}" var="data" varStatus="l">
                                <option value="${data.employeename}">${data.employeename}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>


                <div class="form-group">
                    <div class="label"><label>入库商品数量：</label></div>
                    <div class="field">
                        <input type="number" name="quantity" class="input w50" min="1" max="999" required>
                    </div>
                </div>
                    <%--<div class="form-group">
                        <div class="label"><label>入库商品花销</label></div>
                        <div class="field">
                            <input type="text" id="rmb" name="rmb" class="input w50">
                        </div>
                    </div>--%>
                    <div class="form-group">
                        <div class="label"><label>入库商品花销：</label></div>
                        <div class="field">
                            <input type="number" name="rmb" class="input w50" min="1" max="9999" required>
                        </div>
                    </div>
            <%--</div>
            <div class="form-group" >
                <div class="label"><label>主图：</label></div>
                <div class="field" >
                    <input type="file" class="input w50" name="file" />
                    <div class="tips" ></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label"><label>商品描述：</label></div>
                <div class="field" >
                    <input type="text" class="input w200" name="desccription" data-validate="required:请输入商品描述"  value="${obj.desccription}"/>
                    <div class="tips"></div>
                </div>
            </div>--%>
            <div class="form-group">
                <div class="label"><label>描述：</label></div>
                <div class="field">
                    <script type="text/plain" id="remark_txt_1" name="detel" style="width: 100%;height: 300px;"></script>
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