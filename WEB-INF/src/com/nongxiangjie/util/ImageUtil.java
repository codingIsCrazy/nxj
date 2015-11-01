package com.nongxiangjie.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.UUID;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

/**
 * 上传图片到阿里云
 * @author liujianjun
 *
 */
public class ImageUtil {

	private static final String public_bucket = "nxj-public";
	
	private static final String ALIYUN_IMAGE_URL = "http://oss-cn-beijing.aliyuncs.com";
	
	private static final String ALIYUN_IMAGE_KEY = "3aBRYRaFRxAWWOy5";
	
	private static final String ALIYUN_IMAGE_VALUE = "T6U3NfnyieaaMgFYXUDzA8rLaelIb6";
	
	private static final String ALIYUN_IMAGE_TARGET_URL_PRE = "http://image.nongxiangjie.com/";
	
	private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
	
	
	/**
	 * 上传图片获取url
	 * @param input
	 * @param fileName
	 * @return
	 */
	public static String getAliImgurl(InputStream input, String fileName,String dir){
	    	String key = dir + "/"+UUID.randomUUID().toString().replaceAll("-", "")+fileName;
	    	byte[] btImg = null;
		try {
			btImg = readInputStream(input);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NullPointerException("上传阿里云图片报错："+e.getMessage());
		}//得到图片的二进制数据
        OSSClient c = new OSSClient(ALIYUN_IMAGE_URL,ALIYUN_IMAGE_KEY,ALIYUN_IMAGE_VALUE);
        ObjectMetadata m = new ObjectMetadata();
        m.setContentLength(btImg.length);
        m.setContentType("image/jpg");
        InputStream bin = new ByteArrayInputStream(btImg);
        m.setCacheControl("max-age=50000000");
        PutObjectResult re = c.putObject(public_bucket, key, bin, m);

        return ALIYUN_IMAGE_TARGET_URL_PRE  + key;
    }
}
