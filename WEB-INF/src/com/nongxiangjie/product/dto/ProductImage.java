package com.nongxiangjie.product.dto;

/**
 * 产品图片
 * @author liujianjun
 *
 */
public class ProductImage {

	public String id;
	
	public String imageUrl;
	
	public String productId;
	
	public int status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
