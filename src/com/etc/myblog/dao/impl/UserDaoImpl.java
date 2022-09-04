package com.etc.myblog.dao.impl;

import java.util.List;

import com.etc.myblog.dao.UserDao;
import com.etc.myblog.entity.User;
import com.etc.myblog.util.DBUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public User queryUserByNameAndPwd(String name, String pwd) {
		List<User> list=DBUtil.exQuery("select * from t_user where user_name=? and user_pwd=?", User.class, name,pwd);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User queryUserById(Integer userid) {
		List<User> list=DBUtil.exQuery("select * from t_user where user_id=?", User.class, userid);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		int i=DBUtil.exUpdate("insert into t_user(user_name,user_pwd,user_job,user_info,user_signature) values(?,?,?,?,?)", 
				user.getUser_name(),user.getUser_pwd(),user.getUser_job(),user.getUser_info(),"这个人很懒，什么都没有留下");
		return i>0;
	}

}
