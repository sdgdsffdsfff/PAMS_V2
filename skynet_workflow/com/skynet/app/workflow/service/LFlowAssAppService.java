package com.skynet.app.workflow.service;

import java.sql.Timestamp;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.workflow.pojo.LFlowAssApp;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;

@InjectName("lflowassappService")
@IocBean(args = { "refer:dao" })
public class LFlowAssAppService extends SkynetNameEntityService<LFlowAssApp> {
	public LFlowAssAppService() {
		super();
	}

	public LFlowAssAppService(Dao dao) {
		super(dao);
	}

	public LFlowAssAppService(Dao dao, Class<LFlowAssApp> entityType) {
		super(dao, entityType);
	}
	
	public String create(LFlowAssApp entity) throws Exception
	{
		String id = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
		entity.setId(id);
		Timestamp createtime = new Timestamp(System.currentTimeMillis());
		entity.setCreatetime(createtime);
		sdao().insert(entity);
		return entity.getId();
	}
}
