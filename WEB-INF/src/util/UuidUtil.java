package util;

import java.util.Random;
import java.util.UUID;

public class UuidUtil {
	
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
	
	/**  
     * 生成32位编码  
     * @return string  
     */    
    public static String getUUID(){    
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");    
        return uuid;    
    }    
    
    /**  
     * 自定义规则生成32位编码  
     * @return string  
     */    
//    public static String getUUIDByRules(String rules)    
//    {    
//      int rpoint = 0;    
//        StringBuffer generateRandStr = new StringBuffer();    
//        Random rand = new Random();    
//        int length = 32;    
//        for(int i=0;i<length;i++)    
//        {    
//            if(rules!=null){    
//                rpoint = rules.length();    
//                int randNum = rand.nextInt(rpoint);    
//                generateRandStr.append(radStr.substring(randNum,randNum+1));    
//            }    
//        }    
//        return generateRandStr+"";    
//    }   
}
