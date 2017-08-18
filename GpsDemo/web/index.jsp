<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="${basePath }/static/css/tab_change.css">
<%
  String path = request.getContextPath();
  request.setAttribute("path", path);
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path;
  request.setAttribute("basePath", basePath);
%>
<html>
  <head>
      <script language="javascript" type="text/javascript" src="${basePath}/static/jquery.min.js"></script>
      <script type="text/javascript" src="${basePath }/static/layer3.0.3/layer/layer.js"></script>
      <script language="javascript" type="text/javascript" src="${basePath}/static/gps/swfobject.js"></script>
      <script language="javascript" type="text/javascript" src="${basePath}/static/jquery-ui.min.js"></script>
      <script language="javascript" type="text/javascript" src="${basePath}/static/My97DatePicker/WdatePicker.js"></script>
      <style type="text/css">
          div{
              height:100%;
          }
          #left{
              width:24%;
              border:1px solid red;
              float: left;
          }
          #right{
              width:70%;
              border:1px solid green;
              float: left;
          }

          .tv_box span {height: 22px;line-height:18px;display:inline-block;padding: 0 5px;position:relative;margin-top: 9px;}
          .tv_box span i {  background: url('${basePath}/static/gps/icon_s.png') 0 0 no-repeat;width:14px;height:14px;cursor: pointer;}
          i{display: inline-block;  font-style: normal;  vertical-align: middle;}
          .tv_box span i.icon_num2 {background-position: -120px -326px;}
          .tv_box span i.icon_num3 {background-position: -140px -326px;}
          .tv_box span i.icon_num4 {background-position: -160px -326px;}
          .tv_box span i.icon_num6 {background-position: -200px -326px;}
      </style>

    <title>GPSDemo</title>
  </head>
  <body>

  <div id="segDownload_pop" style="display: none">
      <p id="seg_tips" style="color: red"></p>
      <div>
      时间段:<input id="segStartTime" name="segStartTime" value="" type="text"   onclick="WdatePicker({maxDate:'#F{$dp.$D(\'segStartTime\')}',skin:'twoer',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/>
      至
      <input id="segEndTime" name="segendTime" value="" type="text"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'segendTime\')}',skin:'twoer',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/>
      <br />标签:<input type="text" name="lab" id="seg_lab" />
      <br /><input type="button" onclick="sumitSegDownload()" value="提交" />
      </div>
  </div>


  <div id="left">
      <div class="tv_box" style="height: 5%;">
      <span><i class="icon icon_num2"></i></span>
      <span><i class="icon icon_num3"></i></span>
      <span><i class="icon icon_num4"></i></span>
      <span><i class="icon icon_num6"></i></span>
    </div>
      <div id="cmsv6flash"></div>




  </div>

  <div id="right">
      <ul id="tabs">
          <li><a href="#" title="tab1">基础接口</a></li>
          <li><a href="#" title="tab2">录像查询下载</a></li>
          <li><a href="#" title="tab3">Three</a></li>
          <li><a href="#" title="tab4">Four</a></li>
      </ul>
      <div id="content">
          <div id="tab1">
                  <p>登录信息：result:${sessionScope.loginForm.result}</p>
                  <p>${loginForm.jsession}</p>
                  <c:if test="${sessionScope.loginForm.result ne '0'}">
                    <a href="${basePath}/gps/login">登录</a>
                  </c:if>
          </div>
          <div id="tab2">
              <form method="post" action="${basePath}/gps/video/back" style="height: 600px;overflow: scroll">
             查询条件：<br />
              设备号:<input type="text" name="deviceNo" value="${videoQuyery.deviceNo}" /><br />
              时间段:<input id="startTime" name="startTime" value="${videoQuyery.startTime}" type="text"   onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',skin:'twoer',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/>
              至
              <input id="endTime" name="endTime" value="${videoQuyery.endTime}" type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',skin:'twoer',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/>
                <br />
                  文件位置:<select name="loc">
                      <option  value="1" ${videoQuyery.loc eq '1'?"selected=selected":""} >终端设备</option>
                      <option  value="2" ${videoQuyery.loc eq '2'?"selected=selected":""}>储存服务器</option>
                      <option  value="4" ${videoQuyery.loc eq '4'?"selected=selected":""}>下载服务器</option>
                  </select>

                  <br/>文件类型：
                  <label><input name="rectype" type="radio" ${videoQuyery.rectype eq '0'?"checked=checked":""}  value="0" />常规录像 </label>
                  <label><input name="rectype" type="radio" ${videoQuyery.rectype eq '1'?"checked=checked":""}  value="1" />报警 </label>
                  <label><input name="rectype" type="radio" ${videoQuyery.rectype eq '-1'?"checked=checked":""} value="-1"/>所有</label>
                  <br /> <input type="submit" value="查询"/>

                  <hr />
                  查询结果:${record.result}<br />
                  数据： ${fn:length(record.files)} <br />

                  <table style="border:1px solid grey;width: 100%;">
                      <tr>
                          <td>设备号</td>
                          <td>通道</td>
                          <td>开始时间</td>
                          <td>结束时间</td>
                          <td>文件名称</td>
                          <td>文件大小(MB)</td>
                          <td>录像类型</td>
                          <td>文件位置</td>
                          <td>操作</td>
                      </tr>

                      <c:forEach items="${record.files}" var="file">
                          <tr style="line-height:30px;">
                              <td>${file.devIdno}</td>
                              <td>${file.chn + 1}</td>
                              <td>${file.startTime}</td>
                              <td>${file.endTime}</td>
                              <td>${file.file}</td>
                              <td>${file.len/(1024*1024)}</td>
                              <td>${file.type eq 0?"常规":"报警"}</td>
                              <td>
                              <c:choose>
                                  <c:when test="${file.loc eq 1}">终端设备</c:when>
                                  <c:when test="${file.loc eq 2}">存储服务器</c:when>
                                  <c:when test="${file.loc eq 4}">存储服务器</c:when>
                                  <c:otherwise>未知</c:otherwise>
                              </c:choose>
                              </td>
                              <td>
                                  <a href="javascript:void(0)" onclick="fregmentDownload('${file.len}','${file.devIdno}','${file.startTime}','${file.endTime}','${file.file}','${file.type}','${file.chn}')">分段下载</a>
                                  <a download_url="&jsession=${jsession}&DevIDNO=${file.devIdno}&FLENGTH=${file.len}&FOFFSET=0&MTYPE=1&FPATH=${file.file}&SAVENAME=${file.file}" href="javascript:void(0);" onclick="dowloadAll(this)">直接下载</a>
                                  <a play_url="&DevIDNO=${file.devIdno}&FILELOC=${file.loc}&FILESVR=${file.svr}&FILECHN=${file.chn}&FILEBEG=${file.beg}&FILEEND=${file.end}&PLAYIFRM=0&PLAYCHN=0&PLAYFILE=${file.file}&PLAYBEG=0&PLAYEND=0" href="javascript:void(0);" onclick="remotePlay(this)">播放</a>
                              </td>
                          </tr>
                      </c:forEach>

                  </table>
              </form>
          </div>
          <div id="tab3">
            内容3
          </div>
          <div id="tab4">
           内容4
          </div>
      </div>
      <br>
      <br>




  </div>

  </body>
<script type="text/javascript">
    var num = 4;
    /**
     * 事件绑定
     */
    function eventHandler(){
        $(".icon_num2").click(function(){
            updateCurrentWindowByNum(4);
        });
        $(".icon_num3").click(function(){
            updateCurrentWindowByNum(6);
        });

        $(".icon_num4").click(function(){
            updateCurrentWindowByNum(8);
        });
        $(".icon_num6").click(function(){
            updateCurrentWindowByNum(16);
        });
    }

    function updateCurrentWindowByNum(count) {
        num = count;
        initFlash();
    }



    var isInitFinished = false;//视频插件是否加载完成
    //初始化视频插件
    function initPlayerExample() {
        //视频插件初始化参数
        var params = {
            allowFullscreen: "true",
            allowScriptAccess: "always",
            bgcolor: "#FFFFFF",
            wmode: "transparent"
        };
        //初始化flash
        swfobject.embedSWF("${basePath}/static/gps/player.swf", "cmsv6flash", 300, 300, "11.0.0", null, null, params, null);
        initFlash();
    }
    //视频插件是否加载完成
    function initFlash() {
        if (swfobject.getObjectById("cmsv6flash") == null ||
                typeof swfobject.getObjectById("cmsv6flash").setWindowNum == "undefined" ) {
            setTimeout(initFlash, 50);
        } else {
            //设置视频插件的语言
            swfobject.getObjectById("cmsv6flash").setLanguage("${basePath}/static/gps/cn.xml");
            //先将全部窗口创建好
            swfobject.getObjectById("cmsv6flash").setWindowNum(36);
            //再配置当前的窗口数目
            swfobject.getObjectById("cmsv6flash").setWindowNum(num);
            //设置视频插件的服务器
            swfobject.getObjectById("cmsv6flash").setServerInfo("120.77.253.46", "6605");
            isInitFinished = true;
        }
    }
    initPlayerExample();
    eventHandler();
</script>

  <script type="text/javascript">
      var GPS_ERROR={
          6:"系统出现异常",
          11:"录像下载任务已存在"
      }
  </script>

  <script type="text/javascript">
      $(document).ready(function() {
          $("#content div").hide(); // Initially hide all content
          $("#tabs li:first").attr("id","current"); // Activate first tab
          $("#content div:first").fadeIn(); // Show first tab content

          $('#tabs a').click(function(e) {
              e.preventDefault();
              $("#content div").hide(); //Hide all content
              $("#tabs li").attr("id",""); //Reset id's
              $(this).parent().attr("id","current"); // Activate this
              $('#' + $(this).attr('title')).fadeIn(); // Show content for current tab
          });
      })();
  </script>

<script type="text/javascript">
    function remotePlay(obj){
        debugger;
        var base_url = "http://120.77.253.46:6604/3/5?DownType=5";
        console.log(this);
        console.log(obj);
        var play_url = base_url + $(obj).attr("play_url");
        console.log(play_url);

        swfobject.getObjectById("cmsv6flash").stopVideo(0);
        var ret = swfobject.getObjectById("cmsv6flash").startVod(0, play_url);
    }
    function dowloadAll(obj){
        var base_url = "http://120.77.253.46:6604/3/5?DownType=3";
        var url = base_url + $(obj).attr("download_url");
        console.log(url);
        window.location.href = url;
    }

    function fregmentDownload(len,deviceNo,startTime,endTime,path,type,ch){
        $("#seg_tips").text("可选时间段: "+startTime+"  至  " + endTime);
        $("#segStartTime").val(startTime);
        $("#segEndTime").val(endTime);
        $("#segStartTime").attr("onclick","WdatePicker({minDate:\'"+startTime+"\',maxDate:\'"+endTime+"\',dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'twoer'})");
        $("#segEndTime").attr("onclick","WdatePicker({minDate:\'"+startTime+"\',maxDate:\'"+endTime+"\',dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'twoer'})");

        var dowload_url = "?jsession=${jsession}&did="+deviceNo+"&fbtm={0}&fetm={1}&sbtm="+startTime+"&setm="+endTime+"&lab={2}&fph="+path+"&vtp="+type+"&len="+len+"&chn="+ch+"&dtp=2";
        $("#segDownload_pop").attr("seg_down_url",dowload_url);


        //页面层-自定义
        layer.open({
            type: 1,
            area: ['500px','300px'],
            content: $('#segDownload_pop')
    });
    }

    function sumitSegDownload(){
        debugger;
        var base_url = "http://120.77.253.46:8080/StandardApiAction_addDownloadTask.action";
        var downUrl = $("#segDownload_pop").attr("seg_down_url");
        downUrl =downUrl.replace("{0}",$("#segStartTime").val()).replace("{1}",$("#segEndTime").val()).replace("{2}",$("#seg_lab").val());
        console.log(base_url + downUrl);
       // window.location.href= base_url + downUrl;

        $.ajax({
            url:base_url + downUrl,
            dataType:'jsonp',
            processData: false,
            type:'get',
            success:function(r){
                debugger;
                if(r["result"] == "0"){
                    layer.msg('分段录像任务保存成功！',{area: ['200px', '120px']});
                }else if(r["result"] == "11"){
                    layer.msg('录像下载任务已存在！',{area: ['200px', '120px']});
                }else{
                    layer.msg('保存失败,gps响应结果！'+r["result"],{area: ['200px', '120px']});
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                layer.msg('保存失败,gps响应结果！'+r["result"],{area: ['200px', '120px']});
            }});
     }
</script>
</html>

