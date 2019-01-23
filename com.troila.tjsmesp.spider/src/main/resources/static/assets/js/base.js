$(function() {
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
	//浏览的方法

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
	// $input.keydown(function () {
	// 	toggleshow($(this));
	// })

	$(".searchinput_box span").click(function () {
		$(this).siblings("input").focus();
	})
	//头部导航
	$(".nav li:eq(0),.crumb li:eq(0)").css({"padding-left":0})
	// 后台
	// 后台左侧进度导航
	subnav();
	$(".sidebar>li:last").addClass('nobod')
	// $(".subnav li").click(function() {
	// 	$(this).removeClass('subPass').addClass('subnavCur').nextAll().removeClass('subnavCur subPass');
	// 	subnav();
	// });
	// 后台下拉框
//	$('select').selectOrDie();
	//上传文件按钮样式
	$(".fileBtn").hover(function() {
		$(this).find('.formBtn1').addClass('formBtn1H')
	}, function() {
		$(this).find('.formBtn1').removeClass('formBtn1H')
	});
	// 春雨计划
	$(".subMenu ul li:first").addClass('subM1st')
	$(".springDtlTit dl dd:first").css('background', 'none');

	//============================================================政府工作侧导航start===============================================================
	$(".nav_title").click(function () {
		$(this).next().slideToggle();

    })
	//审核服务机构链接跳转
	$(".gov_sidebar li").click(function () {
		var n = $(this).index();
		switch (n){
			case 1:
				location.href = $("#path").val()+"/page/jsp/govaudit/govauditBaseInfo.jsp?user_code="+user_code+"";

				break;
			case 2:
                location.href = $("#path").val()+"/page/jsp/govaudit/govauditFinance.jsp?user_code="+user_code+"";
                $(".gov_sidebar li:eq(2)").addClass("current");
                break;
            case 3:
                location.href = $("#path").val()+"/page/jsp/govaudit/govauditInstitution.jsp?user_code="+user_code+"";
                $(".gov_sidebar li:eq(3)").addClass("current");
                break;

		}

    })
    // 侧导航步骤流程图标
    var onL = $(".gov_sidebar .on").length;
    switch(onL){
        case 0:
            break;
            $(".steppass").height("0");
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
			window.location.href=$("#path").val()+"/page/jsp/govaudit/servicesTransactions/servicesTransactionsList.jsp";
        break;
        case "发布政策法规":
            window.location.href=$("#path").val()+"/page/jsp/govaudit/relPoliciesReguList/relPoliciesReguList.jsp";
            break;
        case "服务机构推荐":
            window.location.href=$("#path").val()+"/page/jsp/govaudit/relPoliciesReguList/relPoliciesReguList.jsp";
            break;
        case "服务机构信息":
            window.location.href=$("#path").val()+"/page/jsp/govaudit/enterpriseList/enterpriseList.jsp?status=2";
            break;
        case "企业信息":
            window.location.href=$("#path").val()+"/page/jsp/govaudit/enterpriseList/enterpriseList.jsp?status=1";
            break;


	}
});


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

