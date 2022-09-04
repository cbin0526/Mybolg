package com.etc.myblog.dto;

import com.etc.myblog.entity.User;

public class BlogDto {
	private Integer blog_id;
	private String blog_title;
	private String blog_content;
	private String blog_type;
	private String blog_time;
	private String blog_username;
	public Integer getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(Integer blog_id) {
		this.blog_id = blog_id;
	}
	public String getBlog_title() {
		return blog_title;
	}
	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}
	public String getBlog_content() {
		return blog_content;
	}
	public void setBlog_content(String blog_content) {
		this.blog_content = blog_content;
	}
	public String getBlog_type() {
		return blog_type;
	}
	public void setBlog_type(String blog_type) {
		this.blog_type = blog_type;
	}
	public String getBlog_time() {
		return blog_time;
	}
	public void setBlog_time(String blog_time) {
		this.blog_time = blog_time;
	}
	public String getBlog_username() {
		return blog_username;
	}
	public void setBlog_username(String blog_username) {
		this.blog_username = blog_username;
	}
	
	
	
}
