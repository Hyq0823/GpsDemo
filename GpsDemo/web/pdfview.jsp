<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${basePath }/static/layer3.0.3/layer/layer.js"></script>
<script type="text/javascript" src="${basePath }/static/js/files/jquery.min.js"></script>


<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
    request.setAttribute("basePath", basePath);
%>


<script type="text/javascript">
    if("${html}"!=""){
        window.location.href = "${basePath}/html/${html}";
    }
</script>


<script type="text/javascript" src="${basePath }/static/pdf/jquery.media.js"></script>
<%
    String pdf_name = request.getParameter("pdf_name");
    request.setAttribute("pdf_name",pdf_name);
%>



<!DOCTYPE html>
<html>
<title>PDF预览详情页</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" user-scalable="no" />

<div style="border:1px solid red;width:200px;height:20px;">效果一</div>
<div>
    <a class="media" href="${basePath}/static/pdf/${pdf_name}">展示的pdf文件</a>
</div>


<script type="text/javascript">
    $(function() {//启动pdf预览...
        $('a.media').media({width:"100%", height:"800"});
    });
</script>
</body>
</html>