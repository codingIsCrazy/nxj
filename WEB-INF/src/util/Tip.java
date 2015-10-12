package util;

import javax.servlet.http.HttpServletRequest;

public class Tip {

	public static String getTip(String tip){
		String tipStr = "";
		if(tip == null){
			tipStr = "提示的内容为空";
		}
		if(tip.equals("success")){
			tipStr = "操作成功";
		}
		if(tip.equals("updateSuccess")){
			tipStr = "更新成功";
		}
		if(tip.equals("addSuccess")){
			tipStr = "添加成功";
		}
		if(tip.equals("deleteSuccess")){
			tipStr = " 删除成功";
		}
		return tipStr;
	}
	
	public static String getTipName(HttpServletRequest request){
		return request.getParameter("tip");
	}
}
