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

<#import "/page/report/com/ray/nwpn/itsm/report/common/view/macros.ftl" as pub_macros>
<@pub_macros.showheader repname="信息共享执行时长指标统计" />

<#include "/page/report/com/ray/nwpn/itsm/report/common/view/nav.ftl">

<br />
<div class="section sec1">
<h2><span class="title tab_zxscmx">信息共享执行时长指标统计</span><span class="more"></span></h2>
<div class="p8" id="tab_zxscmx">
</div>
</div>

<#include "/page/report/com/ray/nwpn/itsm/report/common/view/script.ftl">

<script>

function page_load_zxscmx_table()
{
	page_load_table("${base}/module/pams/gxgl/rep/zxqk/gxd/rep/tab_zxqk_yfqzs.action", "zxscmx", 0, new Array("ownerctx","reptype"), new Array("${obj.ownerctx}","${obj.reptype}"));
}

function page_load()
{
	page_load_zxscmx_table();
}

page_load();

</script>

</div></body>
</html>
