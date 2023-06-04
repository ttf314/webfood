<%@page language="java" contentType="text/html; character=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${ctx}/resource/css/style2.css">
    <link rel="stylesheet" href="${ctx}/resource/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/resource/css/admin.css">
    <script src="${ctx}/resource/js/jquery.js"></script>
    <script src="${ctx}/resource/js/pintuer.js"></script>
    <script src="${ctx}/resource/js/script2.js" type="text/javascript" defer></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/qrcodejs/1.0.0/qrcode.min.js" integrity="sha512-CNgIRecGo7nphbeZ04Sc13ka07paqdeTu0WR1IM4kNcpmBAUSHSQX0FslNhTDadL4O5SAGapGt4FodqL8My0mA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <title>Document</title>
</head>
<%--
<body>
<div id="main-container" class="main-container">
    <h1 class="title">二维码生成器</h1>
    <p class="p-tips">请在下方输入URL以生成对应的二维码并下载对应的二维码</p>
    <form id="generator-form">
        <input type="url" id="url" class="url" placeholder="请输入URL">
        <select name="size" id="size" class="size">
            <option value="100">100x100</option>
            <option value="200">200x200</option>
            <option value="300" selected>300x300</option>
            <option value="400">400x400</option>
            <option value="500">500x500</option>
            <option value="600">600x600</option>
            <option value="700">700x700</option>
        </select>
        <div class="btn-container">
            <button class="btn">生成二维码</button>
        </div>
    </form>
</div>
<div id="bottom-container" class="bottom-container">
    <div id="spinner" class="spinner">
        <div class="lds-dual-ring"></div>
    </div>
    <div id="qrcode" class="qrcode"></div>
</div>

</body>--%>
<body>
<div id="main-container" class="main-container">
    <h1 class="title">生成店内专属二维码</h1>
    <form id="generator-form">

        <input type="text" id="value" class="input" placeholder="您的店铺编号为${id}" name="no" class="input" value="${id}" style="width: 250px;line-height: 17px;display: inline-block" />
        <%-- <input type="text" id="value" class="value" placeholder="您的店铺编号为${id}" >--%>
        <select name="size" id="size" class="size">
            <option value="100">100x100</option>
            <option value="200">200x200</option>
            <option value="300" selected>300x300</option>
            <option value="400">400x400</option>
            <option value="500">500x500</option>
            <option value="600">600x600</option>
            <option value="700">700x700</option>
        </select>
        <div class="btn-container">
            <button style="width: 200px;" class="button border-main">生成二维码</button>
        </div>
    </form>
</div>

<div id="bottom-container" class="bottom-container">
    <div id="spinner" class="spinner">
        <div class="lds-dual-ring"></div>
    </div>
    <div id="qrcode" class="qrcode"></div>
</div>

<script>
    const form = document.querySelector("#generator-form")
    const qrcode = document.querySelector("#qrcode")

    const onGenerateForm = (e) => {
        e.preventDefault()

        clearUI()

        const value = document.querySelector("#value").value
        const size = document.querySelector("#size").value

        if (value === "") {
            alert("请输入要生成二维码的值")
        } else {
            showSpinner()

            setTimeout(() => {
                hideSpinner()
                generateQRCode(value, size)

                setTimeout(() => {
                    const downloadUrl = qrcode.querySelector("img").src
                    createDownloadBtn(downloadUrl)
                }, 100)
            }, 1000)
        }
    }

    // 显示spinner
    const showSpinner = () => {
        const spinner = document.querySelector("#spinner")
        spinner.style.display = "block"
    }

    // 隐藏spinner
    const hideSpinner = () => {
        const spinner = document.querySelector("#spinner")
        spinner.style.display = "none"
    }

    // 生成二维码
    const generateQRCode = (text, size) => {
        const qrcode = new QRCode("qrcode", {
            text: text,
            width: size,
            height: size,
        })
    }

    const clearUI = () => {
        qrcode.innerHTML = ""

        const btn = document.querySelector("#download-url")
        if (btn) {
            btn.remove()
        }
    }

    const createDownloadBtn = (downloadUrl) => {
        const link = document.createElement("a")
        link.id = "download-url"
        link.className = "download-btn"
        link.href = downloadUrl
        link.download = "二维码"
        link.innerHTML = "下载二维码图片"
        document.querySelector("#bottom-container").appendChild(link)
    }

    form.addEventListener("submit", onGenerateForm)
</script>
</body>

</html>
