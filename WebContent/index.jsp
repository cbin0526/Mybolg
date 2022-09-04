<%@page import="com.etc.myblog.dto.BlogDto"%>
<%@page import="com.etc.myblog.entity.Blog"%>
<%@page import=" java.util.List"%>
<%@page import="com.etc.myblog.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>个人博客</title>
<meta name="keywords" content="个人博客" />
<meta name="description" content="" />
<link rel="stylesheet" href="css/index.css"/>
<link rel="stylesheet" href="css/style.css"/>
<script type="text/javascript" src="js/jquery1.42.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<script>
function loginout(){
	
	if(confirm('你确定要注销当前登录状态吗？')){
		
		location.href="DoBlog?flag=loginout";
		
	}
	
}

var loginuser = "${sessionScope.loginuser}";
if(loginuser == ""){
	alert('尚未登录，请先登录！');
	location.href='login.jsp';
}

</script>

</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>
    <!--content start-->
    <div id="content">
         <!--left-->
         <div class="left" id="c_left">
           <div class="s_tuijian">
           <h2>文章<span>推荐</span> <a style="float: right;" href="creatMyBlog.jsp">创建博客</a></h2>
          </div>
          <div class="content_text">
          
          <c:forEach items="${requestScope.list}" var="blog" varStatus="" >
           <div class="wz">
            <h3><a href="#" title="浅谈：html5和html的区别">${ pageScope.blog.blog_title}</a></h3>
             <dl>
               <dt><img src="images/s.jpg" width="200"  height="123" alt=""></dt>
               <dd>
               <div class="dd_text_1"  style="height: 125px; overflow: hidden;">${ pageScope.blog.blog_content}</div>
               <p class="dd_text_2">
               <span class="left author">${ pageScope.blog.blog_username}</span><span class="left sj" style="width: 200px;">时间:${ pageScope.blog.blog_time}</span>
               <span class="left fl">分类:<a href="#" title="学无止境">${ pageScope.blog.blog_type}</a></span>
               <span class="left yd"><a href="DoBlog?flag=showblogByid&blogid=${ pageScope.blog.blog_id}" title="阅读全文">阅读全文</a></span>
                <div class="clear"></div>
               </dd>
             </dl>
             </div>
             </c:forEach>
             </div>
           <div align="center">
           <div>共${requestScope.AllBlogSize}页</div>
         	<a href="DoBlog?flag=getBlogByLoginUser&page=1&size=${requestScope.size}">首页</a>
			<a href="DoBlog?flag=getBlogByLoginUser&page=${requestScope.page-1}&size=${requestScope.size}">上一页</a>
			
			<a href="DoBlog?flag=getBlogByLoginUser&page=${requestScope.page+1}&size=${requestScope.size}">下一页</a>
			<a href="DoBlog?flag=getBlogByLoginUser&page=${requestScope.AllBlogSize}&size=${requestScope.size}">尾页</a>
    	 	</div>
         </div>
         <!--left end-->
         <jsp:include page="right.jsp"></jsp:include>
         <div class="clear"></div>
         
    <!--content end-->
    <!--footer start-->
    <div id="footer">
    
     <p>Design by:<a href="http://www.duanliang920.com" target="_blank">少年</a> 2014-8-9</p>
    </div>
    <!--footer end-->
    <script type="text/javascript">jQuery(".lanmubox").slide({easing:"easeOutBounce",delayTime:400});</script>
    <script  type="text/javascript" src="js/nav.js"></script>

</body>
</html>