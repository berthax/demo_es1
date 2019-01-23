<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="jquery-1.11.3.min.js"></script>
</head>
<body>
	<li class="clearfix">
		<label for="">发文部门：</label>
		<div class="select2Box w350">
	        <select name="source_1" id="source_1" class="multiple-select selecttag" onchange="change_select()">
	        	<option value="请选择" >请选择</option>
	            <option value="国家" level="0">国家</option>
	            <option value="市级" level="1">市级</option>
	            <option value="区级" level="2">区级</option>
	        </select>
	
	        <select name="source_2" id="source_2">
	        	<option value="" selected="selected">二级自动带出</option>
	            <!-- <option value="中共中央" level="0">中共中央</option>
	            <option value="全国人大常委会" level="0">全国人大常委会</option>
	            <option value="国务院" level="0">国务院</option>
	            <option value="国家能源局" level="0">国家能源局</option>
	            <option value="国土资源局" level="0">国土资源局</option>
	            <option value="中央网信办" level="0">中央网信办</option>
	            <option value="民政部" level="0">民政部</option>
	            <option value="国务院办公厅" level="0">国务院办公厅</option>
	            <option value="工信部" level="0">工信部</option>
	            <option value="发改委" level="0">发改委</option>
	            <option value="科技部" level="0">科技部</option>
	            <option value="财政部" level="0">财政部</option>
	            <option value="人力社保部" level="0">人力社保部</option>
	            <option value="住建部" level="0">住建部</option>
	            <option value="商务部" level="0">商务部</option>
	            <option value="中国人民银行" level="0">中国人民银行</option>
	            <option value="海关总署" level="0">海关总署</option>
	            <option value="税务总局" level="0">-税务总局</option>
	            <option value="工商总局" level="0">工商总局</option>
	            <option value="质检总局" level="0">质检总局</option>
	            <option value="安监局" level="0">安监局</option>
	            <option value="食品药监局" level="0">食品药监局</option>
	            <option value="统计局" level="0">统计局</option>
	            <option value="知识产权局" level="0">知识产权局</option>
	            <option value="发展研究中心" level="0">发展研究中心</option>
	            <option value="中国银监会" level="0">中国银监会</option>
	            <option value="中国证监会" level="0">中国证监会</option>
	            <option value="国土资源部" level="0">国土资源部</option>
	            <option value="国家认证认可监督管理委员会办公室" level="0">国家认证认可监督管理委员会办公室</option>
	            <option value="国家旅游局" level="0">国家旅游局</option>
	            <option value="交通运输部" level="0">交通运输部</option>
	            <option value="水利部" level="0">水利部</option>
	            <option value="国家粮食局" level="0">国家粮食局</option>
	            <option value="农业部" level="0">农业部</option>
	            <option value="住房城乡建设部办公厅" level="0">住房城乡建设部办公厅</option>
	            <option value="文广局" level="0">文广局</option>
	            <option value="公安部" level="0">公安部</option>
	            <option value="文化部" level="0">文化部</option>
	            <option value="环境保护部" level="0">环境保护部</option>
	            <option value="工业和信息化部" level="0">工业和信息化部</option> -->
	           
	            <!-- 天津市的 -->
	           <!--  units['市级']=new Array('发展改革委', '教委', '科技局', '工业和信息化局', '民族宗教委', '公安局', '民政局', '司法局', '财政局', '人社局', '规划和自然资源局',
				'生态环境局','住房城乡建设委','城市管理委','交通运输委','水务局','农业农村委','商务局','市文化和旅游局','卫生健康委','市退役军人局','应急局','审计局','外办',
				'市场监管委','国资委','体育局','统计局','金融局','信访办','人防办','合作交流办','政务服务办','粮食和物资局','知识产权局'); -->
	            <!-- <option value="天津市人民政府" level="1">天津市人民政府</option>
	            <option value="中共天津市委" level="1">中共天津市委</option>
	            <option value="市委宣传部" level="1">市委宣传部</option>
	            <option value="市委统战部" level="1">市委统战部</option>
	            <option value="市发展改革委" level="1">市发展改革委</option>
	            <option value="市工业和信息化委" level="1">市工业和信息化委</option>
	            <option value="市商务委" level="1">市商务委</option>
	            <option value="市科委" level="1">市科委</option>
	            <option value="市建委" level="1">市建委</option>
	            <option value="市农委" level="1">市农委</option>
	            <option value="市教委" level="1">市教委</option>
	            <option value="市金融局" level="1">市金融局</option>
	            <option value="市司法局" level="1">市司法局</option>
	            <option value="市财政局(地税局)" level="1">市财政局(地税局)</option>
	            <option value="市人力社保局" level="1">市人力社保局</option>
	            <option value="市规划局" level="1">市规划局</option>
	            <option value="市国土房管局" level="1">市国土房管局</option>
	            新增市粮食局，2019-1-14宣修改
	            <option value="市粮食局" level="1">市粮食局</option>
	            <option value="市市场监督委" level="1">市市场监督委</option>
	            <option value="市统计局" level="1">市统计局</option>
	            <option value="市知识产权局" level="1">市知识产权局</option>
	            <option value="市中小企业局" level="1">市中小企业局</option>
	            <option value="市国税局" level="1">市国税局</option>
	            <option value="市卫生计生委" level="1">市卫生计生委</option>
	            <option value="市工商联" level="1">市工商联</option>
	            <option value="市科协" level="1">市科协</option>
	            <option value="市政府办公厅" level="1">市政府办公厅</option>
	            <option value="市企联" level="1">市企联</option> -->
	           
	            <!-- 区级的  -->
	            <!-- units['区级']=new Array('滨海新区', '和平区', '河北区', '河西区', '河东区', '南开区', '红桥区', '东丽区', '西青区', '津南区', '北辰区','武清区','宝坻区',
				'静海区','宁河区','蓟州区');  -->    
	            <!-- <option value="滨海新区" level="2">滨海新区</option>
				<option value="和平区" level="2">和平区</option>
				<option value="河北区" level="2">河北区</option>
				<option value="河西区" level="2">河西区</option>
				<option value="河东区" level="2">河东区</option>
				<option value="南开区" level="2">南开区</option>
				<option value="红桥区" level="2">红桥区</option>
				<option value="东丽区" level="2">东丽区</option>
				<option value="西青区" level="2">西青区</option>
				<option value="津南区" level="2">津南区</option>
				<option value="北辰区" level="2">北辰区</option>
				<option value="武清区" level="2">武清区</option>
				<option value="宝坻区" level="2">宝坻区</option>
				<option value="静海区" level="2">静海区</option>
				<option value="宁河区" level="2">宁河区</option>
				<option value="蓟州区" level="2">蓟州区</option> -->
	        </select>
	    </div>
	</li>

</body>
</html>

<script type="text/javascript">            
        var units = new Object();
		units["国家"]=new Array('中共中央','全国人大常委会','国务院','国家能源局','国土资源局','中央网信办','民政部','国务院办公厅','国务院办公厅','工信部','发改委','科技部','财政部','人力社保部','住建部','商务部',
				'中国人民银行','海关总署','税务总局','工商总局','质检总局','安监局','食品药监局','统计局','知识产权局','发展研究中心','中国银监会','中国证监会','国土资源部','国家认证认可监督管理委员会办公室','国家旅游局',
				'交通运输部','水利部','国家粮食局','农业部','住房城乡建设部办公厅','文广局','公安部','文化部','环境保护部','工业和信息化部');
		
		units["市级"]=new Array('市发展改革委', '市教委', '市科技局', '市工业和信息化局', '市民族宗教委', '市公安局', '市民政局', '市司法局', '市财政局', '市人社局', '市规划和自然资源局',
		'市生态环境局','市住房城乡建设委','市城市管理委','市交通运输委','市水务局','市农业农村委','市商务局','市文化和旅游局','市卫生健康委','市退役军人局','市应急局','市审计局','市外办',
		'市市场监管委','市国资委','市体育局','市统计局','市金融局','市信访办','市人防办','市合作交流办','市政务服务办','市粮食和物资局','市知识产权局');
		units["区级"]=new Array('滨海新区', '和平区', '河北区', '河西区', '河东区', '南开区', '红桥区', '东丽区', '西青区', '津南区', '北辰区','武清区','宝坻区',
		'静海区','宁河区','蓟州区');  

		$(document).ready(function (){
		    init();
		});
		//二级联动初始化
	function init()
	{
	    //alert("这是初始化方法");
	    var source_1 = $("#source_1 option:selected").val();
	    var level = $("#source_1 option:selected").attr("level");
	    console.log(source_1+","+level);
	    if(source_1 == '请选择'){
	    	console.log("请选择发文部门的级别……")
	    	return;	    	
	    }
	    var len = units[source_1].length;
	    console.log(len)
	    console.log(units['国家'])
	    $("#source_2").empty();
	    for(var i=0;i<len;i++){
	    	var optionStr = '<option value="'+units[source_1][i]+'" level="'+level+'">'+units[source_1][i]+'</option>';
	    	$("#source_2").append(optionStr);
	    }
	}
	//当省份下拉列表被修改，相应城市列表中的数据也要相应变化
	function change_select()
	{
		var source_1 = $("#source_1 option:selected").val();
	    var level = $("#source_1 option:selected").attr("level");
	    console.log(source_1+","+level);
	    if(source_1 == '请选择'){
	    	console.log("请选择发文部门的级别……")
	    	return;	    	
	    }
	    var len = units[source_1].length;
	    console.log(len)
	    console.log(units['国家'])
	    $("#source_2").empty();
	    for(var i=0;i<len;i++){
	    	var optionStr = '<option value="'+units[source_1][i]+'" level="'+level+'">'+units[source_1][i]+'</option>';
	    	$("#source_2").append(optionStr);
	    }
	    $("#source_2").selectOrDie('update');
		$('#source_2').selectOrDie();
	}
</script>