package com.etc.myblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.etc.myblog.dao.BlogDao;
import com.etc.myblog.dao.UserDao;
import com.etc.myblog.dto.BlogDto;
import com.etc.myblog.entity.Blog;
import com.etc.myblog.entity.BlogCount;
import com.etc.myblog.util.DBUtil;



public class BlogDaoImpl implements BlogDao{

	@Override
	public List<Blog> queryAllBlog(Integer userid) {
		Connection con=DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Blog> list=new ArrayList<Blog>();
		UserDao dao=new UserDaoImpl();
		try {
			 ps=con.prepareStatement("select * from t_blog where blog_author=?");
			 ps.setInt(1, userid);
			 rs=ps.executeQuery();
			 while(rs.next()) {
				Blog blog=new Blog(); 
				blog.setBlog_id(rs.getInt("blog_id"));
				blog.setBlog_content(rs.getString("blog_content"));
				blog.setBlog_time(rs.getString("blog_time"));
				blog.setBlog_title(rs.getString("blog_title"));
				blog.setBlog_type(rs.getString("blog_type"));	
				blog.setBlog_author(dao.queryUserById(rs.getInt("blog_author")));
				list.add(blog);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(ps!=null) {
					ps.close();
				}
				if(con!=null) {
					con.close();
				}
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return list;
	}

	@Override
	public List<BlogDto> queryAllBlog2(Integer userid) {
		return (List<BlogDto>)DBUtil.exQuery("select  b.blog_id,b.blog_time,b.blog_content,b.blog_title,b.blog_type,u.user_name as blog_username from t_blog  as b inner join t_user as u on b.blog_author=u.user_id where b.blog_author=?", BlogDto.class, userid);
		
	}
	
	@Override
	public boolean addBlog(Integer userid, Blog blog) {
		
		String sql = "INSERT INTO `myblog`.`t_blog` (`blog_title`, `blog_content`, `blog_time`, `blog_type`, `blog_author`) VALUES (?,?,?,?,?)";
		return DBUtil.exUpdate(sql, blog.getBlog_title(),blog.getBlog_content(),blog.getBlog_time(),blog.getBlog_type(),userid) > 0;
	}
	
	@Override
	public Blog queryBlogById(Integer blog_id) {
		
		String sql = "SELECT t_blog.blog_id,t_blog.blog_title,t_blog.blog_content,t_blog.blog_time,t_blog.blog_type FROM t_blog where t_blog.blog_id = ?";
		List exQuery = DBUtil.exQuery(sql, Blog.class, blog_id);
		Blog blog = (Blog) exQuery.get(0);
		return blog;
		
	}
	
	@Override
	public boolean updateBlogById(Blog blog) {
		// TODO Auto-generated method stub
		String sql = "UPDATE `myblog`.`t_blog` SET `blog_title`= ? , `blog_content`= ? , `blog_time`= ?, `blog_type`= ? WHERE (`blog_id`= ? )";
		int exUpdate = DBUtil.exUpdate(sql, blog.getBlog_title(),blog.getBlog_content(),blog.getBlog_time(),blog.getBlog_type(),blog.getBlog_id());
		return exUpdate > 0;
	}

	@Override
	public List<BlogDto> queryBlogByPageAndSizeAndUserid(Integer userid, Integer page, Integer size) {
		page = (page-1)*size;
		String sql = "select  b.blog_id,b.blog_time,b.blog_content,b.blog_title,b.blog_type,u.user_name as blog_username from t_blog  as b inner join t_user as u on b.blog_author=u.user_id where b.blog_author=? limit ?,?";
		return  DBUtil.exQuery(sql, BlogDto.class, userid,page ,size);
	}
	
	@Override
	public List<BlogCount> queryBlogSizeByUserid(Integer userid) {
		String sql = "SELECT COUNT(*) as myBlogNum FROM t_blog WHERE t_blog.blog_author = ?";
		return DBUtil.exQuery(sql, BlogCount.class, userid);
		
	}
}
