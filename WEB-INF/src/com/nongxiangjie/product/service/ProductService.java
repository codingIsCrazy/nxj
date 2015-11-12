package com.nongxiangjie.product.service;

import java.util.List;
import java.util.Map;

import util.PagingBean;

import com.nongxiangjie.product.dto.Product;
import com.nongxiangjie.product.dto.ProductComment;
import com.nongxiangjie.product.dto.ProductImage;

public interface ProductService {

	/**
	 * 产品查询
	 */
	public List<Product> getProduct(Map<String, Object> map,PagingBean page) throws Exception;
	
	/**
	 * 产品总数
	 */
	public int getProductCount(Map<String, Object> map) throws Exception;

	/**
	 * 获取产品图片
	 */
	public List<ProductImage> getProductImageListByProductId(String productId) throws Exception;

	/**
	 * 获取产品的评论的数量
	 */
	public int getCommentCountByProductId(String productId) throws Exception;

	/**
	 * 获取产品的评论
	 * 
	 */
	public List<ProductComment> getCommentByProductId(String productId,PagingBean page) throws Exception;

	/**
	 * 查询地区
	 */
	public List<Map<String, String>> getAdminDivision(Map<String, Object> map) throws Exception;
}
