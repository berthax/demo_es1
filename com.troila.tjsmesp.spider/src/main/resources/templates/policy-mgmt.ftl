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
			<div class="adminTit govTit">政策解读</div>
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
					<!-- 搜索栏结束  -->

					<div class="tabIndex_w mt12 tableWrapper_zy">
						<table class="tablelist tableTh40_w tableBoxsiz_zy">
							<tr>
								<th  width="30%">政策名称</th>
			                    <th  width="10%">类别</th>
			                    <th  width="15%">发布时间</th>
			                    <th  width="15%">申请解读数</th>
								<th  width="15%">发布状态</th>
			                    <th  width="15%">操作</th>
							</tr>
						</table>
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
	
	function load(p) {
		var keyword = $.trim($("#keyword").val());
		var url = '/v2/policy/search?key=' + keyword + '&size=10&filter[]=publisher:${user.userId}&page=' + p;
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
	function search() {
		var keyword = $.trim($("#keyword").val());
		var box = layer.msg("正在检索数据， 请稍后",{icon:1, time:0});
		var url = '/v2/policy/search?key=' + keyword + '&page=0&size=10&filter[]=publisher:${user.userId}';
		$.getJSON(url, function(result){
			if(result.code === 200) {
				$("#policy-list").children().remove();
				for(var i = 0; i < result.data.length; i++) {
					var policy = result.data[i];
					var row = composePolicyHtml(policy);
					$("#policy-list").append(row);	
				}
				$(".pagination").pagination(result.pagination.totalElementCount,{
					items_per_page:result.pagination.pageSize,
					num_display_entries:4,
					current_page:result.pagination.currentPage,
					num_edge_entries:5,
					callback: load
				});
				$(".total_page").text("共"+result.pagination.totalPageCount+"页");
				$(".pagination").parent().show();
			}
			layer.close(box);
		});
	}
	var date = new Date();
	function composePolicyHtml(policy) {
		date.setTime(policy.publishDate);
		var row = '<tr><td width="30%" title="' + policy.title + '">' + policy.title + '</td>'
			+ '<td width="10%">' +policy.category+ '</td><td width="15%">' + date.toLocaleDateString() + '</td>'
			+ '<td width="15%">' + policy.priority +'</td>'
			+ '<td width="15%">' + tranlateStatus(policy.status) + '</td>'
			+ '<td width="15%" class="link_blue">';
		if(policy.status != 3) {
			row += '<a class="mr10" href="javascript:cancel(' + policy.id + ')">撤销</a>';
		}
		row += '<a class="mr10" href="/policy/new/plain?parent=' + policy.id + '&uid=${user.userId}">解读</a></td></tr>';
		return row;
	}
	
	search();

})

</script>
</body>
</html>