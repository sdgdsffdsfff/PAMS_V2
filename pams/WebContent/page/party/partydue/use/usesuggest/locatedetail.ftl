
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1,maximum-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>党费使用征求意见表</title>
<link rel="stylesheet" href="/pams/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/pams/lib/font-awesome/css/font-awesome.min.css">
<link href="../logo60.png" rel="apple-touch-icon">
<link href="../logo76.png" rel="apple-touch-icon" sizes="76x76">
<link href="../logo120.png" rel="apple-touch-icon" sizes="120x120">
<link href="../logo152.png" rel="apple-touch-icon" sizes="152x152">
<link rel="stylesheet" href="/pams/lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="/pams/css/animation.css">
<link rel="stylesheet" href="/pams/lib/slider.css">
<link rel="stylesheet" href="/pams/css/main.css">
<script src="/pams/lib/jquery-2.1.1.min.js"></script>
<script src="/pams/lib/jquery.form.js"></script>
</head>
<body>


<div id="gscontent" style="margin:10px;">

	<div class="">
		<div class="opleft">
						
			<button id="bt_save" class="btn btn-primary" onclick="save_detail()">保存</button>

			
			<#if obj.isbackward == true>
			<button id="bt_backward" class="btn btn-primary">返回</button>
			</#if>
			
		</div>
	</div>
	<br/>

  <div class="container"> 
    <h4><i class="fa fa-group"></i> 新增使用征求意见</h4>
    <form id="mainform" method="post" action="" class="form-horizontal">
    	<input id="usesuggestid" name="usesuggestid" value="${obj.usesuggestid}" class="form-control" type="hidden" />
	    <div id="div_jbxx">
     		<h5><a href="#1"><i class="fa fa-calendar"></i> 本部门意见</a></h5>	  
		      <div class="form-group">
		        <label for="deptname" class="col-sm-2 control-label">单位：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4"> 
		          <input id="deptname" name="deptname" required="" value="${obj.usesuggestdetail.deptname}" class="form-control">
		        </div>
		        <label for="suggesttime" class="col-sm-2 control-label">日期：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4"> 
		          <input id="suggesttime" name="suggesttime" required="" value="<#if obj.usesuggestdetail.suggesttime??>${obj.usesuggestdetail.suggesttime}</#if>" class="datetimepicker">
		        </div>            
		      </div>
		      <div class="form-group">
		        <label for="suggestercname" class="col-sm-2 control-label">姓名：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4">
		          <input id="suggestercname" name="suggestercname" required="" value="${obj.usesuggestdetail.suggestercname}" class="form-control">
		        </div>
				<label for="job" class="col-sm-2 control-label">职务：</label>
		        <div class="col-sm-4">
		          <input id="job" name="job" value="${obj.usesuggestdetail.job}" class="form-control">
		        </div>
		      </div>
		      <div class="form-group">
		        <label for="contact" class="col-sm-2 control-label">联系方式：</label>
		        <div class="col-sm-4">
		          <input id="contact" name="contact" value="${obj.usesuggestdetail.contact}" class="form-control">
		        </div>
				<label for="email" class="col-sm-2 control-label">邮箱：</label>
		        <div class="col-sm-4">
		          <input id="email" name="email" value="${obj.usesuggestdetail.email}" class="form-control">
		        </div>
		      </div>
		      
		      <div class="form-group">
		          <label for="address" class="col-sm-2 control-label">地址</label>
		          <div class="col-sm-10"> 
		            <input name="address" id="address" value="${obj.usesuggestdetail.address}" class="form-control">
		          </div>
		      </div>
		      <div class="form-group">
		      	<label for="suggestion" class="col-sm-2 control-label">意见及建议</label>
		      	<div class="col-sm-10"> 
			  		<textarea id="suggestion" name="suggestion" class="form-control">${obj.usesuggestdetail.suggestion}</textarea>
				</div>
			  </div>
			  <div class="form-group">
				  <label for="oupload" class="col-sm-2 control-label">附件</label>
				  <div class="col-sm-10">
				  <button id="uploadBtn" class="btn"><i class="fa fa-paperclip"></i> 选择文件</button>
				  <ul id="attachList" class="edit">
				    </ul>
				  </div>
			  </div>	      
		        
		     
	      </div>
      </form>
    </div>
</div>

<script type="text/javascript">

$("#bt_flowtrace").click(function() {page_flowtrace()});

var actcname = "${obj.ract.cname}";

function page_forward()
{
	var url = "${base}/workflow/ui/forwardselect.action";
	url += "?runactkey=${obj.runactkey}";
	// url += "&tableid={obj.tableid}";
	// openwin(url,"forwardselectsingleframe",pub_width_mid,pub_height_mid);
	// window.open(url);
	window.location = url;
}

// 转发
function page_forwardto(index, endactdefid, endactname, ctype)
{
	var url = "${base}/workflow/ui/forwardselect.action";
	url += "?runactkey=${obj.runactkey}";
	url += "&endactdefid=" + endactdefid;
	// openwin(url,"forwardselect",pub_width_mid,pub_height_mid);
	// window.open(url);
	window.location = url;
}

//流程跟踪
function page_flowtrace()
{
	var url = "${base}/workflow/ui/flowtrace.action";
	url += "?runactkey=${obj.runactkey}";
	url += "&tableid=${obj.tableid}";	
	window.open(url);
}

	

function save_detail()
{
	mainform.action = "${base}/party/partydue/use/usesuggest/savedetail.action";
	mainform.submit();
}
</script>

</body>
</html>