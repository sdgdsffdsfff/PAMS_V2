package com.pams.gxgl.rep.helper;

import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.pams.report.common.RepHelper;

public class ZXQKHelper
{
	// 执行情况通用查询方法
	public static String sql_xxgx_zxqk(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate"); // 结束日期
		String enddate = obj.getFormatAttr("enddate"); // 起始日期
		String sql_cdate = obj.getFormatAttr("sql_cdate"); // 系统与查询比较时间

		String ispublish = obj.getFormatAttr("ispublish"); // 发布状态开关
		String isovertime = obj.getFormatAttr("isovertime"); // 超时状态开关
		String isnodeovertime = obj.getFormatAttr("isnodeovertime"); // 节点超时状态开关
		String creater = obj.getFormatAttr("creater"); // 创建人

		System.out.println("sql_xxgx_zxqk.");
		StringBuffer sql = new StringBuffer();

		sql.append(" select v.deptid, v.creater, v.cno, count(v.actdefid) jds, sum(v.cs) cs  ").append("\n");
		sql.append("   from ").append("\n");
		sql.append(" ( ").append("\n");

		sql.append(" select info.deptid, info.creater, info.cno, ract.actdefid, ").append("\n");
		if ("Y".equals(ispublish))
		{
			sql.append(" case when sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) > 1 then 1 else 0 end cs ").append("\n");
		}
		else if ("N".equals(ispublish))
		{
			sql.append(" case when sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) else UF_Calculate_Duration(ract.completetime, ract.createtime) end) > 1 then 1 else 0 end cs ").append("\n");
		}
		else
		{
			sql.append(" case when sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) else UF_Calculate_Duration(ract.completetime, ract.createtime) end) > 1 then 1 else 0 end cs ").append("\n");
		}

		sql.append("   from t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_app_pdusesuggest info ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rflow.runflowkey = info.runflowkey ").append("\n");
		sql.append("    and rflow.tableid = 'PartyDueUseSuggest' ").append("\n");
		sql.append("    and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append("    and bact.id = ract.actdefid ").append("\n");
		sql.append("    and bact.ctype <> 'BEGIN' ").append("\n");
		sql.append("    and bact.ctype <> 'END' ").append("\n");

		if (!StringToolKit.isBlank(begindate))
		{
			sql.append(RepHelper.date_begin_eq("info.createtime", begindate)).append("\n");
		}

		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("info.createtime", enddate)).append("\n");
		}

		if ("Y".equals(ispublish))
		{
			sql.append("    and rflow.state = '结束' ").append("\n");
		}
		else if ("N".equals(ispublish))
		{

		}

		if (!StringToolKit.isBlank(creater))
		{
			sql.append("    and info.creater = " + SQLParser.charValue(creater)).append("\n");
		}

		sql.append("  group by info.deptid, info.creater, info.cno, ract.actdefid ").append("\n");

		if ("Y".equals(isnodeovertime))
		{
			// sql.append(" having sum(ract.completetime - ract.createtime) > 1 ").append("\n");
			sql.append(" having sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) > 1 ").append("\n");
		}
		else if ("N".equals(isnodeovertime))
		{
			sql.append(" having sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) < 1 ").append("\n");
		}

		sql.append(" ) v ").append("\n");
		sql.append(" group by deptid, creater, cno").append("\n");

		if ("Y".equals(isovertime))
		{
			sql.append(" having sum(v.cs) > 0 ").append("\n");
		}
		else if ("N".equals(isovertime))
		{
			sql.append(" having sum(v.cs) = 0 ").append("\n");
		}

		// sql.append("  order by deptid, creater, cno, actdefid  ").append("\n");

		return sql.toString();
	}

	public static String sql_xxgx_zxqk_zxsc(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate"); // 结束日期
		String enddate = obj.getFormatAttr("enddate"); // 起始日期
		String sql_cdate = obj.getFormatAttr("sql_cdate"); // 系统与查询比较时间

		String ispublish = obj.getFormatAttr("ispublish"); // 发布状态开关
		String isovertime = obj.getFormatAttr("isovertime"); // 超时状态开关
		String isnodeovertime = obj.getFormatAttr("isnodeovertime"); // 节点超时状态开关
		String creater = obj.getFormatAttr("creater"); // 创建人

		System.out.println("sql_xxgx_zxqk_zxsc.");
		StringBuffer sql = new StringBuffer();

		sql.append(" select info.creater, info.creatername, rflow.runflowkey, v.cno, ract.actdefid, bact.cname actcname, ").append("\n");
		sql.append(" sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) ").append("\n");
		sql.append("     else UF_Calculate_Duration(ract.completetime, ract.createtime) end) zxsc ").append("\n");
		sql.append(" from t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_app_pdusesuggest info, ").append("\n");
		sql.append(" ( ").append("\n");
		sql.append(ZXQKHelper.sql_xxgx_zxqk(obj));
		sql.append(" ) v   ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and v.cno = info.cno ").append("\n");
		sql.append("    and rflow.runflowkey = info.runflowkey ").append("\n");
		sql.append("    and rflow.tableid = 'PartyDueUseSuggest' ").append("\n");
		sql.append("    and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append("    and bact.id = ract.actdefid ").append("\n");
		sql.append("    and bact.ctype <> 'BEGIN' ").append("\n");
		sql.append("    and bact.ctype <> 'END' ").append("\n");
		sql.append("  group by info.creater, info.creatername, info.cno, rflow.runflowkey, v.cno, ract.actdefid, bact.cname ").append("\n");

		sql.append(" ) v   ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and v.cno = info.cno ").append("\n");
		sql.append("    and rflow.runflowkey = info.runflowkey ").append("\n");
		sql.append("    and rflow.tableid = 'PartyDueUseSuggest' ").append("\n");
		sql.append("    and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append("    and bact.id = ract.actdefid ").append("\n");
		sql.append("    and bact.ctype <> 'BEGIN' ").append("\n");
		sql.append("    and bact.ctype <> 'END' ").append("\n");

		sql.append("  order by cno desc ").append("\n");

		return sql.toString();
	}

	public static String sql_xxgx_kpi_zxsc(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate"); // 结束日期
		String enddate = obj.getFormatAttr("enddate"); // 起始日期
		String sql_cdate = obj.getFormatAttr("sql_cdate"); // 系统与查询比较时间

		String ispublish = obj.getFormatAttr("ispublish"); // 发布状态开关
		String isovertime = obj.getFormatAttr("isovertime"); // 超时状态开关
		String isnodeovertime = obj.getFormatAttr("isnodeovertime"); // 节点超时状态开关
		String creater = obj.getFormatAttr("creater"); // 创建人

		System.out.println("sql_xxgx_kpi_zxsc.");
		StringBuffer sql = new StringBuffer();
		sql.append(" select info.deptid, info.creater, ract.actdefid, ").append("\n");
		if ("Y".equals(ispublish))
		{
			sql.append(" sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) zxsc ").append("\n");
		}
		else if ("N".equals(ispublish))
		{
			sql.append(" sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) else UF_Calculate_Duration(ract.completetime, ract.createtime) end) zxsc ").append("\n");
		}
		else
		{
			sql.append(" sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) else UF_Calculate_Duration(ract.completetime, ract.createtime) end) zxsc ").append("\n");
		}

		sql.append("   from t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_app_pdusesuggest info ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rflow.runflowkey = info.runflowkey ").append("\n");
		sql.append("    and rflow.tableid = 'PartyDueUseSuggest' ").append("\n");
		sql.append("    and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append("    and bact.id = ract.actdefid ").append("\n");
		sql.append("    and bact.ctype <> 'BEGIN' ").append("\n");
		sql.append("    and bact.ctype <> 'END' ").append("\n");

		if (!StringToolKit.isBlank(begindate))
		{
			sql.append(RepHelper.date_begin_eq("info.createtime", begindate)).append("\n");
		}

		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("info.createtime", enddate)).append("\n");
		}

		if ("Y".equals(ispublish))
		{
			sql.append("    and rflow.state = '结束' ").append("\n");
		}
		else if ("N".equals(ispublish))
		{
		}

		if (!StringToolKit.isBlank(creater))
		{
			sql.append("    and info.creater = " + SQLParser.charValue(creater)).append("\n");
		}

		sql.append("  group by info.deptid, info.creater, ract.actdefid ").append("\n");

		if ("Y".equals(isnodeovertime))
		{
			sql.append(" having sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) > 1 ").append("\n");
		}
		else if ("N".equals(isnodeovertime))
		{
			sql.append(" having sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) < 1 ").append("\n");
		}

		return sql.toString();
	}

	public static String sql_xxgx_zxqk1(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate"); // 结束日期
		String enddate = obj.getFormatAttr("enddate"); // 起始日期
		String sql_cdate = obj.getFormatAttr("sql_cdate"); // 系统与查询比较时间
		String report_type = obj.getFormatAttr("reptype");// 报表类型

		String ispublish = obj.getFormatAttr("ispublish"); // 发布状态开关
		String isovertime = obj.getFormatAttr("isovertime"); // 超时状态开关
		String isnodeovertime = obj.getFormatAttr("isnodeovertime"); // 节点超时状态开关
		String creater = obj.getFormatAttr("creater"); // 创建人

		System.out.println("sql_xxgx_zxqk.");
		StringBuffer sql = new StringBuffer();

		sql.append(" select v.deptid, v.creater, v.cno, count(v.actdefid) jds, sum(v.cs) cs  ").append("\n");
		sql.append("   from ").append("\n");
		sql.append(" ( ").append("\n");

		sql.append(" select info.deptid, info.creater, info.cno, ract.actdefid, ").append("\n");
		if ("Y".equals(ispublish))
		{
			sql.append(" case when sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) > 1 then 1 else 0 end cs ").append("\n");
		}
		else if ("N".equals(ispublish))
		{
			sql.append(" case when sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) else UF_Calculate_Duration(ract.completetime, ract.createtime) end) > 1 then 1 else 0 end cs ").append("\n");
		}
		else
		{
			sql.append(" case when sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) else UF_Calculate_Duration(ract.completetime, ract.createtime) end) > 1 then 1 else 0 end cs ").append("\n");
		}

		sql.append("   from t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wflflowassapp info ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rflow.runflowkey = info.runflowkey ").append("\n");
		sql.append("    and rflow.tableid = 'PartyDueUseSuggest' ").append("\n");
		sql.append("    and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append("    and bact.id = ract.actdefid ").append("\n");
		sql.append("    and bact.ctype <> 'BEGIN' ").append("\n");
		sql.append("    and bact.ctype <> 'END' ").append("\n");
		sql.append("    and info.tableid='" + report_type + "' ").append("\n");
		if (!StringToolKit.isBlank(begindate))
		{
			sql.append(RepHelper.date_begin_eq("info.createtime", begindate)).append("\n");
		}

		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("info.createtime", enddate)).append("\n");
		}

		if ("Y".equals(ispublish))
		{
			sql.append("    and rflow.state = '结束' ").append("\n");
		}
		else if ("N".equals(ispublish))
		{
		}

		if (!StringToolKit.isBlank(creater))
		{
			sql.append("    and info.creater = " + SQLParser.charValue(creater)).append("\n");
		}

		sql.append("  group by info.deptid, info.creater, info.cno, ract.actdefid ").append("\n");

		if ("Y".equals(isnodeovertime))
		{
			// sql.append(" having sum(ract.completetime - ract.createtime) > 1 ").append("\n");
			sql.append(" having sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) > 1 ").append("\n");
		}
		else if ("N".equals(isnodeovertime))
		{
			sql.append(" having sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) < 1 ").append("\n");
		}

		sql.append(" ) v ").append("\n");
		sql.append(" group by deptid, creater, cno").append("\n");

		if ("Y".equals(isovertime))
		{
			sql.append(" having sum(v.cs) > 0 ").append("\n");
		}
		else if ("N".equals(isovertime))
		{
			sql.append(" having sum(v.cs) = 0 ").append("\n");
		}

		// sql.append("  order by deptid, creater, cno, actdefid  ").append("\n");

		return sql.toString();
	}

	public static String sql_xxgx_zxqk_zxsc1(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate"); // 结束日期
		String enddate = obj.getFormatAttr("enddate"); // 起始日期
		String sql_cdate = obj.getFormatAttr("sql_cdate"); // 系统与查询比较时间
		String report_type = obj.getFormatAttr("reptype");// 报表类型

		String ispublish = obj.getFormatAttr("ispublish"); // 发布状态开关
		String isovertime = obj.getFormatAttr("isovertime"); // 超时状态开关
		String isnodeovertime = obj.getFormatAttr("isnodeovertime"); // 节点超时状态开关
		String creater = obj.getFormatAttr("creater"); // 创建人

		System.out.println("sql_xxgx_zxqk_zxsc.");
		StringBuffer sql = new StringBuffer();

		sql.append(" select info.creater, info.creatername, rflow.runflowkey, v.cno, ract.actdefid, bact.cname actcname, ").append("\n");
		sql.append(" sum(case when plan.planenddate is null then UF_Calculate_Duration(" + sql_cdate + ", plan.planstartdate) ").append("\n");
		sql.append("     else UF_Calculate_Duration(plan.planenddate, plan.planstartdate) end) jhsc, ").append("\n");
		sql.append(" sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) ").append("\n");
		sql.append("     else UF_Calculate_Duration(ract.completetime, ract.createtime) end) zxsc ").append("\n");
		sql.append(" from t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wflflowassapp info, t_app_plan plan, ").append("\n");
		sql.append(" ( ").append("\n");
		sql.append(ZXQKHelper.sql_xxgx_zxqk1(obj));
		sql.append(" ) v   ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and v.cno = info.cno ").append("\n");
		sql.append("    and rflow.runflowkey = info.runflowkey ").append("\n");
		sql.append("    and rflow.tableid = " + SQLParser.charValue(report_type)).append("\n");
		sql.append("    and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append("    and bact.id = ract.actdefid ").append("\n");
		sql.append("    and bact.ctype <> 'BEGIN' ").append("\n");
		sql.append("    and bact.ctype <> 'END' ").append("\n");
		sql.append("    and info.tableid='" + report_type + "' ").append("\n");
		sql.append("    and plan.runactkey = ract.runactkey ").append("\n");
		sql.append("  group by info.creater, info.creatername, info.cno, rflow.runflowkey, v.cno, ract.actdefid, bact.cname ").append("\n");

		return sql.toString();
	}

	public static String sql_xxgx_kpi_zxsc1(DynamicObject obj) throws Exception
	{
		String begindate = obj.getFormatAttr("begindate"); // 结束日期
		String enddate = obj.getFormatAttr("enddate"); // 起始日期
		String sql_cdate = obj.getFormatAttr("sql_cdate"); // 系统与查询比较时间
		String report_type = obj.getFormatAttr("reptype");// 报表类型

		String ispublish = obj.getFormatAttr("ispublish"); // 发布状态开关
		String isovertime = obj.getFormatAttr("isovertime"); // 超时状态开关
		String isnodeovertime = obj.getFormatAttr("isnodeovertime"); // 节点超时状态开关
		String creater = obj.getFormatAttr("creater"); // 创建人

		System.out.println("sql_xxgx_kpi_zxsc.");
		StringBuffer sql = new StringBuffer();
		sql.append(" select info.deptid, info.creater, info.cno, ract.actdefid, ").append("\n");
		if ("Y".equals(ispublish))
		{
			sql.append(" sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) zxsc ").append("\n");
		}
		else if ("N".equals(ispublish))
		{
			sql.append(" sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) else UF_Calculate_Duration(ract.completetime, ract.createtime) end) zxsc ").append("\n");
		}
		else
		{
			sql.append(" sum(case when ract.completetime is null then UF_Calculate_Duration(" + sql_cdate + ", ract.createtime) else UF_Calculate_Duration(ract.completetime, ract.createtime) end) zxsc ").append("\n");
		}

		sql.append("   from t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_sys_wflflowassapp info ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rflow.runflowkey = info.runflowkey ").append("\n");
		sql.append("    and rflow.tableid = 'PartyDueUseSuggest' ").append("\n");
		sql.append("    and rflow.runflowkey = ract.runflowkey ").append("\n");
		sql.append("    and bact.id = ract.actdefid ").append("\n");
		sql.append("    and bact.ctype <> 'BEGIN' ").append("\n");
		sql.append("    and bact.ctype <> 'END' ").append("\n");

		if (!StringToolKit.isBlank(begindate))
		{
			sql.append(RepHelper.date_begin_eq("info.createtime", begindate)).append("\n");
		}

		if (!StringToolKit.isBlank(enddate))
		{
			sql.append(RepHelper.date_end("info.createtime", enddate)).append("\n");
		}

		if ("Y".equals(ispublish))
		{
			sql.append("    and rflow.state = '结束' ").append("\n");
		}
		else if ("N".equals(ispublish))
		{
		}

		if (!StringToolKit.isBlank(creater))
		{
			sql.append("    and info.creater = " + SQLParser.charValue(creater)).append("\n");
		}

		sql.append("  group by info.deptid, info.creater, info.cno, ract.actdefid ").append("\n");

		if ("Y".equals(isnodeovertime))
		{
			sql.append(" having sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) > 1 ").append("\n");
		}
		else if ("N".equals(isnodeovertime))
		{
			sql.append(" having sum(UF_Calculate_Duration(ract.completetime, ract.createtime)) < 1 ").append("\n");
		}

		// sql.append("  order by deptid, creater, cno, actdefid  ").append("\n");

		return sql.toString();
	}

}
