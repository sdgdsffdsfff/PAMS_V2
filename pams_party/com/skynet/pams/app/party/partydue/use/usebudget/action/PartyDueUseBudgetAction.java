package com.skynet.pams.app.party.partydue.use.usebudget.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
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
import com.skynet.pams.app.party.service.PartyDueUseBudgetDetailService;
import com.skynet.pams.app.party.service.PartyDueUseBudgetService;
import com.skynet.pams.app.plan.service.PlanService;
import com.skynet.pams.base.pojo.PartyDueUseSuggest;
import com.skynet.pams.base.pojo.Plan;

@IocBean
@At("/party/partydue/use/usebudget")
public class PartyDueUseBudgetAction extends BaseAction {

	@Inject
	private PartyDueUseBudgetService partydueusesuggestService;
	
	@Inject
	private PartyDueUseBudgetDetailService partydueusesuggestdetailService;
	
	@Inject
	private PlanService planService;
	
	public static String tableid = "PartyDueUseSuggest";
	
	@At("/mainframe")
	@Ok("->:/page/party/partydue/research/usesuggest/mainframe.ftl")
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
		PartyDueUseSuggest usesuggest = new PartyDueUseSuggest();
		usesuggest.setId(UUIDGenerator.getInstance().getNextValue());
		usesuggest.setCname(plan.getCname());
		usesuggest.setCreatetime(nowtime);
		usesuggest.setCreater(loginname);
		usesuggest.setCreatercname(username);
		usesuggest.setCyear((new GregorianCalendar()).get(Calendar.YEAR));
		
		DynamicObject swapFlow = new DynamicObject();
		swapFlow.setAttr(GlobalConstants.swap_coperatorid, userid);
		swapFlow.setAttr(GlobalConstants.swap_coperatorloginname, loginname);
		swapFlow.setAttr(GlobalConstants.swap_coperatorcname, username);
		swapFlow.setAttr(GlobalConstants.swap_flowdefid, flowdefid);
		swapFlow.setAttr(GlobalConstants.swap_tableid, tableid);

		// String runactkey = partydueusesuggestService.plancreate(plan, usesuggest, swapFlow);
		
		// ro.put("runactkey", runactkey);
		return ro;
	}
	
	@At("/browseplan")
	@Ok("->:/page/party/partydue/research/usesuggest/browseplan.ftl")
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

		List datas = partydueusesuggestService.sdao().queryForList(
				partydueusesuggestService.get_browseplan_sql(amap));

		return datas; // 后期改为翻页对象
	}

	@At("/browsewait")
	@Ok("->:/page/party/partydue/research/usesuggest/browsewait.ftl")
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

		List datas = partydueusesuggestService.sdao().queryForList(
				partydueusesuggestService.get_browsewait_sql(amap));

		return datas; // 后期改为翻页对象
	}
	
	@At("/browsehandle")
	@Ok("->:/page/party/partydue/research/usesuggest/browsehandle.ftl")
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

		List datas = partydueusesuggestService.sdao().queryForList(
				partydueusesuggestService.get_browsehandle_sql(amap));

		return datas; // 后期改为翻页对象
	}
	
	@At("/browseall")
	@Ok("->:/page/party/partydue/research/usesuggest/browseall.ftl")
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

		List datas = partydueusesuggestService.sdao().queryForList(
				partydueusesuggestService.get_browseall_sql(amap));

		return datas; // 后期改为翻页对象
	}
	
	@At("/readpage")
	@Ok("->:/page/party/partydue/research/usesuggest/readpage.ftl")
	public Map readpage(String runactkey) throws Exception {
		loc(runactkey);
		return ro;
	}

	@At("/locate")
	@Ok("->:/page/party/partydue/research/usesuggest/locate.ftl")
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
		
		DynamicObject ract = partydueusesuggestService.getWorkFlowEngine().getActManager()
				.getRactService().locate(runactkey);
		String id = ract.getFormatAttr("dataid");
		String tableid = ract.getFormatAttr("tableid");
		DynamicObject usesuggest = partydueusesuggestService.locate(id);
		DynamicObject usesuggestdetail = partydueusesuggestdetailService.locateBy(Cnd.where("suggestid", "=", id).and("deptid", "=", deptid));	
		
		usesuggestdetail.setAttr("deptid", deptid);
		usesuggestdetail.setAttr("deptname", deptname);
		usesuggestdetail.setAttr("suggester", loginname);
		usesuggestdetail.setAttr("suggestercname", username);
		usesuggestdetail.setAttr("suggesttime", sf.format(new Date()));
		
		
		List<DynamicObject> usesuggestdetails = partydueusesuggestdetailService.findByCond(Cnd.where("suggestid", "=", id)); // 汇总意见
		// 权限设置
		set_author();
		
		// 查询可选路由
		String actdefid = ract.getFormatAttr("actdefid");
		List<DynamicObject> routes = partydueusesuggestService.getWorkFlowEngine().getFlowManager().getBrouteService().getRoutes(actdefid);

		ro.put("tableid", tableid);
		ro.put("runactkey", runactkey);
		ro.put("usesuggest", usesuggest);
		ro.put("usesuggestdetail", usesuggestdetail);
		ro.put("usesuggestdetails", usesuggestdetails);	
		ro.put("ract", ract);
		ro.put("routes", routes);
	}
	
	// 签收
	@At("/apply")
	@Ok("redirect:locate.action?runactkey=${obj.runactkey}")
	public Map apply(String runactkey) throws Exception
	{
		DynamicObject swapFlow = get_author_common();
		
		String loginname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		boolean sign = partydueusesuggestService.getWorkFlowEngine().getActManager().enableApplyNew(runactkey, loginname, DBFieldConstants.PUB_PARTICIPATOR_PERSON);

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

		partydueusesuggestService.getWorkFlowEngine().getActManager().vapply(vo);

		ro.put("runactkey", runactkey);

		return ro;

	}

	// 设置操作权限
	public void set_author() throws Exception
	{
		DynamicObject flowobj = get_author_common();

		// 以下为常用权限；
		boolean isread = partydueusesuggestService.isread(flowobj); // 是否只读页面
		boolean isedit = partydueusesuggestService.isedit(flowobj); // 是否修改页面

		boolean isapply = partydueusesuggestService.isapply(flowobj); // 可否签收
		boolean isforward = partydueusesuggestService.isforward(flowobj); // 可否转发
		boolean iscallback = partydueusesuggestService.iscallback(flowobj); // 可否收回
		boolean isbackward = partydueusesuggestService.isbackward(flowobj); // 可否退回

		boolean issave = partydueusesuggestService.issave(flowobj); // 可否保存
		boolean isdelete = partydueusesuggestService.isdelete(flowobj); // 可否删除

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
		DynamicObject ract = partydueusesuggestService.getWorkFlowEngine().getActManager().getRactService().locate(runactkey);
		String runflowkey = ract.getFormatAttr("runflowkey");
		String dataid = ract.getFormatAttr("dataid");
		
		swapFlow.setAttr(GlobalConstants.swap_dataid, dataid);
		swapFlow.setAttr(GlobalConstants.swap_runactkey, runactkey);
		swapFlow.setAttr(GlobalConstants.swap_runflowkey, runflowkey);
		
		swapFlow.setAttr(GlobalConstants.swap_tableid, tableid);

		return swapFlow;
	}
	

	

}
