package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;

/**
 * 店铺信息
 * @author:lishilong
 * @date:2016年7月27日
 */
public class ShopInformation extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = -2095628394969483884L;
	private Long id;//主键
	private String name;//店铺名称
	private String headImg;//头像
	private String address;//地址
	private String latitude;//经度
	private String longitude;//纬度
	private String phone;//电话
	private Integer isWifi;//是否有无线
	private Integer isYushi;//是否有浴室
	private Integer isBlss;//是否有便利设施 
	private Integer isMedia;//是否有媒体或科技
	private Integer isFood;//是否有食品或者饮品
	private String content;//内容介绍
	private String backgroudImg;//背景图
	private String startDate;//就餐开始时间
	private String endDate;//毕餐时间
	private Long shopId;//店铺分类Id
	private Long shopUserId;//商户Id
	private Long detailId;//购买须知
	private Integer sex;//性别（0男 1女）
	private Integer age;//年龄
	private String realName;//真实姓名
	private String idCard;//身份证
	private String holdCardImg;//手持证件照
	private String faceCardImg;//身份证正面照
	private String backCardImg;//身份证反面照
	private String businessScope;//经营范围
	private Long accountType;//账户类型 （字典表）
	private String accountName;//账户名称	
	private String bankCard;//银行卡号
	private String accountBank;//开户行
	private Integer isLicense;//是否有营业执照
	private String licenseImg;//营业执照照片
	private String otherImg1;//其他证件照1
	private String otherImg2;//其他证件照2
	private String createTime;//创建时间
	private String shopImg;//商家logo 
	private Integer state;//商家状态（0，正常，1停用，2删除）
	private Integer isAudit;//审核（0，提交审核，1审核通过，2审核不通过）
	private String cashPassWord;//提现密码
	private Double consumption;//人均消费
	private String auditFail;	//审核失败原因
	
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
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getIsWifi() {
		return isWifi;
	}
	public void setIsWifi(Integer isWifi) {
		this.isWifi = isWifi;
	}
	public Integer getIsYushi() {
		return isYushi;
	}
	public void setIsYushi(Integer isYushi) {
		this.isYushi = isYushi;
	}
	public Integer getIsBlss() {
		return isBlss;
	}
	public void setIsBlss(Integer isBlss) {
		this.isBlss = isBlss;
	}
	public Integer getIsMedia() {
		return isMedia;
	}
	public void setIsMedia(Integer isMedia) {
		this.isMedia = isMedia;
	}
	public Integer getIsFood() {
		return isFood;
	}
	public void setIsFood(Integer isFood) {
		this.isFood = isFood;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBackgroudImg() {
		return backgroudImg;
	}
	public void setBackgroudImg(String backgroudImg) {
		this.backgroudImg = backgroudImg;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Long getShopUserId() {
		return shopUserId;
	}
	public void setShopUserId(Long shopUserId) {
		this.shopUserId = shopUserId;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getHoldCardImg() {
		return holdCardImg;
	}
	public void setHoldCardImg(String holdCardImg) {
		this.holdCardImg = holdCardImg;
	}
	public String getFaceCardImg() {
		return faceCardImg;
	}
	public void setFaceCardImg(String faceCardImg) {
		this.faceCardImg = faceCardImg;
	}
	public String getBackCardImg() {
		return backCardImg;
	}
	public void setBackCardImg(String backCardImg) {
		this.backCardImg = backCardImg;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public Long getAccountType() {
		return accountType;
	}
	public void setAccountType(Long accountType) {
		this.accountType = accountType;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public Integer getIsLicense() {
		return isLicense;
	}
	public void setIsLicense(Integer isLicense) {
		this.isLicense = isLicense;
	}
	public String getLicenseImg() {
		return licenseImg;
	}
	public void setLicenseImg(String licenseImg) {
		this.licenseImg = licenseImg;
	}
	public String getOtherImg1() {
		return otherImg1;
	}
	public void setOtherImg1(String otherImg1) {
		this.otherImg1 = otherImg1;
	}
	public String getOtherImg2() {
		return otherImg2;
	}
	public void setOtherImg2(String otherImg2) {
		this.otherImg2 = otherImg2;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getIsAudit() {
		return isAudit;
	}
	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}
	public String getCashPassWord() {
		return cashPassWord;
	}
	public void setCashPassWord(String cashPassWord) {
		this.cashPassWord = cashPassWord;
	}
	public Double getConsumption() {
		return consumption;
	}
	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}
	public String getAuditFail() {
		return auditFail;
	}
	public void setAuditFail(String auditFail) {
		this.auditFail = auditFail;
	}
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	
}
