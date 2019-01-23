<script>
$(function(){
	function loadHotPolicy() {
		$.getJSON("/v2/policy/hot?size=10", function(res) {
			for(var i = 0; i < res.data.length; i++) {
				var policy = res.data[i];
				var li = "<li><a href='/policy?uid=${user.userId}&aid=" + policy.id 
					+ "' target='_self'>" + policy.source + "：" + policy.title + "</a></li>";
				$("#hot-policy").append(li);
			}
		});
	}
	loadHotPolicy();
});
</script>