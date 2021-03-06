package com.htkj.cfdScenic.app.model;

import java.math.BigDecimal;

import com.htrj.common.base.BaseEntity;

/**
 * 景区订单
 * @author:lishilong
 * @date:2016年7月27日
 */
public class VisitorsOrder extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = 745552602499865687L;
	private Long id;//主键
	private String name;//订单名称
	private String orderDescribe;//订单描述
	private Double price;//原价
	private String startValid;//开始有效期
	private String endValid;//结束有效期
	private Integer quantity;//数量
	private Integer payWay;//支付方式（1余额2支付宝3微信）
	private Integer payState;//支付状态（0未支付1已支付）
	private Integer orderState;//订单状态（1确认订单，2已支付，3已使用，4已完成，5申请退款，6退款失败，7退款成功，8已过期，9作废）
	private String createTime;//订单生成时间
	private String payTime;//支付时间
	private String refundTime;//退付时间
	private Long userId;//用户id
	private Double realPrice;//应付价格
	private String orderCode;//订单号
	private Integer isComment;//是否评价（0否1是）
	private Long visitorsId;//景点id
	private Long addressId;//配送地址ID
	private Integer type;//是否积分支付0否1是
	private Integer integraPrice;//积分价格
	private Integer isMention; //是否自提（0否1是）
	private Integer isDelete; //是否删除（0,否1是）
	private Integer isInvoice;//是否已开发票（0未开，1已开）
	private Integer invoiceState;//发票状态（0未开票，1开票中，2已开票）
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
	public String getOrderDescribe() {
		return orderDescribe;
	}
	public void setOrderDescribe(String orderDescribe) {
		this.orderDescribe = orderDescribe;
	}
	public String getStartValid() {
		return startValid;
	}
	public void setStartValid(String startValid) {
		this.startValid = startValid;
	}
	public String getEndValid() {
		return endValid;
	}
	public void setEndValid(String endValid) {
		this.endValid = endValid;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPayWay() {
		return payWay;
	}
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	public Integer getPayState() {
		return payState;
	}
	public void setPayState(Integer payState) {
		this.payState = payState;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Integer getIsComment() {
		return isComment;
	}
	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}
	public Long getVisitorsId() {
		return visitorsId;
	}
	public void setVisitorsId(Long visitorsId) {
		this.visitorsId = visitorsId;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIntegraPrice() {
		return integraPrice;
	}
	public void setIntegraPrice(Integer integraPrice) {
		this.integraPrice = integraPrice;
	}
	public Integer getIsMention() {
		return isMention;
	}
	public void setIsMention(Integer isMention) {
		this.isMention = isMention;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public Double getPrice() {
		return price;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public Integer getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}
	public Integer getInvoiceState() {
		return invoiceState;
	}
	public void setInvoiceState(Integer invoiceState) {
		this.invoiceState = invoiceState;
	}
	
}
