package com.skynet.pams.base.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

// 党费收缴预算表
@Table("T_APP_PDCOLLDEPT")
public class PartyDueCollectDept {
	@Name
	private String id; // 标识

	@Column
	private String collectid; // 缴费登记表标识
	
	@Column
	private String colldeptid; // 收缴单位部门标识
	
	@Column
	private String colldeptname; // 收缴单位部门名称

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCollectid()
	{
		return collectid;
	}

	public void setCollectid(String collectid)
	{
		this.collectid = collectid;
	}

	public String getColldeptid()
	{
		return colldeptid;
	}

	public void setColldeptid(String colldeptid)
	{
		this.colldeptid = colldeptid;
	}

	public String getColldeptname()
	{
		return colldeptname;
	}

	public void setColldeptname(String colldeptname)
	{
		this.colldeptname = colldeptname;
	}
	
	
}
