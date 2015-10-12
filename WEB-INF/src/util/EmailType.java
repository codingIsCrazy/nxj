package util;

public class EmailType {

	public String getEmailType(String email_){
		
		String email = email_.toLowerCase();
		if(email == ""){
			return "";
		}
		if(email.contains("@126.com")){
			return "http://mail.126.com/";
		}
		if(email.contains("@163.com")){
			return "http://mail.163.com/"; 
		}
		if(email.contains("@sina.com")){
			return "http://mail.sina.com.cn/";
		}
		if(email.contains("@qq.com")){
			return "https://mail.qq.com/";
		}
		if(email.contains("@sohu.com")){
			return "http://mail.sohu.com/";
		}
		if(email.contains("@yeah.net")){
			return "http://www.yeah.net/";
		}
		if(email.contains("@139.com")){
			return "http://mail.10086.cn/";
		}
		if(email.contains("@12cn.com")){
			return "http://mail.21cn.com/";
		}
		if(email.contains("@aliyun.com")){
			return "https://passport.alipay.com/login/login.htm?return_url=http%3A%2F%2Fmail.aliyun.com%2Falimail%2Fauth%2FcallbackForHavana%3Freurl%3D%252Falimail%252F&fromSite=9";
			
		}
		if(email.contains("gmail.com")){
			return "https://accounts.google.com/ServiceLogin";
		}
		if(email.contains("hotmail.com")){
			return "https://login.live.com/";
		}
		if(email.contains("@189.cn")){
			return "http://webmail19.189.cn/webmail/";
		}
		return "";
		
	}
}
