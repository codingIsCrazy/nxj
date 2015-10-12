package com.nongxiangjie.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtils {
	
	private static JedisPool pool;
	
	
	
	public static void initRadis(){
		if(pool !=null)
			return;
		//CacheConnectionConfig config = new CacheConnectionConfig(Contants.ServiceIP, 6379);
		// 只需要初始化一次
		//CacheService.initCacheConfig(config);
		pool = new JedisPool(Contants.ServiceIP, 6379);
	}
	
	
	public static Jedis getJedis(){
		if(pool == null)
			initRadis();
		return pool.getResource();
	}
	
	public static void returnResource(Jedis jedis){
		if(jedis != null){
			pool.returnResourceObject(jedis);
		}
		
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
	 	
	 	
	}
	
	

	
}
