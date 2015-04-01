package com.skynet.app.workflow.service;

import java.sql.Timestamp;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.workflow.pojo.LEvent;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;

@InjectName("leventService")
@IocBean(args = { "refer:dao" })
public class LEventService extends SkynetNameEntityService<LEvent> {
	public LEventService() {
		super();
	}

	public LEventService(Dao dao) {
		super(dao);
	}

	public LEventService(Dao dao, Class<LEvent> entityType) {
		super(dao, entityType);
	}
	
	public String create(String eventtime, String eventtype, String runflowkey) throws Exception
	{
		String id = UUIDGenerator.getInstance().getNextValue(); //String.valueOf(System.nanoTime()); ;
		
		LEvent entity = new LEvent();
		entity.setId(id);
		entity.setEventtime(new Timestamp(System.currentTimeMillis()));
		entity.setCeventtime(eventtime);
		entity.setEventtype(eventtype);
		entity.setRunflowkey(runflowkey);
		
		sdao().insert(entity);
		
		return id;
	}	
}
