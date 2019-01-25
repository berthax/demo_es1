<#-- @ftlvariable name="" type="com.troila.sme.service.policy.view.SmeView" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />

    <!--备注：页面中无关的样式和js请去掉-->
    <!--common-->
    <script src="/assets/js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/assets/css/base.css">
    <link rel="stylesheet" href="/assets/css/second_backcommon.css">
    <script src="/assets/js/back-base.js"></script>

    <!--page-->
    <link href="/assets/css/pagination.css" rel="stylesheet" />
    <script src="/assets/js/jquery.pagination.js"></script>
    <!-- 弹窗 -->
    <script type="text/javascript" src="/assets/js/layer/layer.js"></script>
    <!--main-->
    <link rel="stylesheet" href="/assets/css/backmain.css">
    <link rel="stylesheet" href="/assets/css/ly.css">


    <title>政策管理-审批</title>
</head>
<body>
<!--公共部分用include引用开始-->
    <!--header start-->
    <div class="back_header">
        <img src="/assets/images/er_work_top.png" alt="">
        <div class="pdh20">
            <div class="fl">
                <a  class="back_logo"></a>
            </div>
            <div class="fr">
                <ul class="back_logininfo">
                    <li class="li0">欢迎您，<span>hiahia</span></li>
                </ul>
            </div>
        </div>
    </div>
    <!--header over-->
<!--公共部分用include引用结束-->


<!--main gosehere-->
    <div class="rightcontainer">
        <div class="rightwraper">
                <div class="clear"></div>
                <div class="pr p20 po_detail">
                    <h3>${entity.title}</h3>
                    <ul class="clearfix">
                        <li>
                            <span>发布时间：</span>
                            <p>${entity.publishDate?number_to_date}</p>
                        </li>
                        <li>
                            <span>来源：</span>
                            <p>${entity.source}</p>
                        </li>
                        <li>
                            <span>文号：</span>
                            <p>${entity.referenceNumber!"--"}</p>
                        </li>
                    </ul>
                    <div class="lee_detail_tit1">
                        <div class="ch_en">
                            <span class="ch">政策内容</span>
                            <span class="en">Content</span>
                        </div>
                    </div>
                    <div class="detail_txt">
${entity.content}                        
                    </div>
                    <div class="clear"></div>
                    <div class="clearfix mt50">
                        <a href="/v/policy/list?uid=${uid}" class="btn_border fl">返回</a>
                        <a href="#" class="btn_solid fr ly_cancel">拒绝</a>
                        <a href="javascript:approve()" class="btn_solid fr mr30">通过</a>
                    </div>
                </div>
                
        </div>

        <!--footer start 公共部分用include引用-->
        <!-- <div class="back_footer">技术版权所有 © 天津卓朗科技发展有限公司 2013 TROILA.Inc. 保留一切权利。津ICP备10200312号-4</div> -->
        <#include "footer.ftl">
        <!--footer over 公共部分用include引用-->
    </div>
<!--main over-->
<!--撤销弹窗-->
<div class="cancel_box">
    <label for="">拒绝原因：</label>
    <textarea name="reviewMessage" id="reviewMessage" ></textarea>
</div>

<script>
function approve() {
	var box = layer.msg("正在提交数据， 请稍后...", {time: 0, shade: [0.8, '#393D49']});
	$.ajax({
		url: "/v1/policy/approve?uid=${uid}",
		type: "post",
		data: JSON.stringify({id:${entity.id?c},message:"approved"}),
		contentType: "application/json",
		success: function(result) {
			if(result.code == 200) {
				layer.close(box);
				layer.msg("操作成功", {icon: 1, time: 1000});
				window.setTimeout(function(){
					location = "/v/policy/list?uid=${uid}";
				}, 1000);
			}
		}
	});
}
$(function() {
    $(".cancel_box").hide();
    //拒绝弹窗
    $(".ly_cancel").click(function () {
	    $("#reviewMessage").val("");
        var confirm = layer.open({
            title:"拒绝原因",
            skin:"titorange addrolepop",
            type:1,//可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            content:$('.cancel_box'),
            btn: ['确定'],
            //  shade: 0,//遮罩，如需要此处去掉

            move: false,//禁止拖拽
            area: ['460px', 'auto'],
            btn: ['确定', '取消'],
			yes: function(i, l){
				var review = {id:${entity.id}, message:$.trim($("#reviewMessage").val())};
				if(review.message === "") {
					layer.msg("请填写拒绝理由", {icon: 1,time: 1500});
					return;
				}
				var box = layer.msg("正在提交数据， 请稍后...", {icon:1, time: 0});
				$.ajax({
					url: "/v1/policy/reject?uid=${uid}",
					type: "post",
					data: JSON.stringify(review),
					contentType: "application/json",
					success: function(result) {
						if(result.code == 200) {
							layer.close(box);
							layer.close(confirm);
							layer.msg("操作成功", {icon:1,time: 1000});
							window.setTimeout(function(){
								location = "/v/policy/list?uid=${uid}";
							}, 1000);
						}
					}
				});
			}
        })
    })
});


</script>

</body>
</html>