package com.nongxiangjie.user.dto;


/**
 * 用户
 * @author liujianjun
 *
 */
public class User {

	
	
	public String id;
	public String name;
	public String password;
	/**状态**/
	public String status;
	
	public static String STATUS_NORMAL = "normal";
	
	/**来源**/
	public String source;
	/**创建时间**/
	public Long createDate;
	/**token**/
	public String token;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
