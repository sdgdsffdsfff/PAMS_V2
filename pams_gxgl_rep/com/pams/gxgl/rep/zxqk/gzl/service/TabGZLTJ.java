package com.pams.gxgl.rep.zxqk.gzl.service;

import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.pams.report.common.ReportService;

@InjectName("tabGZLTJ")
@IocBean(args = { "refer:dao" })
public class TabGZLTJ extends ReportService 
{
	public List execute(DynamicObject obj) throws Exception
	{
		String runflowkey = obj.getFormatAttr("runflowkey");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select bact.id, bact.cname, usr.cname username, plan.planstartdate, plan.planenddate, ract.createtime, ract.applytime, ract.completetime, ").append("\n");
		sql.append(" case when plan.planenddate is null then trunc(UF_Calculate_Duration(sysdate, plan.planstartdate), 3)  ").append("\n");
		sql.append(" else trunc(UF_Calculate_Duration(plan.planenddate, plan.planstartdate), 3) end jhsc, ").append("\n");
		sql.append(" case when ract.completetime is null then trunc(UF_Calculate_Duration(sysdate, ract.createtime), 3)  ").append("\n");
		sql.append(" else trunc(UF_Calculate_Duration(ract.completetime, ract.createtime), 3) end zxsc ").append("\n");
		sql.append(" from t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wfbact bact, t_sys_wfractowner ractowner, t_sys_user usr, t_app_plan plan ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append(" and bact.ctype <> 'BEGIN'").append("\n");
		sql.append(" and bact.ctype <> 'END'").append("\n");
		sql.append(" and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append(" and ract.actdefid = bact.id ").append("\n");
		sql.append(" and rflow.runflowkey = " + SQLParser.charValue(runflowkey)).append("\n");
		sql.append(" and ract.runactkey = ractowner.runactkey ").append("\n");
		sql.append(" and ractowner.ownerctx = usr.loginname ").append("\n");
		sql.append(" and ract.ispackage is null ").append("\n");
		sql.append(" and plan.runactkey = ract.runactkey ").append("\n");
		sql.append(" order by createtime ").append("\n");
		
		List datas = sdao().queryForList(sql.toString());
		
		return datas;
	}
}
