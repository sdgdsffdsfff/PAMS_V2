package com.skynet.pams.app.party.service;

import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.pams.base.pojo.PartyDueCollectDetail;

@InjectName("partyduecollectdetailService")
@IocBean(args = { "refer:dao" }) 
public class PartyDueCollectDetailService extends SkynetNameEntityService<PartyDueCollectDetail>
{
	public PartyDueCollectDetailService()
	{
		super();
	}
	
	public PartyDueCollectDetailService(Dao dao)
	{
		super(dao);
	}	
	
	public PartyDueCollectDetailService(Dao dao, Class<PartyDueCollectDetail> entityType)
	{
		super(dao, entityType);
	}
	
	// 保存基数核准数据
	public void savedeptdetails(Map map) throws Exception
	{
		String collectid = (String)map.get("collectid");
		String colldeptid = (String)map.get("colldeptid");
		String[] collusers = (String[])map.get("collusers");
		float[] basecosts = (float[])map.get("basecosts");
		float[] rates = (float[])map.get("rates");
		float[] plancollcosts = (float[])map.get("plancollcosts");
		float[] actualcollcosts = (float[])map.get("actualcollcosts");
		
		sdao().clear("t_app_pdcolldetail", Cnd.where("collectid", "=", collectid).and("colldeptid", "=", colldeptid)); // 清除部门原有数据
		
		for(int i=0;i<collusers.length;i++)
		{
			PartyDueCollectDetail p = new PartyDueCollectDetail();
			String id = UUIDGenerator.getInstance().getNextValue();
			p.setId(id);
			p.setColldeptid(colldeptid);
			p.setCollectid(collectid);
			p.setColluser(collusers[i]);
			p.setBasecost(basecosts[i]);
			p.setRate(rates[i]);
			p.setPlancollcost(plancollcosts[i]);
			p.setActualcollcost(actualcollcosts[i]);
			sdao().insert(p);
		}
	}
	
}
