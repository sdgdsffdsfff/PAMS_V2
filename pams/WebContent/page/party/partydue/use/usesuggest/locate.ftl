
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1,maximum-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>高层计划</title>
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
			<button id="bt_gantt" class="btn btn-primary">跟踪甘特</button>
			
			<button id="bt_save" class="btn btn-primary">保存</button>

			<button id="bt_save_detail" class="btn btn-primary" onclick="page_save_detail()">保存(本部门)</button>
			
			<#if obj.iscallback == true>
			<button id="bt_callback" class="btn btn-primary">收回</button>
			</#if>
			
			<#if obj.isapply == true>
			<button id="bt_apply" class="btn btn-primary">签收</button>
			</#if>
			
			<#list obj.routes as aobj>
			<button id="bt_route_${aobj_index}" class="btn btn-primary" ctype="${aobj.ctype}" onclick="page_forwardto(${aobj_index},'${aobj.endactid}','${aobj.endactname}','${aobj.ctype}')">${aobj.routename}</button>
			</#list>
			
			<#if obj.isbackward == true>
			<button id="bt_backward" class="btn btn-primary">退回</button>
			</#if>
			
			<button id="bt_flowtrace" class="btn btn-primary">流程跟踪</button>
			
			<button id="bt_add" class="btn btn-primary" onclick="window.open('${base}/party/partydue/use/usesuggest/inputdetail.action?id=${obj.usesuggestid}')">新增</button>
			
		</div>
	</div>
	<br/>

  <div class="container"> 
    <h4><i class="fa fa-group"></i> 党费使用征求意见表</h4>
    <form id="mainform" method="post" action="" class="form-horizontal">
    <div id="div_jbxx">
    <h5><a href="#1"><i class="fa fa-calendar"></i> 基本信息</a></h5>
     <div class="form-group">
        <label for="cname" class="col-sm-2 control-label">标题名称：<sup class="fa fa-asterisk"></sup></label>
        <div class="col-sm-10"> 
          <input id="cname" name="cname" required="" value="${obj.usesuggest.cname}" class="form-control">
        </div>           
      </div>
 
      <div class="form-group">
        <label for="creatercname" class="col-sm-2 control-label">征集人：<sup class="fa fa-asterisk"></sup></label>
        <div class="col-sm-4"> 
          <input id="creatercname" name="creatercname" required="" value="${obj.usesuggest.creatercname}" class="form-control">
        </div>
        <label for="cyear" class="col-sm-2 control-label">征集年份：<sup class="fa fa-asterisk"></sup></label>
        <div class="col-sm-4"> 
          <input id="cyear" name="cyear" required="" value="${obj.usesuggest.cyear}" class="form-control">
        </div>            
      </div> 
      </div>
      </form>
      
	<div id="div_hzjy">
	      
	      <h5><a href="#1"><i class="fa fa-calendar"></i> 汇总意见</a></h5>
	 	  <table id="suggetTb" class="table personListTable hover">
			<thead>
			    <tr>
			      <th class="check"></th>   
			      <th>单位</th>
			      <th>姓名</th>  
			      <th>反馈意见</th>
			      <th>职务</th>
			    </tr>
			  </thead>
			  <tbody>
			  <#list obj.usesuggestdetails as aobj>
			  <tr>
			      <td class="check"></td>   
			      <td>${aobj.deptname}</td>
			      <td>${aobj.suggestercname}</td> 
			      <td>${aobj.suggestion}</td>			           
			      <td>${aobj.job}</td>			      
			  </tr>    
			  </#list>
			  </tbody>
		  </table>
		  </div>
		
      </div>
    </form>
  </div>
</div>

<script type="text/javascript">

$("#bt_flowtrace").click(function() {page_flowtrace()});

var actcname = "${obj.ract.cname}";

if(actcname=="通知转发")
{
	$("#div_bbmyj").show();
	$("#div_hzyj").hide();
	$("#bt_save").hide();
	$("#bt_save_detail").show();
}
else
{
	$("#div_bbmyj").hide();
	$("#div_hzyj").show();
	$("#bt_save").show();
	$("#bt_save_detail").hide();
}

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

function page_save_detail()
{
	detailform.action = "${base}/party/partydue/research/usesuggestdetail/save.action";
	detailform.submit();
}
</script>

</body>
</html>