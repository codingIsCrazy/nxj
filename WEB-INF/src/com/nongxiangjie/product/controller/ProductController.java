package com.nongxiangjie.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import util.PagingBean;
import util.StringUtil;

import com.nongxiangjie.product.dto.Product;
import com.nongxiangjie.product.dto.ProductComment;
import com.nongxiangjie.product.dto.ProductImage;
import com.nongxiangjie.product.service.ProductService;
import com.nongxiangjie.user.controller.UserController;
import com.nongxiangjie.util.ApiJsonUtil;
import com.nongxiangjie.util.ApiMessage;




@Controller
@RequestMapping(value="api/v1/productController")
public class ProductController {

	final static Logger LOG = LoggerFactory.getLogger(ProductController.class);
	
	
	@Resource
	private ProductService productService;
	
	
	
	/**
	 * 获取
	 */
	@RequestMapping(value="/getProductByCondtion",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProductByCondtion(HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> result = null;
		
		String productName = request.getParameter("productName");
		
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		
		int countPerPage = 20;
		int pageIndex = StringUtil
				.StringToId(request.getParameter("pageIndex"));
		
		try{
			
			//通过订单获取默认显示的产品
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("productName", productName);
			param.put("province", province);
			param.put("city", city);
			param.put("area", area);
			
			//查询总数
			int totalCount = productService.getProductCount(param);
			PagingBean page = new PagingBean(pageIndex, totalCount,countPerPage);
			
			//查询产品信息
			List<Product> list = productService.getProduct(param, page);
			Map<String, Object> data = new HashMap<String,Object>();
			List<Map<String, Object>> productList = new ArrayList<Map<String,Object>>();
			for(Product p : list){
				Map<String, Object> productMap = new HashMap<String,Object>();
				productMap.put("imageUrl", p.getImageUrl());
				productMap.put("id", p.getId());
				productMap.put("fullName", p.getFullName());
				productMap.put("shortName", p.getShortName());
				productMap.put("province", p.getProvince());
				productMap.put("city", p.getCity());
				productMap.put("area", p.getArea());
				productMap.put("address", p.getAddress());
				productMap.put("salePrice", p.getSalePrice());
				productMap.put("discountPrice", p.getDiscountPrice());
				productList.add(productMap);
			}
			
			data.put("productList", productList);
			data.put("page", page);
			
			result = ApiJsonUtil.getApiData(ApiMessage.API_SUCCESS_CODE, data);
			
		}catch(Exception e){
			result = ApiJsonUtil.getServerError();
			e.printStackTrace();
		}
		
		
		return result;
	}
		
		
	/**
	 * 获取产品详情
	 */
	@RequestMapping(value="/getProductById",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProductById(HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> result = null;
		
		String id = request.getParameter("id");
		
		try{
			Map<String, Object> param = new HashMap<String,Object>();
			param.put("id", id);
			List<Product> list = productService.getProduct(param, null);
			Product product = list.get(0);
			
			//查询图片
			List<ProductImage> imageProductList = productService.getProductImageListByProductId(id);
			List<String> imageList = new ArrayList<String>();
			for(ProductImage pi : imageProductList){
				imageList.add(pi.getImageUrl());
			}
			
			product.setImageUrlList(imageList);
			
			Map<String, Object> data = new HashMap<String,Object>();
			data.put("product", product);
			
			result = ApiJsonUtil.getApiData(ApiMessage.API_SUCCESS_CODE, data);
		}catch(Exception e){
			result = ApiJsonUtil.getServerError();
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	/**
	 * 获取评论
	 */
	@RequestMapping(value="/getProductCommentByProductId",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProductCommentByProductId(HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> result = null;
		
		String id = request.getParameter("id");
		int countPerPage = 20;
		int pageIndex = StringUtil
				.StringToId(request.getParameter("pageIndex"));
		try{
			//查询总数
			int totalCount = productService.getCommentCountByProductId(id);
			PagingBean page = new PagingBean(pageIndex, totalCount,countPerPage);
			
			//查询列表
			List<ProductComment> list = productService.getCommentByProductId(id, page);
			Map<String, Object> data = new HashMap<String,Object>();
			List<Map<String, Object>> commietList = new ArrayList<Map<String,Object>>();
			for(ProductComment c : list){
				Map<String, Object> comment = new HashMap<String,Object>();
				comment.put("content", c.getContent());
				comment.put("name", c.getUser().getName());
				comment.put("createTime", c.getCreateTime());
				comment.put("starCount", c.getStarCount());
				
				commietList.add(comment);
			}
			
			data.put("commentList", commietList);
			data.put("page", page);
			
			result = ApiJsonUtil.getApiData(ApiMessage.API_SUCCESS_CODE, data);
			
		}catch(Exception e){
			result = ApiJsonUtil.getServerError();
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 查询地区
	 */
	@RequestMapping(value="/getAdminDivision",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAdminDivision(HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> result = null;
		
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		
		try{
			Map<String, Object> param = new HashMap<String,Object>();
			//一级
			param.put("grade", 1);
			
			//二级
			if(province != null && !"".equals(province)){
				param.put("province", province);
				param.put("grade", 2);
			}
			
			//三级
			if(city != null && !"".equals(city)){
				param.put("city", city);
				param.put("grade", 3);
			}
			
			List<Map<String, String>> divisionMap = productService.getAdminDivision(param);
			Map<String, Object> data = new HashMap<String,Object>();
			data.put("division", divisionMap);
			
			result = ApiJsonUtil.getApiData(ApiMessage.API_SUCCESS_CODE, data);
		}catch(Exception e){
			result = ApiJsonUtil.getServerError();
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
