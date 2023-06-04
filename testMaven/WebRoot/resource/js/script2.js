const form = document.querySelector("#generator-form")

const qrcode = document.querySelector("#qrcode")

const onGenerateForm = (e) => {
    e.preventDefault()

    clearUI()

    const value = document.querySelector("#value").value  // 获取输入框中的值
    const size = document.querySelector("#size").value

    if (value === "") {
        alert("请输入值")
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