package com.nongxiangjie.product.dto;

import java.util.ArrayList;
import java.util.List;


/**
 * 产品信息
 * @author liujianjun
 *
 */
public class Product {

	public String id;
	
	/**全名**/
	public String fullName;
	
	/**缩写**/
	public String shortName;
	
	public String province;
	
	public String city;
	
	public String area;
	
	public String address;
	
	public int status;
	public static int STATUS_NORMAL = 1;
	/**已删除**/
	public static int STATUS_DELETE = 2;
	/**已下架**/
	public static int STATUS_SALE_OUT = 3;
	
	public String imageUrl;
	
	
	/**成本价**/
	public double costPrice;
	
	/**销售价**/
	public double salePrice;
	
	/**折扣价**/
	public double discountPrice;
	
	/**产品详情**/
	public String productDetail;

	public Long createTime;
	
	/**产品规格**/
	public String productStandard;
	
	/**图片**/
	public List<ProductImage> productImageList = new ArrayList<ProductImage>();
	
	/**图片url**/
	public List<String> imageUrlList = new ArrayList<String>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getProductStandard() {
		return productStandard;
	}

	public void setProductStandard(String productStandard) {
		this.productStandard = productStandard;
	}

	public List<ProductImage> getProductImageList() {
		return productImageList;
	}

	public void setProductImageList(List<ProductImage> productImageList) {
		this.productImageList = productImageList;
	}

	public List<String> getImageUrlList() {
		return imageUrlList;
	}

	public void setImageUrlList(List<String> imageUrlList) {
		this.imageUrlList = imageUrlList;
	}
	
	
	

}
