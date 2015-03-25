{
  "projname":"${obj.plan.cname}",
  "projid":"${obj.plan.id}",
  "completepercent":"${obj.plan.completepercent}",
  "desc":"${obj.plan.description}",
  "planworkload":"${obj.plan.planworkload}",
  "baseplanworkload":"${obj.plan.baseplanworkload}",
	"items":[
	<#list obj.subplans as aobj>
	{
		"id": "${aobj.id}",
		"parentid": "${aobj.parentid}",
		"cname": "${aobj.cname}",
		"sequencekey": "${aobj.sequencekey}",
		"completepercent": "0",
		"approvalstatus": "0",
		"description": "",
		"remark": "${aobj.remark}",
		"modifytime": "${aobj.modifytime}",
		"modifier": "${aobj.modifier}",
		"planstartdate": "${aobj.planstartdate}",
		"planenddate": "${aobj.planenddate}",
		"forecastdate": "",
		"forecastenddate": "",
		"actualstartdate": "${aobj.actualstartdate}",
		"actualenddate": "${aobj.actualenddate}",
		"actualheadercname": "${aobj.actualheadercname}",
		"projectid": "${obj.plan.id}",
		"phaseorstep": "${aobj.phaseorstep}",
		"delflag": "0",
		"draftflag": "0",
		"fixworkflow": "0",
		"methodtemplateid": "560",
		"firststartdate": "",
		"firstenddate": "",
		"previousstartdate": "",
		"previousenddate": "",
		"basestartdate": "",
		"baseenddate": "",
		"methodid": "",
		"planworkload": "${aobj.planworkload}",
		"baseplanworkload": "${aobj.baseplanworkload}",
		"firstplanworkload": "0",
		"previosplanworkload": "0",
		"actualworkload": "0",
		"planincomepercent": "0",
		"actualincomepercent": "0",
		"phasestatus": "0",
		"planworkloadrate": "0",
		"actualworkloadrate": "0",
		"delayreason": "",
		"strategy": "",
		"remark2": "",
		"actuaincomedate": "",
		"actuacontractpercent": "",
		"phasebudget": "",
		"phaseplanpercent": "",
		"phasecontractfee": "",
		"actualincomefee": "",
		"incomeremark": "",
		"allworkload": ""
		
	} <#if aobj_index &lt; obj.subplans?size - 1>,</#if>
	</#list>
	]
}