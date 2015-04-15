package com.skynet.pams.app.party.service;

import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.pams.base.pojo.PartyDueBaseDetail;

@InjectName("partyduebasedetailService")
@IocBean(args = { "refer:dao" }) 
public class PartyDueBaseDetailService extends SkynetNameEntityService<PartyDueBaseDetail>
{
	public PartyDueBaseDetailService()
	{
		super();
	}
	
	public PartyDueBaseDetailService(Dao dao)
	{
		super(dao);
	}	
	
	public PartyDueBaseDetailService(Dao dao, Class<PartyDueBaseDetail> entityType)
	{
		super(dao, entityType);
	}
	
	// 保存基数核准数据
	public void savedeptbasedetails(Map map) throws Exception
	{
		String baseid = (String)map.get("baseid");
		String deptid = (String)map.get("deptid");
		String[] baseusers = (String[])map.get("baseusers");
		float[] base1s = (float[])map.get("base1s");
		float[] base2s = (float[])map.get("base2s");
		float[] base3s = (float[])map.get("base3s");
		float[] base4s = (float[])map.get("base4s");
		float[] base5s = (float[])map.get("base5s");
		
		sdao().clear("t_app_pdbasedetail", Cnd.where("baseid", "=", baseid).and("deptid", "=", deptid)); // 清除原有的部门数据
		
		for(int i=0;i<base1s.length;i++)
		{
			PartyDueBaseDetail p = new PartyDueBaseDetail();
			String id = UUIDGenerator.getInstance().getNextValue();
			p.setId(id);
			p.setBaseid(baseid);
			p.setDeptid(deptid);
			p.setBaseuser(baseusers[i]);
			p.setBase1(base1s[i]);
			p.setBase2(base2s[i]);
			p.setBase3(base3s[i]);
			p.setBase4(base4s[i]);
			// p.setBase5(base5s[i]);
			sdao().insert(p);
		}
	}
	
	


	
	
}
