// 执行异步操作
function invoke(_url, _data, _callback, _onError) {

   // var _ctype = _data instanceof FormData ? false : "application/x-www-form-urlencoded";
    $.ajax({
        url: _url,
        type: "post",
        cache: false,
        processData: false,
        async: false,
       // contentType: _ctype,
        data: _data,
        success: function (_result) {
             if (typeof _callback == 'function')
                _callback(_result);
        },
        error: function (r) {
            if (typeof _onError == 'function')
                _onError(r);
            else
                layerMsg("当前操作出现错误,错误代码：" + r.status);
        }
    }, 10);
}
// 消息提示，需要依赖layer.js
function layerMsg(msg, call) {
    msg = msg ? $.trim(msg) : "";
    var _time = (msg.length / 10) * 1200;
    layer.msg(msg, {
        time: _time,
        tipsMore: true
    }, function () {
        if (typeof call == 'function')
            call();
    });
}
function confirm(msg, call) {
    msg = msg ? $.trim(msg) : "";
    layer.confirm(msg, {
        btn: ['确定','取消']
        // 按钮
    }, function () {
        if (typeof call == 'function') {
            call();
        }
    });
}
function closePage() {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
    return false;
}
function openPage(title, url, cfg) {
    cfg = cfg || {};
    cfg.type = (url && (url.indexOf('/') == 0 || url.indexOf('http') == 0)) ? 2 : 0;
    if (cfg.type == 2) {
        url = url + (url.indexOf('?') > 0 ? "&" : "?");
        url += "_page_from=frame";
    }
    cfg.width = Math.min((cfg.width || 500), $(window).width());
    cfg.height = Math.min((cfg.height || 500), $(window).height());
    var opt = {
        type: cfg.type,
        title: title,
        move: false,
        shade: 0.8,
        area: [cfg.width + 'px', cfg.height + 'px'],
        content: url,
        scrollbar: false,
        zIndex: cfg.zIndex, //重点1
        success: function (layero, index) {
            layer.setTop(layero); //重点2
            if (typeof cfg.success == 'function')
                cfg.success(layero, index);
        },
        cancel: function (index) {
            if (typeof cfg.cancel == 'function')
                cfg.cancel(index);
        }

    };
    if (cfg.btns) {
        var _btns = cfg.btns;
        var btn = [];
        for (var i = 0; i < _btns.length; i++) {
            var _b = _btns[i];
            btn.push(_b.text);
            opt["btn" + (i + 1)] = _b.call;
        }
        opt.btn = btn;
    }
    layer.open(opt);
}

function getLocalPath() {
    var href = window.location.href;
    href = href.replace(window.location.protocol + "//", "").replace(
        window.location.host, "");
    return href;
}
function randomString(len) {
    len = len || 32;
    var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefhijklmnopqrstovwxyz0123456789';
    var maxPos = $chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}


function gettimes(s, e) {
    var date1 = new Date(s).getTime();  //开始时间 
    var date2 = e || new Date();
    var date3 = new Date(date2).getTime() - date1;   //时间差的毫秒数        

    //计算出相差天数  
    var days = Math.floor(date3 / (24 * 3600 * 1000));

    var leave1 = date3 % (24 * 3600 * 1000);    //计算天数后剩余的毫秒数  
    var hours = Math.floor(leave1 / (3600 * 1000));
    //计算相差分钟数  
    var leave2 = leave1 % (3600 * 1000);        //计算小时数后剩余的毫秒数  
    var minutes = Math.floor(leave2 / (60 * 1000));

    var _str = "";
    if (days > 0)
        _str += days + "天";
    if (days == 0 && hours > 0)
        _str += hours + "小时";
    if ((days == 0 && hours == 0) || minutes > 0)
        _str += minutes + "分钟";
    return _str;
}

function GetDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate() + AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth() + 1;//获取当前月份的日期
    var d = dd.getDate();
    return y + "-" + m + "-" + d;
}


