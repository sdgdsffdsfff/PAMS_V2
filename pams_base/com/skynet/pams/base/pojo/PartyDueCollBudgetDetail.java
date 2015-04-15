package com.skynet.pams.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

//党费收缴预算明细表
@Table("T_APP_PDCOLLBUDGETDETAIL")
public class PartyDueCollBudgetDetail {
	@Name
	private String id; // 标识

	@Column
	private String collbudgetid; // 预算表标识
	
	@Column
	private String colldeptid; // 收缴单位部门标识
	
	@Column
	private String colldeptname; // 收缴单位部门名称
	
	@Column
	private Timestamp colltime; // 预算计划收缴时间
	
	@Column
	private float collcost1; // 收取预算费用1
	
	@Column
	private float collcost2; // 收取预算费用2
	
	@Column
	private float collcost3; // 收取预算费用3	
	
	@Column
	private float collcost4; // 收取预算费用4

	@Column
	private float collcost5; // 收取预算费用5
	
	@Column
	private float turncost1; // 上缴预算费用1	
	
	@Column
	private float turncost2; // 上缴预算费用2	

	@Column
	private String memo; // 备注

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCollbudgetid()
	{
		return collbudgetid;
	}

	public void setCollbudgetid(String collbudgetid)
	{
		this.collbudgetid = collbudgetid;
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

	public Timestamp getColltime()
	{
		return colltime;
	}

	public void setColltime(Timestamp colltime)
	{
		this.colltime = colltime;
	}

	public float getCollcost1()
	{
		return collcost1;
	}

	public void setCollcost1(float collcost1)
	{
		this.collcost1 = collcost1;
	}

	public float getCollcost2()
	{
		return collcost2;
	}

	public void setCollcost2(float collcost2)
	{
		this.collcost2 = collcost2;
	}

	public float getCollcost3()
	{
		return collcost3;
	}

	public void setCollcost3(float collcost3)
	{
		this.collcost3 = collcost3;
	}

	public float getCollcost4()
	{
		return collcost4;
	}

	public void setCollcost4(float collcost4)
	{
		this.collcost4 = collcost4;
	}

	public float getCollcost5()
	{
		return collcost5;
	}

	public void setCollcost5(float collcost5)
	{
		this.collcost5 = collcost5;
	}

	public float getTurncost1()
	{
		return turncost1;
	}

	public void setTurncost1(float turncost1)
	{
		this.turncost1 = turncost1;
	}

	public float getTurncost2()
	{
		return turncost2;
	}

	public void setTurncost2(float turncost2)
	{
		this.turncost2 = turncost2;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}
	
}
