package com.skynet.pams.app.party.partydue.collect.base.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.skynet.app.workflow.spec.DBFieldConstants;
import com.skynet.app.workflow.spec.GlobalConstants;
import com.skynet.app.workflow.ui.action.ActionHelper;
import com.skynet.app.workflow.vo.VApply;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.pams.app.party.service.PartyDueBaseDetailService;
import com.skynet.pams.app.party.service.PartyDueBaseService;
import com.skynet.pams.app.plan.service.PlanService;
import com.skynet.pams.base.pojo.PartyDueBase;
import com.skynet.pams.base.pojo.Plan;

@IocBean
@At("/party/partydue/collect/base")
public class PartyDueBaseAction extends BaseAction {

	@Inject
	private PartyDueBaseService partyduebaseService;
	
	@Inject
	private PartyDueBaseDetailService partyduebasedetailService;
	
	@Inject
	private PlanService planService;
	
	public static String tableid = "PartyDueBase";
	
	@At("/mainframe")
	@Ok("->:/page/party/partydue/collect/base/mainframe.ftl")
	public Map mainframe() throws Exception
	{
		return ro;
	}
	
	@At("/startplan")
	@Ok("redirect:locate.action?runactkey=${obj.runactkey}")
	public Map startplan(String planid, String flowdefid) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject token = (DynamicObject)session.getAttribute(com.skynet.framework.spec.GlobalConstants.sys_login_token);
		String loginname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_user);
		String userid = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_userid);
		String username = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_username);
		
		Plan plan = planService.get(planid);
		
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());
		PartyDueBase base = new PartyDueBase();
		base.setId(UUIDGenerator.getInstance().getNextValue());
		base.setCreatetime(nowtime);
		base.setCreater(loginname);
		base.setCreatercname(username);
		base.setCyear((new GregorianCalendar()).get(Calendar.YEAR));
		
		DynamicObject swapFlow = new DynamicObject();
		swapFlow.setAttr(GlobalConstants.swap_coperatorid, userid);
		swapFlow.setAttr(GlobalConstants.swap_coperatorloginname, loginname);
		swapFlow.setAttr(GlobalConstants.swap_coperatorcname, username);
		swapFlow.setAttr(GlobalConstants.swap_flowdefid, flowdefid);
		swapFlow.setAttr(GlobalConstants.swap_tableid, tableid);

		String runactkey = partyduebaseService.plancreate(plan, base, swapFlow);
		
		ro.put("runactkey", runactkey);
		return ro;
	}
	
	@At("/browseplan")
	@Ok("->:/page/party/partydue/collect/base/browseplan.ftl")
	public Map browseplan() {
		return ro;
	}
	
	@At("/browseplan/json")
	@Ok("json")
	public List browseplanjson()
			throws Exception {
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject token = (DynamicObject)session.getAttribute(com.skynet.framework.spec.GlobalConstants.sys_login_token);
		String loginname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_user);
		String userid = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_userid);

		Map amap = new HashMap();
		// 特殊字段
		amap.put("loginname", loginname);
		amap.put(GlobalConstants.swap_coperatorid, userid);
		amap.put(GlobalConstants.swap_coperatorloginname, loginname);

		List datas = partyduebaseService.sdao().queryForList(
				partyduebaseService.get_browseplan_sql(amap));

		return datas; // 后期改为翻页对象
	}

	@At("/browsewait")
	@Ok("->:/page/party/partydue/collect/base/browsewait.ftl")
	public Map browsewait() {
		return ro;
	}

	@At("/browsewait/json")
	@Ok("json")
	public List browsewaitjson(String flowcclass)
			throws Exception {
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject token = (DynamicObject)session.getAttribute(com.skynet.framework.spec.GlobalConstants.sys_login_token);
		String loginname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_user);
		String userid = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_userid);
		flowcclass = "DFGL";

		Map amap = new HashMap();
		// 特殊字段
		amap.put("loginname", loginname);
		amap.put(GlobalConstants.swap_coperatorid, userid);
		amap.put(GlobalConstants.swap_coperatorloginname, loginname);

		amap.put("flowcclass", flowcclass);

		List datas = partyduebaseService.sdao().queryForList(
				partyduebaseService.get_browsewait_sql(amap));

		return datas; // 后期改为翻页对象
	}
	
	@At("/browsehandle")
	@Ok("->:/page/party/partydue/collect/base/browsehandle.ftl")
	public Map browsehandle() {
		return ro;
	}

	@At("/browsehandle/json")
	@Ok("json")
	public List browsehandlejson(String flowcclass)
			throws Exception {
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject token = (DynamicObject)session.getAttribute(com.skynet.framework.spec.GlobalConstants.sys_login_token);
		String loginname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_user);
		String userid = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_userid);
		flowcclass = "DFGL";

		Map amap = new HashMap();
		// 特殊字段
		amap.put("loginname", loginname);
		amap.put(GlobalConstants.swap_coperatorid, userid);
		amap.put(GlobalConstants.swap_coperatorloginname, loginname);

		amap.put("flowcclass", flowcclass);

		List datas = partyduebaseService.sdao().queryForList(
				partyduebaseService.get_browsehandle_sql(amap));

		return datas; // 后期改为翻页对象
	}
	
	@At("/browseall")
	@Ok("->:/page/party/partydue/collect/base/browseall.ftl")
	public Map browseall() {
		return ro;
	}

	@At("/browseall/json")
	@Ok("json")
	public List browsealljson(String flowcclass)
			throws Exception {
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject token = (DynamicObject)session.getAttribute(com.skynet.framework.spec.GlobalConstants.sys_login_token);
		String loginname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_user);
		String userid = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_userid);
		flowcclass = "DFGL";

		Map amap = new HashMap();
		// 特殊字段
		amap.put("loginname", loginname);
		amap.put(GlobalConstants.swap_coperatorid, userid);
		amap.put(GlobalConstants.swap_coperatorloginname, loginname);

		amap.put("flowcclass", flowcclass);

		List datas = partyduebaseService.sdao().queryForList(
				partyduebaseService.get_browseall_sql(amap));

		return datas; // 后期改为翻页对象
	}
	
	@At("/readpage")
	@Ok("->:/page/party/partydue/collect/base/readpage.ftl")
	public Map readpage(String runactkey) throws Exception {
		loc(runactkey);
		return ro;
	}

	@At("/locate")
	@Ok("->:/page/party/partydue/collect/base/locate.ftl")
	public Map locate(String runactkey) throws Exception {
		loc(runactkey);
		return ro;
	}
	
	public void loc(String runactkey) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject token = (DynamicObject)session.getAttribute(com.skynet.framework.spec.GlobalConstants.sys_login_token);
		String loginname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_user);
		String username = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_username);		
		String deptid = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_dept);
		String deptname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_deptname);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		DynamicObject ract = partyduebaseService.getWorkFlowEngine().getActManager()
				.getRactService().locate(runactkey);
		String id = ract.getFormatAttr("dataid");
		String tableid = ract.getFormatAttr("tableid");
		DynamicObject base = partyduebaseService.locate(id);
		List<DynamicObject> deptbasedetails = partyduebaseService.browsedeptbasedetails(id, deptid); // 本部门基数核准
		List<DynamicObject> basedetails = partyduebaseService.browsesumdeptbasedetails(id, "00000000"); // 基数核准汇总

		// 权限设置
		set_author();
		
		// 查询可选路由
		String actdefid = ract.getFormatAttr("actdefid");
		List<DynamicObject> routes = partyduebaseService.getWorkFlowEngine().getFlowManager().getBrouteService().getRoutes(actdefid);

		ro.put("tableid", tableid);
		ro.put("runactkey", runactkey);
		ro.put("base", base);
		ro.put("deptbasedetails", deptbasedetails);
		ro.put("basedetails", basedetails);	
		ro.put("ract", ract);
		ro.put("routes", routes);
	}
	
	
	
	@At("/savedeptbasedetails")
	@Ok("redirect:locate.action?runactkey=${obj.runactkey}")
	public Map savedeptbasedetails(String runactkey, String baseid, String[] baseuser, float[] base1, float[] base2, float[] base3, float[] base4) throws Exception {

		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject token = (DynamicObject)session.getAttribute(com.skynet.framework.spec.GlobalConstants.sys_login_token);
		String loginname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_user);
		String username = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_username);		
		String deptid = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_dept);
		String deptname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_deptname);
		
		DynamicObject map = new DynamicObject();
		map.setAttr("baseid", baseid);
		map.setAttr("deptid", deptid);
		map.setObj("baseusers", baseuser);
		map.setObj("base1s", base1);
		map.setObj("base2s", base2);
		map.setObj("base3s", base3);
		map.setObj("base4s", base4);
		
		partyduebasedetailService.savedeptbasedetails(map);
		
		ro.setAttr("runactkey", runactkey);
		
		return ro;
	}
	
	// 签收
	@At("/apply")
	@Ok("redirect:locate.action?runactkey=${obj.runactkey}")
	public Map apply(String runactkey) throws Exception
	{
		DynamicObject swapFlow = get_author_common();
		
		String loginname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		boolean sign = partyduebaseService.getWorkFlowEngine().getActManager().enableApplyNew(runactkey, loginname, DBFieldConstants.PUB_PARTICIPATOR_PERSON);

		if (!sign)
		{
			throw new RuntimeException("您没有操作权限!");
		}
		
		VApply vo = new VApply();
		vo.userid = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorid);
		vo.loginname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		vo.username = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		vo.usertype = DBFieldConstants.PUB_PARTICIPATOR_PERSON;
		vo.runactkey = runactkey;

		partyduebaseService.getWorkFlowEngine().getActManager().vapply(vo);

		ro.put("runactkey", runactkey);

		return ro;

	}

	// 设置操作权限
	public void set_author() throws Exception
	{
		DynamicObject flowobj = get_author_common();

		// 以下为常用权限；
		boolean isread = partyduebaseService.isread(flowobj); // 是否只读页面
		boolean isedit = partyduebaseService.isedit(flowobj); // 是否修改页面

		boolean isapply = partyduebaseService.isapply(flowobj); // 可否签收
		boolean isforward = partyduebaseService.isforward(flowobj); // 可否转发
		boolean iscallback = partyduebaseService.iscallback(flowobj); // 可否收回
		boolean isbackward = partyduebaseService.isbackward(flowobj); // 可否退回

		boolean issave = partyduebaseService.issave(flowobj); // 可否保存
		boolean isdelete = partyduebaseService.isdelete(flowobj); // 可否删除

		// 以下为特定业务权限
		
		// 设置权限
		ro.put("isread", isread);
		ro.put("isedit", isedit);

		ro.put("isapply", isapply);
		ro.put("isforward", isforward);
		ro.put("iscallback", iscallback);
		ro.put("isbackward", isbackward);

		ro.put("issave", issave);
		ro.put("isdelete", isdelete);
	}
	
	public DynamicObject get_author_common() throws Exception
	{
		// 获取各类权限
		// 流程信息
		DynamicObject swapFlow = ActionHelper.prepared_author_base();

		String runactkey = Mvcs.getReq().getParameter("runactkey");
		DynamicObject ract = partyduebaseService.getWorkFlowEngine().getActManager().getRactService().locate(runactkey);
		String runflowkey = ract.getFormatAttr("runflowkey");
		String dataid = ract.getFormatAttr("dataid");
		
		swapFlow.setAttr(GlobalConstants.swap_dataid, dataid);
		swapFlow.setAttr(GlobalConstants.swap_runactkey, runactkey);
		swapFlow.setAttr(GlobalConstants.swap_runflowkey, runflowkey);
		
		swapFlow.setAttr(GlobalConstants.swap_tableid, tableid);

		return swapFlow;
	}
	

	

}
