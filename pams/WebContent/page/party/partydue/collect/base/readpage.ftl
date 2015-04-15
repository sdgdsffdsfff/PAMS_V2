
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1,maximum-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>高层计划</title>
<link rel="stylesheet" href="/pams/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/pams/lib/font-awesome/css/font-awesome.min.css">
<link href="../logo60.png" rel="apple-touch-icon">
<link href="../logo76.png" rel="apple-touch-icon" sizes="76x76">
<link href="../logo120.png" rel="apple-touch-icon" sizes="120x120">
<link href="../logo152.png" rel="apple-touch-icon" sizes="152x152">
<link rel="stylesheet"
	href="/pams/lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="/pams/css/animation.css">
<link rel="stylesheet" href="/pams/lib/slider.css">
<link rel="stylesheet" href="/pams/css/main.css">
<script src="/pams/lib/jquery-2.1.1.min.js"></script>
</head>
<body>



<div id="gscontent" style="margin:10px;">

	<div class="">
		<div class="opleft">
			<button id="bt_gantt" class="btn btn-primary">跟踪甘特</button>
			
			<#if obj.isedit == true>
			<button id="bt_edit" class="btn btn-primary">修改</button>
			</#if>

			<#if obj.isapply == true>
			<button id="bt_apply" class="btn btn-primary">签收</button>
			</#if>
			
			<#if obj.iscallback == true>
			<button id="bt_callback" class="btn btn-primary">收回</button>
			</#if>
			
			<#if obj.isforward == true>
			<#list obj.routes as aobj>
			<button id="bt_route_${aobj_index}" class="btn btn-primary" ctype="${aobj.ctype}" onclick="page_forwardto(${aobj_index},'${aobj.endactid}','${aobj.endactname}','${aobj.ctype}')">${aobj.routename}</button>
			</#list>
			</#if>
			
			<#if obj.isbackward == true>
			<button id="bt_backward" class="btn btn-primary">退回</button>
			</#if>
			
			<button id="bt_flowtrace" class="btn btn-primary">流程跟踪</button>
			
		</div>
	</div>
	<br/>

	  <div class="container"> 
	    <h4><i class="fa fa-group"></i> 党费收缴基数核准表</h4>
	    <form method="post" action="" class="form-horizontal">
	    <div id="div_jbxx">
	    <h5><a href="#1"><i class="fa fa-calendar"></i> 基本信息</a></h5>
	     <div class="form-group">
	        <label for="cname" class="col-sm-2 control-label">标题名称：<sup class="fa fa-asterisk"></sup></label>
	        <div class="col-sm-10"> 
	          <input id="cname" name="cname" required="" value="${obj.base.cname}" class="form-control">
	        </div>           
	      </div>
	 
	      <div class="form-group">
	        <label for="cyear" class="col-sm-2 control-label">基准年份：<sup class="fa fa-asterisk"></sup></label>
	        <div class="col-sm-4"> 
	          <input id="cyear" name="cyear" required="" value="${obj.base.cyear}" class="form-control">
	        </div> 	      
	        <label for="creatercname" class="col-sm-2 control-label">下发人：<sup class="fa fa-asterisk"></sup></label>
	        <div class="col-sm-4"> 
	          <input id="creatercname" name="creatercname" required="" value="${obj.base.creatercname}" class="form-control">
	        </div>
	      </div>
	      
	      </div>
	      
		  <div id="div_hzjy">
	      
	      <h5><a href="#1"><i class="fa fa-calendar"></i> 基数汇总表</a></h5>
	 	  <table id="basedetailTb" class="table personListTable hover">
			<thead>
			    <tr>
			      <th class="check"></th>   
			      <th>单位部门</th>
			      <th>姓名</th>      
			      <th>小计</th>
			      <th>岗位工资</th>
			      <th>技能工资</th>
			      <th>年工工资</th>
			      <th>其它</th>
			      <th>备注</th>	
			    </tr>
			  </thead>
			  <tbody>
			  <#list obj.basedetails as aobj>
			  <tr>
			      <td class="check"></td>   
			      <td>${aobj.deptname}</td>
			      <td>${aobj.baseusername}</td>      
			      <td></td>
			      <td>${aobj.base1}</td>
			      <td>${aobj.base2}</td>
			      <td>${aobj.base3}</td>
			      <td>${aobj.base4}</td>
			      <td></td>
			  </tr>    
			  </#list>
			  </tbody>
		  </table>
		  </div>
		  
		  <div id="div_bbmyj">
		  
		  <h5><a href="#1"><i class="fa fa-calendar"></i> 本部门基准表</a></h5>	  
	 	  <table id="deptbasedetailTb" class="table personListTable hover">
			<thead>
			    <tr>
			      <th class="check"></th>   
			      <th>单位部门</th>
			      <th>姓名</th>      
			      <th>小计</th>
			      <th>岗位工资</th>
			      <th>技能工资</th>
			      <th>年工工资</th>
			      <th>其它</th>
			      <th>备注</th>	
			    </tr>
			  </thead>
			  <tbody>
			  <#list obj.deptbasedetails as aobj>
			  <tr>
			      <td class="check"></td>   
			      <td>${aobj.deptname}</td>
			      <td>${aobj.baseusername}</td>      
			      <td></td>
			      <td>${aobj.base1}</td>
			      <td>${aobj.base2}</td>
			      <td>${aobj.base3}</td>
			      <td>${aobj.base4}</td>
			      <td></td>
			  </tr>    
			  </#list>
			  </tbody>
		  </table>
			
	      </div>
	    </form>
	  </div>
	</div>


<script type="text/javascript">

$("#bt_edit").click(function() {page_edit()});
$("#bt_apply").click(function() {page_apply()});
$("#bt_forward").click(function() {page_forward()});
$("#bt_callback").click(function() {page_callback()});
$("#bt_backward").click(function() {page_backward()});
$("#bt_flowtrace").click(function() {page_flowtrace()});

var actcname = "${obj.ract.cname}";

if(actcname=="编制")
{
	$("#div_bbmyj").show();
	$("#div_hzyj").hidden();
}
else
{
	$("#div_bbmyj").hide();
	$("#div_hzyj").show();
}
	


// 签收
function page_apply()
{
	var url = "${base}/party/partydue/collect/base/apply.action?runactkey=${obj.runactkey}";	 
	window.location = url;
}

// 修改
function page_edit()
{
	var url = "${base}/party/partydue/collect/base/locate.action?runactkey=${obj.runactkey}";	 
	window.location = url;
}

// 转发
function page_forward()
{
	var url = "${base}/workflow/ui/forwardselect.action";
	url += "?runactkey=${obj.runactkey}";
	// openwin(url,"forwardselectsingleframe",pub_width_mid,pub_height_mid);
	// window.open(url);	
	window.location = url;
}

//转发
function page_forwardto(index, endactdefid, endactname, ctype)
{
	var url = "${base}/workflow/ui/forwardselect.action";
	url += "?runactkey=${obj.runactkey}";
	url += "&endactdefid=" + endactdefid;
	// openwin(url,"forwardselect",pub_width_mid,pub_height_mid);
	// window.open(url);
	window.location = url;
}

// 收回
function page_callback() 
{
	var url = "${base}/workflow/ui/callback.action";
	url += "?runactkey=${obj.runactkey}";
	url += "&tableid=${obj.tableid}";

	// openwin(url,"callback",pub_width_mid,pub_height_mid);	
	window.location = url;
}

// 退回
function page_backward()
{
	if (confirm("确定退回操作吗？")==true)
	{
		var url = "${base}/workflow/ui//backward.action";
		url += "?runactkey=${obj.runactkey}";
		url += "&tableid=${obj.tableid}";
		
		// openwin(url,"backward",pub_width_mid,pub_height_mid);
		window.location = url;
	}				   
}

//流程跟踪
function page_flowtrace()
{
	var url = "${base}/workflow/ui/flowtrace.action";
	url += "?runactkey=${obj.runactkey}";
	url += "&tableid=${obj.tableid}";	
	window.open(url);
}
</script>

</body>
</html>