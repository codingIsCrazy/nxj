package com.nongxiangjie.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import util.DateUtil;


public class ReportUtil {

	/**
	 * 返回excle的名称
	 * @description:
	 * @returnType: String
	 * @throws 
	 * @create:2014-7-10 下午1:33:20
	 * @author:liujianjun
	 */
	public static String getExcelName(String name,String date){
		String exportName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString();
		String preExprotName = "";
		if(date == null || date == ""){
			preExprotName = new SimpleDateFormat("yyyyMMdd").format(DateUtil.parseDate(getBeforeDay())).toString() + "-";
		}else{
			preExprotName = new SimpleDateFormat("yyyyMMdd").format(DateUtil.parseDate(date)).toString() + "-";
		}
		return preExprotName + name + "-" + exportName;
	}

	/**
	 * 返回excle的名称
	 * @description:
	 * @returnType: String
	 * @throws 
	 * @create:2014-7-10 下午1:33:20
	 * @author:liujianjun
	 */
	public static String getExcelNameTwo(String name,String startDate,String endDate){
		//默认查询当月
		if(startDate == null || "".equals(startDate)){
			startDate = ReportUtil.getFirstDayEveryMonth();
			endDate = DateUtil.getNowDateStr();
		}
		String exportName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString();
		String startDate_  = new SimpleDateFormat("yyyyMMdd").format(DateUtil.parseDate(startDate)).toString() + "-";
		String endDate_  = new SimpleDateFormat("yyyyMMdd").format(DateUtil.parseDate(endDate)).toString() + "-";
		return startDate_ + endDate_  + name + "-" + exportName;
	}
	
	/**
	 * 获取excel的sheet
	 * @description:
	 * @returnType: Sheet
	 * @throws 
	 * @create:2014-7-10 下午1:36:18
	 * @author:liujianjun
	 */
	public static Sheet getExcelSheet(Workbook wb){
		Sheet sheet = wb.createSheet("sheet1");
		sheet.setColumnWidth(4, 10*256);
		
		// 定义单元格样式
		CellStyle style = wb.createCellStyle();
		// 边框
		style.setBorderBottom((short) 1);
		style.setBorderLeft((short) 1);
		style.setBorderRight((short) 1);
		style.setBorderTop((short) 1);
			
		// 对齐
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		
		CellStyle style1 = wb.createCellStyle();
		// 边框
		style1.setBorderBottom((short) 1);
		style1.setBorderLeft((short) 1);
		style1.setBorderRight((short) 1);
		style1.setBorderTop((short) 1);
		// 对齐
		style1.setAlignment(CellStyle.ALIGN_CENTER);
		style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style1.setWrapText(true);
		return sheet;
	}
	
	
	
	/**
	 * map中对象转int
	 * @description:
	 * @returnType: int
	 * @throws 
	 * @create:2014-7-9 下午4:45:04
	 * @author:liujianjun
	 */
	public static int objToInt(Object obj){
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}else{
			return 0;
		}
	}
	
	
	/**
	 * map中对象转int
	 * @description:
	 * @returnType: int
	 * @throws 
	 * @create:2014-7-9 下午4:45:04
	 * @author:liujianjun
	 */
	public static double objToDouble(Object obj){
		if(obj != null){
			return Double.parseDouble(obj.toString());
		}else{
			return 0;
		}
	}
	
	/**
	 * map对象格式化问字符串
	 * @description:
	 * @returnType: String
	 * @throws 
	 * @create:2014-7-10 下午3:01:36
	 * @author:liujianjun
	 */
	public static String objFormatStr(Object obj){
		DecimalFormat df = new DecimalFormat("######0.00");
		return df.format(objToDouble(obj));
	}
	
	/**
	 * 通过map中获取百分比字符串
	 * @description:
	 * @returnType: String
	 * @throws 
	 * @create:2014-7-10 下午3:03:03
	 * @author:liujianjun
	 */
	public static String getPercentStr(Object obj){
		DecimalFormat df = new DecimalFormat("######0.00");
	    return df.format(objToDouble(obj)*100) + "%";
	}
	
	/**
	 * 把list转为字符串
	 * @description:
	 * @returnType: String
	 * @throws 
	 * @create:2014-7-8 下午7:27:11
	 * @author:liujianjun
	 */
	public static String listToString(List<Integer> list){
		StringBuilder str =new StringBuilder();
		if(list.size() > 0){
			for(Integer object : list){
				str.append(object.toString());
				str.append(",");
			}
			return str.toString().substring(0, str.toString().length()-1);
		}else{
			return "";
		}
	}
	
	
	/**
	 * 获取本月第一天
	 * @description:
	 * @returnType: String
	 * @throws 
	 * @create:2014-7-10 下午5:37:12
	 * @author:liujianjun
	 */
	public static String getFirstDayEveryMonth(){
		String date = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		date = sdf.format(cal.getTime()) + "-01";
		return date;
	}
	
	

	/**
	 * 获取地区名称
	 * @description:
	 * @returnType: String
	 * @throws 
	 * @create:2014-7-10 下午7:41:28
	 * @author:liujianjun
	 */
	public static String getStockAreaName(int stockArea){
		String stockAreaName = "";
		switch (stockArea) {
		case 0:
			stockAreaName = "北京";
			break;
		case 1:
			stockAreaName = "芳村";
			break;
		case 2:
			stockAreaName = "广速";
			break;
		case 3:
			stockAreaName = "增城";
			break;
		case 4:
			stockAreaName = "无锡";
			break;
		}
		return stockAreaName;
	}
	
	/**
	 * 获取代销，经销
	 * @description:
	 * @returnType: String
	 * @throws 
	 * @create:2014-7-10 下午7:35:42
	 * @author:liujianjun
	 */
	public static String getSaleTypeName(int saleType){
		if(saleType == 1){
			return "代销";
		}else if(saleType == 2){
			return "经销";
		}
		return "";
	}
	
	/**
	 * 获取前一天
	 * @description:
	 * @returnType: String
	 * @throws 
	 * @create:2014-7-9 下午3:16:48
	 * @author:liujianjun
	 */
	public static String getBeforeDay(){
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return formate.format(calendar.getTime());
	}
	
	
	/**
	 * 获取指定日期的前一天
	 * @description:
	 * @returnType: String
	 * @throws 
	 * @create:2014-7-16 下午12:27:20
	 * @author:liujianjun
	 */
	public static String getBeforeDayByDate(String now){
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(formate.parse(now));
			calendar.add(Calendar.DAY_OF_MONTH, -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formate.format(calendar.getTime());
	}
	
	
	/**
	 * 获取指定日期的下一天
	 * @description:
	 * @returnType: String
	 * @throws 
	 * @create:2014-7-16 下午12:28:03
	 * @author:liujianjun
	 */
	public static String getNextDayByDate(String now){
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try{
			calendar.setTime(formate.parse(now));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}catch(Exception e){
			e.printStackTrace();
		}
		return formate.format(calendar.getTime());
	}
	
	
	/**
	 * iso转utf-8
	 * @description:
	 * @returnType: String
	 * @throws 
	 * @create:2014-7-18 上午11:12:03
	 * @author:liujianjun
	 */
	public static String isoToUTF8(String str){
		String targetStr = "";
		try{
			if(str == null || "".equals(str)){
			}else{
				targetStr = new String(str.getBytes("iso-8859-1"),"utf-8").trim();
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return targetStr;
	}
	
	
//	/**
//	 * 计算除法，由于jdbctemplate返回的是bigDecimal,因此除数需要处理
//	 * @description:
//	 * @returnType: double
//	 * @throws 
//	 * @create:2014-7-22 下午6:12:16
//	 * @author:liujianjun
//	 */
//	public static double bigDecimalDivToDouble(Object sumObj,Object count){
//		if(objToInt(count) == 0){
//			return 0;
//		}else{
//			return Arith.div(objToDouble(sumObj), objToInt(count), 2);
//		}
//	}
}
