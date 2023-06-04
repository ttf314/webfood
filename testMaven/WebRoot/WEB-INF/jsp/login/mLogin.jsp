<%@page language="java" contentType="text/html; character=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
    <link type="text/css" rel="stylesheet" href="${ctx}/resource/css/index.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/resource/ml/css/style.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/resource/css/pintuer.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/resource/css/admin.css">
    <script src="${ctx}/resource/js/jquery.js"></script>
    <script src="${ctx}/resource/js/pintuer.js"></script>
    <title>管理员登录</title>
</head>

<body>
<section>
    <div class="container">
        <div class="line bouncein">
            <div class="xs6 xm4 xs3-move xm4-move">
    <div class="form-box">
        <div class="form-value">
            <form action="${ctx}/login/toLogin" method="post">
                <h2>管理员登录</h2>
                <div class="inputbox">
                    <%--<input type="text" class="input input-big" name="userName" value="admin" placeholder="登录账号" data-validate="required:请填写账号" />
                    <label for="">Email</label>--%>
                        <ion-icon name="mail-outline"></ion-icon>
                        <input type="text" name="userName" value="admin" required data-validate="required:请填写账号">
                        <label >账户</label>
                </div>
                <div class="inputbox">
                   <%-- <input type="password" class="input input-big" name="passWord" value="111111" placeholder="登录密码" data-validate="required:请填写密码" />
--%>
                       <ion-icon name="lock-closed-outline"></ion-icon>
                       <input type="password" name="passWord" value="111111"required >
                       <label >密码</label>
                </div>
             <%--   <div class="forget">
                    <input type="checkbox">Remember Me  <a href="#">Forget Password</a></label>

                </div>--%>
                <button>登录</button>
               <%-- <input type="submit" class="button button-block bg-main text-big input-big" value="登录" />
                --%><div class="register">
                    <p>是商家---> <a href="${ctx}/login2/mtuichu.html">点击</a></p>
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