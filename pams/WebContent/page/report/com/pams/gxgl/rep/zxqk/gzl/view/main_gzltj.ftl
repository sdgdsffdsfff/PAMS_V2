<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
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

<#import "com/ray/nwpn/itsm/report/common/view/macros.ftl" as pub_macros>
<@pub_macros.showheader repname="信息共享执行时长指标统计" />

<#include "/com/ray/nwpn/itsm/report/common/view/nav.ftl">

<br />
<div class="section sec1">
<h2><span class="title tab_zxqk">信息共享执行时长指标统计</span><span class="more"></span></h2>
<div class="p8" id="tab_zxqk">
</div>
</div>

<#include "/com/ray/nwpn/itsm/report/common/view/script.ftl">

<script>

function page_load_zxqk_table()
{
	page_load_table("${base}/module/pams/gxgl/rep/zxqk/ry/rep_tab_zxqk.action", "zxqk", 0, new Array("internal","orginternal"), new Array("${arg.internal}","${arg.orginternal}"));
}

function page_load()
{
	page_load_zxqk_table();
}

page_load();

</script>

</div></body>
</html>
