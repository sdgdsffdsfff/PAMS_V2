package com.skynet.app.workflow.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_WFLFLOWASSAPP")
public class LFlowAssApp extends IdEntity {
	
	@Column
	private String runflowkey;

	@Column
	private String formid;

	@Column
	private String dataid;

	@Column
	private String tableid;

	@Column
	private String workname;

	@Column
	private String flowdefid;

    @Column
    @ColDefine(type=ColType.DATETIME)
	private Timestamp createtime; // 创建时间
    
	@Column
	private String creater; // 创建者用户账号名   
    
	@Column
	private String creatername; // 创建者姓名
	
	@Column
	private String deptid; // 创建者部门标识
	
	@Column
	private String deptname; // 创建者部门名称
	
	@Column
	private String orgid; // 创建者机构标识
	
	@Column
	private String orgname; // 创建者机构名称
	
	@Column
	private String cno; // 业务数据单据号

	@Column
	private String planid; // 扩展与计划关联

	public String getRunflowkey() {
		return runflowkey;
	}

	public void setRunflowkey(String runflowkey) {
		this.runflowkey = runflowkey;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public String getDataid() {
		return dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

	public String getWorkname() {
		return workname;
	}

	public void setWorkname(String workname) {
		this.workname = workname;
	}

	public String getFlowdefid() {
		return flowdefid;
	}

	public void setFlowdefid(String flowdefid) {
		this.flowdefid = flowdefid;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreatername() {
		return creatername;
	}

	public void setCreatername(String creatername) {
		this.creatername = creatername;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

}
