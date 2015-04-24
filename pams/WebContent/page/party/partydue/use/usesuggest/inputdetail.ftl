
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1,maximum-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>新增使用征求意见</title>
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
						
			<button id="bt_save" class="btn btn-primary" onclick="insert_detail()">保存</button>

			
			<#if obj.isbackward == true>
			<button id="bt_backward" class="btn btn-primary">返回</button>
			</#if>
			
		</div>
	</div>
	<br/>

  <div class="container"> 
    <h4><i class="fa fa-group"></i> 新增使用征求意见</h4>
    <form id="mainform" method="post" action="" class="form-horizontal">
    	<input id="suggestid" name="suggestid" value="${obj.usesuggestid}" class="form-control" type="hidden" />
	    <div id="div_jbxx">
     		<h5><a href="#1"><i class="fa fa-calendar"></i> 本部门意见</a></h5>	  
		      <div class="form-group">
		        <label for="deptname" class="col-sm-2 control-label">单位：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4">
		        	<input id="deptid" name="deptid" required="" value="" class="form-control" type="hidden">
		        	<input id="deptname" name="deptname" value="" class="form-control" type="hidden">
		        	<select
					id="masterdeptnametext" name="masterdeptnametext" class="form-control">
		        	<#list obj.depts as aobj>
		        		<option value="${aobj.id}">${aobj.cname}</option>
		        	</#list>
					</select> 
		        </div>     
		        <label for="suggesttime" class="col-sm-2 control-label">日期：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4"> 
		          <input id="suggesttime" name="suggesttime" required="" value="" class="datetimepicker">
		        </div>            
		      </div>
		      <div class="form-group">
		        <label for="suggestercname" class="col-sm-2 control-label">姓名：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4">
		        	<input id="suggester" name="suggester" required="" value="" class="form-control" type="hidden">
		        	 <input id="suggestercname" name="suggestercname" required="" value=""  class="form-control" type="hidden">
		        	
		        	<select
					id="suggestercnametext" name="suggestercnametext" class="form-control">
		        	<#list obj.users as aobj>
		        		<option value="${aobj.loginname}">${aobj.cname}</option>
		        	</#list>
					</select> 
		        	
		        </div> 	    
				<label for="job" class="col-sm-2 control-label">职务：</label>
		        <div class="col-sm-4">
		          <input id="job" name="job" value="" class="form-control">
		        </div>
		      </div>
		      <div class="form-group">
		        <label for="contact" class="col-sm-2 control-label">联系方式：</label>
		        <div class="col-sm-4">
		          <input id="contact" name="contact" value="" class="form-control">
		        </div>
				<label for="email" class="col-sm-2 control-label">邮箱：</label>
		        <div class="col-sm-4">
		          <input id="email" name="email" value="" class="form-control">
		        </div>
		      </div>
		      
		      <div class="form-group">
		          <label for="address" class="col-sm-2 control-label">地址</label>
		          <div class="col-sm-10"> 
		            <input name="address" id="address" value="" class="form-control">
		          </div>
		      </div>
		      <div class="form-group">
		      	<label for="suggestion" class="col-sm-2 control-label">意见及建议</label>
		      	<div class="col-sm-10"> 
			  		<textarea id="suggestion" name="suggestion" class="form-control"></textarea>
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

// 新建
function insert_detail()
{
	// 赋值
    $('#deptid').val($('#masterdeptnametext').val());
    $('#deptname').val($('#masterdeptnametext').find("option:selected").text()); 
    
    $('#suggester').val($('#suggestercnametext').val());
    $('#suggestercname').val($('#suggestercnametext').find("option:selected").text());        

	mainform.action = "${base}/party/partydue/use/usesuggest/insertdetail.action";
	mainform.submit();
}

//流程跟踪
function page_flowtrace()
{
	var url = "${base}/workflow/ui/flowtrace.action";
	url += "?runactkey=${obj.runactkey}";
	url += "&tableid=${obj.tableid}";	
	window.open(url);
}
	
function page_save_detail()
{
	deptbasedetailform.action = "${base}/party/partydue/use/usesuggest/insertdetail.action";
	deptbasedetailform.submit();
}

    


</script>

</body>
</html>