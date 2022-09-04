<%@page import="com.etc.myblog.entity.Diary"%>
<%@page import="com.etc.myblog.dto.BlogDto"%>
<%@page import="com.etc.myblog.entity.Blog"%>
<%@page import=" java.util.List"%>
<%@page import="com.etc.myblog.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>添加博客</title>
<meta name="keywords" content="个人博客" />
<meta name="description" content="" />
<link rel="stylesheet" href="css/index.css" />
<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery1.42.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor/ueditor.all.min.js"></script>
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
		<div class="left" id="riji">
			<div class="weizi">
				<div class="wz_text">
					当前位置：<a href="DoBlog?flag=getBlogByLoginUser">首页</a>>
					<h1>编写日记</h1>
				</div>
			</div>

			<div class="about_content">

				<div class="rj_content">

					<!-- 提交日记表单开始 -->
					<div class="shiguang animated bounceIn">
						<form action="DoBlog?flag=addDiaryByLoginUser" method="post">
							<div class="left sg_ico">
								<img src="images/my_1.jpg" width="120" height="120" alt="">
							</div>
							<div class="right sg_text">
								<img src="images/left.png" width="13" height="16" alt="左图标">
								<textarea rows="" cols=""
									style="width: 535px; height: 84px; border: none;"
									name="diary_content" required="required"></textarea>
								<input type="date" name="diary_time" required="required" /> 
								<input type="submit"style="color: blue; text-align: center; width: 50px; height: 20px; float: right;" value="提交">
							</div>
						</form>
					</div>
					<!-- 提交日记表单结束 -->
					<c:forEach items="${requestScope.list}" var="diary">
					<!--时光-->
					<div class="shiguang animated bounceIn">
						<div class="left sg_ico">
							<img src="images/my_1.jpg" width="120" height="120" alt="">
						</div>
						<!-- 修改表单开始 -->
						<form action="DoBlog?flag=updateMydiary" method="post">
							<div class="right sg_text">
								<img src="images/left.png" width="13" height="16" alt="左图标">
								<textarea name="diary_content" style="width: 530px; height: 80px; border: none;">${pageScope.diary.diary_content}</textarea>
								<br>
								<div>
									<input name="diary_id" hidden="hidden" value="${pageScope.diary.diary_id}" />
									<button  style="float: right;"onclick="updatediary('${pageScope.diary.diary_id}')">修改日志</button>
								</div>
						</form>
						<!-- 修改表单结束 -->
						<div>
							<button type="button" style="float: right;" onclick="deletediary('${pageScope.diary.diary_id}')">删除日志</button>
						</div>
								<div>
									<span style="float: right;">${pageScope.diary.diary_time}&nbsp;&nbsp;&nbsp;&nbsp;</span>
								</div>
							</div>
						<div class="clear"></div>
					</div>
					<!--时光 end-->
					</c:forEach>
				</div>
			</div>
			<div class="clear"></div>
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
	<script type="text/javascript">jQuery(".lanmubox").slide({easing:"easeOutBounce",delayTime:400});</script>
	<script type="text/javascript" src="js/nav.js"></script>
	<script type="text/javascript">
	function deletediary(diary_id) {
		var flag = confirm("确认删除第（"+diary_id+"）篇日记");
		 console.log(flag);
        if(flag){
         location.href="DoBlog?flag=deletediaryByLoginUser&diary_id="+diary_id; 
        }
	}
	
	function updatediary(diary_id) {
		var flag = confirm("确认修改这篇日记吗?");
	}
	</script>
</body>
</html>
