var gpsMain = (function(){
    /**
     * 默认配置
     */
    var settings ={
        basePath : "",//项目根路径
        videoDivId:"cmsv6flash",//视频插件所在div的id
        swfPath:"player.swf",//视频插件路径
        cnPath:"cn.xml",//语言包路径
        videoWidth:400,//视频播放插件宽
        videoHeight:400,//视频播放插件高
        serverIp:"120.77.253.46",//默认服务器ip
        serverPort:"6605",//默认端口
        totalWindowNum:36,//视频窗口总数
        currentWindowNum:4,//当前视频窗口数量
        isInitFinished:false,//视频插件是否加载完成
        baiduMapDivId:"allmap",//百度地图的div id
        initFlashTryCount:30,//初始化falsh失败时重试的次数
        deviceWindowMap:{},//设备号:窗口下标 map
        windowDeviceMap:{},//窗口下标:设备号 map
        deviceMarkerMap:{},//设备号:百度marker map
        deviceCarNoMap:{},//设备号:车牌号 map
        ztree:null,//ztree的树对象
        gpsStatusPath:"car/monitor/gpsStatus",//gps接口请求路径
        onlineIconPath:"static/images/gps/caronline.png",//在线车辆图标
        offlineIconPath:"static/images/gps/caroffline.png",//离线车辆图标
        refreshGpsTime:60000//刷新gps信息的时间间隔（毫秒）
    }
    var Util ={
        video_count_4: $(".icon_num2"),
        video_count_6: $(".icon_num3"),
        video_count_8: $(".icon_num4"),
        video_count_16: $(".icon_num6"),
        ztree_hasDeviceNo:function(treeNode){//判断节点是否含有设备号
            if(!treeNode || !treeNode.devNo){
                return false;
            }
            return true;
        },
        windowIndex_devNo_Map_push:function(devNo,windowIndex){//设备号窗口下标存储
            if(!settings.deviceWindowMap.hasOwnProperty(devNo)){
                settings.deviceWindowMap[devNo] = windowIndex;
            }
            if(!settings.windowDeviceMap.hasOwnProperty(windowIndex)){
                settings.windowDeviceMap[windowIndex] = devNo;
            }
            Util.deviceWindowLog();
        },
        windowIndex_devNo_Map_remove:function(devNo,windowIndex){//设备号窗口下标移除
            if(settings.deviceWindowMap.hasOwnProperty(devNo)){
                delete settings.deviceWindowMap[devNo];
            }
            if(settings.windowDeviceMap.hasOwnProperty(windowIndex)){
                delete settings.windowDeviceMap[windowIndex];
            }
            Util.deviceWindowLog();
        },
        devNo_marker_map_push:function (devNo,marker){//存储车辆覆盖物
            if(!settings.deviceMarkerMap.hasOwnProperty(devNo)){
                settings.deviceMarkerMap[devNo] = marker;
            }
        },
        devNo_marker_map_remove:function(devNo){//移除车辆覆盖物
            if(settings.deviceMarkerMap.hasOwnProperty(devNo)){
                ObjCache.baiduMap.removeOverlay(settings.deviceMarkerMap[devNo]);
                delete settings.deviceMarkerMap[devNo];
            }
        },
        deviceWindowLog:function(){//日志
            // console.group("===========设备号:窗口===========");
            // console.info(settings.deviceWindowMap);
            // console.info(settings.windowDeviceMap);
            // console.groupEnd();
        },
        commonCheckedNodeFilter:function(node){//返回[设备号存在]且[当前被选中]的[普通子节点]
            return (!node.isParent&&node.devNo&&node.checked);
        }
    }

    var ObjCache={
        baiduMap:null,
        isSingleChoice:false//是否单选节点

    }

    /**
     * 事件绑定
     */
    function eventHandler(){
        Util.video_count_4.click(function(){
            updateCurrentWindowByNum(4);
        });
        Util.video_count_6.click(function(){
            updateCurrentWindowByNum(6);
        });

        Util.video_count_8.click(function(){
            updateCurrentWindowByNum(8);
        });
        Util.video_count_16.click(function(){
            updateCurrentWindowByNum(16);
        });
    }

    /**
     * 更新当前视频窗口数量
     * @param num 窗口数量
     */
    function updateCurrentWindowByNum(num){
        settings.currentWindowNum = num;
        initFlash();
    }

    /**
     * 全局初始化
     * @param options 配置
     */
    function init(options){
        $.extend(settings,options);
        eventHandler();
        initPlayerExample();
        initBaiduMap();
    }

    /**
     * 初始化Flash配置
     */
    function initPlayerExample() {
        var params = {allowFullscreen: "true", allowScriptAccess: "always", bgcolor: "#FFFFFF", wmode: "transparent"};
        swfobject.embedSWF(settings.swfPath, settings.videoDivId,settings.videoWidth,settings.videoHeight, "11.0.0", null, null, params, null);
        initFlash();
    }
    /**
     * 初始化Flash
     */
    function initFlash() {
        if (swfobject.getObjectById(settings.videoDivId) == null ||
            typeof swfobject.getObjectById(settings.videoDivId).setWindowNum == "undefined" ) {
            if(settings.initFlashTryCount<0 && !settings.isInitFinished){
                layer.msg('初始化Flash插件不成功,请检查您的Flash插件是否安装和启用！', {icon: 5});
                return;
            }
            settings.initFlashTryCount--;
            setTimeout(initFlash, 50);
        } else {
            swfobject.getObjectById(settings.videoDivId).setLanguage(settings.cnPath);//设置视频插件的语言
            swfobject.getObjectById(settings.videoDivId).setWindowNum(settings.totalWindowNum);//先将全部窗口创建好
            swfobject.getObjectById(settings.videoDivId).setWindowNum(settings.currentWindowNum);//再配置当前的窗口数目
            swfobject.getObjectById(settings.videoDivId).setServerInfo(settings.serverIp,settings.serverPort);//设置视频插件的服务器
            settings.isInitFinished = true;
        }
    }

    /**
     * 播放单个节点视频
     * @param windowIndex 视频窗口下标
     * @param title  视频播放的标题
     * @param jsession 会话号
     * @param devNo 设备号
     * @param channel 设备通道
     */
    function playSingleNodeVideo(windowIndex,title,jsession,devNo,channel){
        stopPlayVideo(windowIndex);
        setVideoTitle(windowIndex,title);
        swfobject.getObjectById(settings.videoDivId).startVideo(windowIndex,jsession,devNo, channel, 1, true);
    }

    /**
     * 停止播放视频
     * @param windowIndex 窗口下标
     */
    function stopPlayVideo(windowIndex) {
        swfobject.getObjectById(settings.videoDivId).stopVideo(windowIndex);
    }

    /**
     * 重置视频窗口
     * @param windowIndex 窗口下标
     */
    function reSetVideo(windowIndex) {
        swfobject.getObjectById(settings.videoDivId).reSetVideo(windowIndex);
    }

    /**
     * 设置播放视频窗口标题
     * @param windowIndex 视频窗口下标
     * @param title 标题
     */
    function setVideoTitle(windowIndex,title){
        swfobject.getObjectById(settings.videoDivId).setVideoInfo(windowIndex,title);
    }

    /**
     * 获取当前可用的窗口下标集合
     */
    function getAvailableWindowIndexArray(){
        var array = [];
        var index_count = settings.currentWindowNum;
        for(var i = 0; i<index_count; i++){
            if (i%2 ==0 && !settings.windowDeviceMap.hasOwnProperty(i)){
                array.push(i);
            }
        }
        return array;
    }

    /**
     * 获取一个当前可用的窗口下标
     */
    function getCurrentAvailableIndex(){
        var array = getAvailableWindowIndexArray();
        if(array && array.length == 0){
            //throw  new Error("当前没有更多的视频窗口!")
            return -9999;
        }
        return array[0];
    }

    /**
     * @param tree 树对象
     * @param event
     * @param treeId
     * @param treeNode
     */
    function treeClickHandler(tree,event,treeId,treeNode){
        if(!settings.ztree){
            settings.ztree = tree;
        }
        //操作单个节点
        if(!treeNode.isParent){
            ObjCache.isSingleChoice = true;
            if(treeNode.checked){
                singleNodeCheckedHandler(treeNode);
            }else{
                singleNodeUnCheckedHandler(treeNode);
            }
        }

        //操作父节点
        if(treeNode.isParent){
            ObjCache.isSingleChoice = false;
            if(treeNode.checked){
                parentNodeCheckedHandler(treeNode);
            }else{
                parentNodeUnCheckedHandler(treeNode);
            }
        }
    }

    /**
     * 父节点取消选中
     * @param treeNode
     */
    function parentNodeUnCheckedHandler(treeNode){
        var nodes = settings.ztree.getCheckedNodes(false);//当前未被勾选的node
        for(var i in nodes){
            var e = nodes[i];
            if(!e.isParent && !e.checked){
                singleNodeUnCheckedHandler(e);
            }
        }
    }

    /**
     * 父节点选中
     * @param treeNode 父节点
     */
    function parentNodeCheckedHandler(treeNode){
        var nodes = settings.ztree.getNodesByFilter(Util.commonCheckedNodeFilter);
        for(var i in nodes){
            singleNodeCheckedHandler(nodes[i]);
        }
    }

    /**
     * 单节点取消选中响应
     * @param treeNode
     */
    function singleNodeUnCheckedHandler(treeNode){
        if(Util.ztree_hasDeviceNo(treeNode)){
            var deviceNo = treeNode.devNo;
            if(settings.deviceWindowMap.hasOwnProperty(deviceNo)){
                var windowIndex = settings.deviceWindowMap[deviceNo];
                reSetVideo(windowIndex);
                reSetVideo(windowIndex +　1);
                Util.windowIndex_devNo_Map_remove(deviceNo,windowIndex);
            }
            //移除地图覆盖物
            Util.devNo_marker_map_remove(deviceNo);
        }
    }

    /**
     * 单节点选中响应
     * @param treeNode
     */
    function singleNodeCheckedHandler(treeNode){
        if(!Util.ztree_hasDeviceNo(treeNode)){
            layer.msg('该车辆尚未配置设备号！',{offset: ['300px', '2px'],icon: 5});
            settings.ztree.checkNode(treeNode,false,true);
            return;
        }
        singleCheckedTips(treeNode);

        if('1' == treeNode.online){
            configSingleNodeVideo(treeNode);
        }
        configbaiduMap();
    }

    /**
     * @param treeNode
     */
    function singleCheckedTips(treeNode){
        if(!ObjCache.isSingleChoice){
            return;
        }
        if(!treeNode.online || '0' == treeNode.online) {
            layer.tips('车辆不在线,将显示最后位置', '#' + treeNode.tId + "_a");
        }
    }

    /**
     * 配置地图gps数据
     */
    function configbaiduMap(){
        var checkedNodes = settings.ztree.getNodesByFilter(Util.commonCheckedNodeFilter);
        var str_devNos = ""; //拼接逗号分隔的设备号
        for(var i in checkedNodes){
            str_devNos+=checkedNodes[i].devNo+",";
        }
        if(str_devNos!=""){//ajax 批量拉取车辆的gps信息
            var loading_index = layer.msg('正在加载轨迹数据', {offset: ['300px', '74%'],icon: 16  ,shade: 0.01});
            /*
            加载数据:
            $.post(settings.gpsStatusPath,{"devNos":str_devNos},function(data){
                if("0"==data.result){
                    configByGpsArray(data.status);//gps设备轨迹状态配置
                }else{
                    layer.msg('轨迹数据拉取异常..状态:'+data.result);
                }
                layer.close(loading_index);
            },"json");
            */
            var array = "[{\"id\":\"30128\",\"dt\":2,\"lc\":2622553,\"vid\":null,\"pt\":1,\"sp\":450,\"net\":3,\"gw\":\"G1\",\"ol\":1,\"s1\":4483,\"s2\":0,\"s3\":780,\"s4\":0,\"t1\":0,\"t2\":0,\"t3\":0,\"t4\":0,\"hx\":237,\"lng\":103408208,\"lat\":30409866,\"mlng\":\"103.417041\",\"mlat\":\"30.413391\",\"pk\":0,\"gt\":\"2017-08-01 15:29:10.0\",\"ac\":1,\"ft\":2,\"fdt\":1,\"yl\":0,\"imei\":null,\"imsi\":null,\"hv\":null,\"sv\":null,\"po\":null,\"lid\":0,\"drid\":0,\"dct\":0,\"sfg\":0,\"snm\":0,\"sst\":0,\"or\":0,\"os\":0,\"ov\":0,\"ojt\":0,\"ost\":0,\"ojm\":0}]";
            array =  JSON.parse(array);
            configByGpsArray(array); //gps设备轨迹状态配置
            layer.close(loading_index);
        }
    }

    /**
     *通过返回的gps数据（ 经纬度 ）配置地图
     * @param gpsArray
     */
    function configByGpsArray(gpsArray){
        if(!gpsArray || gpsArray.length<1){
            console.info("gps返回数据为空....");
            return;
        }
        for(var i in gpsArray){
            var gpsNode = gpsArray[i];
            if(settings.deviceMarkerMap.hasOwnProperty(gpsNode.id)){
                var marker = settings.deviceMarkerMap[gpsNode.id];//更新覆盖物
                updateMarker(marker,gpsNode);
            }else{
                addMarker(gpsNode);//添加覆盖物
            }
            //addWarningMsg(gpsNode);//添加报警信息
        }
    }
    /**
     * 添加覆盖物
     * @param gpsNode 北斗返回的gps node
     */
    function addMarker(gpsNode){
        var carIcon = new BMap.Icon(getMapIconByGpsState(gpsNode.ol), new BMap.Size(32, 32), {anchor: new BMap.Size(32, 0)});
        var carMaker = new BMap.Marker(new BMap.Point(gpsNode.mlng , gpsNode.mlat ), {icon: carIcon});  // 创建标注
        var getCarNo = settings.deviceCarNoMap[gpsNode.id];
        var labelTxt = "未获取到设备号"+gpsNode.id+"的车牌";
        if(getCarNo){labelTxt = getCarNo;}
        carMaker.setLabel(new BMap.Label(labelTxt,{offset: new BMap.Size(32, 6)}));//标签显示车牌
        carMaker.setTitle(gpsNode.id);//设备号码
        ObjCache.baiduMap.addOverlay(carMaker);
        settings.deviceMarkerMap[gpsNode.id] = carMaker;
        Util.devNo_marker_map_push(gpsNode.id,carMaker);
        carMaker.addEventListener("click", function(event){// 添加点击事件 打开信息窗口
            layer.open({//查询参数为设备号（没有设备号的车辆不会有轨迹）
                type: 2,
                shadeClose: true,
                title: "车辆订单详情",
                area: ['400px', '400px'],
                offset: '100px',//距离top,横向默认居中
                content: ['http://www.google.com', 'no']
            });
        });
    }
    /**
     * 更新覆盖物
     * @param gpsNode 北斗返回的gps node
     * @param marker 百度地图的覆盖物
     */
    function updateMarker(marker,gpsNode){
        if(!marker || !gpsNode){return;}
        marker.setPosition(new BMap.Point(gpsNode.mlng , gpsNode.mlat ));
        var carIcon = new BMap.Icon(getMapIconByGpsState(gpsNode.ol), new BMap.Size(32, 32), {anchor: new BMap.Size(32, 0)});
        marker.setIcon(carIcon);
        marker.setTitle(gpsNode.id);
        var labelTxt = "暂未获取到车牌"
        var getCarNo = settings.deviceCarNoMap[gpsNode.id];
        if(getCarNo){
            labelTxt = getCarNo;
        }
        marker.setLabel(labelTxt);

    }
    /**
     * 根据状态返回在线/离线的车辆图标
     * @param onlineState（1在线 0离线）
     */
    function getMapIconByGpsState(onlineState){
        if(onlineState && "1" == onlineState){
            return settings.onlineIconPath;
        }
        return settings.offlineIconPath;
    }

    /**
     * 配置单节点的视频播放
     */
    function configSingleNodeVideo(treeNode){
        try{
            var winIndex = getCurrentAvailableIndex();
            if(winIndex != -9999){
                playSingleNodeVideo(winIndex,treeNode.name,treeNode.jsession,treeNode.devNo,0);
                Util.windowIndex_devNo_Map_push(treeNode.devNo,winIndex);
                playSingleNodeVideo(winIndex + 1,treeNode.name,treeNode.jsession,treeNode.devNo,1);
            }
        }catch(e){
            console.error("配置视频错误："+e);
          //settings.ztree.checkNode(treeNode,false, true);
            layer.msg(e.message,{offset: ['50px', '400px'],icon: 5});
        }
    }

    /**
     * 初始化百度地图
     */
    function initBaiduMap(){
        ObjCache.baiduMap = new BMap.Map(settings.baiduMapDivId);
        ObjCache.baiduMap.enableScrollWheelZoom();//启用滚轮放大缩小，默认禁用。
        ObjCache.baiduMap.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT})); //左上角，添加默认缩放平移控件
        ObjCache.baiduMap.addControl(new BMap.NavigationControl());// 左上角，添加比例尺
        ObjCache.baiduMap.centerAndZoom(new BMap.Point(103.47819,30.411099), 11);//设初始化地图
        //var geolocation = new BMap.Geolocation();
    }

    /**
     * 注入设备号：车牌号集合
     * @param map
     * @example {"30331":"川A12345"}
     */
    function injectDeviceCarNoMap(map){
        settings.deviceCarNoMap = map;
    }
    return {
        init:init,
        treeClickHandler:treeClickHandler,//外部接口--点击车辆树时调用
        injectDeviceCarNoMap:injectDeviceCarNoMap//外部接口==注入 设备号：车牌号集合
    }
})(window);
window.gpsMain = gpsMain;
