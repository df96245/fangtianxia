package com.deyi.common;

public class Constants {

	public static final String  CURRENT_USER="current_user";
	public static final  String USERNAME="username";
	public static final  String PHONE="phone";

	//用接口比枚举相对较轻量级.
	public interface Role{
		int ROLE_CUSTOMER=0;
		int ROLE_ADMIN=1;
	}
}
