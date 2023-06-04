<%@page language="java" contentType="text/html; character=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>商家后台</title>
    <link rel="stylesheet" href="${ctx}/resource/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/resource/css/admin.css">
    <script src="https://cdn.jsdelivr.net/npm/qrcode@1.4.4/qrcode.min.js"></script>
    <script src="${ctx}/resource/js/jquery.js"></script>
    <script src="${ctx}/resource/js/pintuer.js"></script>
        <script>
            // 获取“订单管理”链接的元素
            var orderLink = document.getElementById("dd");
            // 定义一个函数，模拟点击链接
            function clickOrderLink() {
                orderLink.click();
            }
            // 监听“订单管理”链接的点击事件
            orderLink.addEventListener("click", function() {
                // 设置一个计时器，在1秒后开始每隔1秒触发一次 clickOrderLink 函数
                setInterval(clickOrderLink, 1000);
            });
        </script>
    <script>
        window.onload = function() {
            const link = document.querySelector('a[target="right"]');
            function clickLink() {
                link.click();
            }
            setInterval(clickLink, 1000);
        }
    </script>

</head>
<body style="background-color: #f2f9fd">
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1>平台祝"${id}"号商家财源滚滚</h1>
    </div>
    <div class="logo margin-big-left fadein-top" id="scrollBox">${xingxi}</div>
    <script type="text/javascript">
        window.onload = function(){
            var data = {
                xingxi: "${xingxi}"
            };
            var scrollBox = document.getElementById("scrollBox");
            scrollBox.innerText = data.xingxi;
            var boxWidth = scrollBox.offsetWidth;
            var screenWidth = window.screen.width;
            var curPos = screenWidth;
            var speed = 50; // 调整滚动速度，单位为像素/秒
            var timer = setInterval(function(){
                if(curPos + boxWidth < 0){
                    curPos = screenWidth;
                }
                curPos -= 1;
                scrollBox.style.transform = "translateX(" + curPos + "px)";
            }, 1000 / speed);
        }



    </script>
    <!-- 在页面中添加 marquee 标签 -->

</div>
<div class="leftnav">
    <div class="leftnav-title"><strong><span class="icon-list">列表</span> </strong></div>
    <h2><span class="icon-user"></span>改密 </h2>
        <ul style="display: block">
            <li>
                <a href="${ctx}/itemCategory/findBySql2?pid=${id}" target="right">
                    <span class="icon-caret-right"></span>餐品类别管理
                </a>
            </li>
            <li>
                <a href="${ctx}/meals/findBySql" target="right">
                    <span class="icon-caret-right"></span>餐品管理
                </a>
            </li>
            <li>
            <a href="${ctx}/order/findBySql" target="right" id="dingdan">
                <span class="icon-caret-right"></span>订单管理
            </a>
            <li>
                <a href="${ctx}/evaluates/findBySql2" target="right" id="pinjia">
                    <span class="icon-caret-right"></span>本店评价查看
                </a>
            </li>

           <%-- <li>
                <a href="#" target="right">
                    <span class="icon-caret-right"></span>账单管理
                </a>
            </li>--%>
            <li>
                <a onclick="toggleSubMenu2()">
                    <span class="icon-sort-down"></span>仓库管理
                </a>
                <ul id="submenu2" style="display:none">
                    <li><a target="right" href="${ctx}/Item_warehouse/findBySql2?pid=${id}"><span class="icon-caret-right"></span>类别管理</a></li>
                    <li><a target="right" href="${ctx}/inbound/findBySql"><span class="icon-caret-right"></span>进货管理</a></li>
                </ul>
            </li>
            <li>
                <a onclick="toggleSubMenu()">
                    <span class="icon-sort-down"></span>店员管理
                </a>
                <ul id="submenu" style="display:none">
                    <li><a target="right" href="${ctx}/employeeinfo/findBySql"><span class="icon-caret-right"></span>主要管理</a></li>
                    <li><a target="right" href="${ctx}/employeeinfo/findBySql2"><span class="icon-caret-right"></span>薪资管理</a></li>
                    <li><a target="right" href="${ctx}/employeeinfo/findBySql3"><span class="icon-caret-right"></span>考勤管理</a></li>
                </ul>
            </li>
           <%-- <li>
                <a href="${ctx}/xingxi/findBySql2" target="right" id="erweima">
                    <span class="icon-caret-right"></span>二维码
                </a>
            </li>
            <li>
                <a  target="right" id="erweima2">
                    <span class="icon-caret-right"></span>二维码
                </a>
            </li>--%>
            <li>
                <a href="${ctx}/xingxi/findBySql2?id=${id}" target="right" id="erweima">
                    <span class="icon-caret-right"></span>二维码
                </a>
            </li>
        </ul>
    </div>

    <script>
        function toggleSubMenu() {
            var subMenu = document.getElementById('submenu');
            if (subMenu.style.display === 'none') {
                subMenu.style.display = 'block';
            } else {
                subMenu.style.display = 'none';
            }
        }

        function toggleSubMenu2() {
            var subMenu = document.getElementById('submenu2');
            if (subMenu.style.display === 'none') {
                subMenu.style.display = 'block';
            } else {
                subMenu.style.display = 'none';
            }
        }
    </script>



    <ul class="bread">
        <li><a href="${ctx}/itemCategory/charts2" target="right" class="icon-home">首页</a> </li>
        <li><a href="#">商家信息</a> </li>
        <li><a href="mtuichu.html">退出</a> </li>
    </ul>

   <%-- <div class="admin">
        <iframe name="right" scrolling="auto" frameborder="1" width="100%" height="100%"></iframe>
    </div>--%>
    <div class="admin">
        <iframe scrolling="auto" rameborder="0" src="${ctx}/itemCategory/charts2" name="right" width="100%" height="100%"></iframe>

    </div>
    <div id="qrcode-container"></div>
</body>
</html>