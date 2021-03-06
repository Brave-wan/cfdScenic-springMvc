package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;

public class Travelogs extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = -3675754350935590589L;
	private Long id;				//游记id
	private String title;			//游记标题
	private String content;			//游记内容
	private String travelImg;		//图片
	private String travelVideo;		//游记视频
	private String travelDate;		//旅游时间
	private String createDate;		//创建时间
	private Long travelId;			//旅游id
	private String travelName;		//旅游名称
	private Integer type;			//类型（0视频攻略1精彩游记2必去清单）
	private String address;			//地址
	private String longitude;		//经度
	private String latitude;		//纬度
	private Long userId;			//关联user表Id
	private Integer travelType;		//游记类型（1视频，2图片，3文字）
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTravelVideo() {
		return travelVideo;
	}
	public void setTravelVideo(String travelVideo) {
		this.travelVideo = travelVideo;
	}
	public String getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}
	public Long getTravelId() {
		return travelId;
	}
	public void setTravelId(Long travelId) {
		this.travelId = travelId;
	}
	public String getTravelName() {
		return travelName;
	}
	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTravelImg() {
		return travelImg;
	}
	public void setTravelImg(String travelImg) {
		this.travelImg = travelImg;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Integer getTravelType() {
		return travelType;
	}
	public void setTravelType(Integer travelType) {
		this.travelType = travelType;
	}
	
	
	
}
