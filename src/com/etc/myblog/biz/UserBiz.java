package com.etc.myblog.biz;

import com.etc.myblog.entity.User;

public interface UserBiz {

	/**
	 * 登录验证的方法
	 * @param name  登录名
	 * @param pwd   密码
	 * @return  登录的用户，如果没有则返回null
	 */
	User checkLogin(String name,String pwd);
	
	boolean registerUser(User user);
}
