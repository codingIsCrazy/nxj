package com.nongxiangjie.product.dao.mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.spring.support.AbstractDaoSupport;
import org.springframework.stereotype.Repository;

import util.PagingBean;

import com.nongxiangjie.product.dao.ProductDao;
import com.nongxiangjie.product.dto.Product;
import com.nongxiangjie.product.dto.ProductComment;
import com.nongxiangjie.product.dto.ProductImage;

@Repository
public class ProductMapper extends AbstractDaoSupport implements ProductDao{

	@Override
	public List<Product> getProduct(Map<String, Object> map,PagingBean page) throws Exception {
		if(page != null){
			map.put("limit", page.getCountPerPage());
			map.put("offset", page.getCurrentPageIndex()*page.getCountPerPage());
		}
		return getSession().selectList(map);
	}

	@Override
	public int getProductCount(Map<String, Object> map) throws Exception {
		return getSession().selectOne(map);
	}

	@Override
	public List<ProductImage> getProductImageListByProductId(String productId)
			throws Exception {
		return getSession().selectList(productId);
	}

	@Override
	public int getCommentCountByProductId(String productId)
			throws Exception {
		return getSession().selectOne(productId);
	}

	@Override
	public List<ProductComment> getCommentByProductId(String productId,PagingBean page)
			throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productId", productId);
		if(page != null){
			param.put("limit", page.getCountPerPage());
			param.put("offset", page.getCurrentPageIndex()*page.getCountPerPage());
		}
		return getSession().selectList(param);
	}

	@Override
	public List<Map<String, String>> getAdminDivision(Map<String, Object> map)
			throws Exception {
		return getSession().selectList(map);
	}

}
