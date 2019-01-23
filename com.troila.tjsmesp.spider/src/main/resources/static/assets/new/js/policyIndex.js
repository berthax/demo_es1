/**
 * Created by Administrator on 2017/4/20 0020.
 */
$(document).ready(function(){
    //分页
    $(".pagination").each(function(){
        $(this).pagination(100,{
            items_per_page:4,
            num_display_entries:4,
            current_page:0,
            num_edge_entries:1

        })
    });
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
    }).on("click",".clearAll_PJY",function(){
        $(this).hide();
        $(".all_PJY").each(function(){
            $(this).trigger("click")
        })
    });
    //点击筛选单位
    var btn2=1;
    $("body").on("click",".highChange_PJY>ul>li:not('.filterList_PJY'):not('.depart_PJY') li:not('.all_PJY')",function(){
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
            $(".clearAll_PJY").show()

        }
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
    //列表样式
    $(".indexList_PJY").each(function(){
        $(this).find("li:first-child>a").css({borderTop:"1px dashed #cccccc"});
    });
    //$(".indexList_PJY>li:first-child>a").css({borderTop:"1px dashed #cccccc"});
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
});
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
