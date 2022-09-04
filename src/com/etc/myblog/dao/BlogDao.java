package com.etc.myblog.dao;

import java.util.List;

import com.etc.myblog.dto.BlogDto;
import com.etc.myblog.entity.Blog;
import com.etc.myblog.entity.BlogCount;

public interface BlogDao {

	/**
	 * 查询当前用户的全部博客
	 * @param userid  要查询的用户的id
	 * @return 该用户写的所有博客
	 */
	List<Blog> queryAllBlog(Integer userid);
	
	List<BlogDto> queryAllBlog2(Integer userid);
	/**
	 * 添加一篇博客
	 * @param userid
	 * @param blog
	 * @return
	 */
	boolean addBlog(Integer userid,Blog blog);
	/**
	 * 通过博客id查询博客
	 * @param blog_id
	 * @return
	 */
	Blog queryBlogById(Integer blog_id);
	
	/**
	 * 通过id修改博客
	 * @param blog
	 * @return
	 */
	boolean updateBlogById(Blog blog);
	
	/**
	 * 查询当前用户的博客的总条数
	 * @param userid	用户id
	 * @return Integer 当前用户博客条数
	 */
	List<BlogCount> queryBlogSizeByUserid(Integer userid);
	
	/**
	 * @param userid 用户id
	 * @param page 当前页数
	 * @param size	页面大小
	 * @return
	 */
	List<BlogDto> queryBlogByPageAndSizeAndUserid(Integer userid,Integer page,Integer size);
}
