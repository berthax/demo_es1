<#-- @ftlvariable name="" type="com.troila.sme.service.policy.view.SmeView" -->
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    
    <link rel="stylesheet" href="/policy/assets/new/css/base.css">
    <link rel="stylesheet" href="/policy/assets/new/css/layout.css">
    <link rel="stylesheet" href="/policy/assets/new/css/style.css">
    <link rel="stylesheet" href="/policy/assets/new/css/button.css">
</head>
<body>
    <!-- header -->
    <#include "policy-header.ftl">
    <!-- header end -->
	<!-- content -->
	<div class="content clearfix">
		<!-- left -->
		<div class="content_l">
            <div class="cLBox_zy">
                <div class="cLBox_t_zy">
                    <h2>${policy.title}</h2>
                    <div class="fromAndTime_zy clearfix">
                        <div class="cDetailL_w">发文部门：<a href="javascript:;" class="come_w">${policy.source}</a></div>
                        <div class="fr">
                            <span>发布时间：</span>
                            <span>${policy.gmtCreate?date}</span>
                        </div>
                    </div>
                    <div class="blueBox mt20 clearfix">
                        <ul class="compInfo clearfix fl">
                            <li>
                                <span>政策类别：</span>
                                <i>${policy.category}</i>
                            </li>
                            <li>
                                <span>扶持行业：</span>
                                <i id="industry-name"></i>
                            </li>
                            <li>
                                <span>适用范围：</span>
                                <i>${policy.area}</i>
                            </li>
                            <li>
                                <span>文号：</span>
                                <i>${policy.referenceNumber}</i>
                            </li>
                            <li>
                                <span>起始日期：</span>
                                <i><#if policy.gmtStart??>${policy.gmtStart?date}<#else>--</#if></i>
                            </li>
                            <li>
                                <span>截止日期：</span>
                                <i><#if policy.gmtEnd??>${policy.gmtEnd?date}<#else>--</#if></i>
                            </li>
                        </ul>
                        <#if policy.type == 1>
                        <div class="fr">
                            <a class="bmBtn" href="<#if policy.url??>${policy.url}<#else>javascript:;</#if>">我要申报</a>
                            <!-- 结束按钮-->
                            <!--<a class="overBtn" href="javascript:;">结束</a>-->
                            <p class="bmDisc">申报截止时间<br/><#if policy.gmtEnd??>${policy.gmtEnd?date}<#else>--</#if></p>
                        </div>
                        </#if>
                    </div>
                    <!--文章内容 start-->
                    <div class="articleCon mt20">
                    	${policy.content}
                    </div>
                    <!--文章内容 end-->
                </div>
                
            </div>
            <div class="fr mt20">
                <a href="javascript:approve(${policy.id});" class="btn_solid mr10">通 过</a>
                <a href="javascript:reject(${policy.id});" class="btn_border">拒 绝</a>
            </div>
            <div class="clear"></div>
		</div>

        <!-- left end -->
        <!-- right -->
        <div class="content_r">
            <a href="javascript:;" class="link_to mb20">
                <b>国家专项</b>
                <span>点击资金申报管理中心</span>
            </a>
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
    <!-- <div class="footer_warp">
        <div class="footer clearfix">
            <div class="fl">
                <ul class="link clearfix">
                    <li><a href="javascript:;">友情链接</a></li>
                    <li><a href="javascript:;">友情链接</a></li>
                    <li><a href="javascript:;">友情链接</a></li>
                    <li><a href="javascript:;">友情链接</a></li>
                    <li><a href="javascript:;">友情链接</a></li>
                    <li><a href="javascript:;">友情链接</a></li>
                    <li><a href="javascript:;">友情链接</a></li>
                    <li><a href="javascript:;">友情链接</a></li>
                    <li><a href="javascript:;">友情链接</a></li>
                    <li><a href="javascript:;">友情链接</a></li>
                    <li><a href="javascript:;">友情链接</a></li>
                    <li><a href="javascript:;">友情链接</a></li>
                    <li><a href="javascript:;">友情链接</a></li>
                    <li><a href="javascript:;">友情链接</a></li>
                </ul>
                <p class="copyright">天津市中小企业生产力促进中心 保留版权所有 | 津ICP备17002884号</p>
            </div>
            <div class="fr">
                <a href="javascript:;"">关于我们</a>
                <a href="javascript:;">常见问题</a>
            </div>
        </div>
    </div> -->
    <#include "footer.ftl">
    <!-- footer end -->
    <script src="/policy/assets/new/js/jquery-1.11.3.min.js"></script>
    <script src="/policy/assets/js/layer/layer.js"></script>
    <script>
    
    
    function approve(aid) {
    	layer.confirm('您确定要审核通过本篇政策吗？', {
		  btn: ['确定','取消'] //按钮
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
		  		return;
		  	}
		  	if(message.length > 200) {
				layer.msg("撤销理由不要超过200字限制", {time: 1500,icon:1});
				return;
			}
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
		});
    }
    
    $(function(){
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
</body>
</html>