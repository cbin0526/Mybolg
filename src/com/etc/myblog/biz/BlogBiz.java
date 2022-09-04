package com.etc.myblog.biz;

import java.util.List;

import com.etc.myblog.dto.BlogDto;
import com.etc.myblog.entity.Blog;

public interface BlogBiz {
	
	List<Blog> getBlogDataByUserId(Integer userid);
	
	List<BlogDto> getBlogDataByUserId2(Integer userid);
	
	boolean addblogByLoginUser(Integer userid,Blog blog);

	Blog getBlogDataByBlogid(Integer blog_id);
	
	boolean updateBlogByBlogId(Blog blog);
	
	List<BlogDto> getBlogDataByPageAndSizeAndUserid(Integer userid,Integer page,Integer size);
	
	Integer queryBlogSizeByUserid(Integer userid);
}
