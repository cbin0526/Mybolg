package com.etc.myblog.biz;

import com.etc.myblog.entity.User;

public interface UserBiz {

	/**
	 * ��¼��֤�ķ���
	 * @param name  ��¼��
	 * @param pwd   ����
	 * @return  ��¼���û������û���򷵻�null
	 */
	User checkLogin(String name,String pwd);
	
	boolean registerUser(User user);
}
