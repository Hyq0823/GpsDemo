<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${basePath }/static/js/files/jquery.min.js"></script>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
    request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<title>PDF预览-demo</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" user-scalable="no">
<head>
<style type="text/css">
    .main{
        border:1px solid green;
        position: absolute;
        width:400px;
        heigth:200px;
        left:50%;
        top:50%;
        margin: -100px 0 0 -200px;
        text-align: center;
    }
</style>
</head>

<div style="margin-top: 20px;">
    <hr />
    <div class="main">
     <p>
         <a href="${basePath}/pdfview.jsp?pdf_name=desine.pdf">设计模式-方式1</a>
     </p>
    <p>
        <a href="${basePath}/pdfview.jsp?pdf_name=jmx.pdf">jms扫描版-方式1</a>
    </p>

        <p>
            <a href="${basePath}/pdfview.jsp?pdf_name=2.pdf">泰文-方式1</a>
        </p>

        <hr />
    <p>
        <a href="${basePath}/pdf/parse?pdf_name=netty">netty分析-方式2</a>
    </p>

    <p>
        <a href="${basePath}/pdf/parse?pdf_name=guice">guice pdf-方式2</a>
    </p>

        <p>
            <a href="${basePath}/html/222.html">泰文 pdf-方式2</a>
        </p>
    </div>



</div>
<script type="text/javascript">
</script>
</body>
</html>