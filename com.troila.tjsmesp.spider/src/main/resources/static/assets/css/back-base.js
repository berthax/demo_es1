/**
 * Created by Liqiaomiao on 2016/12/7.
 */
$(document).ready(function () {
    scrollpos();
$(window).resize(function () {
    scrollpos()
});
//最小宽度计算
var  $ww = window.screen.width;
var  $wh = window.screen.height;
var rightminW = $ww - 245;
var bodyminW  = $ww-20;
$(".rightwraper").css({"min-width":rightminW})
$("body").css({"min-width":bodyminW,"height":$wh})

    //侧导肮链接
    $(".back_sidebar li").click(function () {
    var n = $(this).index();
    switch (n){
        case 0:
            window.location.href = "二期内容-后台管理-发布管理.html";
            break;
        case 1:
            window.location.href = "二期内容-后台管理-系统管理-平台用户管理.html";
            break;
        case 2:
            window.location.href = "二期内容-后台管理-管理系统-政府用户管理.html";
            break;
        case 3:
            window.location.href = "二期内容-后台管理-市场营销.html";
            break;
        case 4:
            window.location.href = "二期内容-后台管理-调研问卷-调研问卷.html";
            break;
        case 5:
            window.location.href = "二期内容-后台管理-发布管理、市场营销、绩效考核-3业绩考核.html"
            break;
        case 6:
            window.location.href = "二期内容-后台管理-6业绩考核报告下载.html";
    }
})

});
// 页面滚动条显示位置
function scrollpos() {
    var sH = $(window).height();
    var rH = sH-90;
    var sidebarH = sH - 70;
    var rightcontentH = $(".rightwraper").height()+50;

    if (rightcontentH<rH){$(".back_footer").addClass("footer_pf")}
    else{$(".back_footer").removeClass("footer_pf")}
    $(".rightcontainer").height(rH);
    $(".back_sidebar").height(sidebarH);
}