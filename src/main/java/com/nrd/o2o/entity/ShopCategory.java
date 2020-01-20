package com.nrd.o2o.entity;

import java.util.Date;

public class ShopCategory {
	private Long shopCategoryId;
	private String shopCategoryName;
	private String shopCateforyDesc;
	private String shopCateforyImg;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;
	private ShopCategory parent;

	public Long getShopCategoryId() {
		return shopCategoryId;
	}

	public void setShopCategoryId(Long shopCategoryId) {
		this.shopCategoryId = shopCategoryId;
	}

	public String getShopCategoryName() {
		return shopCategoryName;
	}

	public void setShopCateforyName(String shopCateforyName) {
		this.shopCategoryName = shopCateforyName;
	}

	public String getShopCateforyDesc() {
		return shopCateforyDesc;
	}

	public void setShopCateforyDesc(String shopCateforyDesc) {
		this.shopCateforyDesc = shopCateforyDesc;
	}

	public String getShopCateforyImg() {
		return shopCateforyImg;
	}

	public void setShopCateforyImg(String shopCateforyImg) {
		this.shopCateforyImg = shopCateforyImg;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public ShopCategory getParent() {
		return parent;
	}

	public void setParent(ShopCategory parent) {
		this.parent = parent;
	}
}
