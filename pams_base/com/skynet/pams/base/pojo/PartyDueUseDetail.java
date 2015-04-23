package com.skynet.pams.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

//党费使用计划明细表
@Table("T_APP_PDUSEDETAIL")
public class PartyDueUseDetail {
	@Name
	private String id; // 标识

	@Column
	private String usebudgetid; // 预算表标识
	
	@Column
	private String cname; // 项目名称
	
	@Column
	private Timestamp plantime; // 立项时间
	
	@Column
	private Timestamp starttime; // 开始时间

	@Column
	private Timestamp endtime; // 结束时间	
	
	@Column
	private float cost; // 计划费用
	
	@Column
	private float costsource1; // 资金来源1
	
	@Column
	private float costsource2; // 资金来源2
	
	@Column
	private String context; // 项目内容	
	
	@Column
	private String charger; // 项目负责人
	
	@Column
	private String chargername; // 项目负责人姓名
	
	@Column
	private String leader; // 主管领导
	
	@Column
	private String leadername; // 主管领导姓名
	
	@Column
	private String chargedeptid; // 主管部门标识
	
	@Column
	private String chargedeptname; // 主管部门名称
	
	@Column
	private String masterdeptid; // 主办部门标识
	
	@Column
	private String masterdeptname; // 主办部门名称
	
	@Column
	private String salvedeptid; // 协办部门标识
	
	@Column
	private String slavedeptname; // 协办部门名称
	
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

	public String getUsebudgetid()
	{
		return usebudgetid;
	}

	public void setUsebudgetid(String usebudgetid)
	{
		this.usebudgetid = usebudgetid;
	}

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public Timestamp getPlantime()
	{
		return plantime;
	}

	public void setPlantime(Timestamp plantime)
	{
		this.plantime = plantime;
	}

	public Timestamp getStarttime()
	{
		return starttime;
	}

	public void setStarttime(Timestamp starttime)
	{
		this.starttime = starttime;
	}

	public Timestamp getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Timestamp endtime)
	{
		this.endtime = endtime;
	}

	public float getCost()
	{
		return cost;
	}

	public void setCost(float cost)
	{
		this.cost = cost;
	}

	public float getCostsource1()
	{
		return costsource1;
	}

	public void setCostsource1(float costsource1)
	{
		this.costsource1 = costsource1;
	}

	public float getCostsource2()
	{
		return costsource2;
	}

	public void setCostsource2(float costsource2)
	{
		this.costsource2 = costsource2;
	}

	public String getContext()
	{
		return context;
	}

	public void setContext(String context)
	{
		this.context = context;
	}

	public String getCharger()
	{
		return charger;
	}

	public void setCharger(String charger)
	{
		this.charger = charger;
	}

	public String getChargername()
	{
		return chargername;
	}

	public void setChargername(String chargername)
	{
		this.chargername = chargername;
	}

	public String getLeader()
	{
		return leader;
	}

	public void setLeader(String leader)
	{
		this.leader = leader;
	}

	public String getLeadername()
	{
		return leadername;
	}

	public void setLeadername(String leadername)
	{
		this.leadername = leadername;
	}

	public String getChargedeptid()
	{
		return chargedeptid;
	}

	public void setChargedeptid(String chargedeptid)
	{
		this.chargedeptid = chargedeptid;
	}

	public String getChargedeptname()
	{
		return chargedeptname;
	}

	public void setChargedeptname(String chargedeptname)
	{
		this.chargedeptname = chargedeptname;
	}

	public String getMasterdeptid()
	{
		return masterdeptid;
	}

	public void setMasterdeptid(String masterdeptid)
	{
		this.masterdeptid = masterdeptid;
	}

	public String getMasterdeptname()
	{
		return masterdeptname;
	}

	public void setMasterdeptname(String masterdeptname)
	{
		this.masterdeptname = masterdeptname;
	}

	public String getSalvedeptid()
	{
		return salvedeptid;
	}

	public void setSalvedeptid(String salvedeptid)
	{
		this.salvedeptid = salvedeptid;
	}

	public String getSlavedeptname()
	{
		return slavedeptname;
	}

	public void setSlavedeptname(String slavedeptname)
	{
		this.slavedeptname = slavedeptname;
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
