package com.skynet.app.workflow.enginee;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.workflow.common.WFTimeDebug;
import com.skynet.app.workflow.pojo.LFlowAssApp;
import com.skynet.app.workflow.pojo.RAct;
import com.skynet.app.workflow.pojo.RFlow;
import com.skynet.app.workflow.service.BActService;
import com.skynet.app.workflow.service.BFlowService;
import com.skynet.app.workflow.service.BFormService;
import com.skynet.app.workflow.service.BRouteService;
import com.skynet.app.workflow.service.LEventActService;
import com.skynet.app.workflow.service.LEventFlowService;
import com.skynet.app.workflow.service.LEventRouteReceiverService;
import com.skynet.app.workflow.service.LEventRouteService;
import com.skynet.app.workflow.service.LEventService;
import com.skynet.app.workflow.service.LFlowAssAppService;
import com.skynet.app.workflow.service.RActAssorterService;
import com.skynet.app.workflow.service.RActHandlerService;
import com.skynet.app.workflow.service.RActOwnerService;
import com.skynet.app.workflow.service.RActService;
import com.skynet.app.workflow.service.RActTaskService;
import com.skynet.app.workflow.service.RFlowAuthorService;
import com.skynet.app.workflow.service.RFlowReaderService;
import com.skynet.app.workflow.service.RFlowService;
import com.skynet.app.workflow.service.WFWaitWorkService;
import com.skynet.app.workflow.spec.DBFieldConstants;
import com.skynet.app.workflow.spec.GlobalConstants;
import com.skynet.app.workflow.vo.VCreate;
import com.skynet.framework.common.generator.TimeGenerator;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;

@InjectName("flowManager")
@IocBean
public class FlowManager {
	
	protected DynamicObject swapFlow = new DynamicObject();
	
	@Inject
	private BActService bactService;
	
	@Inject
	private BFlowService bflowService;
	
	@Inject
	private BRouteService brouteService;	
	
	@Inject
	private BFormService bformService;
	
	@Inject
	private RFlowService rflowService;
	
	@Inject
	private RActService ractService;
	
	@Inject
	private RActHandlerService racthandlerService;
	
	@Inject
	private RActOwnerService ractownerService;
	
	@Inject
	private RActAssorterService ractassorterService;
	
	@Inject
	private RFlowAuthorService rflowauthorService;
	
	@Inject
	private RFlowReaderService rflowreaderService;
	
	@Inject
	private RActTaskService racttaskService;
	
	@Inject
	private LEventService leventService;
	
	@Inject
	private LEventFlowService leventflowService;
	
	@Inject
	private LEventActService leventactService;

	@Inject
	private LEventRouteService leventrouteService;
	
	@Inject
	private LEventRouteReceiverService leventroutereceiverService;

	@Inject
	private WFWaitWorkService waitworkService;
		
	@Inject
	private LFlowAssAppService lflowassappService;
	
	public String create(VCreate vo) throws Exception
	{
		String flowdefid = vo.flowdefid;
		String workname = vo.workname;
		String priority = vo.priority;
		String tableid = vo.tableid;
		String dataid = vo.dataid;
		
		String creater = vo.loginname;
		String creatercname = vo.username;
		String suprunflowkey = vo.suprunflowkey;
		String suprunactkey = vo.suprunactkey;
		
		String cno = vo.cno;
		String planid = vo.planid;
		
		String formid = bformService.select_fk_bflow(flowdefid).getAttr("id");
		
		WFTimeDebug.log("flow create begin time: ");
		// .新增流程实例记录
		// [查询该流程定义对应表单]
		
		RFlow rflow = new RFlow();
		rflow.setWorkname(workname);
		rflow.setFlowdefid(flowdefid);
		rflow.setState(DBFieldConstants.RFlOW_STATE_INITIATED);
		rflow.setPriority(priority);
		rflow.setTableid(tableid);
		rflow.setDataid(dataid);
		rflow.setFormid(formid);
		rflow.setCreater(creater);
		rflow.setSuprunflowkey(suprunflowkey);
		rflow.setSuprunactkey(suprunactkey);
		rflow.setPlanid(planid);
		String runflowkey = rflowService.create(rflow);
		// 新增待办功能

		// .新增流程实例的初始活动实例
		// BActService dao_bact = new BActService();

		// [查询初始活动定义]
		String id_begin = (bactService.select_bact_begin(flowdefid)).getAttr("id");
		// RActService dao_ract = new RActService();

		// [新增初始活动]
		String handletype = bactService.locate(id_begin).getAttr("handletype");
		
		RAct ract_begin = new RAct();
		ract_begin.setRunflowkey(runflowkey);
		ract_begin.setCreatetime(new Timestamp(System.currentTimeMillis()));
		ract_begin.setFlowdefid(flowdefid);
		ract_begin.setActdefid(id_begin);
		ract_begin.setState(DBFieldConstants.RACT_STATE_COMPLETED);
		ract_begin.setPriority(priority);
		ract_begin.setDataid(dataid);
		ract_begin.setTableid(tableid);
		ract_begin.setFormid(formid);
		ract_begin.setHandletype(handletype);
		
		// String runactkey_begin = bactService.create(runflowkey, TimeGenerator.getTimeStr(), flowdefid, id_begin, DBFieldConstants.RACT_STATE_COMPLETED, priority, dataid, formid, tableid, handletype);
		String runactkey_begin = ractService.create(ract_begin);
		
		// .新增流程实例的初始活动实例
		// [通过初始活动找到第一个活动]
		String id_first = (bactService.select_bact_first(flowdefid, id_begin)).getAttr("endactid");
		// [新增第一个活动]
		handletype = bactService.locate(id_first).getAttr("handletype");
		
		RAct ract = new RAct();
		ract.setRunflowkey(runflowkey);
		ract.setCreatetime(new Timestamp(System.currentTimeMillis()));
		ract.setFlowdefid(flowdefid);
		ract.setActdefid(id_first);
		ract.setState(DBFieldConstants.RACT_STATE_ACTIVE);
		ract.setTableid(tableid);
		ract.setDataid(dataid);
		ract.setHandletype(handletype);
		
		String runactkey_first = ractService.create(ract);
		// 增加设置最新可用标识功能 蒲剑 2014/08/06
		
		// 第一个活动的签收时间同创建时间;
		// ractService.set_apply_time(runactkey_first, tableid);
		ractService.sdao().update(RAct.class, Chain.make("applytime", new Timestamp(System.currentTimeMillis())), Cnd.where("runactkey", "=", runactkey_first));
		
		// .新增第一个活动的指定所有者
		// RActOwnerService dao_ractowner = new RActOwnerService();
		ractownerService.create(runactkey_first, creater, creatercname, DBFieldConstants.PUB_PARTICIPATOR_PERSON, "N", tableid, null,null,null);
		// .新增第一个活动的实际所有者
		// RActHandlerService dao_racthandle = new RActHandlerService();
		racthandlerService.create(runactkey_first, creater, creatercname, DBFieldConstants.PUB_PARTICIPATOR_PERSON, tableid);

		// .新增流程实例的作者
		// RFlowAuthorService dao_flowauthor = new RFlowAuthorService();
		DynamicObject ag = new DynamicObject();
		rflowauthorService.create(creater, creatercname, DBFieldConstants.PUB_PARTICIPATOR_PERSON, runflowkey, runactkey_first, DBFieldConstants.RFLOWAUTHOR_SOURCE_ACTDEF, tableid);
		// .新增流程实例的读者
		// RFlowReaderService dao_rflowreader = new RFlowReaderService();
		// [将流程定义读者添加到流程实例读者中]
		rflowreaderService.update_from_bact_reader(flowdefid, runflowkey, tableid);
		// [将当前用户添加到流程实例读者中]
		rflowreaderService.create(creater, creatercname, DBFieldConstants.PUB_PARTICIPATOR_PERSON, runflowkey, runactkey_first, DBFieldConstants.RFLOWREADER_SOURCE_ACTDEF, tableid);
		// 添加特殊权限用户
		appendSupUser(tableid, dataid);

		// .创建第一个活动的任务实例
		// RActTaskService dao_racttask = new RActTaskService();

		racttaskService.update_from_bact_task(id_first, runactkey_first, tableid);
		// .新增应用数据实例与流程实例的关联
		// [仅增加关联，流程读者和作者在流程完成后再写入关联表读者和作者中]
		// LFlowAssAppService dao_lflowassapp = new LFlowAssAppService();
		LFlowAssApp lflowassapp = new LFlowAssApp();
		lflowassapp.setRunflowkey(runflowkey);
		lflowassapp.setFormid(formid);
		lflowassapp.setDataid(dataid);
		lflowassapp.setTableid(tableid);
		lflowassapp.setCno(cno);
		lflowassapp.setWorkname(workname);
		lflowassapp.setFlowdefid(flowdefid);
		lflowassapp.setCreater(creater);
		lflowassapp.setCreatername(creatercname);
		
		lflowassappService.create(lflowassapp);

		// .新增待办
		String ownerorg = swapFlow.getFormatAttr(GlobalConstants.swap_coperatororgid);
		String jgcname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatororgcname);
		String ownerdept = swapFlow.getFormatAttr(GlobalConstants.swap_coperatordeptid);
		String bmcname = swapFlow.getFormatAttr(GlobalConstants.swap_coperatordeptcname);

		// WaitWorkDao dao_waitwork = new WaitWorkDao();
		// dao_waitwork.setStmt(stmt);
		// dao_waitwork.create("", id_first, "", "", "", tableid, dataid,
		// creater, "", creater, "", runactkey_first, jgcname, bmcname,
		// ownerorg, ownerdept);
		// createMsg_WaitWork(tableid, dataid, id_first, creater, creater, "N");

		// .新增事件日志记录
		// LEventService dao_levent = new LEventService();
		// LEventFlowService dao_leventflow = new LEventFlowService();
		// LEventActService dao_leventact = new LEventActService();

		String id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_FLOW, runflowkey);
		leventflowService.create(id_event, creater, DBFieldConstants.RFLOW_STATE_NULL, DBFieldConstants.RFlOW_STATE_INITIATED, flowdefid, runflowkey, DBFieldConstants.LEVENTFLOW_EVENTTYPE_CREATE);

		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_FLOW, runflowkey);
		leventflowService.create(id_event, creater, DBFieldConstants.RFlOW_STATE_INITIATED, DBFieldConstants.RFlOW_STATE_RUNNING, flowdefid, runflowkey, null);

		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
		String username = swapFlow.getAttr(GlobalConstants.swap_coperatorcname);
		String dept = swapFlow.getAttr(GlobalConstants.swap_coperatordeptid);
		String deptcname = swapFlow.getAttr(GlobalConstants.swap_coperatordeptcname);

		leventactService.create(id_event, creater, username, dept, deptcname, DBFieldConstants.RACT_STATE_NULL, DBFieldConstants.RACT_STATE_INACTIVE, id_begin, runactkey_begin, flowdefid, runflowkey);

		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
		leventactService.create(id_event, creater, username, dept, deptcname, DBFieldConstants.RACT_STATE_INACTIVE, DBFieldConstants.RACT_STATE_ACTIVE, id_begin, runactkey_begin, flowdefid, runflowkey);

		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
		leventactService.create(id_event, creater, username, dept, deptcname, DBFieldConstants.RACT_STATE_ACTIVE, DBFieldConstants.RACT_STATE_COMPLETED, id_begin, runactkey_begin, flowdefid, runflowkey);

		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ROUTE, runflowkey);
		// LEventRouteService dao_leventroute = new LEventRouteService();

		// dao_leventroute.create(id_event, flowdefid, runflowkey, creater,
		// id_first, runactkey_first, id_begin, runactkey_begin,
		// DBFieldConstants.LEVENTROUTE_EVENTTYPE_CREATE);
		leventrouteService.create(id_event, flowdefid, runflowkey, creater, creatercname, id_first, runactkey_first, id_begin, runactkey_begin, null);

		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
		leventactService.create(id_event, creater, username, dept, deptcname, DBFieldConstants.RACT_STATE_NULL, DBFieldConstants.RACT_STATE_INACTIVE, id_first, runactkey_first, flowdefid, runflowkey);

		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_ACT, runflowkey);
		leventactService.create(id_event, creater, username, dept, deptcname, DBFieldConstants.RACT_STATE_INACTIVE, DBFieldConstants.RACT_STATE_ACTIVE, id_first, runactkey_first, flowdefid, runflowkey);

		id_event = leventService.create(TimeGenerator.getTimeStr(), DBFieldConstants.LEVENT_EVENTTYPE_FLOW, runflowkey);
		leventflowService.create(id_event, creater, DBFieldConstants.RFlOW_STATE_RUNNING, DBFieldConstants.RFlOW_STATE_ACTIVE, flowdefid, runflowkey, null);

		// 返回流程实例标识
		WFTimeDebug.log("flow create end time: ");

		return runactkey_first;
	}
	
	/*
	 * 过程作者：蒲剑 过程名称：获取应用流程跟踪信息 参数说明： 过程说明：
	 */
	public List getFlowTraceInfo(String runflowkey) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id, a.eventtime, a.ceventtime, a.eventtype ");
		sql.append(" from t_sys_wflevent a, t_sys_wflflowassapp b ");
		sql.append(" where 1 = 1 ");
		sql.append(" and a.runflowkey = b.runflowkey ");
		sql.append(" and b.runflowkey = " + SQLParser.charValue(runflowkey));
		sql.append(" order by a.ceventtime ");

		List eventObjs = new ArrayList<List>();

		List events = rflowService.sdao().queryForList(sql.toString());

		int count_events = events.size();

		for (int i = 0; i < count_events; i++)
		{
			sql = new StringBuffer();
			DynamicObject o = (DynamicObject) events.get(i);
			String eventid = o.getFormatAttr("id");
			String eventtype = o.getFormatAttr("eventtype");
			System.out.println(i + ":" + eventtype);
			if (eventtype.equals(DBFieldConstants.LEVENT_EVENTTYPE_ROUTE))
			{
				
				sql
						.append("select 'ROUTE' eventstyle, a.id, a.eventer, case when a.eventer = '' then '系统管理员' else b.name end eventercname, a.eventdept, a.startactdefid, c.cname startactcname, a.startrunactkey, a.endactdefid, d.cname endactcname, a.endrunactkey, a.eventtype, g.eventtime \n");
				sql.append("  from t_sys_wfleventroute a, t_sys_wfperson b, t_sys_wfbact c, t_sys_wfbact d, t_sys_wflevent g \n");
				sql.append(" where 1 = 1 \n");
				sql.append("   and a.id = " + SQLParser.charValueRT(eventid));
				sql.append("   and a.id = g.id \n");
				sql.append("   and a.eventer = b.personid \n");
				sql.append("   and a.startactdefid = c.id \n");
				sql.append("   and a.endactdefid = d.id \n");

				DynamicObject event_act = rflowService.sdao().queryForMap(sql.toString());

				sql = new StringBuffer();
				sql.append("select a.id, a.receiver, a.receiverctype, \n");
				sql.append("  case when receiverctype='PERSON' then b.name \n");
				sql.append("       when receiverctype='ROLE' then c.name \n");
				sql.append("       when receiverctype='DEPT' then d.name \n");
				sql.append("       when receiverctype='DEPTROLE' then e.name \n");
				sql.append("   end receivercname \n");
				sql.append("  from t_sys_wfleventroute_receiver a \n");
				sql.append("  left join t_sys_wfperson b \n");
				sql.append("    on a.receiver = b.personid \n");
				sql.append("   and a.receiverctype = 'PERSON' \n");
				sql.append("  left join t_sys_wfrole c \n");
				sql.append("    on a.receiver = c.roleid \n");
				sql.append("   and a.receiverctype = 'ROLE' \n");
				sql.append("  left join t_sys_wfdepartment d \n");
				sql.append("    on a.receiver = d.deptid \n");
				sql.append("   and a.receiverctype = 'DEPT' \n");
				sql.append("  left join t_sys_wfdepartment e \n");
				sql.append("    on substr(a.receiver, 1, instr(a.receiver, ':') - 1) = e.deptid \n");
				sql.append("   and a.receiverctype = 'DEPTROLE' \n");
				sql.append(" where 1 = 1 \n");
				sql.append("   and a.eventid = " + SQLParser.charValueRT(eventid));

				List event_route_receivers = rflowService.sdao().queryForList(sql.toString());

//				List oneEvent = new ArrayList();
//				oneEvent.add(event_act);
//				oneEvent.add(event_route_receivers);
				
				DynamicObject oneEvent = new DynamicObject();
				oneEvent.setObj("a", event_act);
				oneEvent.setObj("b", event_route_receivers);

				eventObjs.add(oneEvent);
			}
			else if (eventtype.equals(DBFieldConstants.LEVENT_EVENTTYPE_ACT))
			{
				sql.append("select 'ACT' eventstyle, a.id, a.eventer, case when a.eventer = '' then '系统管理员' else b.name end eventercname, a.eventdept, a.actdefid, c.cname actcname, a.runactkey, a.eventtype, g.eventtime \n");
				sql.append("  from t_sys_wfleventact a, t_sys_wfperson b, t_sys_wfbact c, t_sys_wflevent g \n");
				sql.append(" where 1 = 1 \n");
				sql.append("   and a.id = " + SQLParser.charValueRT(eventid));
				sql.append("   and a.id = g.id \n");
				sql.append("   and a.eventer = b.personid \n");
				sql.append("   and a.eventer = b.personid \n");
				sql.append("   and a.actdefid = c.id \n");

				DynamicObject event_act = rflowService.sdao().queryForMap(sql.toString());

				sql = new StringBuffer();
				sql.append("select a.id, a.receiver, a.receiverctype, \n");
				sql.append("  case when receiverctype='PERSON' then b.name \n");
				sql.append("       when receiverctype='ROLE' then c.name \n");
				sql.append("       when receiverctype='DEPT' then d.name \n");
				sql.append("       when receiverctype='DEPTROLE' then e.name \n");
				sql.append("   end receivercname \n");
				sql.append("  from t_sys_wfleventact_receiver a \n");
				sql.append("  left join t_sys_wfperson b \n");
				sql.append("    on a.receiver = b.personid \n");
				sql.append("   and a.receiverctype = 'PERSON' \n");
				sql.append("  left join t_sys_wfrole c \n");
				sql.append("    on a.receiver = c.roleid \n");
				sql.append("   and a.receiverctype = 'ROLE' \n");
				sql.append("  left join t_sys_wfdepartment d \n");
				sql.append("    on a.receiver = d.deptid \n");
				sql.append("   and a.receiverctype = 'DEPT' \n");
				sql.append("  left join t_sys_wfdepartment e \n");
				sql.append("    on substr(a.receiver, 1, instr(a.receiver, ':') - 1) = e.deptid \n");
				sql.append("   and a.receiverctype = 'DEPTROLE' \n");
				sql.append(" where 1 = 1 \n");
				sql.append("   and a.eventid = " + SQLParser.charValueRT(eventid));

				List event_act_receivers = rflowService.sdao().queryForList(sql.toString());

//				List oneEvent = new ArrayList();
//				oneEvent.add(event_act);
//				oneEvent.add(event_act_receivers);
				
				DynamicObject oneEvent = new DynamicObject();
				oneEvent.setObj("a", event_act);
				oneEvent.setObj("b", event_act_receivers);

				eventObjs.add(oneEvent);
			}
			else if (eventtype.equals(DBFieldConstants.LEVENT_EVENTTYPE_FLOW))
			{
				sql.append("select 'FLOW' eventstyle, a.id, a.eventer, case when a.eventer = '' then '系统管理员' else b.name end eventercname, a.eventdept, a.flowdefid, c.cname flowcname, a.runflowkey, a.eventtype, g.eventtime \n");
				sql.append("  from t_sys_wfleventflow a, t_sys_wfperson b, t_sys_wfbflow c, t_sys_wflevent g \n");
				sql.append(" where 1 = 1 \n");
				sql.append("   and a.id = " + SQLParser.charValueRT(eventid));
				sql.append("   and a.id = g.id \n");
				sql.append("   and a.eventer = b.personid \n");
				sql.append("   and a.eventer = b.personid \n");
				sql.append("   and a.flowdefid = c.id \n");

				DynamicObject event_act = rflowService.sdao().queryForMap(sql.toString());

				List event_act_receivers = new ArrayList();

				// System.out.println("flow receiver size:" +
				// event_act_receivers.size());

//				List oneEvent = new ArrayList();
//				oneEvent.add(event_act);
//				oneEvent.add(event_act_receivers);
				
				DynamicObject oneEvent = new DynamicObject();
				oneEvent.setObj("a", event_act);
				oneEvent.setObj("b", event_act_receivers);

				eventObjs.add(oneEvent);
			}

		}

		return eventObjs;
	}
	
	
	public void appendSupUser(String tableid, String dataid) throws Exception
	{

	}
	
	public List<DynamicObject> sub_plans(String runactkey) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		List<DynamicObject> subplans = new ArrayList<DynamicObject>();
		
		sql.append(" select ract.runactkey id, ractowner.organname cname, ract.actdefid, ract.applytime actualstartdate, ract.completetime actualenddate, ractowner.cname actualheadercname ").append("\n");
		sql.append("   from t_sys_wfract ract, t_sys_wfbact bact, t_sys_wfractowner ractowner ").append("\n");
		sql.append("  where 1 = 1").append("\n");
		sql.append("    and bact.id = ract.actdefid ").append("\n");
		sql.append("    and ractowner.runactkey = ract.runactkey ").append("\n");
		sql.append("    and suprunactkey = ").append(SQLParser.charValue(runactkey));
		
		subplans = ractService.queryForList(sql.toString());
		
		return subplans;
	}
	

	public DynamicObject getSwapFlow() {
		return swapFlow;
	}

	public void setSwapFlow(DynamicObject swapFlow) {
		this.swapFlow = swapFlow;
	}

	public BActService getBactService() {
		return bactService;
	}

	public void setBactService(BActService bactService) {
		this.bactService = bactService;
	}

	public BFlowService getBflowService() {
		return bflowService;
	}

	public void setBflowService(BFlowService bflowService) {
		this.bflowService = bflowService;
	}

	public BRouteService getBrouteService() {
		return brouteService;
	}

	public void setBrouteService(BRouteService brouteService) {
		this.brouteService = brouteService;
	}

	public BFormService getBformService() {
		return bformService;
	}

	public void setBformService(BFormService bformService) {
		this.bformService = bformService;
	}

	public RFlowService getRflowService() {
		return rflowService;
	}

	public void setRflowService(RFlowService rflowService) {
		this.rflowService = rflowService;
	}

	public RActService getRactService() {
		return ractService;
	}

	public void setRactService(RActService ractService) {
		this.ractService = ractService;
	}

	public RActHandlerService getRacthandlerService() {
		return racthandlerService;
	}

	public void setRacthandlerService(RActHandlerService racthandlerService) {
		this.racthandlerService = racthandlerService;
	}

	public RActOwnerService getRactownerService() {
		return ractownerService;
	}

	public void setRactownerService(RActOwnerService ractownerService) {
		this.ractownerService = ractownerService;
	}

	public RActAssorterService getRactassorterService() {
		return ractassorterService;
	}

	public void setRactassorterService(RActAssorterService ractassorterService) {
		this.ractassorterService = ractassorterService;
	}

	public RFlowAuthorService getRflowauthorService() {
		return rflowauthorService;
	}

	public void setRflowauthorService(RFlowAuthorService rflowauthorService) {
		this.rflowauthorService = rflowauthorService;
	}

	public RFlowReaderService getRflowreaderService() {
		return rflowreaderService;
	}

	public void setRflowreaderService(RFlowReaderService rflowreaderService) {
		this.rflowreaderService = rflowreaderService;
	}

	public RActTaskService getRacttaskService() {
		return racttaskService;
	}

	public void setRacttaskService(RActTaskService racttaskService) {
		this.racttaskService = racttaskService;
	}

	public LEventService getLeventService() {
		return leventService;
	}

	public void setLeventService(LEventService leventService) {
		this.leventService = leventService;
	}

	public LEventFlowService getLeventflowService() {
		return leventflowService;
	}

	public void setLeventflowService(LEventFlowService leventflowService) {
		this.leventflowService = leventflowService;
	}

	public LEventActService getLeventactService() {
		return leventactService;
	}

	public void setLeventactService(LEventActService leventactService) {
		this.leventactService = leventactService;
	}

	public LEventRouteService getLeventrouteService() {
		return leventrouteService;
	}

	public void setLeventrouteService(LEventRouteService leventrouteService) {
		this.leventrouteService = leventrouteService;
	}

	public LEventRouteReceiverService getLeventroutereceiverService() {
		return leventroutereceiverService;
	}

	public void setLeventroutereceiverService(
			LEventRouteReceiverService leventroutereceiverService) {
		this.leventroutereceiverService = leventroutereceiverService;
	}

	public WFWaitWorkService getWaitworkService() {
		return waitworkService;
	}

	public void setWaitworkService(WFWaitWorkService waitworkService) {
		this.waitworkService = waitworkService;
	}

	public LFlowAssAppService getLflowassappService() {
		return lflowassappService;
	}

	public void setLflowassappService(LFlowAssAppService lflowassappService) {
		this.lflowassappService = lflowassappService;
	}
	
	
}
