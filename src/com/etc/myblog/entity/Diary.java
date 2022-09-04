package com.etc.myblog.entity;

public class Diary {
   private Integer diary_id;
   private String diary_content;
   private String diary_time;
   private User user = new User();
   
public Integer getDiary_id() {
	return diary_id;
}
public void setDiary_id(Integer diary_id) {
	this.diary_id = diary_id;
}
public String getDiary_content() {
	return diary_content;
}
public void setDiary_content(String diary_content) {
	this.diary_content = diary_content;
}
public String getDiary_time() {
	return diary_time;
}
public void setDiary_time(String diary_time) {
	this.diary_time = diary_time;
}
public Integer getDiary_author() {
	return user.getUser_id();
}
public void setDiary_author(User user) {
	this.user.setUser_id(user.getUser_id());
}
	
	
	
}
