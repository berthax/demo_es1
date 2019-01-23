<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link rel="stylesheet" href="assets/new/css/sideCatalog.css">
    <link rel="stylesheet" href="assets/new/css/base.css">
    <link rel="stylesheet" href="assets/new/css/layout.css">
    <link rel="stylesheet" href="assets/new/css/style.css">
    <link rel="stylesheet" href="assets/new/css/msword.css">
    <link rel="stylesheet" href="assets/new/css/msword.css">
    <link rel="stylesheet" href="assets/new/css/msword.css">
    <link rel="stylesheet" href="jiedu.css">
    <link rel="stylesheet" href="sub.css">
    <link rel="stylesheet" href="ztfw.css">
    <script src="jquery-1.11.3.min.js"></script>
    <style type="text/css">
    	ul.compInfo li{padding:15px 0}
		P.MsoNormal {FONT-SIZE: 12pt; FONT-FAMILY: "Times New Roman","serif"; MARGIN: 0cm 0cm 0pt; LINE-HEIGHT: 18pt}		
		LI.MsoNormal {FONT-SIZE: 12pt; FONT-FAMILY: "Times New Roman","serif"; MARGIN: 0cm 0cm 0pt; LINE-HEIGHT: 18pt}		
		DIV.MsoNormal {FONT-SIZE: 12pt; FONT-FAMILY: "Times New Roman","serif"; MARGIN: 0cm 0cm 0pt; LINE-HEIGHT: 18pt}		
		.TRS_PreAppend .MsoChpDefault {FONT-SIZE: 10pt}		
		.TRS_PreAppend DIV.Section1 {page: Section1}
	</style>
</head>
<body>
<div class="main">

  <div class="JD_detail">
<!--  <div class="function"><a href="javascript:window.print();">打印本页</a></div> -->
   <div class="JD_key02">
    <table class="JD_table_key02" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	     <tbody id="theader">
		     <tr>
		      <td width="9%" height="25">报送单位：</td>
		      <td width="38%">市财政局</td>
		      <td width="9%">主题分类：</td>
		      <td width="38%"><span id="span_publisher" name="span_publisher"></span></td>
		    </tr>
		    <tr>
		      <td>发文字号：</td>
		      <td>津财采〔2019〕1号</td>
		      <td>成文日期：</td>
		      <td>2019-01-04</td>
		    </tr>
		 
		    <tr>
		       <td>标　　题：</td>
		      <td colspan="3"><span id="span_docTitle" name="span_docTitle">
			 	 天津市财政局关于做好政府采购定向支持中小企业有关工作的通知
			  </span></td>
		    </tr>
		</tbody>
   </table>
  </div>
  <div id="mainContent">
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
			url:"http://localhost:8088/mysql/getOne?id=8d497469873dbe171632602a17afbbeb",
			async: false,
			timeout: 30000,
			dataType: 'json',
			data: {
				
			},
			beforeSend: function (XMLHttpRequest) {
			},
			success: function (data, textStatus) {
				console.log(data)
				showDetail(data);
			},
			error: function () {
			}
		});		
	}
	function showDetail(data){
	    var theader = '<tr>'+
	      '<td width="9%" height="25">报送单位：</td>'+
	      '<td width="38%">'+data.publishUnit+'</td>'+
	      '<td width="9%">主题分类：</td>'+
	      '<td width="38%"><span id="span_publisher" name="span_publisher"></span></td>'+
	    '</tr>'+
	    '<tr>'+
	      '<td>发文字号：</td>'+
	      '<td>'+data.publishNo+'</td>'+
	      '<td>成文日期：</td>'+
	      '<td>'+data.publishDate+'</td>'+
	    '</tr>'+	 
	    '<tr>'+
	       '<td>标　　题：</td>'+
	       '<td colspan="3"><span id="span_docTitle" name="span_docTitle">'+data.title+'</span></td>'+
	    '</tr>'+
	'</tbody>';
	$("#theader").html(theader);
	$("#mainContent").html(data.content);
	}
</script>
