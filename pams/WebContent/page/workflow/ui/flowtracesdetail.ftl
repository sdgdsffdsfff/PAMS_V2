<!DOCTYPE HTML>
<html>
<head>
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
<title>流程跟踪</title>
<body>
<form action="" method="post">
<table id="otable" class="table personListTable hover">
<tbody>
<#assign t_runactkey = "">
<#list obj.bean_traces as oneTraces>
	<#assign eventObj = oneTraces.a>
	<#assign eventObj_Receiver = oneTraces.b>
	<#assign eventtype = eventObj.eventtype>
	<#if eventtype == "FORWARD">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<#if eventObj.endactcname == '结束'>
		<tr>
			<td align="left">${eventObj.eventtime?substring(0,16)}: ${eventObj.eventercname} 结束流程 </td>
		</tr>
		<#else>
		<tr>
			<td align="left">${eventObj.eventtime?substring(0,16)}: ${eventObj.eventercname} 从 <b>${eventObj.startactcname}</b> 转发到 <b>${eventObj.endactcname}</b> 交给 ${receivers_str}</td>
		</tr>
		</#if>
	<#elseif eventtype == "CFORWARD">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<tr>
			<td align="left">${eventObj.eventtime?substring(0,16)}: ${eventObj.eventercname} 从 <b>${eventObj.startactcname}</b> 转给下一人到  <b>${eventObj.endactcname}</b> 交给 ${receivers_str}</td>
		</tr>
	<#elseif eventtype == "BACKWARD">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<tr>
			<td align="left">${eventObj.eventtime?substring(0,16)}: ${eventObj.eventercname} 从 <b>${eventObj.startactcname}</b> 退回到  <b>${eventObj.endactcname}</b> 交给 ${receivers_str}</td>
		</tr>
	<#elseif eventtype == "CBACKWARD">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<tr>
			<td align="left">${eventObj.eventtime?substring(0,16)}: ${eventObj.eventercname} 从 <b>${eventObj.startactcname}</b> 退回上一人到  <b>${eventObj.endactcname}</b> 交给 ${receivers_str}</td>
		</tr>	
	<#elseif eventtype == "RESET">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<tr>
			<td align="left">${eventObj.eventtime?substring(0,16)}: ${eventObj.eventercname} 从 <b>${eventObj.startactcname}</b> 重定位到  <b>${eventObj.endactcname}</b> 交给 ${receivers_str}</td>
		</tr>												
	<#elseif eventtype == "CONSIGN_HANDER">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<tr>
			<td align="left">${eventObj.eventtime?substring(0,16)}: ${eventObj.eventercname} 在 <b>${eventObj.actcname}</b> 转交办给 ${receivers_str}</td>
		</tr>
	<#elseif eventtype == "CONSIGN_ASSORTER">
		<#assign receivers_str = "">
		<#assign count = eventObj_Receiver?size>
		<#list eventObj_Receiver as o>
			<#assign receivers_str = receivers_str + o.receivercname>
			<#if o_index &lt; count-1>
			<#assign receivers_str = receivers_str + ",">
			</#if>
		</#list>
	
		<tr>
			<td align="left">${eventObj.eventtime?substring(0,16)}: ${eventObj.eventercname} 在 <b>${eventObj.actcname}</b> 转协办给 ${receivers_str}</td>
		</tr>
	<#elseif eventtype == "APPLY">
		<tr id="tr_${oneTraces_index}">
			<td align="left">${eventObj.eventtime?substring(0,16)}: ${eventObj.eventercname} 签收 <b>${eventObj.actcname}</b> 业务</td>
		</tr>
		<script>
		$.post("flowtraceopinion.action?runactkey=${eventObj.runactkey}", 
		function (data){
			$('#tr_${oneTraces_index}').after(data);
		});
		</script>
	<#elseif eventtype == "CREATE">
		<tr>
			<td align="left">${eventObj.eventtime?substring(0,16)}: ${eventObj.eventercname} 启动流程 </td>
		</tr>
	<#elseif eventtype == "SUSPEND">
		<tr>
			<td align="left">${eventObj.eventtime?substring(0,16)}: ${eventObj.eventercname} 挂起流程 </td>
		</tr>
	<#elseif eventtype == "RESUME">
		<tr>
			<td align="left">${eventObj.eventtime?substring(0,16)}: ${eventObj.eventercname} 恢复运行流程 </td>
		</tr>											
	<#elseif eventtype == "TERMINATE">
		<tr>
			<td align="left">${eventObj.eventtime?substring(0,16)}: ${eventObj.eventercname} 终止流程 </td>
		</tr>
	<#elseif eventtype == "COMPLETE">
		<tr>
			<td align="left">${eventObj.eventtime?substring(0,16)}: 流程结束 </td>
		</tr>																																																																														
	</#if>
</#list>
</tbody>
</table>	
												
</form>
<script>
function page_viewprogress()
{
	url = "${base}/module/app/system/workflow/define/define_viewprogress.action?runflowkey=${obj.runflowkey}&actdefid=${obj.actdefid}";
	openwin(url, 'viewprogress', pub_width_mid,pub_height_mid);
}
</script>
	
</body>
</html>