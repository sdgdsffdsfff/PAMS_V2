package com.pams.gxgl.rep.zxqk.gzl.action;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.pams.gxgl.rep.zxqk.gzl.service.TabGZLTJ;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;

@IocBean
@InjectName("rep_gxgl_zxqk_gzl")
@At("/module/pams/gxgl/rep/zxqk/gzl/rep")
public class RepAction extends BaseAction {
	private String _searchname;

	@Inject
	private Dao dao;

	@At("/tab_gzltj")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gzl/view/tab_gzltj.ftl")
	public Map tab_gzltj() throws Exception {
		String runflowkey = Mvcs.getReq().getParameter("runflowkey");

		DynamicObject obj = new DynamicObject();
		obj.setAttr("runflowkey", runflowkey);

		TabGZLTJ tabGZLTJ = new TabGZLTJ();
		tabGZLTJ.setDao(dao);
		List datas = tabGZLTJ.execute(obj);

		ro.setObj("datas", datas);
		ro.setAttr("runflowkey", runflowkey);
		// return "tab_gzltj";
		return ro;
	}
}