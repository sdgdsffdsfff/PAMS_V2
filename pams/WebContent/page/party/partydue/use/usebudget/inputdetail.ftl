
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1,maximum-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>使用计划新增</title>
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
    <h4><i class="fa fa-group"></i> 新增党费使用预算表</h4>
    <form id="mainform" method="post" action="" class="form-horizontal">
    	<input id="usebudgetid" name="usebudgetid" value="${obj.usebudgetid}" class="form-control" type="hidden" />
	    <div id="div_jbxx">
     		<div class="form-group">
	        	<label for="cname" class="col-sm-2 control-label">项目名称：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-10"> 
		          <input id="cname" name="cname" required="" value="" class="form-control">
		        </div>           
	      	</div>
	 
		      <div class="form-group">
		        <label for="cyear" class="col-sm-2 control-label">项目开始时间：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4"> 
		          <input id="starttime" name="starttime" required="" value="" class="form-control datepicker">
		        </div> 	      
		        <label for="creatercname" class="col-sm-2 control-label">项目结束时间：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4"> 
		          <input id="endtime" name="endtime" required="" value="" class="form-control datepicker">
		        </div>
		      </div>
		      <div class="form-group">
		        <label for="masterdeptname" class="col-sm-2 control-label">主办部门：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4">
		        	<input id="masterdeptid" name="masterdeptid" required="" value="" class="form-control" type="hidden">
		        	<input id="masterdeptname" name="masterdeptname" value="" class="form-control" type="hidden">
		        	<select
					id="masterdeptnametext" name="masterdeptnametext" class="form-control">
		        	<#list obj.depts as aobj>
		        		<option value="${aobj.id}">${aobj.cname}</option>
		        	</#list>
					</select> 
		        </div>     
		        <label for="creatercname" class="col-sm-2 control-label">项目计划费用：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4"> 
		          <input id="creatercname" name="creatercname" required="" value="" class="form-control">
		        </div>
		      </div>
		      <div class="form-group">
		        <label for="masterdeptname" class="col-sm-2 control-label">主管部门：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4">
		        	<input id="chargedeptid" name="chargedeptid" required="" value="" class="form-control" type="hidden">
		        	<input id="chargedeptname" name="chargedeptname" value="" class="form-control" type="hidden">
		        	<select
					id="chargedeptnametext" name="chargedeptnametext" class="form-control">
		        	<#list obj.depts as aobj>
		        		<option value="${aobj.id}">${aobj.cname}</option>
		        	</#list>
					</select> 
		        </div>           
		        <label for="masterdeptname" class="col-sm-2 control-label">协办部门：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4">
		        	<input id="salvedeptid" name="salvedeptid" required="" value="" class="form-control" type="hidden">
		        	<input id="slavedeptname" name="slavedeptname" value="" class="form-control" type="hidden">
		        	<select
					id="slavedeptnametext" name="slavedeptnametext" class="form-control">
		        	<#list obj.depts as aobj>
		        		<option value="${aobj.id}">${aobj.cname}</option>
		        	</#list>
					</select> 
		        </div>      
		      </div>
		      <div class="form-group">
		        <label for="planheadercname" class="col-sm-2 control-label">项目负责人：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4">
		        	<input id="charger" name="charger" required="" value="" class="form-control" type="hidden">
		        	 <input id="chargername" name="chargername" value="" class="form-control" type="hidden">
		        	
		        	<select
					id="chargernametext" name="chargernametext" class="form-control">
		        	<#list obj.users as aobj>
		        		<option value="${aobj.loginname}">${aobj.cname}</option>
		        	</#list>
					</select> 
		        	
		        </div> 	      
		        <label for="creatercname" class="col-sm-2 control-label">主管领导：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4">
		        	<input id="leader" name="leader" required="" value="" type="hidden">
		        	 <input id="leadername" name="leadername" value="" class="form-control" type="hidden">
		        	
		        	<select
					id="leadernametext" name="leadernametext" class="form-control">
		        	<#list obj.users as aobj>
		        		<option value="${aobj.loginname}">${aobj.cname}</option>
		        	</#list>
					</select> 
		        	
		        </div> 	  
		      </div>
		      <div class="form-group">
		        <label for="cyear" class="col-sm-2 control-label">项目内容：<sup class="fa fa-asterisk"></sup></label>
		        <div class="col-sm-4"> 
		          <input id="context" name="context" required="" value="" class="form-control">
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
	mainform.action = "${base}/party/partydue/use/usebudget/insertdetail.action";
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
	deptbasedetailform.action = "${base}/party/partydue/use/usebudget/savedeptbasedetails.action";
	deptbasedetailform.submit();
}
</script>

</body>
</html>