package com.skynet.pams.app.party.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.pams.base.pojo.PartyDueCollectDept;

@InjectName("partyduecollectdeptService")
@IocBean(args = { "refer:dao" }) 
public class PartyDueCollectDeptService extends SkynetNameEntityService<PartyDueCollectDept>
{
	public PartyDueCollectDeptService()
	{
		super();
	}
	
	public PartyDueCollectDeptService(Dao dao)
	{
		super(dao);
	}	
	
	public PartyDueCollectDeptService(Dao dao, Class<PartyDueCollectDept> entityType)
	{
		super(dao, entityType);
	}
	

}
