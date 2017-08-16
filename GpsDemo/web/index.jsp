<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
      <script language="javascript" type="text/javascript" src="${basePath}/static/gps/swfobject.js"></script>
      <script language="javascript" type="text/javascript" src="${basePath}/static/jquery.min.js"></script>
      <script language="javascript" type="text/javascript" src="${basePath}/static/jquery-ui.min.js"></script>
      <style type="text/css">
          div{
              height:100%;
          }
          #left{
              width:48%;
              border:1px solid red;
              float: left;
          }
          #right{
              width:50%;
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
          <li><a href="#" title="tab2">Two</a></li>
          <li><a href="#" title="tab3">Three</a></li>
          <li><a href="#" title="tab4">Four</a></li>
      </ul>
      <div id="content">
          <div id="tab1">
                  <p>登录信息：result:${loginForm.result}</p>
                  <p>${loginForm.jsession}</p>
                  <c:if test="${loginForm.result ne '0'}">
                    <input type="button" value="登录"  />
                  </c:if>
          </div>
          <div id="tab2">
             内容2
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
        swfobject.embedSWF("${basePath}/static/gps/player.swf", "cmsv6flash", 600, 500, "11.0.0", null, null, params, null);
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

  <script>
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
</html>
