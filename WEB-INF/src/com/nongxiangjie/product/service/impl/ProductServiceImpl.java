package com.nongxiangjie.product.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import util.PagingBean;

import com.nongxiangjie.product.dao.ProductDao;
import com.nongxiangjie.product.dto.Product;
import com.nongxiangjie.product.dto.ProductComment;
import com.nongxiangjie.product.dto.ProductImage;
import com.nongxiangjie.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Resource
	private ProductDao productDao;

	@Override
	public List<Product> getProduct(Map<String, Object> map, PagingBean page)
			throws Exception {
		return productDao.getProduct(map, page);
	}

	@Override
	public int getProductCount(Map<String, Object> map) throws Exception {
		return productDao.getProductCount(map);
	}

	@Override
	public List<ProductImage> getProductImageListByProductId(String productId)
			throws Exception {
		return productDao.getProductImageListByProductId(productId);
	}

	@Override
	public int getCommentCountByProductId(String productId) throws Exception {
		return productDao.getCommentCountByProductId(productId);
	}

	@Override
	public List<ProductComment> getCommentByProductId(String productId,
			PagingBean page) throws Exception {
		return productDao.getCommentByProductId(productId, page);
	}

	@Override
	public List<Map<String, String>> getAdminDivision(Map<String, Object> map)
			throws Exception {
		return productDao.getAdminDivision(map);
	}

	
}
