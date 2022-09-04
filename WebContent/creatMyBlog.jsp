<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>添加博客</title>
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
		<div class="left" id="about_me" style="height: 620px;">
			<div class="weizi">
				<div class="wz_text">
					当前位置：<a href="DoBlog?flag=getBlogByLoginUser">首页</a>>
					<h1>编写博客</h1>
				</div>
			</div>
			<div class="about_content">

				<!-- 提交博客表单开始 -->
				<form action="DoBlog?flag=addblogByLoginUser" method="post">
					<div>
						<h1>
							标题： <span> <input type="text" name="blogtitle" id="blogtitle" style="width: 200px; height: 20px; padding-top: 2px;"	placeholder="输入标题" required="required" />
							</span> <span style="float: right"> 
							<a style="font-size: 16px">博客类型：</a><input type="text" name="blogtype" id="blogtype" placeholder="博客类型" required="required" />
							</span>
						</h1>
					</div>
					<a>---------------------------------------------------------------------------------------------------------------------</a>
					<br />
					<textarea name="blogcontent" id="ueditor" ></textarea>
					<script type="text/javascript" charset="utf-8"src="ueditor/ueditor.config.js"></script>
					<script type="text/javascript" charset="utf-8"src="ueditor/ueditor.all.min.js"></script>
					<script type="text/javascript" charset="utf-8"src="ueditor/lang/zh-cn/zh-cn.js"></script>
					<script type="text/javascript">
						//实例化编辑器
						UE.getEditor('ueditor', {
							initialFrameWidth : null,
							initialFrameHeight : 400,
							zIndex : 0
						})
						$(document).ready(function() {
							var ue = UE.getEditor('ueditor');
							//编辑器默认值赋值
							ue.ready(function() {//编辑器初始化完成再赋值
								ue.setContent(''); //赋值给UEditor
							});

						});
					</script>
					<input type="submit" style="color: blue; text-align: center; width: 50px; height: 20px; float: right;"value="提交">
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
