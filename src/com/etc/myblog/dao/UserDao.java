package com.etc.myblog.dao;

import com.etc.myblog.entity.User;

public interface UserDao {

	/**
	 * �����û����������ѯ���ݵķ���
	 * @param name ��¼��
	 * @param pwd ����
	 * @return ��¼���û�������鲻��������null
	 */
	User queryUserByNameAndPwd(String name,String pwd);
	
	User queryUserById(Integer userid);
	
	/**
	 * �����û��ķ���
	 * @param user
	 * @return
	 */
	boolean  insertUser(User user);
}
