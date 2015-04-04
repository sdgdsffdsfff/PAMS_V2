<table class="repgGrid">
<tr>
<th width="40">序号</th>
<th width="150">人员</th>
<th>业务单号</th>
<th>业务节点</th>
<th>计划时长（天）</th>
<th>执行时长（天）</th>
<th>执行时长（小时）</th>
<th>超时时长</th>
</tr>
<#assign creatername_old = "">
<#assign cno_old = "">

<#assign total = 0>
<#list obj.zxscs as aobj>
<tr>
<td>${aobj_index + 1}</td>
<td><#if creatername_old == "${aobj.creatername}"><#else>${aobj.creatername}</#if></td>
<td><#if cno_old == "${aobj.cno}"><#else><a href="${base}/module/pams/gxgl/rep/zxqk/gzl/rep/tab_gzltj.action?runflowkey=${aobj.runflowkey}" target="_blank">${aobj.cno}</a></#if></td>
<td>${aobj.actcname}</td>
<td>${aobj.jhsc?number?string("#.000#")}</td>
<td>${aobj.zxsc?number?string("#.000#")}</td>
<td>${((aobj.zxsc?number)*24)?string("#.000#")}</td>
<td <#if (aobj.zxsc?number-1) &gt; 0>style="font-weight:bold;color:red"<#else></#if>><#if (aobj.zxsc?number-1) &gt; 0>${(aobj.zxsc?number-1)?string("#.000#")}<#else>0</#if></td>
</tr>
<#assign creatername_old = aobj.creatername>
<#assign cno_old = aobj.cno>
<#if (aobj.zxsc?number-1) &gt; 0>
<#assign total = total + aobj.zxsc?number-1>
</#if>
</#list>

<tr>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td <#if total &gt; 0>style="font-weight:bold;color:red"</#if>><#if total &gt; 0>${total?string("#.000#")}<#else>0</#if></td>
</tr>
</table>
