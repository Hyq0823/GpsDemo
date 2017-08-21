<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp"></jsp:include>
<script type="text/javascript" src="${basePath }/static/layer3.0.3/layer/layer.js"></script>

<style type="text/css">
    .operate{
        margin-left:40px;
    }
    label{
        float: left;
    }

    td input{
        width:100%;
        height: 100%;
    }
    .dynDiv input{
        float: left;
    }
    .diplayNone{display: none}
</style>



<!-- Table with checkboxes -->
<div class="widget">
    <div class="whead"><span class="titleIcon check"><input type="checkbox" id="titleCheck" name="titleCheck" /></span><h6>Static table with checkboxes</h6><div class="clear"></div></div>
    <table cellpadding="0" cellspacing="0" width="100%" class="tDefault checkAll check" id="checkAll">
        <thead>
        <tr>
            <td><img src="images/elements/other/tableArrows.png" alt="" /></td>
            <td>Column name</td>
            <td>Column name</td>
            <td>Column name</td>
            <td>Column name</td>
            <td>Column name</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="checkbox" id="titleCheck2" name="checkRow" /></td>
            <td>Row 1</td>
            <td>Row 2</td>
            <td>Row 3</td>
            <td>Row 4</td>
            <td>Row 5</td>
        </tr>
        <tr>
            <td><input type="checkbox" id="titleCheck3" name="checkRow" /></td>
            <td>Row 1</td>
            <td>Row 2</td>
            <td>Row 3</td>
            <td>Row 4</td>
            <td>Row 5</td>
        </tr>
        <tr>
            <td><input type="checkbox" id="titleCheck4" name="checkRow" /></td>
            <td>Row 1</td>
            <td>Row 2</td>
            <td>Row 3</td>
            <td>Row 4</td>
            <td>Row 5</td>
        </tr>
        <tr>
            <td><input type="checkbox" id="titleCheck5" name="checkRow" /></td>
            <td>Row 1</td>
            <td>Row 2</td>
            <td>Row 3</td>
            <td>Row 4</td>
            <td>Row 5</td>
        </tr>
        <tr>
            <td><input type="checkbox" id="titleCheck6" name="checkRow" /></td>
            <td>Row 1</td>
            <td>Row 2</td>
            <td>Row 3</td>
            <td>Row 4</td>
            <td>Row 5</td>
        </tr>
        </tbody>
    </table>
</div>







<div  class="condition" style="width: 700px;height: 50px;">
    <div>
        <label>规则条件：</label>
        <input id="r1" type="radio" name="calculateWay" value="td_calculate" checked="checked"/><label for="r1">计量</label>
        <input id="r2" type="radio" name="calculateWay" value="td_calculate_gap" /><label for="r2">计量区间</label>
    </div>

    <div style="margin-top:10px;"></div>
    <br />
    <div class="dynDiv">
        <label>动态列：</label>
        <input id="c1" type="checkbox" name="dyncolumn" value="td_weight_gap" /><label for="c1">重量区间</label>
        <input id="c2" type="checkbox" name="dyncolumn" value="td_gover_aid" /><label for="c2">政府补贴</label>
        <input id="c3" type="checkbox" name="dyncolumn" value="td_pay_back" /><label for="c3">返还金额</label>
    </div>
    <br />
    <input id="condition_btn_submit"  type="button"  value="添加" />
</div>

<!-- Table with checkboxes -->
<div class="widget" id="tableTemplate" style="display:none;">
    <div class="whead">
        <span class="titleIcon check">
            <input type="checkbox"  name="titleCheck" />
        </span>
        <h6>表格模板</h6>
        <div class="operate">
            <a class="btn_row_add" href="#" style="margin-left:40px;">增加行</a>
            <a class="btn_row_del" href="#" style="margin-left: 20px;">删除行</a>
            <a class="btn_table_del" href="#" style="margin-left: 20px;">删除表格</a>
        </div>

        <div class="clear"></div>
    </div>
    <table cellpadding="0" cellspacing="0" width="100%" class="tDefault checkAll check" >
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
            <td><input type="checkbox"  name="checkRow" /></td>
            <td>
                <input type="text" name="collect" />
            </td>
            <td>
                <input type="text" name="type" />
            </td>
            <td>
                <input type="text" name="unit" />
            </td>

            <!--根据规则显示：1.计量  2.计量区间 -->
            <td class="td_calculate" style="display: none"><input type="text" name="weight_only"  disabled="disabled"/></td>

            <td class="td_calculate_gap" style="display: none"><input type="text" name="weight_left" disabled="disabled" /></td>
            <td class="td_calculate_gap" style="display: none"><input type="text" name="weight_right" disabled="disabled" /></td>


            <!-- 是否勾选重量区间（是：显示2个td  否：不显示两个td）-->
            <td class="td_weight_gap" style="display: none"><input type="text" name="weight_min" disabled="disabled"  /></td>
            <td class="td_weight_gap" style="display: none"><input type="text" name="weight_max" disabled="disabled" /></td>


            <!-- 收费-->
            <td class="td_charge"><input type="text" name="charge" /></td>


            <!--这2项--根据条件多选 -->
            <!--政府补贴 -->
            <td class="td_gover_aid" style="display: none;">
                <input type="text" name="gover"  disabled="disabled"/>
            </td>
            <!--返还金额 -->
            <td class="td_pay_back" style="display: none">
                <input type="text" name="payback"  disabled="disabled"/>
            </td>




        </tr>
        </tbody>
    </table>
</div>

<div>
<form id="table_areas" method="post" action="${basePath}/table">


    <div style="margin-top:40px;"></div>
  <input type="submit" value="提交" />
</form>

</div>


<script type="text/javascript">
var dynamicMain =(function(){
    var Util={
        tableCount:0 //动态table index
        ,tableTemplate:$("#tableTemplate")//表格模板
        ,btn_submit:$("#condition_btn_submit")//数据提交按钮

        //其他
        //,calculateWay:$("input[name='calculateWay']").parent()//计量方式选中
    }
    function DynamicTable(tableTemplate){
        this.tableTemplate = tableTemplate;
        this.tableId ="table"+Util.tableCount;
        this.trIndex = 0;
        this.init();
    }
    DynamicTable.prototype.init=function(){
        var table = this.tableTemplate.clone(true);
        table.attr("id",this.tableId);
        table.find("h6").text("表格id:"+Util.tableCount);

        this.obj = table;
        this.tableBody = this.obj.find("tbody");
        this.firstTr = this.tableBody.find("tr:first");
    }
    DynamicTable.prototype.showTable=function(){
        this.obj.show();
        Util.tableCount++;
    }
    DynamicTable.prototype.destory=function(){
        this.obj.remove();
        Util.tableCount--;
    }
    DynamicTable.prototype.addRow=function(){
        var html_tr = this.firstTr[0].outerHTML;
        html_tr = html_tr.replace("<tr>",'<tr id="' + this.tableId+'_tr_' + this.trIndex + '">');
        this.trIndex++;
        console.log(html_tr);
        $(this.tableBody).append(html_tr);
        console.groupEnd();
        $.uniform.update();
    }
    DynamicTable.prototype.delRow=function(){
        console.log(this.trIndex);
        if(this.trIndex<1){return;}
        var lastTrObj =$("#"+this.tableId+"_tr_"+(this.trIndex - 1));
        lastTrObj.remove();
        this.trIndex--;
        console.groupEnd();
        $.uniform.update();
    }

    function eventHandler(){
        $(Util.btn_submit).click(function(){
            var calculateWays = $("input[name=calculateWay]").fieldValue();//计量方式：1.计量     2.计量区间
            var dyncolumns = $("input[name='dyncolumn']").fieldValue();  //动态列类型: 1.重量区间 2.政府补贴 3.返还金额

            var dynamicTable = new DynamicTable(Util.tableTemplate);
            $.each(calculateWays,function(index,value) {
                dynamicTable.obj.find("." + value).show().find("input").removeAttr("disabled");;
            });
            $.each(dyncolumns,function(index,value){
                dynamicTable.obj.find("." + value).show().find("input").removeAttr("disabled");;
            });
            dynamicTable.obj.find(".btn_table_del").click(function(){
                dynamicTable.destory();
            });
            dynamicTable.obj.find(".btn_row_del").click(function(){
                dynamicTable.delRow();
            });
            dynamicTable.obj.find(".btn_row_add").click(function(){
                dynamicTable.addRow();
            });
            dynamicTable.showTable();
            $("#table_areas").append(dynamicTable.obj);
            $.uniform.update();
        });


        //表单提交
        /*
        $("#table_areas").submit(function(){
            var data = $("#table_areas").serialize();
            console.log(data);
        })*/

    }
        function init(){
            eventHandler();
        }
        return {
            init:init
        }
    })();

dynamicMain.init();
</script>

</body>
</html>