<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/repinclude.ftl">
<script type="text/javascript">
jQuery(function($){
/////////////////////////////////////



/////////////////////////////////////
})
</script>
<style type="text/css">
.sec1 {width:90%;float:left;margin-right:10px;}
</style>
</head>
<body><div id="gContainer">

<br />
<div class="section sec1">
<h2><span class="title tab_zxqk">流程统计</span><span class="more"></span></h2>
<div class="p8" id="tab_zxqk">
	<table class="repgGrid">
	<tr>
	<th>流程节点</th>
	<th>处理人</th>
	<th>到达时间</th>
	<th>签收时间</th>
	<th>完成时间</th>
	<th>工作时长(天)</th>
	<th>超时时长(天)</th>
	</tr>
	<#list obj.datas as aobj>
	<tr>
	<td>${aobj.cname}</td>
	<td>${aobj.username}</td>
	<td><#if aobj.createtime!="">${aobj.createtime?datetime?string("yyyy-MM-dd HH:mm")}</#if></td>
	<td><#if aobj.applytime!="">${aobj.applytime?datetime?string("yyyy-MM-dd HH:mm")}</#if></td>
	<td><#if aobj.completetime!="">${aobj.completetime?datetime?string("yyyy-MM-dd HH:mm")}</#if></td>
	<td>${aobj.zxsc?number?string("#.000#")}</td>
	<td <#if (aobj.zxsc?number-1) &gt; 0>style="font-weight:bold;color:red"<#else></#if>><#if aobj.zxsc?number-1 &gt; 0>${(aobj.zxsc?number-1)?string("#.000#")}<#else>0</#if></td>
	</tr>
	</#list>
	</table>
</div>
</div>

</body>
</html>