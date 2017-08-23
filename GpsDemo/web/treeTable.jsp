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
<link href="${basePath}/static/treeTable/themes/vsStyle/treeTable.css" rel="stylesheet" type="text/css" />
<script src="${basePath}/static/treeTable/jquery.treeTable.js" type="text/javascript"></script>
-->
<script src="${basePath}/static/js/mustache/mustache.min.js" type="text/javascript"></script>
<link href="${basePath}/static/treeTable2/themes/vsStyle/treeTable.min.css" rel="stylesheet" type="text/css" />
<script src="${basePath}/static/treeTable2/jquery.treeTable.min.js" type="text/javascript"></script>

<script type="text/javascript">
    function findByParentId(parentId,tpl,root){
        var html="";
        $.ajax({
            url:"${basePath}/table/findByParentId",
            async:false,
            data:{
                parentId:parentId
            },
            success:function(data){
                if(!data || data.length==0){
                    $(event.target).css("visibility","hidden");
                }
                for (var i=0; i<data.length;i++){
                    var row = data[i];
                    if (row){
                        console.log("产生html前row.parentId: ："+row.parentId +"row.hasChild:"+row.hasChild);
                        html+= Mustache.render(row.hasChild=='1'?tpl:tpl.replace('haschild="true"',''), {
                            pid: (row.parentId==""?0:row.parentId),row: row
                        });
                    }
                }
            }
        });
        console.log(html);
        return html;
    }

    $(document).ready(function() {
        var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
        $("#treeTableList").append(findByParentId("0",tpl,true));
        var $treeTable=$("#treeTable").treeTable({
            expandLevel : 2,
            beforeExpand : function($treeTable, id) {
                //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
                if ($('.' + id, $treeTable).length) { return; }
                var html=findByParentId(id,tpl,false);
                $treeTable.addChilds(html);
            },
            onSelect : function($treeTable, id) {
                window.console && console.log('onSelect:' + id);
            }

        });
       // $treeTable.addChilds(findByParentId($("#treeTableList").find("tr").eq(0).prop("id"),tpl,false));
    });


</script>


    <div class="main">
        <form method="" action="">
            <fieldset>
                <div >
                    <div class="formRow">
                        <div class="grid3"><label></label> </div>
                        <div class="grid9 moreFields onlyText">
                            <ul>
                                <li class="sep">名称：</li>
                                <li><input type="text" name="question" maxlength="6" /></li>
                                <li class="sep">岗位：</li>
                                <li>
                                    <div class="searchDrop import">
                                        <select class="select" tabindex="2">
                                            <option value="1">司机</option>
                                            <option value="2">系统管理员</option>
                                            <option value="3">门卫</option>
                                            <option value="4">车间主任</option>
                                        </select>
                                    </div>
                                </li>
                                <li class="sep">组织机构：</li>
                                <li><input type="text" name="question" maxlength="6" /></li>
                                <li class="sep">状态：</li>
                                <li>
                                    <div class="searchDrop import">
                                        <select class="select" tabindex="2">
                                            <option value="1">全部</option>
                                            <option value="2">启用</option>
                                            <option value="3">禁用</option>
                                        </select>
                                    </div>
                                </li>
                                <li><input style="margin: 0 20px;" type="submit" value="查询" class="buttonS bDefault mb10 mt5" /> </li>
                            </ul>
                            <div class="clear"></div>
                        </div>
                        <div class="clear"></div>
                    </div>

                </div>
            </fieldset>
        </form>


        <!-- With some stuff in the head -->
        <div class="widget" style="margin-top:0;">
            <div class="whead">
                <h6>机构列表</h6>
                <div class="titleOpt">
                    <a href="${basePath}/sysUserManage/add" style="padding: 0">
                        <img width="30px" height="30px" src="${basePath}/static/images/home/user_add.png" alt="添加"/>
                        <span class="clear"></span></a>
                </div>
                <div class="clear"></div>
            </div>
            <table id="treeTable" cellpadding="0" cellspacing="0" width="100%" class="tDefault">
                <thead>
                <tr>
                    <td style="width:200px;">地区名称</td>
                    <td>地区编码</td>
                </tr>
                </thead>
                <tbody id="treeTableList">

                </tbody>
            </table>
    </div>
</div>

<script type="text/template" id="treeTableTpl">
    <tr id="{{row.id}}" pId="{{pid}}" haschild="true">
        <td>{{row.name}}</td>
        <td>{{row.code}}</td>
    </tr>
</script>
</body>
</html>