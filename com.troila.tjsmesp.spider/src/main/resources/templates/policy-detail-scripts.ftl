<script>

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
                temp.push('<li class="l1"><span class="c-dot"></span>'+index1+'. <a class="l1-text">'+text+'</a></li>');
            }else{
                index2++;
                temp.push('<li class="l2"><span>'+index2+'.'+'</span> <a class="l2-text">'+text+'</a></li>');

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
            var thisheight =$(".catalogbox").height()-260;

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
          /*      $(".catalogright").css({"position":"absolute","bottom":"40px","top":"auto","margin":"0","left":"auto"})*/
                $(".catalogright").css({"position":"absolute","bottom":"40px","top":"auto"})
            }
            else{

               /* $(".catalogright").css({"position":"fixed","top":"50%","margin-top":"-130px","left":ofl})*/
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
<#if preview>
	layer.msg("预览模式无法执行该操作!", {icon: 3, time: 2000});
<#else>
	<#if user.isGuest()>
	layer.msg("游客身份无法执行该操作!", {icon: 3, time: 2000});
	<#else>
	var clicked = $(ele).hasClass("click");
	$.ajax({
		url : "/v2/policy/mark/" + op + "?aid=${policyId}&uid=${user.userId}&trueOrFalse=" + !clicked,
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
</#if>
}
$(function(){
	$.getJSON("/v2/policy?aid=${policyId}&uid=${user.userId}", function(res){
		var policy = res.data;
		var date = new Date();
		date.setTime(policy.gmtCreate);
		$("#policy-title").text(policy.title);
		$("#policy-source").text(policy.source);
		$("#policy-create").text(date.toLocaleDateString());
		$(".catalogContent").html(policy.content);
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
		initContentNavigators();
	});
});
</script>