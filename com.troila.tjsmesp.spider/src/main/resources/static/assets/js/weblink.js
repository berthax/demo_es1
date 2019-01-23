$(function() {
    //浏览的方法
    //浏览
    var mc = myBrowser();
    if ("IE" == mc) {
        fileN1();
    }
    if ("FF" == mc) {
        fileN2();
    }
    if ("Chrome" == mc) {
        fileN1();
    }
    if ("Opera" == mc) {
        fileN1();
    }
    if ("Safari" == mc) {
        fileN1();
    }
    //字数统计
    //footer样式
    $(".link li:last").addClass('nobod');
    $(".link li:first").addClass('nomargin');

    //下拉
    $('select').selectOrDie();
    //搜索栏触发失去输入
    var $input = $(".searchinput_box input")
    $input.focus(function (event) {
        var val = $(this).val();
        if(val==""||val==null){
            $(this).siblings("span").hide();
        }

    })
    $input.blur(function () {
        toggleshow($(this));
    })

    $(".searchinput_box span").click(function () {
        $(this).siblings("input").focus();
    })
    //头部导航
    $(".nav li:eq(0),.crumb li:eq(0)").css({"padding-left":0})
    // 后台
    // 后台左侧进度导航
    subnav();
    $(".sidebar>li:last").addClass('nobod')
    //上传文件按钮样式
    $(".fileBtn").hover(function() {
        $(this).find('.formBtn1').addClass('formBtn1H')
    }, function() {
        $(this).find('.formBtn1').removeClass('formBtn1H')
    });
    // 春雨计划
    $(".subMenu ul li:first").addClass('subM1st');
    $(".springDtlTit dl dd:first").css('background', 'none');

    //============================================================政府工作侧导航start===============================================================
    // 审核服务机构
    $(".gov_sidebar_box div:first p").click(function () {
        $(this).siblings(".nav_slide").slideToggle();
        $(".hsubnav .nav_title").removeClass('on');
        $(".hsubnav >dl").slideUp();
        $(".hsubnav >dl dd").slideUp();
        $(".hsubnav i").removeClass("open");
        $(this).addClass("current");
    });
    //多级导航：一级
    $(".hsubnav .nav_titlebox").click(function () {
        var $hsubnav =  $(this).parent(".hsubnav").siblings(".hsubnav");
        $(".hsubnav dl i").removeClass("open");
        $(this).siblings("dl").slideToggle();
        $(".nav_slide").slideUp().siblings(".nav_title").removeClass("current");
        $(this).find("i").toggleClass('open');
        $(".hsubnav:first").find("dd").slideUp();
        $hsubnav.find("i").removeClass('open');
        $hsubnav.find("dl").slideUp();
        $hsubnav.find("dl").find("dl").slideDown();
        $(this).find("p").addClass("on");
        $hsubnav.find(".nav_title").removeClass('on');
    })
    // 多级导航：二级dt
   $(".hsubnav dt").click(function (ev) {
        var oevent = ev||event;
        oevent.preventDefault();
        var $parent = $(this).parent("dl");
        $(this).nextAll("dd").slideToggle();
        $parent.parent("dd").siblings("dd").find("dd").slideUp();
        $(this).find("i").toggleClass("open");
        $parent.parent("dd").siblings("dd").find("i").removeClass('open');
        $parent.siblings("dl").find("dd").slideUp();
        $parent.siblings("dl").find('i').removeClass('open');
    })
    //审核服务机构链接跳转
    $(".gov_sidebar li").click(function () {
        var n = $(this).index();
        switch (n){
            case 1:
                location.href = "后台-政府-基本信息审核.html";
                break;
            case 2:
                location.href = "后台-政府-中小企业互动平台后台_动态信息审核(第二版).html";
                $(".gov_sidebar li:eq(2)").addClass("current");
                break;
            case 3:
                location.href = "后台-政府-机构认定信息审核.html";
                $(".gov_sidebar li:eq(3)").addClass("current");
                break;
        }

    })
    // 侧导航步骤流程图标
    var onL = $(".gov_sidebar .on").length;
    switch(onL){
        case 0:
            $(".steppass").height("0");
            break;
        case 1:
            $(".steppass").height("18px");
            break;
        case 2:
            $(".steppass").height("49px");
            break;
        case 3:
            $(".steppass").height("81px");
            break;
    }
    $(".gov_sidebar_border").click(function (event) {
        event.preventDefault();
        var name = $(this).find(".nav_title").html();

        switch (name){
            case "分派申诉单":
                window.location.href="二期设计-政府-02-1分派申诉单.html";
                break;
            case "发布政策法规":
                window.location.href="二期设计-政府-03-1发布政策法规.html";
                break;
            case "服务机构推荐":
                window.location.href="二期设计-政府-05-1服务机构推荐.html";
                break;
            case "服务机构信息":
                window.location.href="二期设计-政府-06-1服务机构信息.html";
                break;
            case "企业信息":
                window.location.href="二期设计-政府-07-1企业信息.html";
                break;


        }
    })

    $(".gov_sidebar_border:eq(2) ul li").click(function (event) {
        event.preventDefault();
        var name2 = $(this).html();
        switch (name2){
            case "窗口资讯":
                window.location.href="二期设计-政府-04-1-1责任服务窗口-窗口咨询.html";
                break;
            case "窗口责任":
                window.location.href="二期设计-政府-04-2责任服务窗口-窗口责任.html";
                break;
        }
    })
    //============================================================政府侧导航over===============================================================
});
//顶部搜索方法
function toggleshow(a) {
    var search_val_blur = $(a).val();
    if(search_val_blur==""||search_val_blur=="null"){
        $(a).siblings("span").show();
    }else{
        $(a).siblings("span").hide();
    }
}
// 后台左侧进度导航
function subnav(){
    $(".subnav li:first").addClass('subnav_1st');
    $(".subnav li:last").addClass('subnav_end');
    $(".subnavCur").prevAll().addClass('subPass').removeClass('subnavCur');
}
function wordNum(n){
    $(".paperSty3_PJY dl").each(function(i,e){
        $(this).find(".textarea01").on("input propertychange",function(){
            var allStr=$(this).val();
            var number=allStr.length;
            var limited=n;
            if(number>limited-1){
                var html1=allStr.slice(0,limited);
                $(this).val(html1);
                $(this).next().find("span").html(0)
            }else{
                $(this).next().find("span").html(limited-number)
            }
        })
    })
}
//  浏览
function myBrowser(){
    var userAgent = navigator.userAgent;
    var isOpera = userAgent.indexOf("Opera") > -1;
    if (isOpera) {
        return "Opera"
    };
    if (userAgent.indexOf("Firefox") > -1) {
        return "FF";
    }
    if (userAgent.indexOf("Chrome") > -1){
        return "Chrome";
    }
    if (userAgent.indexOf("Safari") > -1) {
        return "Safari";
    }
    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
        return "IE";
    };
}
//浏览器方法
function fileN1(){
    $(".filtInp_PJY").each(function(i,e){
        $(this).on("focus",function(){
            $(this).parent().find(".myFile_PJY").on("change",function(){
                var val=$(this).val();
                var strFileName=val.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名
                var FileExt1=val.replace(/.+\./,""); //格式
                $(this).parent().find(".filtInp_PJY").val(strFileName+"."+FileExt1)
            }).trigger("click");
        })
    })
    $(".bower_PJY").each(function(i,e){
        $(this).on("focus",function(){
            $(this).parent().find(".myFile_PJY").on("change",function(){
                var val=$(this).val();
                var strFileName=val.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名
                var FileExt1=val.replace(/.+\./,""); //格式
                $(this).parent().find(".filtInp_PJY").val(strFileName+"."+FileExt1)
            }).trigger("click");
        })
    })
}
function fileN2(){
    $(".filtInp_PJY").each(function(i,e){
        $(this).on("focus",function(){
            $(this).parent().find(".myFile_PJY").on("change",function(){
                var val=$(this).val();
                var strFileName=val.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名
                $(this).parent().find(".filtInp_PJY").val(strFileName)
            }).trigger("click");
        })
    })
    $(".bower_PJY").each(function(i,e){
        $(this).on("focus",function(){
            $(this).parent().find(".myFile_PJY").on("change",function(){
                var val=$(this).val();
                var strFileName=val.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名
                $(this).parent().find(".filtInp_PJY").val(strFileName)
            }).trigger("click");
        })
    })
}

