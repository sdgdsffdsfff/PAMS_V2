package com.skynet.pams.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

// 党费基准核准表
@Table("T_APP_PDBASE")
public class PartyDueBase {
	@Name
	private String id; // 标识

	@Column
	private String cno; // 单号

	@Column
	private String deptid; // 部门标识
	
	@Column
	private String deptname; // 部门标识
	
	@Column
	private String user; // 人员标识

	@Column
	private String username; // 人员姓名
	
	@Column
	private float base1; // 基数1
	
	@Column
	private float base2; // 基数2

	@Column
	private float base3; // 基数2
	
	@Column
	private float base4; // 基数4

	@Column
	private float base5; // 基数5

	@Column
	private String planid; // 计划标识

	@Column
	private String flowdefid; // 流程定义标识

	@Column
	@ColDefine(type = ColType.DATETIME)
	private Timestamp createtime; // 创建时间

	@Column
	private String creater; // 创建人用户名

	@Column
	private String creatercname; // 创建人姓名

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCno()
	{
		return cno;
	}

	public void setCno(String cno)
	{
		this.cno = cno;
	}

	public String getDeptid()
	{
		return deptid;
	}

	public void setDeptid(String deptid)
	{
		this.deptid = deptid;
	}

	public String getDeptname()
	{
		return deptname;
	}

	public void setDeptname(String deptname)
	{
		this.deptname = deptname;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public float getBase1()
	{
		return base1;
	}

	public void setBase1(float base1)
	{
		this.base1 = base1;
	}

	public float getBase2()
	{
		return base2;
	}

	public void setBase2(float base2)
	{
		this.base2 = base2;
	}

	public float getBase3()
	{
		return base3;
	}

	public void setBase3(float base3)
	{
		this.base3 = base3;
	}

	public float getBase4()
	{
		return base4;
	}

	public void setBase4(float base4)
	{
		this.base4 = base4;
	}

	public float getBase5()
	{
		return base5;
	}

	public void setBase5(float base5)
	{
		this.base5 = base5;
	}

	public String getPlanid()
	{
		return planid;
	}

	public void setPlanid(String planid)
	{
		this.planid = planid;
	}

	public String getFlowdefid()
	{
		return flowdefid;
	}

	public void setFlowdefid(String flowdefid)
	{
		this.flowdefid = flowdefid;
	}

	public Timestamp getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Timestamp createtime)
	{
		this.createtime = createtime;
	}

	public String getCreater()
	{
		return creater;
	}

	public void setCreater(String creater)
	{
		this.creater = creater;
	}

	public String getCreatercname()
	{
		return creatercname;
	}

	public void setCreatercname(String creatercname)
	{
		this.creatercname = creatercname;
	}

}
