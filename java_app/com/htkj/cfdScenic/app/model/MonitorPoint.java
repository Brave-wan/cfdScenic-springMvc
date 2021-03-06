package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;

public class MonitorPoint extends BaseEntity {
    private Long id;

    private String name;

    private String xPoint;

    private String yPoint;
    
    private String code;
    
    private String content;

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
        this.name = name == null ? null : name.trim();
    }

    public String getxPoint() {
        return xPoint;
    }

    public void setxPoint(String xPoint) {
        this.xPoint = xPoint == null ? null : xPoint.trim();
    }

    public String getyPoint() {
        return yPoint;
    }

    public void setyPoint(String yPoint) {
        this.yPoint = yPoint == null ? null : yPoint.trim();
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}