<#macro status status>
	<#if status == 0>
	待审批
	<#elseif status == 1>
	已发布
	<#elseif status == 2>
	已拒绝
	<#elseif status == 3>
	已撤销
	<#elseif status == 99>
	待提交
	<#else>
	未知
	</#if>
</#macro>
<script>
function tranlateStatus(status) {
	if(status == 0) {
		return "待审批";
	} else if(status == 1){
		return "已发布";
	} else if(status == 2){
		return "已拒绝"
	} else if(status == 3){
		return "已撤销"
	} else if(status == 99){
		return "待提交"
	} else {
		return "未知"
	}
}
</script>