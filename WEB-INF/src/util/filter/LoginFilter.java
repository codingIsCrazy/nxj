package util.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import util.ConstantValue;

public class LoginFilter implements Filter{

	final static Logger LOG = LoggerFactory.getLogger(LoginFilter.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;	
		
		
		String ip = request.getRemoteAddr();
		
		LOG.info("ip is :" + ip);
		
	
		String path = request.getRequestURL().toString();
		
		chain.doFilter(request, response);  
		
	}



	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	public List<String> getNoFilter(){
		List<String> list = new ArrayList<String>();
		
	
		return list;
		
	}
	
	public boolean isContain(String path){
		List<String> list = this.getNoFilter();
		String[] str = path.split("/");
		String realPath = str[str.length-1];
		if(list.contains(realPath)){
			return true;
		}else{
			return false;
		}
	}
	
}
