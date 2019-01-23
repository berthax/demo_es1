<#-- @ftlvariable name="" type="com.troila.sme.service.policy.view.SmeView" -->
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<link rel="stylesheet" type="text/css" href="/policy/assets/css/base.css">
	<link rel="stylesheet" href="/policy/assets/js/custom-scrollbar/jquery.mCustomScrollbar.css">
	<script src="/policy/assets/js/jquery-1.11.3.min.js"></script>
	<script src="/policy/assets/js/jquery.form.js"></script>
	<script src="/policy/assets/js/custom-scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>

	<!-- 下拉 -->
	<link href="/policy/assets/css/select-top.css" rel="stylesheet" />
	<link href="/policy/assets/css/select2.css" rel="stylesheet" />
	<script src="/policy/assets/js/select.js"></script>
	<script src="/policy/assets/js/select2.full.js"></script>
	<!-- 内容下拉框 -->
	<link href="/policy/assets/css/admin_select.css" rel="stylesheet" />

	<!-- 分页 -->
	<link rel="stylesheet" type="text/css" href="/policy/assets/css/pagination.css">
	<script type="text/javascript" src="/policy/assets/js/jquery.pagination.js"></script>
    <script type="text/javascript" src="/policy/assets/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/policy/assets/js/jquery.validate.messages_zh.js"></script>
	<script type="text/javascript" src="/policy/assets/js/layer/layer.js"></script>

	<!-- 日期插件 -->
	<link rel="stylesheet" type="text/css" href="/policy/assets/css/jquery-ui-1.10.4.custom.css">
	<script type="text/javascript" src="/policy/assets/js/jquery.ui.core.js"></script>
	<script type="text/javascript" src="/policy/assets/js/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="/policy/assets/js/jquery.ui.datepicker.js"></script>
	<!-- 富文本编辑器 -->
    <link rel="stylesheet" type="text/css" href="/policy/assets/js/kindeditor/themes/default/default.css" >
    <link rel="stylesheet" type="text/css" href="/policy/assets/js/kindeditor/themes/simple/simple.css" >
    <script src="/policy/assets/js/kindeditor/kindeditor.js"></script>
    <script src="/policy/assets/js/kindeditor/lang/zh_CN.js"></script>
	<!-- 复选框、单选框 -->
	<script src="/policy/assets/js/check.js"></script>
	<link rel="stylesheet" href="/policy/assets/css/admin.css">
	<link rel="stylesheet" href="/policy/assets/css/houtaim.css">
	<link rel="stylesheet" href="/policy/assets/css/leeAdmin.css">
	<link rel="stylesheet" href="/policy/assets/css/wy.css">
	<link rel="stylesheet" href="/policy/assets/css/message_PJY.css"/>
	<link rel="stylesheet" href="/policy/assets/css/mscrollrest.css">
	<link rel="stylesheet" href="/policy/assets/css/zy.css">
	<title>政策发布</title>
	<style>
	.po_ly_li li{float:left; margin-right:70px;position:relative}
	.po_ly_li li label.error{position:absolute;right:-70px;text-align:left;width:60px}
 	.select2-results{ 
 		max-height: 200px; 
 		overflow:hidden; 
 	} 
	</style>
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
		<!-- sidebar over -->
		<!-- 右侧白色区域 -->
		<div class="admMmainR">
			<!-- 栏目名 -->
			<div class="adminTit govTit">政策发布</div>
			<div class="bgf">
				<ul class="po_ly_li clearfix">
					<li class="clearfix"><label for="title">政策标题：</label><input id="title" name="title" type="text" class="w350" value="<#if policy??><#if tag="chop">摘编:<#elseif tag!="edit">解读:</#if>${policy.title}</#if>"></li>
					<li class="clearfix"><label for="referenceNumber">&nbsp;&nbsp;&nbsp;&nbsp;文号：</label><input id="referenceNumber" name="referenceNumber" type="text" class="w350" value="<#if policy?? && policy.referenceNumber??>${policy.referenceNumber}</#if>"></li>
					<#include "policy-source.ftl">
					<input type="hidden" name="policyLevel" id="policyLevel" value="<#if policy??>${policy.policyLevel}<#else>-1</#if>" />
					<li class="clearfix">
						<label for="">政策类别：</label>
						<div class="adminSelect selW350">
                            <select name="category" id="category">
	                            <option <#if policy?? && policy.category?? && policy.category=="资金扶持">selected</#if>>资金扶持</option>
								<option <#if policy?? && policy.category?? && policy.category=="金融税收">selected</#if>>金融税收</option>
								<option <#if policy?? && policy.category?? && policy.category=="产业指导">selected</#if>>产业指导</option>
								<option <#if policy?? && policy.category?? && policy.category=="创业创新">selected</#if>>创业创新</option>
								<option <#if policy?? && policy.category?? && policy.category=="创业创新">selected</#if>>综合政策</option>
                            </select>
                        </div>
					</li>
					<li class="clearfix"><label for="gmtStart">起始日期：</label><input id="gmtStart" name="gmtStart" type="text" class="w350 datepicker" readonly value="<#if policy?? && policy.gmtStart??>${policy.gmtStart?date}</#if>"></li>
					<li class="clearfix">
						<label for="gmtEnd">截止日期：</label>
						<input id="gmtEnd" name="gmtEnd" type="text" class="w350 datepicker" readonly value="<#if policy?? && policy.gmtEnd??>${policy.gmtEnd?date}</#if>">
						<label for=""><input type="checkbox" class="fl" id="never-expire" <#if policy?? && policy.gmtEnd?? && policy.gmtEnd?string["yyyy"]=="2099">checked="true"</#if>> 长期</label>
					</li>
					<li class="clearfix">
						<label for="">扶持行业：</label>
						<div class="select2Box w350">
                            <select name="industry" id="industry" class="multiple-select">
                            	<option value="ALL" <#if policy?? && policy.industry=="ALL">selected</#if>>全行业</option>
                                <#list industries as industry>
                                <#if industry.code != "ALL">
                                <option value="${industry.code}" <#if policy?? && policy.industry=="${industry.code}">selected</#if>>${industry.name}</option>
                                </#if>
                                </#list>
                            </select>
                        </div>
					</li>
					<#include "policy-area.ftl">
					<li class="clearfix"><label for="fromSite">转载来源：</label><input id="fromSite" name="fromSite" type="text" class="w350" value="<#if policy??>${policy.fromSite}</#if>"></li>
					<li class="clearfix"><label for="fromLink">转载链接：</label><input id="fromLink" name="fromLink" type="text" class="w350" value="<#if policy??>${policy.fromLink}</#if>"></li>
					<li class="clearfix">
						<label for="logo">政策logo：</label>
						<div class="w350 fl pr">
							<div class="file_box file_Box_C">
								<label class="btn_border" for="logo">上传图片</label>
								<form enctype="multipart/form-data" id="form1">
									<input type="file" id="logo" name="file"/>
								</form>
							</div>
							<#if policy?? && policy.logo??>
							<div class="pic_rz_box fl" style="display:inline-block">
								<img id="logoimg" src="${policy.logo}" width="50" height="30" class="imgB">
								<a class="icon_close"></a>
							</div>
							</#if>
						</div>
					</li>
					<#if tag=="chop" || (tag=="edit"&&policy??&&policy.type=1)>
					<li class="clearfix"><label for="url">申报地址：</label><input id="url" name="url" type="text" class="w350" value="<#if policy??&&policy.url??>${policy.url}</#if>"></li>
					</#if>
					<li class="clearfix"><label for="supervisor">&nbsp;&nbsp;核批人：</label><input id="supervisor" name="supervisor" type="text" class="w350" value="<#if policy??>${policy.supervisor}</#if>"></li>
					<li class="clearfix">
						<label for="">关键字：</label>
						<input name="" type="text" class="w350 inpzi" value="">
						<a class="btn_border ml10 w70 addzi ly_bgW">+添加</a>
					</li>
					<li style="display: none">
						<dl id="content-keys">
						</dl>
					</li>
					<li class="clearfix"><label for="policyAbstract">&nbsp;&nbsp;摘要：</label>
						<inpu t type="textarea" name="policyAbstract" id="policyAbstract" class="editor" style="height: 180px">
					</li>
					<#if tag == "chop"|| (tag=="edit"&&policy??&&policy.type=1)>
					<li class="clearfix"><label for="field1">申报条件：</label>
						<input type="textarea" name="field1" id="field1" class="editor" style="height: 180px">
					</li>
					<li class="clearfix"><label for="field2">扶持重点：</label>
						<input type="textarea" name="field2" id="field2" class="editor" style="height: 180px">
					</li>
					<li class="clearfix"><label for="field3">支持方式及金额：</label>
						<input type="textarea" name="field3" id="field3" class="editor" style="height: 180px">
					</li>
					<li class="clearfix"><label for="field4">申报材料：</label>
						<input type="textarea" name="field4" id="field4" class="editor" style="height: 180px">
					</li>
					<#else>
					<li class="clearfix"><label for="source">&nbsp;&nbsp;正文：</label>
						<input type="textarea" name="content" id="content" class="editor" style="height: 290px">
					</li>
					</#if>
					<li class="clearfix"><label for="contact">联系方式：</label>
						<input type="textarea" name="contact" id="contact" class="editor" style="height: 100px">
					</li>
				</ul>
				
				<div class="fr mt20">
					<a href="javascript:publish(99);" class="btn_solid mr10">保存草稿</a>
	                <a href="javascript:publish(0);" class="btn_border">提交审批</a>
	                <!--
	                <a href="javascript:preview();" class="btn_border">预 览</a>
	                -->
	            </div>
			</div>

			<div class="clear"></div>

		</div>
		<div class="clearfix"></div>
	</div>


	<!-- maincontent over -->
	<!-- 底部footer -->
	<div class="adm_footer clear">
		<p>技术版权所有 © 天津卓朗科技发展有限公司 2013 TROILA.Inc. 保留一切权利。津ICP备10200312号-4</p>
	</div>
	<!--放大图片-->
	<div class="pic_pop" >
		<a class="picclose"></a>
		<img src="<#if policy??&&policy.logo??>${policy.logo}</#if>">
		<div class="picmarsk"></div>
	</div>
	<#if policy??>
	<div id="hidden-content" style="display:none">${policy.content}</div>
	</#if>
	<script>
		function getQueryString(name) {
		     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		     var r = window.location.search.substr(1).match(reg);
		     if(r!=null)return  unescape(r[2]); return null;
		}
		function trim(str){   
		    str = str.replace(/^(\s|\u00A0)+/,'');   
		    for(var i=str.length-1; i>=0; i--){   
		        if(/\S/.test(str.charAt(i))){   
		            str = str.substring(0, i+1);   
		            break;   
		        }   
		    }   
		    return str;   
		} 
		function imgSize(){
			//放大图片
			$(".w350").on("click",".imgB",function(){
				$(".pic_pop").show();
			})
			//关闭放大图片
			$(".picclose").click(function () {
				$(".pic_pop").hide();
			})
			//删除小图片
			$(".w350").on("click",".icon_close",function(){
				$(this).parent(".pic_rz_box").remove();
			})
			
		}
		$(document).ready(function () {
			//checkbox
			$('input:checkbox').iCheck({
				checkboxClass: 'icheckbox_square-orange',
			});
			<#if (tag=="edit") && policy?? && policy.gmtEnd?? && (policy.gmtEnd?string["yyyy"]=="2099")>
			$("input:checkbox").iCheck("check");
			</#if>
			var __PREVIOUS_GMTEND__ = null;
			$('input:checkbox').on("ifChecked", function(){
				var now = new Date();
				now.setFullYear(2099);
				__PREVIOUS_GMTEND__ = $("#gmtEnd").datepicker("getDate")
				$("#gmtEnd").datepicker("setDate", now).datepicker("option", "disabled", true); // set date
			});
			$('input:checkbox').on("ifUnchecked", function(){
				$("#gmtEnd").datepicker("setDate", __PREVIOUS_GMTEND__).datepicker("option", "disabled", false);
			});
			$("#logo").change(function(){
				$("#logoimg").remove();
				var isIE = !+"\v1"; 
				var contentType = isIE ? "text/html" : "json";
				$("#form1").ajaxSubmit({
                    success: function (data) {
                    	var res = isIE ? JSON.parse(data) : data;
                    	var imgBox='<div class="pic_rz_box fl" style="display:inline-block">'
							+'<img id="logoimg" src="'+res.url+'" width="50" height="30" class="imgB">'
							+'<a class="icon_close"></a>'
							+'</div>'
						$("#logo").parents(".w350").append(imgBox);
                    	$(".pic_pop img").attr("src", res.url);
                    },
                    error: function (error) { 
                    	console.log(error)
                    	if(error.status == 413) {
                    		layer.msg("LOGO超过1M上传失败",{icon:2});
                    	} else {
                    		layer.msg("LOGO上传失败，请联系管理员",{icon:2});
                    	}
                    },
                    url: "/policy/attach/logo",
                    type: "post",
                    dataType: contentType,
                    resetForm:true
                });
			})
			imgSize();
			//下拉
    		$('.adminSelect select').selectOrDie();
			$(".select2Box select").select2({
				minimumResultsForSearch: Infinity,
				multiple: true,
				tokenSeparators: [',', ' ']
			});
			$(".datepicker").datepicker({changeYear:true, changeMonth:true, yearRange: "1990:2099"});
			<#if tag=="edit"&&policy??&&policy.keywords??>
			var keywords = "${policy.keywords}".split(",");
			for(var i = 0; i < keywords.length; i++) {
				addKeyword(keywords[i]);
			}
			</#if>
			function addKeyword(key) {
				if(key =="") return;
				$(".po_ly_li li dd").each(function(index, el) {
					var ddText = trim($(this).text());
					if (key==ddText) {
						alert("此关键词已有，请重新输入");
						$(".inpzi").val("");
						return key="";
					};
				});
				if ($(".po_ly_li li dd").length>4) {
						alert("最多设置5个关健词");
						$(".inpzi").val("");
						return key="";
					};
				if (key!="") {
					$(".po_ly_li li dl").append('<dd>'+key+'<i></i></dd>');
					$(".po_ly_li li dl").parent().show();
					$(".inpzi").val("");
				};
				$(".po_ly_li li dl dd i").click(function () {
					var li = $(this).parent().parent().parent();
					$(this).parent().remove();
					if(li.find("dd").length == 0) {
						li.hide();
					}
                })
			}
			$(".addzi").click(function() {
				var keyword = trim($(".inpzi").val());
				addKeyword(keyword);
			});
			
			//富文本编辑器,items为显示的工具图标
	        KindEditor.ready(function(K) {
	        	window.editor = {};
	        	$(".editor").each(function(i, ele) {
	        		var $editor = $(this);
	        		var height = $editor.css("height");
	        		var id = $editor.attr("id");
	        		window.editor[id] = K.create("#" + id,{
		                width:"970px",
		                height: height,
		                items : [
		                    'preview', 'wordpaste','|', 'justifyleft', 'justifycenter', 'justifyright',
		                    'justifyfull','|','formatblock','fontname','fontsize','|','forecolor','hilitecolor','bold','italic','underline','|','insertfile','table','hr','link','unlink','|',
		                    'fullscreen','image'
		                ],
		                uploadJson : '/policy/attach/kindeditor'
		            })
	        	});
	        });

			//分页
			$(".pagination").pagination(100,{
				items_per_page:4,
				num_display_entries:4,
				current_page:0,
				num_edge_entries:1,

			});
			//表格隔行变色；
			$(".tableWrapper_zy table tr:even").addClass("tr_even");
			$(".tableWrapper_zy table tr:first").removeClass("tr_even");
			
			//表格验证规则 --ben
            $("form").validate({
	    		rules: {
	      			title: "required",
	      			content: "required",
					source: "required"
				}
			});
		})
		
		function xssValid() {
			var htmlRegex = /<[^>]*>|<\/[^>]*>/igm;
			var scriptRegex = /(<|&lt;)script(>|&gt;)/igm;
			var ok = true;
			$("input").each(function(){
				if(htmlRegex.test($(this).val())) {
					$(this).addClass("error");
					ok = false;
					return;
				}
			}); 
			for(var i in window.editor) {
				var html = window.editor[i].html();	
				if(scriptRegex.test(html)) {
					ok = false;
					break;
				}
			}
			return ok;
		}
		
		function getEntity() {
			if(!$("form").valid()) {
				throw "invalid";
			}
			if(!xssValid()) {
				layer.msg("您输入的参数含有非法字符!",{icon:2});
				throw "invalid";
			}
			var entity = {};
			entity.title = $.trim($("#title").val());
			entity.source = getSourceArray().join(",");
			if(entity.source == "") {
				layer.msg("请选择发文部门...", {icon:3, time: 2000});
				throw "invalid";
			}
			entity.type = <#if tag=="chop">1<#elseif tag=="edit">${policy.type}<#else><#if policy??>2<#else>0</#if></#if>;
			entity.referenceNumber = $.trim($("#referenceNumber").val());
			entity.industry = $("#industry").val().join();
			entity.area = $("#area").val().join();
			<#if tag=="edit">
			entity.id = ${policy.id};
			entity.parentId = ${policy.parentId}
			<#else>
			entity.parentId = <#if policy??>${policy.id}<#else>-1</#if>
			</#if>
			entity.policyAbstract = window.editor["policyAbstract"].html();
			entity.contact = window.editor["contact"].html();
			entity.supervisor = $("#supervisor").val();
			entity.policyLevel = $("#policyLevel").val();
			entity.fromSite = $.trim($("#fromSite").val());
			entity.fromLink = $.trim($("#fromLink").val());
			<#if tag=="edit">
			var date = new Date();
			entity.gmtCreate = Date.parse("${policy.gmtCreate}");
			</#if>
			var date = $("#gmtStart").datepicker("getDate");
			if(!date) {
				layer.msg("起始日期为必填项", {icon:3, time: 2000});
				throw "invalid";
			}
			entity.gmtStart = date.getTime();
			date = $("#gmtEnd").datepicker("getDate");
			if(!date) {
				layer.msg("截止日期为必填项", {icon:3, time: 2000});
				throw "invalid";
			}
			entity.gmtEnd = date.getTime();
			if(entity.gmtEnd && entity.gmtStart && (entity.gmtEnd <= entity.gmtStart)) {
				layer.msg("截止日期不应早于起始日期...", {icon:3, time: 2000});
				throw "invalid";
			}
			entity.category = $("#category option:selected").text();
			entity.logo = $("#logoimg").attr("src");
			<#if tag=="chop" || (tag=="edit"&&policy??&&policy.type=1)>
			entity.url = $.trim($("#url").val());
			if(entity.url != "" && !checkUrl(entity.url)) {
				layer.msg("请填写正确的报名地址, 地址以http/https开始", {icon:3, time: 2000});
				throw "invalid url";
			}
			var field0 = "<h3 class='articleTit'>摘要</h3><div class='articleTxt'>" + window.editor["policyAbstract"].html()+"</div>";
			var field1 = "<h3 class='articleTit'>申报条件</h3><div class='articleTxt'>" + window.editor["field1"].html()+"</div>";
			var field2 = "<h3 class='articleTit'>扶持重点</h3><div class='articleTxt'>" + window.editor["field2"].html()+"</div>";
			var field3 = "<h3 class='articleTit'>支持方式及金额</h3><div class='articleTxt'>" + window.editor["field3"].html()+"</div>";
			var field4 = "<h3 class='articleTit'>申报材料</h3><div class='articleTxt'>" + window.editor["field4"].html()+"</div>";
			var field5 = "<h3 class='articleTit'>联系人及联系方式</h3><div class='articleTxt'>" + window.editor["contact"].html()+"</div>";
			entity.content = " <div class='articleList'>"+ field0 + field1 + field2 + field3 + field4 + field5+'</div>'
			<#else>
			var field0 = "<h3 class='articleTit'>摘要</h3><div class='articleTxt'>" + window.editor["policyAbstract"].html()+"</div>";
			var field1 = "<h3 class='articleTit'>正文</h3><div class='articleTxt'>" + window.editor["content"].html()+"</div>";
			var field2 = "<h3 class='articleTit'>联系人及联系方式</h3><div class='articleTxt'>" + window.editor["contact"].html()+"</div>";
			entity.content = " <div class='articleList'>"+ field0 + field1 + field2 +'</div>'
			</#if>
			var keys = getKeywords();
			if(keys.length>0) {
				entity.keywords = keys.join(",");
				entity.content = processContent(keys, entity.content);
			}
			return entity;
		}
		
		function checkUrl(url){
			if(url!=""){
				var reg=/(http|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?/;
				return reg.test(url);
		    }
		    return false;
		}
		function preview() {
			var entity = getEntity();
			$.ajax({
					url: "/policy/preview?uid=${user.userId}",
					type: "post",
					data: JSON.stringify(entity),
					contentType: "application/json",
					success: function(result){
						layer.open({
							title: "政策发布预览",
							area: ['90%','90%'],
							content: result
						});
					},
					error: function(){}
				});
		}
		function publish(status) {
			var entity = getEntity();
			entity.status = status;
			var msg = status == 99 ? "保存草稿" : "发布政策";
			var box = layer.msg(msg, {icon:1, time: 0});
			$.ajax({
				url: "/v2/policy?uid=${user.userId}",
				type: "post",
				data: JSON.stringify(entity),
				contentType: "application/json",
				success: function(result){
					if(result.code == 200) {
						layer.close(box);
						var msg = status == 99 ? "保存草稿成功" : "政策发布成功";
						layer.msg(msg, {icon:1, time: 2000});
						setTimeout(function(){
							<#if from??>
								<#if from==1>
								location = "/policy/list?uid=${user.userId}&from=${from}&status="+status;
								<#else>
								location = "/policy/list?uid=${user.userId}&filter=true&from=${from}&status="+status;
								</#if>
							<#else>
								location = document.referrer.replace("status=0","status="+status);
							</#if>
						}, 1000);
					}
				},
				error: function(){}
			});
		}
		
		function getKeywords() {
			var keys = [];
			$("#content-keys dd").each(function(i, ele){
				keys.push($(ele).text());
			});
			return keys;
		}
		
		function processContent(keys, content) {
			// 只对标签为中文或英文长度好过5的标签进行替换
			for(var i = 0; i < keys.length; i++) {
				if(escape(keys[i]).indexOf("%u") !=-1||keys[i].length>5) {
					var re = new RegExp(keys[i],"gim")
					content = content.replace(re, "<i style='background-color:yellow'>" + keys[i] + "</i>")
				}
			}
			return content;
		}
		<#if policy??>
		$(function(){
			var $select2 = $("#source").select2();//
			$("#area").select2().val("${policy.area}".split(",")).trigger("change");
			$select2.data('preserved-order', "${policy.source}".split(","));
			$select2.val("${policy.source}".split(",")).trigger("change");
			//select2_renderSelections($("#source"));
			$("#industry").select2().val("${policy.industry}".split(",")).trigger("change");
			<#if tag=="edit">
			var content = $("#hidden-content").html();
			var dom = $.parseHTML(content)
			var pieces = $(dom).children("div.articleTxt");
			KindEditor.ready(function(){
				<#if policy.type=1>
				window.editor["policyAbstract"].html(pieces[0].innerHTML);
				window.editor["field1"].html(pieces[1].innerHTML);
				window.editor["field2"].html(pieces[2].innerHTML);
				window.editor["field3"].html(pieces[3].innerHTML);
				window.editor["field4"].html(pieces[4].innerHTML);
				window.editor["contact"].html(pieces[5].innerHTML);
				<#else>
				window.editor["policyAbstract"].html(pieces[0].innerHTML);
				window.editor["content"].html(pieces[1].innerHTML);
				window.editor["contact"].html(pieces[2].innerHTML);
				</#if>
			})
			</#if>
		});
		</#if>
	function getSourceArray() {
		var values=[];
        var $valuelist =  $(".select2Box:first ").find(".select2-selection__choice:visible");
        $valuelist.each(function (index,item) {
            values[index]=$(item).attr("title").replace(/-/g,"")
        });
        return values;
	}	
	function select2_renderSelections(obj) {
        var  order = obj.data('preserved-order') || [];
        var $container = obj.next('.select2-container');
        var $tags = $container.find('li.select2-selection__choice');
		$tags.each(function(i, t){
			var found = false;
			for(var i=0;i<order.length;i++){
				if($(t).data("data").id===order[i]) {
					found = true;
					break;
				}
			}
			if(!found)$(t).remove();
		});
		var $tags = $container.find('li.select2-selection__choice');
        var $input= $tags.last().next();
        for(var i=0;i<order.length;i++){
            var val =order[i];
            var $el = $tags.filter(function(i,tag){
             	return $(tag).data('data').id === val;
             });
          	$input.before($el);
        }
    }
	function selectionHandler(e) {
        var $this = $(this);
        var order =  $this.data('preserved-order') || [];
        switch (e.type){
            case 'select2:select':
            	var val= e.params.data.id;
		        var newLevel = $(e.params.data.element).attr("level");
		    	var oldLevel = $("#policyLevel").val();
	          	if(oldLevel!=newLevel&&oldLevel!=-1) {
	          		layer.msg("政策发文部门必须为同一级别",{icon:2});
	          	} else {
		          	$("#policyLevel").val(newLevel);
		          	order.push(val);
			    }
                break;
            case 'select2:unselect':
            	var val= e.params.data.id;
                var found_index = order.indexOf(val);
                if (found_index >= 0 ){
                    order.splice(found_index,1);
                };
                if(order.length==0) {
                	$("#policyLevel").val("-1");
                }
                break;
        }
        $this.data('preserved-order', order);
        select2_renderSelections($this);
    }
	$(function() {
		var $selecttag =  $(".selecttag").select2();
	    $(".selecttag").on('select2:open', function (evt) {//加滚动条
	        $(".select2-results ul.select2-results__options").unbind("mousewheel");
	        $('.select2-results').mCustomScrollbar();
	    });
	    select2_renderSelections($selecttag)
	    //点击其他地方关闭下拉；
	    $(document).on("mouseup",function (event) {
	        var tcaname = event.target.className;
	        if(tcaname.indexOf("select2")===-1){
	           $(".selecttag").select2("close")
	        }
	    });
	    //重排序开始
	    
	    
	    $selecttag.on('select2:select select2:unselect', selectionHandler);

//	    ie8兼容indexof();
	    if (!Array.prototype.indexOf)
	    {
	        Array.prototype.indexOf = function(elt /*, from*/)
	        {
	            var len = this.length >>> 0;
	            var from = Number(arguments[1]) || 0;
	            from = (from < 0)
	                ? Math.ceil(from)
	                : Math.floor(from);
	            if (from < 0)
	                from += len;
	            for (; from < len; from++)
	            {
	                if (from in this &&
	                    this[from] === elt)
	                    return from;
	            }
	            return -1;
	        };
	    }
	});
	</script>
</body>
</html>