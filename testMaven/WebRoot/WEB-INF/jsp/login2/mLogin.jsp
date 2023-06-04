<%@page language="java" contentType="text/html; character=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>商家登录</title>
    <link type="text/css" rel="stylesheet" href="${ctx}/resource/css/index.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/resource/ml/css/style.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/resource/css/pintuer.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/resource/css/admin.css">
    <script src="${ctx}/resource/js/jquery.js"></script>
    <script src="${ctx}/resource/js/pintuer.js"></script>
</head>
<%--
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height: 150px;"></div>
            <div class="media media-y margin-big-bottom"></div>
            <form action="${ctx}/login2/toLogin" method="post">
                <div class="panel login-box">
                    &lt;%&ndash;<div class="panel login-box">&ndash;%&gt;
                    <div class="text-center margin-big padding-big-top"><h1>商家登录</h1></div>
                    <div class="panel-body" style="padding: 30px;padding-bottom: 10px; padding-top: 10px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input input-big" name="id" value="1" placeholder="登录账号" data-validate="required:请填写账号" />
                                <span class="icon icon-user margin-small"></span>&lt;%&ndash;//图标&ndash;%&gt;
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" class="input input-big" name="pass" value="1" placeholder="登录密码" data-validate="required:请填写密码" />
                                <span class="icon icon-key margin-small"></span>&lt;%&ndash;//图标&ndash;%&gt;
                            </div>
                        </div>
                    </div>
                    <div style="padding: 30px;">
                       <input type="submit" class="button button-block bg-main text-big input-big" value="登录" />
                    </div>
                </div>
            </form>
        </form>
        </div>
    </div>
</div>
</body>
--%>


<body>
<section>
    <div class="container">
        <div class="line bouncein">
            <div class="xs6 xm4 xs3-move xm4-move">
                <div class="form-box">
                    <div class="form-value">
                        <form action="${ctx}/login2/toLogin" method="post">
                            <h2>商家登录</h2>
                            <div class="inputbox">
                                <%--<input type="text" class="input input-big" name="userName" value="admin" placeholder="登录账号" data-validate="required:请填写账号" />
                                <label for="">Email</label>--%>
                                <ion-icon name="mail-outline"></ion-icon>
                                <input type="text" name="id" value="28" required data-validate="required:请填写账号">
                                <label for=>账户</label>
                            </div>
                            <div class="inputbox">
                                <%-- <input type="password" class="input input-big" name="passWord" value="111111" placeholder="登录密码" data-validate="required:请填写密码" />
             --%>
                                <ion-icon name="lock-closed-outline"></ion-icon>
                                <input type="password" name="pass" value="1"required >
                                <label for=>密码</label>
                            </div>
                            <%--   <div class="forget">
                                   <input type="checkbox">Remember Me  <a href="#">Forget Password</a></label>

                               </div>--%>
                            <button>登录</button>
                            <%-- <input type="submit" class="button button-block bg-main text-big input-big" value="登录" />
                             --%><div class="register">
                        </div>
                            <div class="register">
                                <p>是管理员---> <a href="${ctx}/login/mtuichu.html">点击</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>