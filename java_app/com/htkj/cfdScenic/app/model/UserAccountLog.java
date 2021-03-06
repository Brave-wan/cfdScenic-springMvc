package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;

public class UserAccountLog extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = -7898805448766222984L;
	private Long id;					//用户交易记录表id
	private String name;				//交易名称
	private Integer type;				//类型（0余额交易1积分交易2结算交易3,商户给用户充值4退款）
	private Double price;			//交易金额
	private Double balance;			//剩余金额
	private Integer tradeIntegration;	//交易积分
	private Integer integration;		//剩余积分
	private String createTime;			//日期
	private Long shopId;				//商户id
	private Long userId;				//用户id
	private Integer extractType;		//提现申请（0审核中，1审核通过，2审核失败）
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getTradeIntegration() {
		return tradeIntegration;
	}
	public void setTradeIntegration(Integer tradeIntegration) {
		this.tradeIntegration = tradeIntegration;
	}
	public Integer getIntegration() {
		return integration;
	}
	public void setIntegration(Integer integration) {
		this.integration = integration;
	}
	
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getExtractType() {
		return extractType;
	}
	public void setExtractType(Integer extractType) {
		this.extractType = extractType;
	}
}
