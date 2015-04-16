package com.skynet.pams.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

//党费收缴预算明细表
@Table("T_APP_PDCOLLDETAIL")
public class PartyDueCollectDetail {
	@Name
	private String id; // 标识

	@Column
	private String collectid; // 缴费登记表标识
	
	@Column
	private String collectdeptid; // 缴费登记部门表标识
	
	@Column
	private String colldeptid; // 收缴单位部门标识
	
	@Column
	private String colldeptname; // 收缴单位部门名称
	
	@Column
	private String colluser; // 收缴党员用户名
	
	@Column
	private String collusername; // 收缴党员姓名	
	
	@Column
	private Timestamp colltime; // 党员收缴时间
	
	@Column
	private float basecost; // 党员月收费基数	
	
	@Column
	private float rate; // 交费比例	
	
	@Column
	private float plancollcost; // 应缴费用	
	
	@Column
	private float actualcollcost; // 实缴费用	

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

	public String getCollectid()
	{
		return collectid;
	}

	public void setCollectid(String collectid)
	{
		this.collectid = collectid;
	}

	public String getCollectdeptid()
	{
		return collectdeptid;
	}

	public void setCollectdeptid(String collectdeptid)
	{
		this.collectdeptid = collectdeptid;
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

	public String getColluser()
	{
		return colluser;
	}

	public void setColluser(String colluser)
	{
		this.colluser = colluser;
	}

	public String getCollusername()
	{
		return collusername;
	}

	public void setCollusername(String collusername)
	{
		this.collusername = collusername;
	}

	public Timestamp getColltime()
	{
		return colltime;
	}

	public void setColltime(Timestamp colltime)
	{
		this.colltime = colltime;
	}

	public float getBasecost()
	{
		return basecost;
	}

	public void setBasecost(float basecost)
	{
		this.basecost = basecost;
	}

	public float getRate()
	{
		return rate;
	}

	public void setRate(float rate)
	{
		this.rate = rate;
	}

	public float getPlancollcost()
	{
		return plancollcost;
	}

	public void setPlancollcost(float plancollcost)
	{
		this.plancollcost = plancollcost;
	}

	public float getActualcollcost()
	{
		return actualcollcost;
	}

	public void setActualcollcost(float actualcollcost)
	{
		this.actualcollcost = actualcollcost;
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
