{
	"t": "${obj.plan.cname}",
	"start": "${obj.plan.planstartdate?date('yyyy-MM-dd')}",
	"end": "${obj.plan.planenddate?date('yyyy-MM-dd')}",
	"plan": "0.33",
	"actual": "0.5",
	"phases": [
	<#list obj.plan.subplans as subplan>
	{
		"t": "${subplan.cname}",
		"start": "<#if subplan.planstartdate==""><#else>${subplan.planstartdate?date('yyyy-MM-dd')}</#if>",
		"end": "<#if subplan.planenddate==""><#else>${subplan.planenddate?date('yyyy-MM-dd')}</#if>",
		"plan": "0.8",
		"actual": "0.5"
	}
	<#if subplan_index &lt; obj.plan.subplans?size-1>,</#if>
	</#list>
	]
}