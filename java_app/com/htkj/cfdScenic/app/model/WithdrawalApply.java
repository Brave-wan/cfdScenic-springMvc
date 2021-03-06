package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;

public class WithdrawalApply extends BaseEntity{
	/**
	 * Title:
	 * @author:lishilong
	 * @date:2016年9月6日
	 */
	private static final long serialVersionUID = 5932957493397536762L;
	
	private Long id;//主键ID
	private Long userId;//关联userId
	private	Double balance;//提现金额
	private Double beginBalance;//提现之前的金额
	private	String bankId;//银行卡余额
	private String createTime;//申请提现时间
	private	String name;//提现名称
	private Integer state;//审核状态
	private String auditTime;//审核时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getBeginBalance() {
		return beginBalance;
	}
	public void setBeginBalance(Double beginBalance) {
		this.beginBalance = beginBalance;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
}
