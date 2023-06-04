<%--
  Created by IntelliJ IDEA.
  User: 32355
  Date: 2023/5/19
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.google.zxing.BarcodeFormat, com.google.zxing.EncodeHintType, com.google.zxing.MultiFormatWriter,
com.google.zxing.WriterException, com.google.zxing.common.BitMatrix, com.google.zxing.qrcode.QRCodeWriter,
com.google.zxing.client.j2se.MatrixToImageWriter" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String id = "your_id_value"; // 替换为您的实际id值
    int qrCodeWidth = 300;
    int qrCodeHeight = 300;

    String qrCodeData = "https://example.com?id=" + id; // 替换为您的实际URL

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, qrCodeWidth, qrCodeHeight);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
    baos.flush();

    byte[] qrCodeBytes = baos.toByteArray();
    baos.close();
%>

<img src="data:image/png;base64,${fn:escapeXmlString(org.apache.commons.codec.binary.Base64.encodeBase64String(qrCodeBytes))}" alt="QR Code">
