package com.skynet.pams.app.party.service;

import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.pams.base.pojo.PartyDueCollectBudgetDetail;

@InjectName("partyduecollectbudgetdetailService")
@IocBean(args = { "refer:dao" }) 
public class PartyDueCollectBudgetDetailService extends SkynetNameEntityService<PartyDueCollectBudgetDetail>
{
	public PartyDueCollectBudgetDetailService()
	{
		super();
	}
	
	public PartyDueCollectBudgetDetailService(Dao dao)
	{
		super(dao);
	}	
	
	public PartyDueCollectBudgetDetailService(Dao dao, Class<PartyDueCollectBudgetDetail> entityType)
	{
		super(dao, entityType);
	}
	
	// 保存基数核准数据
	public void savedeptbudgetdetails(Map map) throws Exception
	{
		String collectbudgetid = (String)map.get("collectbudgetid");
		String[] deptids = (String[])map.get("deptids");
		float[] collcost1s = (float[])map.get("collcost1s");
		float[] collcost2s = (float[])map.get("collcost2s");
		float[] collcost3s = (float[])map.get("collcost3s");
		float[] turncost1s = (float[])map.get("turncost1s");
		float[] turncost2s = (float[])map.get("turncost2s");
		
		sdao().clear("t_app_pdcollbudgetdetail", Cnd.where("collbudgetid", "=", collectbudgetid)); // 清除原有的部门数据
		
		for(int i=0;i<deptids.length;i++)
		{
			PartyDueCollectBudgetDetail p = new PartyDueCollectBudgetDetail();
			String id = UUIDGenerator.getInstance().getNextValue();
			p.setId(id);
			p.setCollbudgetid(collectbudgetid);
			p.setColldeptid(deptids[i]);
			p.setCollcost1(collcost1s[i]);
			p.setCollcost2(collcost2s[i]);
			p.setCollcost3(collcost3s[i]);
			p.setTurncost1(turncost1s[i]);
			p.setTurncost2(turncost2s[i]);
		
			sdao().insert(p);
		}
	}
	
}
