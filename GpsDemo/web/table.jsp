<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/header.jsp"></jsp:include>
<script type="text/javascript" src="${basePath }/static/layer3.0.3/layer/layer.js"></script>

<link rel="stylesheet" type="text/css" href="${basePath }/static/js/plugins/forms/validate/validationEngine.jquery.css">
<script type="text/javascript" src="${basePath }/static/js/plugins/forms/validate/jquery.validationEngine.js"></script>
<script type="text/javascript"
        src="${basePath }/static/js/plugins/forms/validate/jquery.validationEngine-zh_CN.js"></script>

<style type="text/css">
    .operate {
        margin-left: 40px;
    }

    td input {
        width: 100%;
        height: 100%;
    }

    .dynDiv input {
        float: left;
    }

    .diplayNone {
        display: none
    }
</style>


<div style="border:1px solid red;width: 800px;height:40px;">
    ${table}
</div>

<div class="condition" style="width: 700px;height: 50px;">
    <div>
        <label>规则条件：</label>
        <input id="r1" type="radio" name="calculateWay" value="td_calculate" checked="checked"/><label
            style="float: left;" for="r1">计量</label>
        <input id="r2" type="radio" name="calculateWay" value="td_calculate_gap"/><label for="r2" style="float: left;">计量区间</label>
    </div>

    <div style="margin-top:10px;"></div>
    <br/>
    <div class="dynDiv">
        <label>动态列：</label>
        <input id="c1" type="checkbox" name="dyncolumn" value="td_weight_gap"/><label for="c1">重量区间</label>
        <input id="c2" type="checkbox" name="dyncolumn" value="td_gover_aid"/><label for="c2">政府补贴</label>
        <input id="c3" type="checkbox" name="dyncolumn" value="td_pay_back"/><label for="c3">返还金额</label>
    </div>
    <br/>
    <input id="condition_btn_submit" type="button" value="添加"/>
</div>

<!-- Table with checkboxes -->
<div class="widget" id="tableTemplate" style="display:none;">
    <div class="whead">
        <span class="titleIcon check">
            <input type="checkbox" style="width: 40px;height: 37px" name="titleCheck" class="headCheckBox"/>
        </span>
        <h6>表格模板</h6>
        <div class="operate">
            <a class="btn_row_add" href="#" style="margin-left:40px;">增加行</a>
            <a class="btn_row_del" href="#" style="margin-left: 20px;">删除行</a>
            <a class="btn_table_del" href="#" style="margin-left: 20px;">删除表格</a>
        </div>

        <div class="clear"></div>
    </div>
    <table cellpadding="0" cellspacing="0" width="100%" class="tDefault checkAll check">
        <thead>
        <tr>
            <td></td>
            <td>收集物</td>
            <td>类别</td>
            <td>单位</td>
            <!--两种：1.计量  2.计量小,计量大（区间） -->
            <td class="td_calculate" style="display: none">计量</td>
            <td class="td_calculate_gap" style="display: none">计量(小)</td>
            <td class="td_calculate_gap" style="display: none">计量(大)</td>

            <!--是否勾选重量区间,是：显示,否：不显示 -->
            <td class="td_weight_gap" style="display: none">最小重量</td>
            <td class="td_weight_gap" style="display: none">最大重量</td>

            <td class="td_charge">收费</td>
            <td class="td_gover_aid" style="display: none">政府补贴</td>
            <td class="td_pay_back" style="display: none">返还金额</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="checkbox" name="checkRow"/></td>
            <td>
                <input type="text" name="collect"/>
            </td>
            <td>
                <input type="text" name="type"/>
            </td>
            <td>
                <input type="text" name="unit"/>
            </td>

            <!--根据规则显示：1.计量  2.计量区间 -->
            <td class="td_calculate" style="display: none"><input class="validate[custom[number]]" type="text"
                                                                  name="weight_only" disabled="disabled"/></td>

            <td class="td_calculate_gap" style="display: none"><input type="text" class="validate[custom[number]]"
                                                                      name="weight_left" disabled="disabled"/></td>
            <td class="td_calculate_gap" style="display: none"><input type="text" class="validate[custom[number]]"
                                                                      name="weight_right" disabled="disabled"/></td>


            <!-- 是否勾选重量区间（是：显示2个td  否：不显示两个td）-->
            <td class="td_weight_gap" style="display: none"><input type="text" class="validate[custom[number]]"
                                                                   name="weight_min" disabled="disabled"/></td>
            <td class="td_weight_gap" style="display: none"><input type="text" class="validate[custom[number]]"
                                                                   name="weight_max" disabled="disabled"/></td>


            <!-- 收费-->
            <td class="td_charge"><input type="text" class="validate[custom[number]]" name="charge"/></td>


            <!--这2项--根据条件多选 -->
            <!--政府补贴 -->
            <td class="td_gover_aid" style="display: none;">
                <input type="text" name="gover" class="validate[custom[number]]" disabled="disabled"/>
            </td>
            <!--返还金额 -->
            <td class="td_pay_back" style="display: none">
                <input type="text" class="validate[custom[number]]" name="payback" disabled="disabled"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<div>
    <form id="table_areas" method="post" action="${basePath}/table/table">


        <div style="margin-top:40px;"></div>
        <input type="submit" value="提交"/>
    </form>

</div>


<script type="text/javascript">
    var dynamicMain = (function () {
        var Util = {
            tableCount: 0 //动态table index
            , tableTemplate: $("#tableTemplate")//表格模板
            , btn_submit: $("#condition_btn_submit")//数据提交按钮
        }

        function DynamicTable(tableTemplate) {
            this.tableTemplate = tableTemplate;
            this.tableId = "table" + Util.tableCount;
            this.trIndex = 0;
            this.init();

        }
        DynamicTable.prototype.init = function () {
            var table = this.tableTemplate.clone(true);
            table.attr("id", this.tableId);
            table.find("h6").text("表格id:" + Util.tableCount);

            this.obj = table;
            this.tableBody = this.obj.find("tbody");
            this.firstTr = this.tableBody.find("tr:first");
        }
        DynamicTable.prototype.showTable = function () {
            this.obj.show();
            Util.tableCount++;
        }
        DynamicTable.prototype.destory = function () {
            this.obj.remove();
            Util.tableCount--;
        }
        DynamicTable.prototype.addRow = function () {
            var html_tr = this.firstTr[0].outerHTML;
            html_tr = html_tr.replace("<tr>", '<tr id="' + this.tableId + '_tr_' + this.trIndex + '">');
            html_tr = html_tr.replace('class="thisRow"',"");
            this.trIndex++;
           //console.log(html_tr);
            $(this.tableBody).append(html_tr);
            console.groupEnd();
        }
        DynamicTable.prototype.delRow = function () {
            var checkedTrs = this.tableBody.find("tr[class=thisRow]");
            this.trIndex-=(checkedTrs.length);
            //勾选删除还是从最后一行开始删除
            $.each(checkedTrs, function (index, value){
               /* if (index == 0) {
                    return true;
                }*/
                value.remove();
            });

            //TODO tr的索引最后减为 -1 不能用length来减，他是下标
            console.log("剩余tr索引:" + this.trIndex);
            alert("剩余tr:" + this.trIndex);

            /*
             //删除最后一行
             console.log(this.trIndex);
             if(this.trIndex<1){return;}
             var lastTrObj =$("#"+this.tableId+"_tr_"+(this.trIndex - 1));
             lastTrObj.remove();
             this.trIndex--;
             console.groupEnd();
             */
        }

        function eventHandler() {
            $(Util.btn_submit).click(function () {
                var calculateWays = $("input[name=calculateWay]").fieldValue();//计量方式：1.计量     2.计量区间
                var dyncolumns = $("input[name='dyncolumn']").fieldValue();  //动态列类型: 1.重量区间 2.政府补贴 3.返还金额

                var dynamicTable = new DynamicTable(Util.tableTemplate);
                $.each(calculateWays, function (index, value) {
                    dynamicTable.obj.find("." + value).show().find("input").removeAttr("disabled");
                    ;
                });
                $.each(dyncolumns, function (index, value) {
                    dynamicTable.obj.find("." + value).show().find("input").removeAttr("disabled");
                });
                dynamicTable.obj.find(".btn_table_del").click(function () {
                    dynamicTable.destory();
                });
                dynamicTable.obj.find(".btn_row_del").click(function () {
                    dynamicTable.delRow();
                });
                dynamicTable.obj.find(".btn_row_add").click(function () {
                    dynamicTable.addRow();
                });
                dynamicTable.showTable();
                $("#table_areas").append(dynamicTable.obj);
            });
        }

        function init() {
            eventHandler();
        }

        return {
            init: init
        }
    })();
    dynamicMain.init();
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $.uniform.restore("input:checkbox");
        jQuery(document).ready(function () {
            $("#table_areas").validationEngine({
                validationEventTriggers: "blur",  //触发的事件  validationEventTriggers:"keyup blur",
                inlineValidation: true,//是否即时验证，false为提交表单时验证,默认true
                success: false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
                promptPosition: "bottomLeft",//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
            });
        });
    });

</script>

</body>
</html>