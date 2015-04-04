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
	<th>计划开始时间</th>
	<th>计划完成时间</th>
	<th>实际到达时间</th>
	<th>实际开始时间</th>
	<th>实际完成时间</th>
	<th>计划时长(天)</th>
	<th>执行时长(天)</th>
	<th>超时时长(天)</th>
	</tr>
	<#list obj.datas as aobj>
	<tr>
	<td>${aobj.cname}</td>
	<td>${aobj.username}</td>
	<td><#if aobj.planstartdate!="">${aobj.planstartdate?datetime?string("yyyy-MM-dd HH:mm")}</#if></td>
	<td><#if aobj.planenddate!="">${aobj.planenddate?datetime?string("yyyy-MM-dd HH:mm")}</#if></td>
	<td><#if aobj.createtime!="">${aobj.createtime?datetime?string("yyyy-MM-dd HH:mm")}</#if></td>
	<td><#if aobj.applytime!="">${aobj.applytime?datetime?string("yyyy-MM-dd HH:mm")}</#if></td>
	<td><#if aobj.completetime!="">${aobj.completetime?datetime?string("yyyy-MM-dd HH:mm")}</#if></td>
	<td>${aobj.jhsc?number?string("#.000#")}</td>
	<td>${aobj.zxsc?number?string("#.000#")}</td>
	<td <#if (aobj.zxsc?number-aobj.zxsc?number) &gt; 0>style="font-weight:bold;color:red"<#else></#if>><#if aobj.zxsc?number-aobj.zxsc?number &gt; 0>${(aobj.zxsc?number-aobj.zxsc?number)?string("#.000#")}<#else>0</#if></td>
	</tr>
	</#list>
	</table>
</div>
</div>

</body>
</html>