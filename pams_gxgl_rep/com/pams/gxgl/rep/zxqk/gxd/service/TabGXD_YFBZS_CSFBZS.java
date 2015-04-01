package com.pams.gxgl.rep.zxqk.gxd.service;

import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.pams.report.common.RepHelper;
import com.skynet.pams.report.common.ReportService;

@InjectName("tabGXD_YFBZS_CSFBZS")
@IocBean(args = { "refer:dao" })
public class TabGXD_YFBZS_CSFBZS extends ReportService {
	public List execute(DynamicObject obj) throws Exception {
		System.out.println("已发布_超时发布共享单");
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String ownerctx = obj.getFormatAttr("ownerctx");
		String report_type = obj.getFormatAttr("reptype");

		String sql_cdate = RepHelper.compare_sysdate(enddate);

		obj.setAttr("sql_cdate", sql_cdate);
		obj.setAttr("ispublish", "Y");
		obj.setAttr("isovertime", "Y");
		obj.setAttr("creater", ownerctx);
		obj.setAttr("reptype", report_type);

		StringBuffer sql = new StringBuffer();

		sql.append(
				"select info.title,info.deptid,info.creatername, bv.cno,bv.jhsc jhsc,bv.runflowkey runflowkey, ((UF_Calculate_Duration(info.publishtime, info.obtaintime)) - bv.jhsc) cs,(UF_Calculate_Duration(info.publishtime, info.obtaintime)) sjsc")
				.append("\n");
		sql.append(" from t_app_pubinfo info, ").append("\n");
		sql.append("( ").append("\n");
		sql.append(" select bv.cno, (count(0) + 1) jhsc,bv.runflowkey").append(
				"\n");
		sql.append(" from").append("\n");
		sql.append(" (").append("\n");
		sql.append("select bv.cno, ract.actdefid,ract.runflowkey ")
				.append("\n");

		sql.append(
				"from t_sys_wfbact bact, t_sys_wfrflow rflow, t_sys_wfract ract, t_app_pubinfo bv")
				.append("\n");
		sql.append(" where 1 = 1").append("\n");
		sql.append("   and rflow.dataid = bv.id").append("\n");
		sql.append("   and rflow.tableid = 'InfoShare'").append("\n");
		sql.append("   and rflow.runflowkey = ract.runflowkey").append("\n");
		sql.append("   and bact.id = ract.actdefid").append("\n");
		sql.append("   and bact.ctype <> 'BEGIN'").append("\n");

		sql.append("   and bact.ctype <> 'END'").append("\n");
		sql.append("   and rflow.state = '结束'").append("\n");
		sql.append("    and bv.reptype='" + report_type + "' ").append("\n");

		if (!StringToolKit.isBlank(begindate)) {
			sql.append(RepHelper.date_begin_eq("bv.createtime", begindate))
					.append("\n");
		}

		if (!StringToolKit.isBlank(enddate)) {
			sql.append(RepHelper.date_end("bv.createtime", enddate)).append(
					"\n");
		}

		sql.append("    and bv.publishtime is not null ").append("\n");
		if (!StringToolKit.isBlank(enddate)) {
			sql.append(RepHelper.date_end("bv.publishtime", enddate)).append(
					"\n");
		}

		sql.append(" group by bv.cno, ract.actdefid,ract.runflowkey").append(
				"\n");
		sql.append("    ) bv").append("\n");
		sql.append("group by cno,bv.runflowkey").append("\n");
		sql.append(") bv ").append("\n");
		sql.append(" where 1 = 1").append("\n");
		sql.append("   and info.cno = bv.cno").append("\n");
		sql.append("   and info.creater = '" + ownerctx + "'").append("\n");

		List datas = sdao().queryForList(sql.toString());

		return datas;
	}

}
