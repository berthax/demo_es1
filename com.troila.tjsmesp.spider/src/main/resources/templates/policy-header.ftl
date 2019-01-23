<div class="header_warp">
	<div class="header">
		<div class="logo"><a href="http://www.smetj.cn"><img src="/policy/assets/new/images/logo.png" alt=""/></a></div>
		<p class="header_name">政策法规专题</p>
		<ul class="nav">
			<li><a <#if (currentPolicyType==1)>class="nav_cur"</#if> href="/policy/index?type=1&uid=${user.userId}">政策摘编</a></li>
			<li><a <#if (currentPolicyType==0)>class="nav_cur"</#if> href="/policy/index?type=0&uid=${user.userId}">政策原文</a></li>
			<li><a <#if (currentPolicyType==2)>class="nav_cur"</#if> href="/policy/index?type=2&uid=${user.userId}">政策解读</a></li>
		</ul>
	</div>
</div>