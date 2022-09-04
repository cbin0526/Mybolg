package com.etc.myblog.dao;

import com.etc.myblog.entity.User;

public interface UserDao {

	/**
	 * 根据用户名和密码查询数据的方法
	 * @param name 登录名
	 * @param pwd 密码
	 * @return 登录的用户，如果查不到，返回null
	 */
	User queryUserByNameAndPwd(String name,String pwd);
	
	User queryUserById(Integer userid);
	
	/**
	 * 插入用户的方法
	 * @param user
	 * @return
	 */
	boolean  insertUser(User user);
}
