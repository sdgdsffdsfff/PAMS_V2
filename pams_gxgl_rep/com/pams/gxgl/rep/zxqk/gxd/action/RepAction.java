package com.pams.gxgl.rep.zxqk.gxd.action;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_WFBZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_WFBZS_CSZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_WFBZS_ZCZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_YFBZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_YFBZS_CSFBZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_YFBZS_ZCFBZS;
import com.pams.gxgl.rep.zxqk.gxd.service.TabGXD_YFQZS;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;

@IocBean
@InjectName("rep_gxgl_zxqk_gxd")
@At("/module/pams/gxgl/rep/zxqk/gxd/rep")
public class RepAction extends BaseAction
{
	private String _searchname;

	@Inject
	private Dao dao;

	@At("/mainframe")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/mainframe.ftl")
	public Map mainframe() throws Exception
	{
		// return "mainframe";
		return ro;
	}

	@At("/main_zxqk_yfqzs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/main_zxqk_yfqzs.ftl")
	public Map main_zxqk_yfqzs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String report_type = Mvcs.getReq().getParameter("reptype");
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", report_type);		
//		return "main_zxqk_yfqzs";
		return ro;
	}
	
	@At("/tab_zxqk_yfqzs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/tab_zxqk_yfqzs.ftl")
	public Map tab_zxqk_yfqzs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String reptype = Mvcs.getReq().getParameter("reptype");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		obj.setAttr("reptype", reptype);
		
		TabGXD_YFQZS tabYFQZS = new TabGXD_YFQZS();
		tabYFQZS.setDao(dao);
		
		List yfqzs = tabYFQZS.execute(obj);
		
		ro.setObj("zxscs", yfqzs);
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);
		
//		return "tab_zxqk_yfqzs";
		return ro;
	}
	
	@At("/main_zxqk_yfbzs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/main_zxqk_yfbzs.ftl")
	public Map main_zxqk_yfbzs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String reptype = Mvcs.getReq().getParameter("reptype");
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);
		
//		return "main_zxqk_yfbzs";
		return ro;
	}
	
	@At("/tab_zxqk_yfbzs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/tab_zxqk_yfbzs.ftl")
	public Map tab_zxqk_yfbzs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String reptype = Mvcs.getReq().getParameter("reptype");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);
		
		TabGXD_YFBZS tabYFBZS = new TabGXD_YFBZS();
		tabYFBZS.setDao(dao);
		
		List yfbzs = tabYFBZS.execute(obj);
		
		ro.setObj("zxscs", yfbzs);
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);
		
//		return "tab_zxqk_yfbzs";
		return ro;
	}
	
	@At("/main_zxqk_wfbzs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/main_zxqk_wfbzs.ftl")
	public Map main_zxqk_wfbzs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String reptype = Mvcs.getReq().getParameter("reptype");
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);	
//		return "main_zxqk_wfbzs";
		return ro;
	}
	
	@At("/tab_zxqk_wfbzs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/tab_zxqk_wfbzs.ftl")
	public Map tab_zxqk_wfbzs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String reptype = Mvcs.getReq().getParameter("reptype");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		obj.setAttr("reptype", reptype);
		
		TabGXD_WFBZS tabWFBZS = new TabGXD_WFBZS();
		tabWFBZS.setDao(dao);
		
		List wfbzs = tabWFBZS.execute(obj);
		
		ro.setObj("zxscs", wfbzs);
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);
		
//		return "tab_zxqk_wfbzs";
		return ro;
	}
	
	@At("/main_zxqk_yfbzs_zcfbzs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/main_zxqk_yfbzs_zcfbzs.ftl")
	public Map main_zxqk_yfbzs_zcfbzs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String reptype = Mvcs.getReq().getParameter("reptype");
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);
		
//		return "main_zxqk_yfbzs_zcfbzs";
		return ro;
	}
	
	@At("/tab_zxqk_yfbzs_zcfbzs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/tab_zxqk_yfbzs_zcfbzs.ftl")
	public Map tab_zxqk_yfbzs_zcfbzs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String reptype = Mvcs.getReq().getParameter("reptype");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		obj.setAttr("reptype", reptype);
		
		TabGXD_YFBZS_ZCFBZS tabYFBZS_ZCFBZS = new TabGXD_YFBZS_ZCFBZS();
		tabYFBZS_ZCFBZS.setDao(dao);
		
		List yfbzs_zcfbzs = tabYFBZS_ZCFBZS.execute(obj);
		
		ro.setObj("zxscs", yfbzs_zcfbzs);
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);
		
//		return "tab_zxqk_yfbzs_zcfbzs";
		return ro;
	}
	
	@At("/main_zxqk_yfbzs_csfbzs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/main_zxqk_yfbzs_csfbzs.ftl")
	public Map main_zxqk_yfbzs_csfbzs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String reptype = Mvcs.getReq().getParameter("reptype");
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);
		
//		return "main_zxqk_yfbzs_csfbzs";
		return ro;
	}
	
	@At("/tab_zxqk_yfbzs_csfbzs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/tab_zxqk_yfbzs_csfbzs.ftl")
	public Map tab_zxqk_yfbzs_csfbzs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String reptype = Mvcs.getReq().getParameter("reptype");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		obj.setAttr("reptype", reptype);
		
		TabGXD_YFBZS_CSFBZS tabYFBZS_CSFBZS = new TabGXD_YFBZS_CSFBZS();
		tabYFBZS_CSFBZS.setDao(dao);
		
		List yfbzs_csfbzs = tabYFBZS_CSFBZS.execute(obj);
		
		ro.setObj("yfbzs_csfbzs", yfbzs_csfbzs);
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);
		
//		return "tab_zxqk_yfbzs_csfbzs";
		return ro;
	}	
	
	@At("/main_zxqk_wfbzs_zczs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/main_zxqk_wfbzs_zczs.ftl")
	public Map main_zxqk_wfbzs_zczs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String reptype = Mvcs.getReq().getParameter("reptype");
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);
		
//		return "main_zxqk_wfbzs_zczs";
		return ro;
	}
	
	@At("/tab_zxqk_wfbzs_zczs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/tab_zxqk_wfbzs_zczs.ftl")
	public Map tab_zxqk_wfbzs_zczs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String reptype = Mvcs.getReq().getParameter("reptype");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		obj.setAttr("reptype", reptype);
		
		TabGXD_WFBZS_ZCZS tabWFBZS_ZCZS = new TabGXD_WFBZS_ZCZS();
		tabWFBZS_ZCZS.setDao(dao);
		
		List wfbzs_zczs = tabWFBZS_ZCZS.execute(obj);
		
		ro.setObj("zxscs", wfbzs_zczs);
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);
		
//		return "tab_zxqk_wfbzs_zczs";
		return ro;
	}
	
	@At("/main_zxqk_wfbzs_cszs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/main_zxqk_wfbzs_cszs.ftl")
	public Map main_zxqk_wfbzs_cszs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String reptype = Mvcs.getReq().getParameter("reptype");
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);
		
//		return "main_zxqk_wfbzs_cszs";
		return ro;
	}
	
	@At("/tab_zxqk_wfbzs_cszs")
	@Ok("->:/page/report/com/pams/gxgl/rep/zxqk/gxd/view/tab_zxqk_wfbzs_cszs.ftl")
	public Map tab_zxqk_wfbzs_cszs() throws Exception
	{
		String begindate = Mvcs.getReq().getParameter("begindate");
		String enddate = Mvcs.getReq().getParameter("enddate");
		String ownerctx = Mvcs.getReq().getParameter("ownerctx");
		String reptype = Mvcs.getReq().getParameter("reptype");
		
		DynamicObject obj = new DynamicObject();
		obj.setAttr("begindate", begindate);
		obj.setAttr("enddate", enddate);
		obj.setAttr("ownerctx", ownerctx);
		obj.setAttr("reptype", reptype);
		
		TabGXD_WFBZS_CSZS tabWFBZS_CSZS = new TabGXD_WFBZS_CSZS();
		tabWFBZS_CSZS.setDao(dao);
		
		List wfbzs_cszs = tabWFBZS_CSZS.execute(obj);
		
		ro.setObj("zxscs", wfbzs_cszs);
		
		ro.setAttr("begindate", begindate);
		ro.setAttr("enddate", enddate);
		ro.setAttr("ownerctx", ownerctx);
		ro.setAttr("reptype", reptype);
		
//		return "tab_zxqk_wfbzs_cszs";
		return ro;
	}


}