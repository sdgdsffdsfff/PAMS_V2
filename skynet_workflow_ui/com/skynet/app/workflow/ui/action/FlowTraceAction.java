package com.skynet.app.workflow.ui.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.skynet.app.workflow.enginee.WorkFlowEngine;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;

@IocBean
@At("/workflow/ui")
public class FlowTraceAction extends BaseAction {
	@Inject
	private WorkFlowEngine workFlowEngine;

	@At("/flowtrace")
	@Ok("->:/page/workflow/ui/flowtracesdetail.ftl")
	public Map execute() throws Exception {

		HttpServletRequest req = Mvcs.getReq();

		String runactkey = req.getParameter("runactkey");
		String tableid = req.getParameter("tableid");
		DynamicObject ract = workFlowEngine.getActManager().getRactService()
				.locate(runactkey);
		String runflowkey = ract.getFormatAttr("runflowkey");
		String actdefid = ract.getFormatAttr("actdefid");
		List traces = workFlowEngine.getFlowManager().getFlowTraceInfo(
				runflowkey);
		for(int i=0;i<traces.size();i++)
		{
			System.out.println(traces.get(i));
		}
		
		
		List test = new ArrayList();
		for(int i=0;i<10;i++)
		{
			DynamicObject a = new DynamicObject();
			a.setAttr("cno", i+":test");
			test.add(a);
		}
		ro.put("runflowkey", runflowkey);
		ro.put("actdefid", actdefid);
		ro.put("bean_traces", traces);
		ro.put("test", test);

		return ro;
	}

}
