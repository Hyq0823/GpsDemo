<%@ page language="Java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<head>
	<meta charset="utf-8">
	<title>科农ERP</title>
	<link rel="stylesheet" type="text/css" href="${basePath }/static/css/styles.css">
	<script type="text/javascript" src="${basePath }/static/js/files/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/forms/ui.spinner.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/forms/jquery.mousewheel.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/files/jquery-ui.min.js"></script>

	<script type="text/javascript" src="${basePath }/static/js/plugins/forms/autogrowtextarea.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/forms/jquery.uniform.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/forms/jquery.inputlimiter.min.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/forms/jquery.tagsinput.min.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/forms/jquery.maskedinput.min.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/forms/jquery.autotab.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/forms/jquery.chosen.min.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/forms/jquery.dualListBox.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/forms/jquery.cleditor.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/forms/jquery.ibutton.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/wizards/jquery.form.wizard.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/wizards/jquery.form.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/ui/jquery.collapsible.min.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/ui/jquery.breadcrumbs.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/ui/jquery.tipsy.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/ui/jquery.progress.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/ui/jquery.timeentry.min.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/ui/jquery.colorpicker.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/ui/jquery.jgrowl.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/ui/jquery.fancybox.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/ui/jquery.fileTree.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/ui/jquery.sourcerer.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/others/jquery.fullcalendar.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/others/jquery.elfinder.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/plugins/ui/jquery.easytabs.min.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/files/bootstrap.js"></script>
	<script type="text/javascript" src="${basePath }/static/js/files/functions.js"></script>
</head>
<body>