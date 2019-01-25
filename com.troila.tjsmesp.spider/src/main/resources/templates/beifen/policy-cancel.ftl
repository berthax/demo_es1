<div class="cancel_box">
    <textarea name="reviewMessage" id="reviewMessage" ></textarea>
</div>
<script type="text/javascript" src="/policy/assets/js/layer/layer.js"></script>
<script>
function cancel(id) {
	$("#reviewMessage").val("");
	var confirm = layer.open({
    title:"撤销原因",
    skin:"titorange addrolepop",
    type:1,//可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
    content:$('.cancel_box'),
        btn: ['确定'],
        //  shade: 0,//遮罩，如需要此处去掉

        move: false,//禁止拖拽
        area: ['460px', 'auto'],
        btn: ['确定', '取消'],
		yes: function(i, l){
			var review = {id:id, message:$.trim($("#reviewMessage").val())};
			if(review.message === "") {
				layer.msg("请填写撤销理由", {time: 1500,icon:1});
				return;
			}
			if(review.message.length > 200) {
				layer.msg("撤销理由不要超过200字限制", {time: 1500,icon:1});
				return;
			}
			var box = layer.msg("正在提交数据， 请稍后...", {icon:1,time: 0});
			$.ajax({
				url: "/v2/policy/cancel?uid=${user.userId}",
				type: "post",
				data: JSON.stringify(review),
				contentType: "application/json",
				success: function(result) {
					layer.close(box);
					layer.close(confirm);
					if(result.code == 200) {
						layer.msg("操作成功", {time: 1000, icon:1});
						setTimeout(function(){
							$("#policy-" + id).remove();
						}, 1000);
					} else {
						layer.msg(result.message, {time: 1000, icon:3});
					}
				}
			});
		}
    })
}
$(function(){
	$(".cancel_box").hide();
});
</script>