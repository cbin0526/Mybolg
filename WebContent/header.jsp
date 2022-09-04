<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header">
      <h1>个人博客</h1>
      <p>${sessionScope.loginuser.user_signature}</p>    
    </div>
     <!--header end-->
    <!--nav-->
     <div id="nav">
        <ul>
         <li><a href="DoBlog?flag=getBlogByLoginUser">首页</a></li>
         <li><a href="aboutme.jsp">关于我</a></li>
         <li><a href="shuo.html">碎言碎语</a></li>
         <li><a href="DoBlog?flag=getDiaryByLoginUser">个人日记</a></li>
         <li><a href="xc.html">相册展示</a></li>
         <li><a href="learn.html">学无止境</a></li>
         <li><a href="guestbook.html">留言板</a></li>
         <div class="clear"></div>
        </ul>
      </div>
 <!--nav end-->