<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/header.jsp"></jsp:include>
<script language="javascript" type="text/javascript" src="${basePath}/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=XsPtkMm8pR9Tc5il0nR4sZvAq7h63rPZ"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script>

<!-- 检索信息框-->
<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css"/>

<script type="text/javascript" src="${basePath }/static/layer3.0.3/layer/layer.js"></script>

<style type="text/css">
    .b {
        font-weight: bold
    }
</style>
<script type="text/javascript">
    var basePath = "${basePath}";
    String.prototype.format = function () {//扩展string能力,使之具有字符串替换的功能。
        if (arguments.length == 0) return this;
        for (var s = this, i = 0; i < arguments.length; i++)
            s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
        return s;
    };
</script>
<html>
<head>
    <title>GPS轨迹Demo--lushu</title>
</head>
<body>

<div id="allmap" style="width: 100%;height:620px;"></div>
<div>
    ${historyTrailForm.result}
    <form id="searchForm" method="post" action="${basePath}/gps/trail/query">
        <input type="text" name="deviceNo" value="30386"/>
        <input id="startTime" value="${historyTrailVo.startTime}" name="startTime" value="${videoQuyery.startTime}"
               type="text"
               onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\') || \'%y-%M-%d %H:%m:%s\'}',minDate:'#F{$dp.$D(\'endTime\',{d:-7})}',skin:'twoer',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
               class="Wdate"/>
        至
        <input id="endTime" name="endTime" value="${historyTrailVo.endTime}" type="text"
               onclick="WdatePicker({maxDate:'#F{$dp.$D(\'startTime\',{d:7}) || \'%y-%M-%d %H:%m:%s\'}',minDate:'#F{$dp.$D(\'startTime\')}',skin:'twoer',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
               class="Wdate"/>
        <a href="#" id="searchBtn">查询</a>

        <a href="#" id="btn_start">开始</a>
        <a href="#" id="btn_pause">暂停</a>
        <div style="height: 40px;width:100px">
            <div id="speed-slider"></div>
            <label>速度：<span id="speedCount"></span></label>
        </div>
    </form>

</div>
</body>

<script type="text/javascript">
    //var BMapLib = window.BMapLib = BMapLib || {};
    //采用这种赋值方式可以保证变量是一个对象.


    var params = {
        jesession: "${jsession}"
        , trail_url: "${trail_history_url}"
    };
    var BaiduMap = {
        carParkIcon: basePath + '/static/gps/images/parking.gif'
        , startEndSrc: basePath + '/static/gps/images/baidu.png'
        , lushu: null
        , map: null
        , calculateGsp: function (start, end) {//计算gps两点间的距离
            if (!start || !end) {
                return 0
            }
            ;
            return (end.lc - start.lc) / 1000;
        }, isLushuInit: function () {
            if (!BaiduMap.lushu) {
                console.log("lushu is not  init");
                return false;
            }
            return true;
        }, setLushuOpts: function (jsonObj) {//设定路书参数
            if (BaiduMap.isLushuInit()) {
                BaiduMap.lushu._setOptions(jsonObj);
            }
        }, clearAll: function () {
            BaiduMap.map.clearOverlays();
        }, init: function () {
            BaiduMap.map = new BMap.Map("allmap");// 创建Map实例
            BaiduMap.map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
            BaiduMap.map.addControl(new BMap.MapTypeControl());//添加地图类型控件
            BaiduMap.map.setCurrentCity("北京");// 设置地图显示的城市 此项是必须设置的
            BaiduMap.map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
        }, buildStartAndEndMarker: function (arr) {
            var start = arr[0];
            var end = arr[arr.length - 1];
            var startIcon = new BMap.Icon(BaiduMap.startEndSrc, new BMap.Size(25, 32), {
                imageOffset: new BMap.Size(-200, -140),
                anchor: new BMap.Size(16, 32)
            });
            var endIcon = new BMap.Icon(BaiduMap.startEndSrc, new BMap.Size(25, 32), {
                imageOffset: new BMap.Size(-225, -140),
                anchor: new BMap.Size(16, 32)
            });

            //计算起点和终点的距离
            var pointLen = new BMap.Point(start.mlng, start.mlat);
            var markerLen = new BMap.Marker(pointLen);  //创建标注
            var labelLen = new BMap.Label("路线长度：" + BaiduMap.calculateGsp(start, end) + "公里", {offset: new BMap.Size(32, 6)});
            markerLen.setLabel(labelLen);
            BaiduMap.map.addOverlay(markerLen);

            BaiduMap.addMapMark(start, startIcon);
            BaiduMap.addMapMark(end, endIcon);
        }, addMapMark: function (p, icon) {//添加覆盖物（p:百度地图坐标,icon:覆盖物icon）
            var point = new BMap.Point(p.mlng, p.mlat);
            var mark = new BMap.Marker(point, {icon: icon});  // 创建标注
            var status = (p.ol == '1') ? '在线' : '离线';
            var content = "<span class='b'>状态:</span>" + status + "<br />"
                    + "<span class='b'>速度：</span> " + (p.sp) / 10 + "km/h<br />"
                    + "<span class='b'>gps时间:</span> " + p.gt + "<br />"
                    + "<span class='b'>里程:</span> " + (p.lc) / 1000 + " 公里<br />"
                    + "<span class='b'>停车时长：</span> " + (p.pk) + " 秒<br />"
                    + "<a class='b' href=" + basePath + "/gps/search?time=" + encodeURIComponent(p.gt) + ">查询时间点附近录像</a><br />"
            var infoWindow = new BMap.InfoWindow(content);// 创建信息窗口对象
            mark.addEventListener("click", function () {
                this.openInfoWindow(infoWindow);
            });
            /* //百度路书搜索信息窗体
             var searchInfoWindow = new BMapLib.SearchInfoWindow(BaiduMap.map,content,{
             title: "轨迹点信息", //标题
             width: 100, //宽度
             height: 100, //高度
             panel : "panel", //检索结果面板
             enableAutoPan : true, //自动平移
             searchTypes :[
             ]
             });
             mark.addEventListener("click", function(e){
             searchInfoWindow.open(mark);
             })
             */
            BaiduMap.map.addOverlay(mark);

        }, buildLushu: function (arr) {
            //创建起点、重点marker
            BaiduMap.buildStartAndEndMarker(arr);
            var parkIconSrc = BaiduMap.carParkIcon;
            var pointArr = [];//轨迹集合
            var warningArr = [];//报警点集合
            for (var i in arr) {
                var p = arr[i];
                //test
                if (i % 151 == 0) {
                    p.s4 = 8192;
                }
                if (!p.mlng || !p.mlat) {
                    continue;
                }
                var point = new BMap.Point(p.mlng, p.mlat);
                if (Util.filterParkWarning(p)) {//过滤停车报警
                    var obj_park = {
                        lng: p.mlng,
                        lat: p.mlat,
                        html: '<p><img src=' + parkIconSrc + '/>停车报警</p>',
                        pauseTime: 1
                    };
                    // var icon_park = new BMap.Icon(basePath+'/static/gps/images/parking.gif', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)});
                    warningArr.push(obj_park);
                    BaiduMap.addMapMark(p, new BMap.Icon(parkIconSrc, new BMap.Size(32, 32)));//在地图上添加报警图片Marker
                }
                pointArr.push(point);
            }
            //产生点坐标  pointArr数组
            BaiduMap.map.addOverlay(new BMap.Polyline(pointArr, {strokeColor: '#32CD32'}));
            BaiduMap.map.setViewport(pointArr);
            BaiduMap.lushu = new BMapLib.LuShu(BaiduMap.map, pointArr, {
                defaultContent: "",//"从天安门到百度大厦"
                autoView: true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                icon: new BMap.Icon('http://lbsyun.baidu.com/jsdemo/img/car.png', new BMap.Size(52, 26), {anchor: new BMap.Size(27, 13)}),
                speed: 1000,
                enableRotation: true,//是否设置marker随着道路的走向进行旋转
                landmarkPois: warningArr
                /*[
                 {lng:116.314782,lat:39.913508,html:'加油站',pauseTime:2},
                 {lng:116.315391,lat:39.964429,html:'高速公路收费<div><img src="http://map.baidu.com/img/logo-map.gif"/></div>',pauseTime:3},
                 {lng:116.381476,lat:39.974073,html:'肯德基早餐<div><img src="http://ishouji.baidu.com/resource/images/map/show_pic04.gif"/></div>',pauseTime:2}
                 ]*/
            });


        }, lushu_start: function () {
            if (BaiduMap.isLushuInit()) {
                BaiduMap.lushu.start();
            }
        }, lushu_stop: function () {
            if (BaiduMap.isLushuInit()) {
                BaiduMap.lushu.stop();
            }
        }, lush_pause: function () {
            if (BaiduMap.isLushuInit()) {
                BaiduMap.lushu.pause();
            }
        }
    }

    var Util = {
        searchBtn: $("#searchBtn")
        , speedUp: $("#speedUp")
        , speedDown: $("#speedDown")
        , speedSlider: $("#speed-slider")
        , speedCount: $("#speedCount") //滑块速度显示器
        , btn_start: $("#btn_start") //路书开始
        , btn_pause: $("#btn_pause") //路书暂停
        , searchParkVideo: $(".searchParkVideo") //停车时间点
        , isEquireStr1: function (obj) {
            if (obj && obj == '1') {
                return true
            }
            ;
            return false;
        }, numberTo32Bit: function (num) {//将整数转换为32位2进制
            var num = +num;
            var value = (num).toString(2);
            var getLen = value.length;
            for (var i = 0; (value.length < 32 && i < (32 - getLen)) > 0; i++) {
                value = "0" + value;
            }
            return value;
        }
        , filterParkWarning: function (trail) {//过滤停车报警
            if (trail.s4 == '0') {
                return
            }
            ;
            var park = Util.numberTo32Bit(trail.s4);
            var c7 = park.charAt(7); //s4:25位	超时停车(平台产生)
            var c18 = park.charAt(18) //s4:14位	超时停车
            return (Util.isEquireStr1(c7) || Util.isEquireStr1(c18)) ? true : false;
        }, loadTips: function (r) {
            if (!r) {
                return false;
            }
            if (r.result != '0') {
                layer.msg('Gps轨迹信息拉取异常,错误码：' + r.result);
                return false;
            } else {
                if (!r.tracks || r.tracks.length < 1) {
                    layer.msg('没有查询到Gps轨迹信息！');
                    return false;
                }
                layer.msg('拉取成功！');
                return true;
            }
        }, searchTrail: function (url) {//查询轨迹信息
            //var layerIndex = layer.msg('拉取Gps信息中.......', {icon: 16,shade: 0.01});
            var layerIndex = layer.load(2, {time: 50000});
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
                    if (Util.loadTips(result)) {//响应提示
                        BaiduMap.clearAll();//清除原有的覆盖物。
                        BaiduMap.buildLushu(result.tracks)//数据路书
                        BaiduMap.lushu_start();
                    }
                }, complete: function (XHR, TS) {
                    layer.close(layerIndex);
                }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.msg('查询错误！,状态：' + textStatus);
                }
            });
        }//searchFunction end
    }//Util end

    var TrailManager = (function () {
        var settings = {
            jesession: ""
            , trail_url: ""
            , mapDivId: "allmap"
            , mapHeight: "500px"
            , mapWidth: "800px"
        }

        function traileventHandler() {
            Util.btn_start.click(function () {//路书开始按钮
                BaiduMap.lushu_start();
            });

            Util.btn_pause.click(function () {//路书暂停按钮
                BaiduMap.lush_pause();
            });
            Util.speedSlider.slider({//车速滑块
                orientation: "vertical",
                range: "min",
                min: 1,
                max: 20,
                value: 1,
                slide: function (event, ui) {
                    Util.speedCount.text(" * " + ui.value);
                    BaiduMap.setLushuOpts({"speed": ui.value * 1000});
                }
            });
            Util.searchBtn.click(function () {//轨迹查询
                var deviceNo = $("input[name=deviceNo]").val();//设备号码 //非空验证
                var startTime = $("input[name=startTime]").val();
                var endTime = $("input[name=endTime]").val();
                console.log(deviceNo + "   " + startTime + "    " + endTime);
                console.log(settings.jesession);
                var url = settings.trail_url.format(settings.jesession, deviceNo, startTime, endTime);
                console.log(url);
                Util.searchTrail(url);
            });
        }

        function init(params) {//全局初始化
            $.extend(settings, params);
            traileventHandler();
            BaiduMap.init();
            console.log(params);
        }

        return {
            init: init
        }
    })();

    TrailManager.init(params);
</script>
</html>
