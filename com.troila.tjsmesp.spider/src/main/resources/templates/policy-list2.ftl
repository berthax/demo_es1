<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="sub.css" rel="stylesheet" type="text/css">
<link href="jiedu.css" rel="stylesheet" type="text/css">
<script src="jquery-1.11.3.min.js"></script>
</head>
<body>
	<div class="main">
 	<div class="list_jd">      
      <div class="hr_20"></div>
      <div class="list_jd_title"><a href="&lt;a href=&quot;../../zcb/&quot;&gt;政策包&lt;/a&gt;">最新政策</a></div>
      <div class="list_jd_content">
        <ul>
		<!-- 
			  <li><a href="../../zcb/gjzc/201901/t20190121_52601.shtml" target="_blank"><span class="date">2019-01-19</span><span class="jd_danwei"> 【国家政策】 </span>国家税务总局 关于小规模纳税人免征增值税政策有关征管问题的公告</a></li>		
			  <li><a href="../../zcb/gjzc/201901/t20190121_52610.shtml" target="_blank"><span class="date">2019-01-19</span><span class="jd_danwei"> 【国家政策】 </span>国家税务总局 关于增值税小规模纳税人地方税种和相关附加减征政策有关征管问题的公告</a></li>     -->    
        </ul>
        <div class="hr_5"></div>
         <!-- <div class="page">
         	<script>
                    var currentPage = 0;//所在页从0开始
                    var prevPage = currentPage-1//上一页
                    var nextPage = currentPage+1//下一页
                    var countPage = 67//共多少页
                    //var record_count = 1910;
                    //document.write("共有"+record_count+"条&nbsp;&nbsp;分"+countPage+"页显示&nbsp;&nbsp;");
					if(countPage>1){
						document.write("共"+countPage+"页&nbsp;&nbsp;");
						//设置上一页代码
						if(currentPage!=0&&currentPage!=1)
							document.write("<a href=\"index.shtml\" class='fanye'>首页</a>&nbsp;<a href=\"index"+"_" + prevPage + "."+"shtml\" class='fanye'>上一页</a>&nbsp;");
						else if(currentPage!=0&&currentPage==1)
							document.write("<a href=\"index.shtml\" class='fanye'>首页</a>&nbsp;<a href=\"index.shtml\" class='fanye'>上一页</a>&nbsp;");
						else
							document.write("首页&nbsp;上一页&nbsp;");
						//循环
						var num = 5;
						for(var i=0+(currentPage-1-(currentPage-1)%num) ; i<=(num+(currentPage-1-(currentPage-1)%num))&&(i<countPage) ; i++){
							if(currentPage==i)
								document.write("<span style=\"color:#FF0000;\">"+(i+1)+"</span>&nbsp;");
							else if(i==0)
								document.write("<a href=\"index.shtml\" class='fanye'>"+(i+1)+"</a>&nbsp;");
							else
								document.write("<a href=\"index"+"_" + i + "."+"shtml\" class='fanye'>"+(i+1)+"</a>&nbsp;");
						}

						//设置下一页代码 
						if(currentPage!=(countPage-1))
							document.write("<a href=\"index"+"_" + nextPage + "."+"shtml\" class='fanye'>下一页</a>&nbsp;<a href=\"index_" + (countPage-1) + ".shtml\" class='fanye'>尾页</a>&nbsp;");
						else if(currentPage==(countPage-1))
							document.write("下一页&nbsp;尾页&nbsp;");
							
						//跳转页面
						document.write("<font class='9ptb'>转到第<input type='text' id='num' value="+(currentPage+1)+" style='width:30px;font-size:12px;' />页"+
											 "&nbsp;<input class='fanye' type='submit' value='提交' onClick=javacript:toPage() /></font>");
					}
                    	             
                    function toPage(){
	                    var _num = document.getElementById("num").value;
	                    if(isNaN(_num)){
		                    alert("请输入数字");
		                    return false;
	                    }
	                    var str = "index"+"_"+(_num-1)+"."+"shtml";
	                    var url = location.href.substring(0,location.href.lastIndexOf("/")+1);
	                    if(_num<=1||_num==null)
		                    location.href = url+"index"+"."+"shtml";
	                    else if(_num>countPage)
		                    alert("本频道最多"+countPage+"页");
	                    else
		                    location.href = url+str;
                    }
                    </script>共67页&nbsp;&nbsp;首页&nbsp;上一页&nbsp;<span style="color:#FF0000;">1</span>&nbsp;<a href="index_1.shtml" class="fanye">2</a>&nbsp;<a href="index_2.shtml" class="fanye">3</a>&nbsp;<a href="index_3.shtml" class="fanye">4</a>&nbsp;<a href="index_4.shtml" class="fanye">5</a>&nbsp;<a href="index_5.shtml" class="fanye">6</a>&nbsp;<a href="index_1.shtml" class="fanye">下一页</a>&nbsp;<a href="index_66.shtml" class="fanye">尾页</a>&nbsp;<font class="9ptb">转到第<input type="text" id="num" value="1" style="width:30px;font-size:12px;">页&nbsp;<input class="fanye" type="submit" value="提交" onclick="javacript:toPage()"></font>

         </div>  -->
   </div>

</div>
</div>
</body>
</html>

<script type="text/javascript">
	$(document).ready(function (){
   	 	init();
	});
	
	function init(){
		$.ajax({
			type: "get",
			url:"http://localhost:8088/mysql/getList?queryStr=123",
			async: false,
			timeout: 30000,
			dataType: 'json',
			data: {
				
			},
			beforeSend: function (XMLHttpRequest) {
			},
			success: function (data, textStatus) {
				console.log(data)
				initList(data);
			},
			error: function () {
			}
		});		
	}
	function initList(data){
		console.log(data.length)
		for(var i=0; i<data.length;i++){
			var currentData = data[i];
			var li = '<li><a href="'+currentData.publishUrl+'" target="_blank"><span class="date">'+currentData.publishDate+'</span><span class="jd_danwei">【'+currentData.publishUnit+'】 </span>'+currentData.title+'</a></li>';
			$(".list_jd_content ul").append(li);
		}
	}
</script>