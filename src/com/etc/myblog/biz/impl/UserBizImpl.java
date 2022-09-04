package com.etc.myblog.biz.impl;

import com.etc.myblog.biz.UserBiz;
import com.etc.myblog.dao.UserDao;
import com.etc.myblog.dao.impl.UserDaoImpl;
import com.etc.myblog.entity.User;

public class UserBizImpl implements UserBiz {

	//业务逻辑类中调用数据访问接口实现对象
	private UserDao dao=new UserDaoImpl();
			
	
	@Override
	public User checkLogin(String name, String pwd) {
		//基本的业务逻辑判断，需要在业务逻辑层中实现
		if(name==null||name.equals("")||pwd==null||pwd.equals("")) {
			return null;
		}	
		return dao.queryUserByNameAndPwd(name, pwd);
	}


	@Override
	public boolean registerUser(User user) {
		if(user==null||user.getUser_name()==null||user.getUser_pwd()==null) {
			return false;
		}
		return dao.insertUser(user);
	}

}
