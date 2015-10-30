package com.cache;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Element;


import util.CacheUtil;

public class CacheServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		System.out.println("init");
		//List<TitleOneBean> titleOneList = StatisticsUtil.queryTitleAll(null);
		//Element element = new Element("titleOneList", titleOneList);
		//CacheUtil.putElement(CacheUtil.CACHE_TITLE,element);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	@Override
	public void destroy() {
		
	}
	
}
