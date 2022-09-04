package com.etc.myblog.dao;

import java.util.List;

import com.etc.myblog.dto.BlogDto;
import com.etc.myblog.entity.Blog;
import com.etc.myblog.entity.BlogCount;

public interface BlogDao {

	/**
	 * ��ѯ��ǰ�û���ȫ������
	 * @param userid  Ҫ��ѯ���û���id
	 * @return ���û�д�����в���
	 */
	List<Blog> queryAllBlog(Integer userid);
	
	List<BlogDto> queryAllBlog2(Integer userid);
	/**
	 * ���һƪ����
	 * @param userid
	 * @param blog
	 * @return
	 */
	boolean addBlog(Integer userid,Blog blog);
	/**
	 * ͨ������id��ѯ����
	 * @param blog_id
	 * @return
	 */
	Blog queryBlogById(Integer blog_id);
	
	/**
	 * ͨ��id�޸Ĳ���
	 * @param blog
	 * @return
	 */
	boolean updateBlogById(Blog blog);
	
	/**
	 * ��ѯ��ǰ�û��Ĳ��͵�������
	 * @param userid	�û�id
	 * @return Integer ��ǰ�û���������
	 */
	List<BlogCount> queryBlogSizeByUserid(Integer userid);
	
	/**
	 * @param userid �û�id
	 * @param page ��ǰҳ��
	 * @param size	ҳ���С
	 * @return
	 */
	List<BlogDto> queryBlogByPageAndSizeAndUserid(Integer userid,Integer page,Integer size);
}
