<%--
  Created by IntelliJ IDEA.
  User: hyq
  Date: 2017/8/22
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp"></jsp:include>
<script type="text/javascript" src="${basePath }/static/layer3.0.3/layer/layer.js"></script>
<!--
-->
<script src="${basePath}/static/areaTree/comm.js" type="text/javascript"></script>
<script src="${basePath}/static/areaTree/linkagesel.js" type="text/javascript"></script>


<select id="demo17"></select>
<script>
    var opts17 = {
        //data: districtData,   // demo7
        ajax: '${basePath}/area/tree',
        selStyle: 'margin-left: 3px;',
        select:  '#demo17',
        defVal: [5, 256, 257]
    };

    var linkageSel17 = new LinkageSel(opts17);
</script>
</body>
</html>