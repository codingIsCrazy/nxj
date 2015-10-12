package util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil {
	
	static CacheUtil cache = new CacheUtil();
	public static String CACHE_TITLE = "title_cache";
	//public static CacheManager cacheManager = CacheManager.create("/WEB-INF/encache.xml");
	
	public static CacheManager getCacheManager(){
		CacheManager cacheManager = CacheManager.create(cache.getPath());
		return cacheManager;
	}
	
	public static Cache  getCache(String cacheName){
		return getCacheManager().getCache(cacheName);
	}
	
	public static Element getElement(String cacheName,String key){
		return CacheUtil.getCache(cacheName).get(key);
	}
	
	public static void putElement(String cacheName,Element element){
		CacheUtil.getCache(cacheName).put(element);
	}
	
	public static void removeElement(String cacheName,String key){
		CacheUtil.getCache(cacheName).remove(key);
	}
	
	public static void removeAllElement(String cacheName){
		CacheUtil.getCache(cacheName).removeAll();
	}
		
	public  String getPath(){
		String path1 = this.getClass().getClassLoader().getResource("resources/ehcache.xml").getPath();
		return path1;
	}
}
