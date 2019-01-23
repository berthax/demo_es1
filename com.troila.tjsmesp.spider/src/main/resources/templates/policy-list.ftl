<#-- @ftlvariable name="" type="com.troila.sme.service.policy.view.SmeView" -->
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<link rel="stylesheet" type="text/css" href="/policy/assets/css/base.css">
	<script src="/policy/assets/js/jquery-1.11.3.min.js"></script>

	<!-- 下拉 -->
	<link href="/policy/assets/css/select-top.css" rel="stylesheet" />
	<script src="/policy/assets/js/select.js"></script>
	<!-- 内容下拉框 -->
	<link href="/policy/assets/css/admin_select.css" rel="stylesheet" />

	<!-- 分页 -->
	<link rel="stylesheet" type="text/css" href="/policy/assets/css/pagination.css">
	<script type="text/javascript" src="/policy/assets/js/jquery.pagination.js"></script>

	<!-- 日期插件 -->
	<link rel="stylesheet" type="text/css" href="/policy/assets/css/jquery-ui-1.10.4.custom.css">
	<script type="text/javascript" src="/policy/assets/js/jquery.ui.core.js"></script>
	<script type="text/javascript" src="/policy/assets/js/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="/policy/assets/js/jquery.ui.datepicker.js"></script>

	<link rel="stylesheet" href="/policy/assets/css/admin.css">
	<link rel="stylesheet" href="/policy/assets/css/houtaim.css">
	<link rel="stylesheet" href="/policy/assets/css/leeAdmin.css">
	<link rel="stylesheet" href="/policy/assets/css/wy.css">
	<script src="/policy/assets/js/weblink.js"></script>
	<link rel="stylesheet" href="/policy/assets/css/message_PJY.css"/>

	<script src="/policy/assets/js/back-base.js"></script>
	<link rel="stylesheet" href="/policy/assets/css/zy.css">
	<link rel="stylesheet" href="/policy/assets/css/backmain.css">
	<link rel="stylesheet" href="/policy/assets/css/ly.css">
	<title></title>
</head>
<body class="askMes_PJY">
	<!-- header start -->
<div id="header">
	<div class="headerarea">
		<div class="container">
			<div class="headerl fl">
				<div class="logo fl">
				</div>
				<div class="font20 fontwhite fl mt20">
				</div>
			</div>
			<div class="headerr fr mb15">
				<div class="font12 fontwhite fr">
					<span class="mr20 ">您好，欢迎来到天津市中小企业服务互动平台！</span>
				服务热线：
					<span class="fontb">022-27728778</span>
				</div>
			
			</div>
			<div class="clear">
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
	<!-- header end -->
	<!-- maincontent start -->
	<div class="admMmain mb60">
		
		<!-- 右侧白色区域 -->
		<div class="admMmainR">
			<!-- 栏目名 -->
			<div class="pr p20">
                    <div class="lee_tab mt20">
                        <ul class="lee_hd fl">
                        	<li <#if defaultStatus==99>class="lee_hd_cur"</#if> onclick="search(this, 'filter[]=eq:status:99&filter[]=eq:publisher:${user.userId}');showTopPolicy(false)"><a href="javascript:;">待提交</a></li>
                            <li <#if defaultStatus==0>class="lee_hd_cur"</#if> onclick="search(this, 'filter[]=eq:status:0');showTopPolicy(false)"><a href="javascript:;">待审批</a></li>
                            <li <#if defaultStatus==1>class="lee_hd_cur"</#if> onclick="search(this, 'filter[]=eq:status:1');showTopPolicy(true)"><a href="javascript:;" >已发布</a></li>
                            <li <#if defaultStatus==2>class="lee_hd_cur"</#if> onclick="search(this, 'filter[]=eq:status:2');showTopPolicy(false)"><a href="javascript:;" >已拒绝</a></li>
                            <li <#if defaultStatus==3>class="lee_hd_cur"</#if> onclick="search(this, 'filter[]=eq:status:3');showTopPolicy(false)"><a href="javascript:;">已撤销</a></li>
                        </ul>
                        <div class="clear"></div>
                    </div>
                <div>
			<div class="bgf">
				<div class="">
					<!-- 搜索栏开始  -->
					<form class="searchWraper" >
						<div class="ConT_w">
							<div class="fr mr10">
								<span class="tar title fl pr2 ">输入关键字：</span>
								<input type="text" class="w200 fl mr10" id="keyword"/>
								<a class="btn_solid fr btn_search">查询</a>
								<div class="clear"></div>
							</div>
							<div class="clear"></div>
						</div>
					</form>
					<#if !filterByUser>
					<div class="counts_zy clearfix">
	                    <div class="fr ml20">
	                        <span>发布政策解读：</span><i id="stat-2">8</i><span>篇</span>
	                    </div>
	                    <div class="fr ml20">
	                        <span>发布政策摘编：</span><i id="stat-1">0</i><span>篇</span>
	                    </div>
	                    <div class="fr">
	                        <span>发布政策详情：</span><i id="stat-0">0</i><span>篇</span>
	                    </div>
	                </div>
	                </#if>
					<!-- 搜索栏结束  -->

					<div class="tabIndex_w mt12 tableWrapper_zy">
						<table class="tablelist tableTh40_w tableBoxsiz_zy">
							<tr>
								<th  width="25%">政策名称</th>
								<th  width="15%">发文部门</th>
			                    <th  width="10%" class="sort_col"><a href="javascript:;" onclick="sort(this,'category');">类别<i class="sort_icon"></i></a></th>
			                    <th  width="10%" class="sort_col"><a href="javascript:;" onclick="sort(this,'gmtCreate');">发布时间<i class="default_sort_icon sort_icon desc"></i></a></th>
			                    <th  width="10%" class="sort_col"><a href="javascript:;" onclick="sort(this,'priority');">申请解读数<i class="sort_icon"></i></a></th>
								<th  width="5%">发布状态</th>
			                    <th  width="25%">操作</th>
							</tr>
						</table>
						<#if !filterByUser>
						<div class="tb_body_w tableBoxsiz_zy tableOverE_zy" style="display:none">
							<table id="top-policy">
							</table>
						</div>
						</#if>
						<#include "directives.ftl">
						<div class="tb_body_w tableBoxsiz_zy tableOverE_zy">
							<table id="policy-list">
							</table>
						</div>
					</div>
					<!--分页-->
					<div class="pagebox_w">
						<div class="pagination"></div>
						<span class="total_page">共1页</span>
					</div>
				</div>
			</div>

			<div class="clear"></div>

		</div>
		<div class="clearfix"></div>
	</div>
	<#include "policy-cancel.ftl">

	<!-- maincontent over -->
	<!-- 底部footer -->
	<div class="adm_footer clear">
		<p>技术版权所有 © 天津卓朗科技发展有限公司 2013 TROILA.Inc. 保留一切权利。津ICP备10200312号-4</p>
	</div>

<script>
function sort(which, col) {
	__CURRENT_DATA_URL__ = __CURRENT_DATA_URL__.replace(/&orderBy=\w+/,"");
	__CURRENT_DATA_URL__ = __CURRENT_DATA_URL__.replace(/&orderType=\w+/,"");
	__CURRENT_DATA_URL__ += "&orderBy="+col;
	var $sortIcon = $(which).find(".sort_icon");
	var sortType = $sortIcon.hasClass("desc") ? "asc":"desc"; 
	__CURRENT_DATA_URL__ += "&orderType=" + sortType;
	$(".sort_icon").removeClass("asc desc");
	$sortIcon.addClass(sortType);
	load(0);
}
var __CURRENT_DATA_URL__ = "";
function search(ele, filter) {
	var keyword = $.trim($("#keyword").val());
	var box = layer.msg("正在检索数据， 请稍后",{icon:1, time:0});
	var url = '/v2/policy/search?key=' + keyword + '&size=10';
	<#if filterByUser>
	url += '&filter[]=eq:publisher:${user.userId}';
	</#if>
	if(typeof(filter) != "undefined") {
		url += '&' + filter;
	}
	if(ele) {
		$(".lee_hd").children("li").removeClass("lee_hd_cur");
		$(ele).addClass("lee_hd_cur")
	}
	url += "&r=" + Math.random(); 
	url = encodeURI(url);
	__CURRENT_DATA_URL__ = url;
	$.getJSON(url, function(result){
		if(result.code === 200) {
			$("#policy-list").children().remove();
			for(var i = 0; i < result.data.length; i++) {
				var policy = result.data[i];
				var row = composePolicyHtml(policy);
				$("#policy-list").append(row);	
			}
			if(result.pagination) {
				$(".pagination").pagination(result.pagination.totalElementCount,{
					items_per_page:result.pagination.pageSize,
					num_display_entries:4,
					current_page:result.pagination.currentPage,
					num_edge_entries:5,
					callback: load
				});
				$(".total_page").text("共"+result.pagination.totalPageCount+"页");
				$(".pagination").parent().show();
			} else {
				$(".pagination").parent().hide();
			}
		}
		layer.close(box);
	}).fail(function(r){
		layer.close(box);
		if(r.responseJSON.code == 404) {
			layer.msg("政策数据服务没有启动，请联系管理员", {time: 2000, icon: 3});
		} else {
			layer.msg("政策数据服务异常，请联系管理员", {time: 2000, icon: 3});
		}
	});
	$(".sort_icon").removeClass("asc desc");
	$(".default_sort_icon").addClass("desc");
}
var date = new Date();
Date.prototype.toDateString = function (fmt) { //author: meizz 
	fmt = fmt || "yyyy-MM-dd";
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function composePolicyHtml(policy) {
	date.setTime(policy.publishDate);
	var row = [];
	row.push('<tr id="policy-'+policy.id+'"><td width="25%" title="' + policy.title + '"><a href="/policy?preview=true&uid=${user.userId}&aid='+policy.id+'" target="_blank">' + policy.title + '</a></td>');
	row.push('<td width="15%">' +policy.source+ '</td>');
	row.push('<td width="10%">' +policy.category+ '</td><td width="10%">' + date.toDateString() + '</td>');
	row.push('<td width="10%">' + policy.priority +'</td>');
	row.push('<td width="5%">' + tranlateStatus(policy.status) + '</td>');
	row.push('<td width="25%" class="link_blue">');
	if(policy.status == 3) {
		row.push('</td></tr>');
		if(typeof(policy.reviewMessage) != "undefined" && policy.reviewMessage != "") {
			row.push("<tr><td colspan='6' align='left'>撤销原因:" + policy.reviewMessage + "</td></tr>");
		}
		return row.join("");
	}
	if(policy.status == 0 || policy.status == 99) {
		row.push('<a class="mr10" href="/policy/edit?uid=${user.userId}&aid=' + policy.id + '">编辑</a>');
	} 
	<#if !filterByUser>
		if(policy.status == 0) {
			row.push('<a class="mr10" href="/policy/review?uid=${user.userId}&aid=' + policy.id + '">审批</a>');
		} else if(policy.status == 1) {
			row.push('<a class="mr10" href="/policy/edit?uid=${user.userId}&from=${from}&aid=' + policy.id + '">编辑</a>');
			row.push('<a class="mr10" href="#" onclick="fixtop(this,' + policy.id + ')">置顶</a>');
		}
	</#if>
	if(policy.type!=1) {
		row.push('<a class="mr10" href="/policy/new/chop?uid=${user.userId}&from=${from}&parent=' + policy.id + '">摘编</a>');
	}
	if(policy.status != 3) {
		row.push('<a class="mr10" href="javascript:cancel(' + policy.id + ')">撤销</a>');
	}
	row.push('<a class="mr10" href="/policy/new/plain?parent=' + policy.id + '&uid=${user.userId}&from=${from}">解读</a></td></tr>');
	if((policy.status == 2) && (typeof(policy.reviewMessage) != "undefined")) {
		row.push("<tr><td colspan='6' align='left'>拒绝原因:" + policy.reviewMessage + "</td></tr>");
	}
	return row.join("");
}

<#if !filterByUser>
function getTopPolicy() {
	$.getJSON("/v2/policy/top", function(result){
		if(result.code === 200) {
			for(var i = 0; i < result.data.length; i++) {
				var policy = result.data[i];
				date.setTime(policy.publishDate);
				var row = [];
				row.push('<tr id="policy-'+policy.id+'"><td width="25%" title="' + policy.title + '"><a href="/policy?preview=true&uid=${user.userId}&aid='+policy.id+'" target="_blank">' + policy.title + '</a></td>');
				row.push('<td width="15%">' +policy.source+ '</td>');
				row.push('<td width="10%">' +policy.category+ '</td><td width="10%">' + date.toLocaleDateString() + '</td>');
				row.push('<td width="10%">' + policy.priority +'</td>');
				row.push('<td width="5%">' + tranlateStatus(policy.status) + '</td>');
				row.push('<td width="25%" class="link_blue">');
				<#if !filterByUser>
				row.push('<a class="mr10" javascript="#" onclick="untop(this,' + policy.id + ')">取消置顶</a>');
				</#if>
				row.push('<a class="mr10" href="/policy/new/chop?uid=${user.userId}&from=${from}&parent=' + policy.id + '">摘编</a>');
				if(policy.status != 3) {
					row.push('<a class="mr10" href="javascript:cancel(' + policy.id + ')">撤销</a>');
				}
				row.push('<a class="mr10" href="/policy/new/plain?parent=' + policy.id + '&uid=${user.userId}&from=${from}">解读</a></td></tr>');
				$("#top-policy").append(row.join(""));	
			}
		}
	});
}

function showTopPolicy(yes) {
	if(yes) {
		if($("#top-policy").children().length>0) {
			$("#top-policy").parent().show();
		}
	} else {
		$("#top-policy").parent().hide();
	}
}

</#if>
function load(p) {
	var keyword = $.trim($("#keyword").val());
	var url = __CURRENT_DATA_URL__ + '&page=' + p;
	$.getJSON(url, function(result){
		if(result.code === 200) {
			$("#policy-list").children().remove();
			for(var i = 0; i < result.data.length; i++) {
				var policy = result.data[i];
				var row = composePolicyHtml(policy);
				$("#policy-list").append(row);	
			}
		}
	});
}

$(document).ready(function () {
	//日期
	$(".choseData").datepicker({changeYear:true,changeMonth:true})
	$(".tableWrapper_zy table tr:even").addClass("tr_even");
	$(".tableWrapper_zy table tr:first").removeClass("tr_even");
	
	$("#keyword").keyup(function (event) {
		if(event.keyCode == 13) {
			search();
		}
	})
	$(".btn_search").click(search);
	
	search(null, "filter[]=eq:status:${defaultStatus}");
	<#if !filterByUser>
	getTopPolicy();
	</#if>
	
	$.getJSON("/v2/stat/policy/type", function(res){
		if(res.data != null) {
			for(var i = 0; i < res.data.length; i++) {
				var stat = res.data[i];
				$("#stat-"+stat.type).text(stat.count);
			}
		}
	});
	
})

function untop(a, id) {
	$.ajax({
		url: "/v2/policy/untop?uid=${user.userId}&aid="+id,
		type: "post",
		success: function(result) {
			if(result.code == 200) {
				var policy = $(a).parent().parent().remove();
				$("#policy-list").prepend(policy[0]);
				$(a).attr("onclick","fixtop(this, "+id+")").text("置顶");
				showTopPolicy(false);
				layer.msg("操作成功", {icon: 1, time: 1500});
			} else {
				layer.msg(result.message, {icon: 1, time: 3000});
			}
		},
		error: function(result) {
			layer.msg("服务器发生异常，请联系管理员" + result.responseJSON.message, {icon: 1, time: 3000});
		}
	});
}
function fixtop(a, id) {
	$.ajax({
		url: "/v2/policy/top?uid=${user.userId}&aid="+id,
		type: "post",
		success: function(result) {
			if(result.code == 200) {
				var policy = $(a).parent().parent().remove();
				$("#top-policy").append(policy[0]);
				$(a).attr("onclick","untop(this, "+id+")").text("取消置顶");
				showTopPolicy(true);
				layer.msg("操作成功", {icon: 1, time: 1000});
			} else {
				layer.msg(result.message, {icon:1, time: 2000});
			}
		},
		error: function(result) {
			layer.msg("服务器发生异常，请联系管理员" + result.responseJSON.message, {icon:1,time: 2000});
		}
	});
}

</script>
</body>
</html>