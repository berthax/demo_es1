<#-- @ftlvariable name="" type="com.troila.sme.service.policy.view.SmeView" -->
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link rel="stylesheet" href="/policy/assets/new/css/base.css">
	<link rel="stylesheet" href="/policy/assets/new/css/layout.css">
	<link rel="stylesheet" href="/policy/assets/new/css/style.css"/>
	<link rel="stylesheet" type="text/css" href="/policy/assets/new/css/pagination.css">
	<style>
		.highChange_PJY .parentList{display:block}
	</style>
</head>
<body>
	<!-- content -->
	<div class="content clearfix">
		<!-- left -->
		<div class="content_l">
			<!-- 高级筛选开始-->
			<div class="highChange_PJY">
				<div>
					<div class="searchBox_PJY w360">
						<input type="text" id="search-box"/>
						<span>请输入关键词</span>
						<i></i>
					</div>
					<a href="javascript:;" class="serBtn_PJY onClick_PJY">高级筛选</a>
				</div>
				<ul class="parentList">
					<li class="filterList_PJY">
						<label for="">筛选条件：</label>
						<div class="listSty_PJY">
							<ul >
							</ul>
							<a href="javascript:;" class="clearAll_PJY">清除筛选</a>
							<div class="clear"></div>
						</div>
						<div class="clear"></div>
					</li>
					<li class="level_PJY">
						<label for="">政策类别：</label>
						<ul>
							<li class="all_PJY">全部</li>
							<li type="category" op="eq">资金扶持</li>
							<li type="category" op="eq">金融税收</li>
							<li type="category" op="eq">产业指导</li>
							<li type="category" op="eq">创业创新</li>
						</ul>
						<div class="clear"></div>
					</li>
					<li class="depart_PJY">
						<label for="">发文部门：</label>
						<ul>
							<li class="all_PJY">全部</li>
							<li>国家级</li>
							<li>天津市</li>
							<li>区级</li>
						</ul>
						<div class="clear"></div>
					</li>
					<li class="departList_PJY">
						<div></div>
						<div style="overflow:hidden">
							<label for="" type="policyLevel" op="eq" value="0">国家级　</label>
							<ul >
								<li type="source" op="like">国务院办公厅</li>
								<li type="source" op="like">工信部</li>
								<li type="source" op="like">发改委</li>
								<li type="source" op="like">科技部</li>
								<li type="source" op="like">财政部</li>
								<li type="source" op="like">人力社保部</li>
								
								
								<li type="source" op="like">住建部</li>
								<li type="source" op="like">商务部</li>
								<li type="source" op="like">中国人民银行</li>
								<li type="source" op="like">海关总署</li>
								<li type="source" op="like">税务总局</li>
								<li type="source" op="like">工商总局</li>
								<li type="source" op="like">质检总局</li>
								<li type="source" op="like">安监局</li>
								<li type="source" op="like">食品药监局</li>
								<li type="source" op="like">统计局</li>
								<li type="source" op="like">知识产权局</li>
								<li type="source" op="like">发展研究中心</li>
								<li type="source" op="like">中国银监会</li>
								<li type="source" op="like">中国证监会</li>
							</ul>
							<div class="clear"></div>
						</div>
						<div style="overflow:hidden">
							<label for="" type="policyLevel" op="eq" value="1">天津市　</label>
							<ul>
								<li type="source" op="like">市委宣传部</li>
					            <li type="source" op="like">市委统战部</li>
					            <li type="source" op="like"">市发展改革委</li>
					            <li type="source" op="like">市工业和信息化委</li>
					            <li type="source" op="like">市商务委</li>
					            <li type="source" op="like">市科委</li>
					            <li type="source" op="like">市建委</li>
					            <li type="source" op="like">市农委</li>
					            <li type="source" op="like">市教委</li>
					            <li type="source" op="like">市金融局</li>
					            <li type="source" op="like">市司法局</li>
					            <li type="source" op="like">市财政局(地税局)</li>
					            <li type="source" op="like">市人力社保局</li>
					            <li type="source" op="like">市规划局</li>
					            <li type="source" op="like">市国土房管局</li>
					            <li type="source" op="like">市市场监督委</li>
					            <li type="source" op="like">市统计局</li>
					            <li type="source" op="like">市知识产权局</li>
					            <li type="source" op="like">市中小企业局</li>
					            <li type="source" op="like">市国税局</li>
					            <li type="source" op="like">市卫生计生委</li>
					            <li type="source" op="like">市工商联</li>
					            <li type="source" op="like">市科协</li>
					            <li type="source" op="like">市政府办公厅</li>
							</ul>
							<div class="clear"></div>
						</div>
						<div style="overflow:hidden">
							<label for="" type="policyLevel" op="eq" value="2">区级　</label>
							<ul>
								<li type="source" op="like">和平区</li>
								<li type="source" op="like">河东区</li>
								<li type="source" op="like">河西区</li>
								<li type="source" op="like">南开区</li>
								<li type="source" op="like">河北区</li>
								<li type="source" op="like">红桥区</li>
								<li type="source" op="like">东丽区</li>
								<li type="source" op="like">西青区</li>
								<li type="source" op="like">津南区</li>
								<li type="source" op="like">北辰区</li>
								<li type="source" op="like">武清区</li>
								<li type="source" op="like">宝坻区</li>
								<li type="source" op="like">滨海新区</li>
								<li type="source" op="like">宁河区</li>
								<li type="source" op="like">静海区</li>
								<li type="source" op="like">蓟州区</li>
							</ul>
							<div class="clear"></div>
						</div>
					</li>
					<li class="status_PJY">
						<label for="">申报状态：</label>
						<ul>
							<li class="all_PJY">全部</li>
							<li op="time_btw">正在申报</li>
						</ul>
						<div class="clear"></div>
					</li>
				</ul>

			</div>
			<!-- 高级筛选结束-->
			<ul class="nav-new">
				<!-- <li><a <#if (currentPolicyType==1)>class="nav-cur-new"</#if> href="/policy/index?type=1&uid=${user.userId}">政策摘编</a></li> -->
				<li><a <#if (currentPolicyType==0)>class="nav-cur-new"</#if> href="/policy/index?type=0&uid=${user.userId}">政策原文</a></li>
				<li><a <#if (currentPolicyType==2)>class="nav-cur-new"</#if> href="/policy/index?type=2&uid=${user.userId}">政策解读</a></li>
			</ul>
			<!--新闻列表开始 -->
			<div class="ChangeList_PJY">
				<!-- 列表开始-->
				<div class="object_PJY">
					<div >
						<ul class="indexList_PJY top_policy"></ul>
						<ul class="indexList_PJY policy_list">
							<#assign now = .now>
							<#assign day_in_millis = 24*60*60*1000>
							<#list policyList.elements as p>
							<#if p.gmtStart?? && p.gmtEnd??>
							<#assign expired = !((now > p.gmtStart) && (now < p.gmtEnd))>
							<#assign dayspan = ((now?long-p.gmtModify?long)/day_in_millis)?int>
							<#else>
							</#if>
							<li>
								<!-- freemarker取数字，多了会中间多逗号，用?c可以去掉逗号，2019-1-11宣修改 -->
								<a href="/policy?uid=${user.userId}&aid=${p.id?c}" title="${p.title}" target="_self">
									<div class="picWin_PJY"><img src="${p.logo}" alt=""/></div>
									<dl>
										<dt>
											<span class="title1_PJY">【<span>${p.category!"无分类"}</span>】</span>
											<span class="title2_PJY">${p.title}</span>
											<#if (dayspan<=3)>
											<img src="/policy/assets/images/isNew.gif" />
											</#if>
											<#if p.type==1 && p.gmtStart?? && p.gmtEnd??>
		                                        <#if (now<p.gmtStart)>
		                                        <b class="title4_PJY">未开始</b>
		                                        <#elseif (now>p.gmtEnd)>
		                                        <b class="title4_PJY">结束</b>
		                                        <#else>
		                                        <b class="title3_PJY">申报中</b>
		                                        </#if>
	                                        </#if>
										</dt>
										<dd class="textCon_PJY" title="${p.policyAbstract}"><#if (p.policyAbstract?length>100)>${p.policyAbstract[0..100]}...<#else>${p.policyAbstract}</#if></dd>
										<dd>
										
											<span>${p.gmtModify?date}</span>
											<span>【<span>${p.source}</span>】</span>
											<#if p.referenceNumber??&&p.referenceNumber!=""><span style="float:left">${p.referenceNumber}&nbsp;&nbsp;&nbsp;&nbsp;</span></#if>
										<#if p.type==1 && p.gmtStart?? && p.gmtEnd??>
											<#if !expired>
											<#assign dayspan=(((p.gmtEnd?long-now?long)/(1000*60*60*24)+1)?int)>
												<#if (p.gmtEnd?string["yyyy"] == "2099")>
												<p><span>长期有效</span></p>
												<#elseif (dayspan > 60)>
												<p>距申报结束还有 <span>${(dayspan/30)?int}</span> 个月</p>
												<#else>
												<p>距申报结束还有 <span>${dayspan}</span> 天</p>
												</#if>
											</#if>
										</#if>
											<div class="clear"></div>
										</dd>
									</dl>
								</a>
							</li>
							</#list>
						</ul>
						<div class="page_box mt20">
							<div class="pagination"></div>
							<span class="totalpage">共${policyList.totalPage}页</span>
						</div>
					</div>
				</div>
				<!-- 列表结束-->
			</div>
			<!--新闻列表结束 -->
		</div>
		<div class="content_r"></div>
		<!-- left end -->
		<!-- right -->
		<div class="content_r">			
			<#include "wechat-qrcode.ftl">
			<div class="right_box">
				<div class="right_box_tit clearfix">
					<b>热门政策</b>
				</div>
				<div class="right_box_con">
					<ul id="hot-policy">	<!--最多显示10条-->
					</ul>
				</div>
			</div>
		</div>
		<!-- right end -->
	</div>
	<!-- content end -->
	<!-- footer -->
	<#include "footer.ftl">
	<!-- footer end -->
	<!-- 筛选项模板-->
	<ul class="hide cloneM_PJY">
		<li>
			<div class="filterBlock_PJY">
				<p></p>
				<i></i>
			</div>
		</li>
	</ul>
<script src="/policy/assets/new/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/policy/assets/new/js/jquery.pagination.js"></script>
<#include "hot-policy.ftl">
<script>
var DAY_IN_MILLIS = 24 * 60 * 60 * 1000;
function trimHtmlTags(s) {
    var reTag = /<(?:.|\s)*?>/g;
    return s.replace(reTag,"");
}
$(document).ready(function(){
    //分页
    $(".pagination").each(function(){
        $(this).pagination(${policyList.total},{
            items_per_page:${policyList.size},
            num_display_entries:4,
            current_page:${policyList.currentPage},
            num_edge_entries:1,
            link_to:"/policy/index?uid=${user.userId}&page=__id__&size=${policyList.size}&type=${tag}",
			callback: null
        })
    });
    
    function highlight(policy, key) {
    	if(key !== "") {
    		var regex = new RegExp(key, "gm");
	    	var sub = "<span style='color:red'>" + key + "</span>";
	    	policy.title = policy.title.replace(regex, sub);
    	}
    	return policy;
    }
    
    function search(pageNumber) {
    	pageNumber = pageNumber||0;
    	var key = $.trim($("#search-box").val());
		var filters = [];
		$(".filter").each(function(i, e) {
			var op = $(e).attr("op");
			var type = $(e).attr("type");
			var val = $(e).val();
			var text = val!="" ? val : $.trim($(e).text());
			var filter = "filter[]=" + op + ":" + type + ":" + text;
			if(op === "time_btw") {// 正在申报的资金扶持类的政策
				filter = "filter[]=eq:type:1&filter[]=time_btw:gmtStart:gmtEnd";
			}
			filters.push(filter);
		});
		var date = new Date();
		var now = date.getTime();
		var url = "/v2/policy/search?uid=${user.userId}&key=" + key + "&filter[]=eq:status:1&" + filters.join("&") + "&page="+pageNumber+"&t="+now;
		url = encodeURI(url);
		$.getJSON(url, function(res){
			$(".policy_list").children().remove();
			for(var i = 0; i < res.data.length; i++) {
				var policy = res.data[i];
				var policy = highlight(policy, key);
				var li = composePolicy(policy)
				$(".policy_list").append(li);
			}
			//composePolicy(res)
			title();
			if(res.data.length>0) {
				$(".totalpage").text("共" + res.pagination.totalPageCount + "页");
				$(".pagination").each(function(){
			        $(this).pagination(res.pagination.totalElementCount,{
			            items_per_page:res.pagination.pageSize,
			            num_display_entries:4,
			            current_page:res.pagination.currentPage,
			            num_edge_entries:1,
			            //link_to:"/v2/policy/search?uid=${user.userId}&key=" + key + "&page=__id__&" + filters.join("&"),
						callback: search
			        });
				});	
				$(".page_box").show();
			} else {
				$(".page_box").hide();
			}
		});
    }
    $(".searchBox_PJY i").click(function(){search(0);});
    //搜索框方法开始
    $(".searchBox_PJY input").on("input propertychange",function(){
        var html=$(this).val();
        if(html!=""){
            $(this).next().hide();
        }else{
            $(this).next().show();
        }
    }).on("focus",function(){
        $(this).next().hide();
    }).on("blur",function(){
        var html=$(this).val();
        if(html!=""){
            $(this).next().hide();
        }else{
            $(this).next().show();
        }
    }).on("keyup", function(e){
    	if(e.which = 13) {
    		search(0);
    	}
    });
    $(".searchBox_PJY span").on("click",function(){
        $(this).prev().trigger("focus");
    });
    //搜索框方法结束
    //高级筛选按钮
    $(".serBtn_PJY").on("click",function(){
        $(this).toggleClass("onClick_PJY");
        $(this).parent().next().stop().slideToggle(200);
    });
    //点击清除筛选项
    $("body").on("click",".filterBlock_PJY i",function(){
        var n1=$(this).parents(".listSty_PJY ul").find("li").length;
        var index=$(this).prev().html();
        $(this).parents(".filterList_PJY").siblings().find("li").each(function(){
        	if($(this).html()==index){
        		$(this).removeClass("on_PJY")
        	}
        })
        if(n1-1==0){
            $(this).parents(".listSty_PJY").find(".clearAll_PJY").hide()
        }
        $(this).parent().parent().remove();
        search();
    }).on("click",".clearAll_PJY",function(){
        $(this).hide();
        $(".all_PJY").each(function(){
            $(this).trigger("click")
        })
        search();
    });
    //点击筛选单位
    var btn2=1;
    $("body").on("click",".highChange_PJY>ul>li:not('.filterList_PJY'):not('.depart_PJY') li:not('.all_PJY'),label",function(){
        $(this).addClass("on_PJY").siblings().removeClass("on_PJY");
        var button1=1;
        var html2=$(this).html();
        if($(".listSty_PJY ul li").length){
            $(".listSty_PJY ul li").each(function(){
                if($(this).find("p").html()==html2){
                    button1=2;
                    var first=$(".listSty_PJY ul li:first-child");
                    first.before(this)
                }
            })
        }
        if(button1!=2){
            $(this).addClass("on_PJY").siblings().removeClass("on_PJY");
            var html1=$(this).html();
            var type=$(this).attr("type");
            var op=$(this).attr("op");
            var value=$(this).attr("value")||""; 
            var clone=$(".cloneM_PJY>li").clone();
            if($(this).parents(".level_PJY").length){
                clone.addClass("dd1")
            }else if($(this).parents(".departList_PJY").length){
                clone.addClass("dd2")
            }else{
                clone.addClass("dd3")
            }
            $(".filterList_PJY .listSty_PJY>ul").prepend(clone);
            $(".filterList_PJY .listSty_PJY>ul>li:first-child").find("p").html(html1);
            $(".filterList_PJY .listSty_PJY>ul>li:first-child").attr("type",type).attr("op", op).attr("value",value).addClass("filter")
            $(".clearAll_PJY").show()

        }
        search();
    }).on("click",".depart_PJY li:not('.all_PJY')",function(){
        var startN=$(this).parent().find(".on_PJY:not('.all_PJY')").length;
        if(startN!=0){
             if($(this).parent().find(".on_PJY:not('.all_PJY')").html()==$(this).html()){
                 var css=$(".departList_PJY").css("display");
                 if(css=="none"){
                     $(".departList_PJY").show(100)
                 }else{
                     $(".departList_PJY div").hide(100);
                     $(".departList_PJY").hide(function(){
                         $(".depart_PJY li").removeClass("on_PJY")
                     });
                 }
             }
        }else{
            $(".departList_PJY").show(100)
        }
        $(this).addClass("on_PJY").siblings().removeClass("on_PJY");
        var index2=$(this).index();
        $(".departList_PJY>div").hide();
        $($(".departList_PJY>div").get(index2)).show();
    });
    //切签
    $(".headerL_PJY").find("li:last-child").css({marginRight:0});
    $(".headerL_PJY li").on("click",function(){
        $(this).addClass("on2_PJY").siblings().removeClass("on2_PJY");
        var index=$(this).index();
        $($(this).parent().next().children("div").get(index)).removeClass("hide").siblings().addClass("hide");
        title();
    });
    //列表title 宽度省略号方法
    title();
    //全部按钮功能
    $("body").on("click",".all_PJY",function(){
        $(this).addClass("on_PJY").siblings().removeClass('on_PJY');
        if($(this).parents('.level_PJY').length){
            $(".listSty_PJY>ul>li.dd1").remove();
        }else if($(this).parents('.depart_PJY').length){
            $(".departList_PJY").hide();
            $(".listSty_PJY>ul>li.dd2").remove();
        }else{
            $(".listSty_PJY>ul>li.dd3").remove();
        }
        num()
    });
    //初始化删选按钮样式
    num();
    
    $.getJSON("/v2/policy/top",function(res){
    	for(var i = 0; i < res.data.length; i++) {
			var policy = res.data[i];
			var li = composePolicy(policy);
			li = li.replace(">"+policy.category+"<",">置顶<");
			$(".top_policy").append(li);
		}
    });
});
function composePolicy(policy) {
	var date = new Date();
	var now = date.getTime();
	var li = [];
	li.push('<li>');
	li.push('<a href="/policy?uid=${user.userId}&aid='+policy.id+'" title="'+policy.title+'">');
	li.push('<div class="picWin_PJY"><img src="' + policy.logo + '" alt=""/></div>');
	li.push('<dl>');
	li.push('<dt>');
	li.push('<span class="title1_PJY">【<span>' + policy.category + '</span>】</span>');
	li.push('<span class="title2_PJY">' + policy.title + '</span>');
	var timespan = now - policy.gmtModify||policy.publishDate;
	var dayspan = timespan / DAY_IN_MILLIS;
	if(dayspan <= 3) {
		li.push('<img src="/policy/assets/images/isNew.gif" />');
	}
	if(policy.type == 1) {//摘编
		if(now<policy.gmtStart) {
			li.push('<b class="title4_PJY">尚未开始</b>');
		} else if(now>policy.gmtEnd) { 
			li.push('<b class="title4_PJY">已结束</b>');
		} else {
            li.push('<b class="title3_PJY">申报中</b>');
        }
    }
    var original = trimHtmlTags(policy.policyAbstract);
    var abstract = original;
    //var abstract = policy.policyAbstract;
    if(abstract && abstract.length > 100) {
    	abstract = abstract.substring(0, 100) + "...";
    }else{
    	abstract = "";
    }
	li.push('</dt>');
	li.push('<dd class="textCon_PJY" title="'+original+'">' + abstract + '</dd>');
	li.push('<dd>');
	if(policy.type==1) {
		if(policy.expired) {
			date.setTime(policy.gmtEnd);
			li.push('<p>截止日期 <span>' + date.toLocaleDateString() + '</span></p>');
		} else {
			var now = new Date();
			if(now < policy.gmtEnd) {
				date.setTime(policy.gmtEnd);
				if(date.getFullYear() == 2099) {
            		li.push('<p><span>长期有效</span></p>');
				} else {
					var span = date.getTime() - now.getTime();
					var dayspan = Math.floor((span / DAY_IN_MILLIS) + 1);
					var monthspan = Math.floor(dayspan / 30);
					if(dayspan < 60) {
						li.push('<p>距申报结束还有距离<span>'+dayspan+'</span> 天</p>');
					} else if(monthspan<12){
						li.push('<p>距申报结束还有距离<span>'+monthspan+'</span> 个月</p>');
					} else {
						li.push('<p>报名截止日期:'+date.toLocaleDateString()+'</p>');
					}
				}
			}
        }
    }
    date.setTime(policy.publishDate);
	li.push('<span>' + date.toLocaleDateString() + '</span>');
	li.push('<span>【<span>' + policy.source + '</span>】</span>');
	if(policy.referenceNumber&&policy.referenceNumber!="") {
		li.push('<span>' + policy.referenceNumber + '&nbsp;&nbsp;&nbsp;&nbsp;</span>');
	}
	li.push('<div class="clear"></div>');
	li.push('</dd>');
	li.push('</dl>');
	li.push('</a>');
	li.push('</li>');
	return li.join("");
}
//每次页面刷新或调用ajax刷新列表请调用title方法确定title宽度样式
function title(){
    $(".indexList_PJY").find("dt").each(function(){
        var w1=$(this).width();
        var w2=$(this).find(".title1_PJY").width();
        $(this).find(".title2_PJY").css({maxWidth:w1-w2-15})
    })
}
function num(){
    if($(".listSty_PJY ul li").length==0){
        $(".clearAll_PJY").hide()
    }

}
</script>
</body>
</html>
