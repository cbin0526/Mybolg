<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>关于我</title>
<meta name="keywords" content="个人博客" />
<meta name="description" content="" />
<link rel="stylesheet" href="css/index.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/animate.css" />
<script type="text/javascript" src="js/jquery1.42.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor/ueditor.all.min.js"></script>
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<script>
	var loginuser = ${sessionScope.loginuser}+"";
	if(loginuser == ""){
		alert('尚未登录，请先登录！');
		location.href='login.jsp';
	}
	//登出函数
	function loginout(){
		if(confirm('你确定要注销当前登录状态吗？')){
			location.href="DoBlog?flag=loginout";
		}
	}
</script>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<!--content start-->
	<div id="content">
		<!--left-->
		<div class="left" id="about_me" style="height: 570px;">
			<div class="weizi">
				<div class="wz_text">
					当前位置：<a href="DoBlog?flag=getBlogByLoginUser">首页</a>>
					<h1>关于我</h1>
				</div>
			</div>
			<div class="about_content">
				<!-- 提交博客表单开始 -->
				<form id="form2" method="post" action="DoUser">
				<div align="center" style="width: 700px;height: 515px;border: solid;">
				<p>---------------------------------------------------------------------------------------</p>
				<p><h1>个人信息</h1></p>
				<p>---------------------------------------------------------------------------------------</p><br>
				<div align="left" style="width: 455px">
            		<p>用户昵称：<input type="text" name="username"/><span></span></p><br>
            		<p>用户密码：<input type="text" name="userpwd"  /><span></span></p><br>
          		 	<p>用户职业：<input type="text" name="userjob"  /><span></span></p><br>
          		 	<p ><span style="vertical-align:top" >个人描述：</span><textarea style="width: 365px; height: 49px;"></textarea><span></span></p><br>
 	                <p>个性签名：<input type="text" name="userinfo" style="width: 364px;height: 25px"/><span></span></p><br>
 	                <p>个人头像：<input type="file" name="userlogo"><br><br>
 	                <img alt="" src="" style="height: 100px;width: 100px"><br>
                </div>
                <button type="submit" style="width: 60px;float: right;margin-right: 20px; margin-bottom: 20px"><h3>修改</h3></button>
                </div>
                </form>
				<!-- 提交博客表单结束 -->
			</div>
		</div>
		<!--end left -->
		<jsp:include page="right.jsp"></jsp:include>
		<div class="clear"></div>
	</div>
	<!--content end-->
	<!--footer-->
	<div id="footer">
		<p>Design by:少年 2014-8-9</p>
	</div>
	<!--footer end-->

</body>
</html>