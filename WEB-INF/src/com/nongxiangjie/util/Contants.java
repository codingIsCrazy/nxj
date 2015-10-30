package com.nongxiangjie.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Contants {
		
	public static String ServiceIP = "localhost";
	//public static String ServiceIP = "10.172.7.46";
	static{
		 try {
			String ip = InetAddress.getLocalHost().getHostAddress();			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		 RedisUtils.initRadis();
	}
	
	
	
}
