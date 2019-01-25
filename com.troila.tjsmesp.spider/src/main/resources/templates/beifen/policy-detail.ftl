	<#-- @ftlvariable name="" type="com.troila.sme.service.policy.view.SmeView" -->
	<!-- 解决freemarker数字位数多时，中间加逗号的问题 -->
	<#setting number_format="#">
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link rel="stylesheet" href="/policy/assets/new/css/sideCatalog.css">
    <link rel="stylesheet" href="/policy/assets/new/css/base.css">
    <link rel="stylesheet" href="/policy/assets/new/css/layout.css">
    <link rel="stylesheet" href="/policy/assets/new/css/style.css">
    <link rel="stylesheet" href="/policy/assets/new/css/msword.css">
    <style>
    	ul.compInfo li{padding:15px 0}
    </style>
</head>
<body>
	<#assign now = .now>
	<!-- content -->
	<div class="content clearfix">
		<!-- left -->
		<div class="content_l">
            <div class="cLBox_w">
            	<h5 class="detailT_w">${policy.title}</h5>
                <div class="cLBox_t_zy">
                    <div class="fromAndTime_zy clearfix">
                        <div class="cDetailL_w">发文部门：<a href="javascript:;" class="come_w">${policy.source}</a></div>
                        <div class="fr">
                            <span><#if (policy.fromSite?? && policy.fromSite != "")>信息转载时间<#else>信息发布时间</#if>：</span>
                            <span>${policy.gmtCreate?date}</span>
                        </div>
                    </div>
                    <div class="blueBox mt20 clearfix">
                        <ul class="compInfo clearfix fl">
                            <li>
                                <span>类别：</span>
                                <i>${policy.category}</i>
                            </li>
                            <!-- 扶持行业隐藏，对应2019-1-8政策提出的需求，2019-1-10-宣修改-->
                            <li style="display:none">
                                <span>扶持行业：</span>
                                <i id="industry-name"></i>
                            </li>
                            <!-- 适用范围隐藏，对应2019-1-18政策提出的需求，2019-1-25-宣修改-->
                            <li style="display:none">
                                <span>适用范围：</span>
                                <i>${policy.area}</i>
                            </li>
                            <li>
                                <span>文号：</span>
                                <!-- <i>${policy.referenceNumber}</i> -->
                                <!-- 发文字号不是必填的了 -->
                                <#if policy.referenceNumber??>${policy.referenceNumber}<#else>--</#if>
                            </li>
                            <!-- 转载来源隐藏，对应2019-1-8政策提出的需求，2019-1-10-宣修改-->
                            <li style="display:none">
                                <span>转载来源：</span>
                                <i>${policy.fromSite!"--"}</i>
                            </li>
                            <!-- 转载链接改为原文链接，对应2019-1-8政策提出的需求，2019-1-10-宣修改-->
                            <li>
                                <!-- <span>转载链接：</span> -->
                                <span>原文链接：</span>
                                <i>${policy.fromLink!"--"}</i>
                            </li>
                            <!-- 起始日期改为发布日期，对应2019-1-8政策提出的需求，2019-1-10-宣修改 -->
                            <li>
                                <span>发布日期：</span>
                                <i><#if policy.gmtStart??>${policy.gmtStart?date}<#else>--</#if></i>
                            </li>
                            <!-- 截止日期暂时隐藏，对应2019-1-8政策提出的需求，2019-1-10-宣修改 -->
                            <li style="display:none">
                                <span>截止日期：</span>
                                <i><#if policy.gmtEnd??><#if policy.gmtEnd?string["yyyy"]=="2099">长期有效<#else>${policy.gmtEnd?date}</#if><#else>--</#if></i>
                            </li>
                        </ul>
                        <div class="fr ov "  style="width:140px;position:relative;top:0">
                        <#if relatedPolicyList ??>
                        <#list relatedPolicyList as p>
                        	<#if p.type==0>
                        		<a class="btn_solid fl" style="margin-bottom:10px" href="/policy?uid=${user.userId}&aid=${p.id?c}">政策原文</a>
                        	<#elseif p.type==1>
                        		<a class="btn_solid fl" style="margin-bottom:10px" href="/policy?uid=${user.userId}&aid=${p.id?c}">政策摘编</a>
                        	<#else>
                        		<a class="btn_solid fl" style="margin-bottom:10px" href="/policy?uid=${user.userId}&aid=${p.id?c}">政策解读</a>
                        	</#if>
                        </#list>
                        </#if>
                        <#if (policy.type == 1) && policy.gmtStart?? && policy.gmtEnd??>
                        <#assign expired = !((now>policy.gmtStart)&&(now<policy.gmtEnd))>
                        	<#if (now<policy.gmtStart)>
                            <a class="overBtn" href="javascript:;">尚未开始</a>
                        	<#elseif (now>policy.gmtEnd)>
                        	<a class="overBtn" href="javascript:;">已结束</a>
                        	<#else>
                              <a class="btn_solid fl" href="<#if policy.url??&&policy.url!=''>${policy.url}<#else>javascript:showMsg('本项政策尚未开放线上申报系统');</#if>">我要申报</a>
                        	</#if>
                            <!-- 结束按钮-->
                           <!--  <p class="bmDisc">申报截止时间<br/><#if policy.gmtEnd??><#if policy.gmtEnd?string["yyyy"]=="2099">长期有效<#else>${policy.gmtEnd?date}</#if><#else>--</#if></p> -->
                        </#if>
                        </div>
                    </div>
                    <!--文章内容 start-->
                    <div class="textBox_w catalogbox">
                    	<div class="textBoxL_w">
							<div class="catalogContent">
	                        	${policy.content}
	                        </div>
                        </div>
                        <div class="textBoxR_w">
							<div class="catalogright"></div>
						</div>
                    </div>
                    <!--文章内容 end-->
                    <!--点赞 start-->
					<div class="textFBox_w">
					<#if tag?? && tag=="detail">
						<div class="textFL_w">
							<a href="javascript:;" onclick="mark(this,'likes')" class="good_w">对我有用（<span id="policy-likes"></span>）</a>
							<a href="javascript:;" onclick="mark(this,'bookmarks')" class="collect_w" id="policy-bookmark">收藏</a>
						</div>
						<a class="textFR_w" href="javascript:;" onclick="mark(this,'priority')">申请解读（<span  id="policy-priority"></span>）</a>
					</#if>
						<div class="clearfix"></div>
					</div>
					<!--点赞 over-->
                </div>
                <#if relatedPolicyList??>
                <!--相关推荐 start-->
                <div class="recommend">
                    <h3 class="articleTit ml20 mb5">相关推荐</h3>
                    <ul class="indexList_PJY">
                    	
                    	<#list similarPolicies as p>
                        <li>
                            <a href="/policy?uid=${user.userId}&aid=${p.id?c}">
                                <div class="picWin_PJY"><img src="${p.logo}" alt=""/></div>
                                <dl>
                                    <dt>
                                        <span class="title1_PJY">【<span>
                                        <#if p.type==1>
                                        政策摘编
                                        <#elseif p.type==0>
                                        政策详情
                                        <#else>
                                        政策解读
                                        </#if>
                                        </span>】</span>
                                        <span class="title2_PJY">${p.title}</span>
                                        <#if (p.type==1) && p.gmtStart?? && p.gmtEnd??>
	                                        <#if (now > p.gmtEnd)>
	                                        <b class="title4_PJY">结束</b>
	                                        <#else>
	                                        <b class="title3_PJY">申报中</b>
	                                        </#if>
                                        </#if>
                                    </dt>
                                    <dd class="textCon_PJY"><#if (p.policyAbstract?length>100)>${p.policyAbstract[0..100]}...<#else>${p.policyAbstract}</#if></dd>
                                    <dd>
                                    	<span>${p.gmtCreate?date}</span>
										<span>【<span>${p.source}</span>】</span>
                                    	<#if p.referenceNumber??&&p.referenceNumber!=""><span style="float:left">${p.referenceNumber}&nbsp;&nbsp;&nbsp;&nbsp;</span></#if>
                                    	<#if p.type==1 && p.gmtStart?? && p.gmtEnd??>
                                    		<#assign expired = !((now > p.gmtStart) && (now < p.gmtEnd))>
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
                                    </dd>
                                </dl>
                            </a>
                        </li>
                        </#list>
                    </ul>
                </div>
                <!--相关推荐 end-->
                </#if>
            </div>
            <#if tag?? && tag == "review">
             <div class="fr mt20">
                <a onclick="approve(${policy.id});" href="javascript:;" class="btn_solid mr10">通 过</a>
                <a onclick="reject(${policy.id});" href="javascript:;" class="btn_border">拒 绝</a>
            </div>
            </#if>
		</div>

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
    <script src="/policy/assets/new/js/jquery-1.11.3.min.js"></script>
    <script src="/policy/assets/js/layer/layer.js"></script>
    <script>
    $(function(){
    $(".indexList_PJY>li:last-child a").css("border",0)
    	// translate industry code into name
    	$.get("/v2/industry?code=${policy.industry}", function(res){
    		var text = typeof(res.data) == "array" ? res.data.join() : res.data;
    		$("#industry-name").text(text);
    	});
    });
    
    // !important! keep following scripts untouched
    $(function(){
        title()
    })
    //每次页面刷新或调用ajax刷新列表请调用title方法确定title宽度样式
    function title(){
        $(".indexList_PJY").find("dt").each(function(){
            var w1=$(this).width();
            var w2=$(this).find(".title1_PJY").width();
            $(this).find(".title2_PJY").css({maxWidth:w1-w2-15})
        })
    }
    </script>
    <#include "hot-policy.ftl">
<script>
function showMsg(msg) {
	layer.msg(msg, {icon: 3, time: 2000});
}
function DirectoryNav($h,config){
    this.opts = $.extend(true,{
        scrollThreshold:0.5,    //滚动检测阀值 0.5在浏览器窗口中间部位
        scrollSpeed:500,        //滚动到指定位置的动画时间
        scrollTopBorder:500,    //滚动条距离顶部多少的时候显示导航，如果为0，则一直显示
        easing: 'swing',        //不解释
        delayDetection:30,     //延时检测，避免滚动的时候检测过于频繁
        scrollChange:function(){}
    },config);
    this.$win = $(window);
    this.$h = $h;
    this.$pageNavList = "";
    this.$pageNavListLis ="";
    this.$curTag = "";
    this.$pageNavListLiH = "";
    this.offArr = [];
    this.curIndex = 0;
    this.scrollIng = false;
    this.init();
}

DirectoryNav.prototype = {
    init:function(){
        this.make();
        this.setArr();
        this.bindEvent();
    },
    make:function(){
        //生成导航目录结构,这是根据需求自己生成的。如果你直接在页面中输出一个结构那也挺好不用 搞js
        $(".catalogright").append('<div class="directory-nav" id="directoryNav"><ul></ul><span class="cur-tag" ></span><span class="c-top"></span><span class="c-bottom"></span><span class="line"></span></div>');
        var $hs = this.$h,

            $directoryNav = $("#directoryNav"),
            temp = [],
            index1 = 0,
            index2 = 0;
        $hs.each(function(index){
            var $this = $(this),
                text = $this.text();
            if(this.tagName.toLowerCase()=='h1'){
                index1++;
                if(index1%2==0) index2 = 0;
                temp.push('<li class="l1"><span class="c-dot"></span>'+index1+'. <a class="l1-text" title="'+text+'">'+text+'</a></li>');
            }else{
                index2++;
                temp.push('<li class="l2"><span>'+index2+'.'+'</span> <a class="l2-text" title="'+text+'">'+text+'</a></li>');

            }
        });
        $directoryNav.find("ul").html(temp.join(""));

        //设置变量
        this.$pageNavList = $directoryNav;
        this.$pageNavListLis = this.$pageNavList.find("li");
        this.$curTag = this.$pageNavList.find(".cur-tag");
        this.$pageNavListLiH = this.$pageNavListLis.eq(0).height();

        if(!this.opts.scrollTopBorder){
            this.$pageNavList.show();
        }
    },
    setArr:function(){
        var This = this;
        this.$h.each(function(){
            var $this = $(this),
                offT = Math.round($this.offset().top);
            This.offArr.push(offT);
        });
    },
    posTag:function(top){
        this.$curTag.css({top:top+'px'});
    },
    bindEvent:function(){
        var This = this,
            show = false,
            step=34,
            timer = 0;
        var offArr = this.offArr;

        this.$win.on("scroll",function(){
           clearTimeout(timer);
            timer = setTimeout(function(){
            var thisheight =$(".catalogContent").height()-260;
            var ws = $(window).scrollTop();
            for(var i = 0;i<offArr.length;i++){
                var x = ws+18;
                if((ws+18)>=offArr[i]){
                    $(".directory-nav li").eq(i).addClass("cur").siblings("li").removeClass("cur");
                    var ctop = $(".cur").position().top+30;
                    if(ctop>180){
                           $('.directory-nav  ul').css({'top': '-=' + 20});
                         ctop = $(".cur").position().top+$(".directory-nav ul").position().top+18;
                    }else{
                        $('.directory-nav  ul').css({'top': 0});
                    }
                    $(".cur-tag").css({"top":ctop});
                }
            }
           var ofl=$(".content_l").offset().left+684;
            if(ws>thisheight){
                $(".catalogright").css({"position":"absolute","bottom":"40px","top":"auto"})
            }
            else{
                $(".catalogright").css({"position":"absolute","top":ws})

            }

        },This.opts.delayDetection);
        });

        this.$pageNavList.on("click","li",function(){
            var $this = $(this),
                index = $this.index();
                This.scrollTo(This.offArr[index]);
                $(".directory-nav li").removeClass("cur");
                $this.addClass("cur");
        })
    },
    scrollTo: function(offset,callback) {
        var This = this;
        $('html,body').animate({
            scrollTop: offset
        }, this.opts.scrollSpeed, this.opts.easing, function(){
            This.scrollIng = false;

            callback && this.tagName.toLowerCase()=='body' && callback();
        });
    }
};
function preprocess(i, e) {
	var content = $.trim($(e).text());
	if(content == "") {
		$(e).remove();
	}
	var reg = /<(?:.|\s)*?>/g;
	var html = $.trim($(e).html());
	html = html.replace(reg,"");
	$(e).html(html);
}
function initContentNavigators() {
	if($(".catalogContent").find("h1").length!=0||$(".catalogContent").find("h2").length!=0){
		$(".catalogContent").find("h1").each(preprocess);
		$(".catalogContent").find("h2").each(preprocess);
        var directoryNav = new DirectoryNav($("h1,h2"),{
            scrollTopBorder:0   //滚动条距离顶部多少的时候显示导航，如果为0，则一直显示
        });
    }
}


<#if tag?? && tag == "detail">

// ------ 政策详情 --------
function follow(ele) {
<#if user.isGuest()>
	layer.msg("游客身份无法执行该操作!", {icon: 3, time: 2000});
<#else>
	var clicked = $(ele).hasClass("click");
	var inst = $("#policy-source").text();
	$.ajax({
		url : "/v1/institution/follow?institution=" + inst + "&uid=${user.userId}&trueOrFalse=" + !clicked,
		type: "get",
		success: function(res) {
			if(res.code == 200) {
				$(ele).toggleClass("click");
				var text = clicked ? "+关注" : "已关注";
				$(ele).text(text);
			} else {
				layer.msg(res.message, {icon: 3, time: 2000});
			}
		}
	});
</#if>
}
function mark(ele, op) {
	<#if user.isGuest()>
	layer.msg("游客身份无法执行该操作,即将跳转到登录页面!", {icon: 3, time: 2000});
	setTimeout(function(){
		console.log(location);
		var me = location.origin + location.pathname + "?aid=${policy.id}";
		location = "${domain}/tjsmesp/page/login.jsp?url="+me;
	}, 2000);
	<#else>
	var clicked = $(ele).hasClass("click");
	$.ajax({
		url : "/v2/policy/mark/" + op + "?aid=${policy.id}&uid=${user.userId}&trueOrFalse=" + !clicked,
		type: "post",
		success: function(res) {
			if(res.code == 200) {
				$(ele).toggleClass("click");
				var count = parseInt($(ele).children("span").text());
				count = clicked ? count - 1 : count + 1;
				$(ele).children("span").text(count);
			} else {
				layer.msg(res.message, {icon: 3, time: 2000});
			}
		}
	});
	</#if>
}

</#if>
$(function(){
	initContentNavigators();
});
<#if tag?? && tag=="detail">
$(function(){
	$.getJSON("/v2/policy?aid=${policy.id}&uid=${user.userId}", function(res){
		var policy = res.data;
		$("#policy-likes").text(policy.likes);
		$("#policy-priority").text(policy.priority);
		if(policy.liked) {
			$("#policy-likes").parent().addClass("click");
		}
		if(policy.prioritized) {
			$("#policy-priority").parent().addClass("click");
		}
		if(policy.bookmarked) {
			$("#policy-bookmark").addClass("click");
		}
	});
});
</#if>
<#if tag?? && tag=="review">
// ------ 政策审核 --------
function approve(aid) {
	layer.confirm('您确定要审核通过本篇政策吗？', {
	  btn: ['确定','取消'], //按钮,
	  skin:'nomb'
	}, function(){
	  $.ajax({
			url: "/v2/policy/approve?uid=${user.userId}",
			type: "post",
			data: JSON.stringify({id: aid, message: "approved"}),
			contentType: "application/json",
			success: function(res) {
				if(res.code == 200) {
    				layer.msg('操作成功，3秒钟后页面将会自动跳转到之前的页面.', {time: 3000, icon: 1});
				} else {
					layer.msg('操作失败，' + res.message, {time: 3000, icon: 2});
				}
				setTimeout(function(){location = document.referrer;}, 3000)
			}
		});
	})
}

function reject(aid) {
	layer.prompt({title: '请填写拒绝原因,不能留空', formType: 2,skin:'titlenomb'}, function(text, index){
	  	layer.close(index);
	  	var message = $.trim(text);
	  	if(message === "") {
	  		layer.msg("必须填写拒绝原因才能继续操作.", {time: 2000, icon: 3});
	  	} else {
	  		$.ajax({
    			url: "/v2/policy/reject?uid=${user.userId}",
    			type: "post",
    			data: JSON.stringify({id: aid, message: message}),
    			contentType: "application/json",
    			success: function(res) {
    				if(res.code == 200) {
	    				layer.msg('操作成功，3秒钟后页面将会自动跳转到之前的页面.', {time: 3000,icon:1});
    				} else {
    					layer.msg('操作失败，' + res.message, {time: 3000, icon: 2});
    				}
    				setTimeout(function(){location = document.referrer;}, 3000)
    			}
    		});
	  	}
	});
}
</#if>
</script>
</body>
</html>