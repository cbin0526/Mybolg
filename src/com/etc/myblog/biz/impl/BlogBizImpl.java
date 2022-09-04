package com.etc.myblog.biz.impl;

import java.util.List;

import com.etc.myblog.biz.BlogBiz;
import com.etc.myblog.dao.BlogDao;
import com.etc.myblog.dao.impl.BlogDaoImpl;
import com.etc.myblog.dto.BlogDto;
import com.etc.myblog.entity.Blog;
import com.etc.myblog.entity.BlogCount;
import com.etc.myblog.util.DBUtil;

public class BlogBizImpl implements BlogBiz {

	private BlogDao dao=new BlogDaoImpl();
	
	@Override
	public List<Blog> getBlogDataByUserId(Integer userid) {
		if(userid==null||userid<=0) {
			return null;
		}
		return dao.queryAllBlog(userid);
	}

	@Override
	public List<BlogDto> getBlogDataByUserId2(Integer userid) {
		if(userid==null||userid<=0) {
			return null;
		}
		return dao.queryAllBlog2(userid);
	}

	@Override
	/**
	 * @param Integer userid 用户id	Blog blog 博客对象
	 * @return boolean false 插入失败	true 插入成功
	 */
	public boolean addblogByLoginUser(Integer userid, Blog blog) {
		
		if(userid == null||blog == null) {
			return false;
		}
		return dao.addBlog(userid, blog);
	}
	
	@Override
	public Blog getBlogDataByBlogid(Integer blog_id) {
		if(blog_id == null) {
			return null;
		}
		return dao.queryBlogById(blog_id);
	}
	
	@Override
	public boolean updateBlogByBlogId(Blog blog) {
		if(blog.getBlog_content() == null | blog.getBlog_id() == null | blog.getBlog_author().getUser_id() == null | blog.getBlog_time() == null) {
			return false;
		}
		return dao.updateBlogById(blog);
	}
	
	@Override
	public List<BlogDto> getBlogDataByPageAndSizeAndUserid(Integer userid, Integer page, Integer size) {
		if(userid == null | page == null | page <= 0 | size == null | size <= 0) {
			return null;
		}
		return dao.queryBlogByPageAndSizeAndUserid(userid, page, size);
	}	
	
	@Override
	public Integer queryBlogSizeByUserid(Integer userid) {
		if(userid == null) {
			return 0;
		}
		List<BlogCount> count = dao.queryBlogSizeByUserid(userid);
		if(count == null | count.get(0) == null | count.get(0).getMyBlogNum() <= 0) {
			return 0;
		}
		return count.get(0).getMyBlogNum();
	}
}
