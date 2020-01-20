package com.nrd.o2o.entity;

import java.util.Date;
import java.util.List;

public class Product {
	private Long priductId;
	private String productName;
	private String productDesc;
	//缩略图
	private String imgAddr;
	private String normalPrice;
	private String promotionPrice;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;
	//0：不可用，1：在前端展示系统展示
	private Integer enableStatus;
	
	private List<ProductImg> productCategory;
	private Shop shop;

	public Long getPriductId() {
		return priductId;
	}

	public void setPriductId(Long priductId) {
		this.priductId = priductId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getImgAddr() {
		return imgAddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	public String getNormalPrice() {
		return normalPrice;
	}

	public void setNormalPrice(String normalPrice) {
		this.normalPrice = normalPrice;
	}

	public String getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
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

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	public List<ProductImg> getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(List<ProductImg> productCategory) {
		this.productCategory = productCategory;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
