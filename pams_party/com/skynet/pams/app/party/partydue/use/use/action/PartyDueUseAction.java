package com.skynet.pams.app.party.partydue.use.use.action;

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
import org.nutz.mvc.annotation.Param;

import com.skynet.app.organ.service.OrganService;
import com.skynet.app.organ.service.UserService;
import com.skynet.app.workflow.spec.DBFieldConstants;
import com.skynet.app.workflow.spec.GlobalConstants;
import com.skynet.app.workflow.ui.action.ActionHelper;
import com.skynet.app.workflow.vo.VApply;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.pams.app.party.service.PartyDueUseDetailService;
import com.skynet.pams.app.party.service.PartyDueUseService;
import com.skynet.pams.app.plan.service.PlanService;
import com.skynet.pams.base.pojo.PartyDueUse;
import com.skynet.pams.base.pojo.PartyDueUseDetail;
import com.skynet.pams.base.pojo.Plan;

@IocBean
@At("/party/partydue/use/use")
public class PartyDueUseAction extends BaseAction {

	@Inject
	private PartyDueUseService partydueuseService;
	
	@Inject
	private PartyDueUseDetailService partydueusedetailService;
	
	@Inject
	private PlanService planService;
	
	@Inject
	private UserService userService;	
	
	@Inject
	private OrganService organService;	
	
	public static String tableid = "PartyDueUse";
	
	@At("/mainframe")
	@Ok("->:/page/party/partydue/use/use/mainframe.ftl")
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
		PartyDueUse use = new PartyDueUse();
		use.setId(UUIDGenerator.getInstance().getNextValue());
		use.setCname(plan.getCname());
		use.setCreatetime(nowtime);
		use.setCreater(loginname);
		use.setCreatercname(username);
		use.setCyear((new GregorianCalendar()).get(Calendar.YEAR));
		
		DynamicObject swapFlow = new DynamicObject();
		swapFlow.setAttr(GlobalConstants.swap_coperatorid, userid);
		swapFlow.setAttr(GlobalConstants.swap_coperatorloginname, loginname);
		swapFlow.setAttr(GlobalConstants.swap_coperatorcname, username);
		swapFlow.setAttr(GlobalConstants.swap_flowdefid, flowdefid);
		swapFlow.setAttr(GlobalConstants.swap_tableid, tableid);

		String runactkey = partydueuseService.plancreate(plan, use, swapFlow);
		
		ro.put("runactkey", runactkey);
		return ro;
	}
	
	@At("/browseplan")
	@Ok("->:/page/party/partydue/use/use/browseplan.ftl")
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

		List datas = partydueuseService.sdao().queryForList(
				partydueuseService.get_browseplan_sql(amap));

		return datas; // 后期改为翻页对象
	}

	@At("/browsewait")
	@Ok("->:/page/party/partydue/use/use/browsewait.ftl")
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

		List datas = partydueuseService.sdao().queryForList(
				partydueuseService.get_browsewait_sql(amap));

		return datas; // 后期改为翻页对象
	}
	
	@At("/browsehandle")
	@Ok("->:/page/party/partydue/use/use/browsehandle.ftl")
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

		List datas = partydueuseService.sdao().queryForList(
				partydueuseService.get_browsehandle_sql(amap));

		return datas; // 后期改为翻页对象
	}
	
	@At("/browseall")
	@Ok("->:/page/party/partydue/use/use/browseall.ftl")
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

		List datas = partydueuseService.sdao().queryForList(
				partydueuseService.get_browseall_sql(amap));

		return datas; // 后期改为翻页对象
	}
	
	@At("/readpage")
	@Ok("->:/page/party/partydue/use/use/readpage.ftl")
	public Map readpage(String runactkey) throws Exception {
		loc(runactkey);
		return ro;
	}

	@At("/locate")
	@Ok("->:/page/party/partydue/use/use/locate.ftl")
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
		
		DynamicObject ract = partydueuseService.getWorkFlowEngine().getActManager()
				.getRactService().locate(runactkey);
		String id = ract.getFormatAttr("dataid");
		String tableid = ract.getFormatAttr("tableid");
		DynamicObject use = partydueuseService.locate(id);
		DynamicObject usedetail = partydueusedetailService.locateBy(Cnd.where("id", "=", id).and("chargedeptid", "=", deptid));	
		
		usedetail.setAttr("masterdeptid", deptid);
		usedetail.setAttr("deptname", deptname);
		usedetail.setAttr("er", loginname);
		usedetail.setAttr("ercname", username);
		usedetail.setAttr("time", sf.format(new Date()));
		
		
		List<DynamicObject> usedetails = partydueusedetailService.findByCond(Cnd.where("id", "=", id)); // 汇总意见
		// 权限设置
		set_author();
		
		// 查询可选路由
		String actdefid = ract.getFormatAttr("actdefid");
		List<DynamicObject> routes = partydueuseService.getWorkFlowEngine().getFlowManager().getBrouteService().getRoutes(actdefid);

		ro.put("tableid", tableid);
		ro.put("runactkey", runactkey);
		ro.put("use", use);
		ro.put("useid", use.getAttr("id"));
		ro.put("usedetail", usedetail);
		ro.put("usedetails", usedetails);	
		ro.put("ract", ract);
		ro.put("routes", routes);
		
		List<DynamicObject> users = userService.findByCond(Cnd.where("1","=","1").orderBy().asc("cname"));
		List<DynamicObject> depts = organService.findByCond(Cnd.where("ctype","=","DEPT").orderBy().asc("internal"));
		ro.setObj("users", users);
		ro.setObj("depts", depts);
	}
	
	@At("/inputdetail")
	@Ok("->:/page/party/partydue/use/use/inputdetail.ftl")
	public Map usedetailinput(String id) throws Exception 
	{
		
		ro.put("useid", id);
		ro.put("isbackward", true);
		return ro;
		
	}
	
	@At("/savedetail")
	@Ok("redirect:locatedetail.action?id=${obj.id}")
	public Map savedetail(@Param("..") PartyDueUseDetail usedetail) throws Exception 
	{
		partydueusedetailService.sdao().update(usedetail);
		ro.put("id", usedetail.getId());
		return ro;
		
	}
	
	@At("/insertdetail")
	@Ok("redirect:locatedetail.action?id=${obj.id}")
	public Map insertdetail(@Param("..") PartyDueUseDetail usedetail) throws Exception 
	{
		String id = UUIDGenerator.getInstance().getNextValue();
		usedetail.setId(id);
		partydueusedetailService.sdao().insert(usedetail);
		ro.put("id", id);
		
		return ro;
		
	}
	
	@At("/locatedetail")
	@Ok("->:/page/party/partydue/use/use/locatedetail.ftl")
	public Map locatedetail(String id) throws Exception 
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject token = (DynamicObject)session.getAttribute(com.skynet.framework.spec.GlobalConstants.sys_login_token);
		String loginname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_user);
		String username = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_username);		
		String deptid = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_dept);
		String deptname = token.getFormatAttr(com.skynet.framework.spec.GlobalConstants.sys_login_deptname);

		DynamicObject usedetail = partydueusedetailService.locate(id);
		
		ro.put("usedetail", usedetail);
		ro.put("usedetailid", id);
		ro.put("useid", usedetail.getAttr("id"));
		ro.put("isedit", true);
		ro.put("isapply", false);
		ro.put("iscallback", false);
		ro.put("isforward", true);

		ro.put("isbackward", true);
		
		return ro;
		
	}
	
	
	// 签收
	@At("/apply")
	@Ok("redirect:locate.action?runactkey=${obj.runactkey}")
	public Map apply(String runactkey) throws Exception
	{
		DynamicObject swapFlow = get_author_common();
		
		String loginname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatorloginname);
		boolean sign = partydueuseService.getWorkFlowEngine().getActManager().enableApplyNew(runactkey, loginname, DBFieldConstants.PUB_PARTICIPATOR_PERSON);

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

		partydueuseService.getWorkFlowEngine().getActManager().vapply(vo);

		ro.put("runactkey", runactkey);

		return ro;

	}

	// 设置操作权限
	public void set_author() throws Exception
	{
		DynamicObject flowobj = get_author_common();

		// 以下为常用权限；
		boolean isread = partydueuseService.isread(flowobj); // 是否只读页面
		boolean isedit = partydueuseService.isedit(flowobj); // 是否修改页面

		boolean isapply = partydueuseService.isapply(flowobj); // 可否签收
		boolean isforward = partydueuseService.isforward(flowobj); // 可否转发
		boolean iscallback = partydueuseService.iscallback(flowobj); // 可否收回
		boolean isbackward = partydueuseService.isbackward(flowobj); // 可否退回

		boolean issave = partydueuseService.issave(flowobj); // 可否保存
		boolean isdelete = partydueuseService.isdelete(flowobj); // 可否删除

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
		DynamicObject ract = partydueuseService.getWorkFlowEngine().getActManager().getRactService().locate(runactkey);
		String runflowkey = ract.getFormatAttr("runflowkey");
		String dataid = ract.getFormatAttr("dataid");
		
		swapFlow.setAttr(GlobalConstants.swap_dataid, dataid);
		swapFlow.setAttr(GlobalConstants.swap_runactkey, runactkey);
		swapFlow.setAttr(GlobalConstants.swap_runflowkey, runflowkey);
		
		swapFlow.setAttr(GlobalConstants.swap_tableid, tableid);

		return swapFlow;
	}
	

	

}
