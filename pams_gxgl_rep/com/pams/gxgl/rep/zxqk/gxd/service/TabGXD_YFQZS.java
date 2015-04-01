package com.pams.gxgl.rep.zxqk.gxd.service;

import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.pams.gxgl.rep.helper.ZXQKHelper;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.pams.report.common.RepHelper;
import com.skynet.pams.report.common.ReportService;

@InjectName("tabGXD_YFQZS")
@IocBean(args = { "refer:dao" })
public class TabGXD_YFQZS extends ReportService {

	public List execute(DynamicObject obj) throws Exception {
		String begindate = obj.getFormatAttr("begindate");
		String enddate = obj.getFormatAttr("enddate");
		String ownerctx = obj.getFormatAttr("ownerctx");
		String report_type = obj.getFormatAttr("reptype");

		String sql_cdate = RepHelper.compare_sysdate(enddate);

		obj.setAttr("sql_cdate", sql_cdate);
		obj.setAttr("ispublish", "");
		obj.setAttr("isovertime", "");
		obj.setAttr("creater", ownerctx);
		obj.setAttr("reptype", report_type);

		StringBuffer sql = new StringBuffer();

		sql.append(ZXQKHelper.sql_xxgx_zxqk_zxsc1(obj));

		List datas = sdao().queryForList(sql.toString());

		return datas;
	}

}
