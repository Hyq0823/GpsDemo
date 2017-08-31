<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/header.jsp"></jsp:include>
<script language="javascript" type="text/javascript" src="${basePath}/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=XsPtkMm8pR9Tc5il0nR4sZvAq7h63rPZ"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script>

<script type="text/javascript" src="${basePath }/static/layer3.0.3/layer/layer.js"></script>
<script type="text/javascript">
    String.prototype.format=function(){
        if(arguments.length==0) return this;
        for(var s=this, i=0; i<arguments.length; i++)
            s=s.replace(new RegExp("\\{"+i+"\\}","g"), arguments[i]);
        return s;
    };
</script>
<html>
<head>
    <title>GPS轨迹Demo--lushu</title>
</head>
<body>

<div id="allmap" style="width: 800px;height:500px;"></div>


<div>
    ${historyTrailForm.result}
    <form id="searchForm" method="post" action="${basePath}/gps/trail/query">
        <input type="text" name="deviceNo" value="${historyTrailVo.deviceNo}" />
        <input id="startTime" value="${historyTrailVo.startTime}" name="startTime" value="${videoQuyery.startTime}" type="text"
               onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\') || \'%y-%M-%d %H:%m:%s\'}',minDate:'#F{$dp.$D(\'endTime\',{d:-7})}',skin:'twoer',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/>
        至
        <input id="endTime" name="endTime" value="${historyTrailVo.endTime}" type="text"
        onclick="WdatePicker({maxDate:'#F{$dp.$D(\'startTime\',{d:7}) || \'%y-%M-%d %H:%m:%s\'}',minDate:'#F{$dp.$D(\'startTime\')}',skin:'twoer',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/>
        <a href="#" id="searchBtn">查询</a>
    </form>
</div>
</body>

<script type="text/javascript">
    var params ={
        jesession: "${jsession}"
        ,trail_url : "${trail_history_url}"
    };
    var BaiduMap = {
        lushu:null
        ,map:null
        ,numberTo32Bit:function(num){//将整数转换为 32位2进制
            var value = num.toString(2);
            var getLen = value.length;
            for(var i=0;(value.length<32&&i<(32-getLen))>0;i++){
                value = "0" + value;
            }
            console.log(value);
            console.log(value.length);

        },init:function(){
            BaiduMap.map = new BMap.Map("allmap");// 创建Map实例
            BaiduMap.map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
            BaiduMap.map.addControl(new BMap.MapTypeControl());//添加地图类型控件
            BaiduMap.map.setCurrentCity("北京");// 设置地图显示的城市 此项是必须设置的
            BaiduMap.map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
        },buildLushu:function(arr){
            var pointArr = [];
            for(var i in arr){
                var p = arr[i];
                if(!p.mlng || !p.mlat){continue;}

                //遍历拿到的坐标后，做一些其他操作
                //filterParkWarning 过滤停车报警
                //filter   过滤开关车门
                pointArr.push(new BMap.Point(p.mlng,p.mlat))
            }
            //产生点坐标  pointArr数组
            BaiduMap.map.addOverlay(new BMap.Polyline(pointArr, {strokeColor: '#32CD32'}));
            BaiduMap.map.setViewport(pointArr);
            BaiduMap.lushu = new BMapLib.LuShu(BaiduMap.map,pointArr,{
                defaultContent:"",//"从天安门到百度大厦"
                autoView:true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                icon  : new BMap.Icon('http://lbsyun.baidu.com/jsdemo/img/car.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)}),
                speed: 1000,
                enableRotation:true,//是否设置marker随着道路的走向进行旋转
                landmarkPois: [
                    {lng:116.314782,lat:39.913508,html:'加油站',pauseTime:2},
                    {lng:116.315391,lat:39.964429,html:'高速公路收费<div><img src="http://map.baidu.com/img/logo-map.gif"/></div>',pauseTime:3},
                    {lng:116.381476,lat:39.974073,html:'肯德基早餐<div><img src="http://ishouji.baidu.com/resource/images/map/show_pic04.gif"/></div>',pauseTime:2}
                ]});
        },lushu_start:function(){
            BaiduMap.lushu.start();
        },lushu_stop:function(){
            BaiduMap.lushu.stop();
        },lush_pause:function(){
            BaiduMap.lushu.pause();
        }
    }

    var Util ={
        searchBtn:$("#searchBtn")
        ,loadTips:function (r){
            if(!r){return;}
            if(r.result!='0'){
                layer.msg('Gps轨迹信息拉取异常,错误码：'+r.result);
                return;
            }else{
                if(!r.tracks || r.tracks.length <1 ){
                    layer.msg('没有查询到Gps轨迹信息！');
                }
                layer.msg('拉取成功！');
            }
        },searchTrail:function(url) {//查询轨迹信息
            //var layerIndex = layer.msg('拉取Gps信息中.......', {icon: 16,shade: 0.01});
            var layerIndex = layer.load(2,{time:60000});
            $.ajax({
                url: url
                //,data:data
                , type: "get"
                , dataType: "jsonp"
                , async: false
                , jsonpCallback: "callback"
                , timeout: 40000
                , success: callback = function (result, textStatus, jqXHR) {
                    console.log(result);
                    Util.loadTips(result);//响应提示
                    badiMap.map.clearoverlays();//清除原有的覆盖物。
                    BaiduMap.buildLushu(result.tracks)//数据路书
                    BaiduMap.lushu_start();

                }, complete: function (XHR, TS) {
                    layer.close(layerIndex);
                }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.msg('查询错误！,状态：' + textStatus);
                }
            });
        }//searchFunction end
    }//Util end

    var TrailManager = (function(){
        var settings = {
            jesession:""
            ,trail_url:""
            ,mapDivId:"allmap"
            ,mapHeight:"500px"
            ,mapWidth:"800px"
        }
        function traileventHandler(){
            Util.searchBtn.click(function(){
                var deviceNo = $("input[name=deviceNo]").val();//设备号码 //非空验证
                var startTime = $("input[name=startTime]").val();
                var endTime = $("input[name=endTime]").val();
                console.log(deviceNo+"   "+startTime+"    "+endTime);
                console.log(settings.jesession);
                var url = settings.trail_url.format(settings.jesession,deviceNo,startTime,endTime);
                console.log(url);
                Util.searchTrail(url);
            });
        }
        function init(params){
            $.extend(settings,params);
            traileventHandler();
            BaiduMap.init();
            console.log(params);
        }


        return {
            init : init
        }
    })();

    TrailManager.init(params);
</script>
</html>
