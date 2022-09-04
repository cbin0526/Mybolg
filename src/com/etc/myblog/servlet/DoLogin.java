package com.etc.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.etc.myblog.biz.BlogBiz;
import com.etc.myblog.biz.UserBiz;
import com.etc.myblog.biz.impl.BlogBizImpl;
import com.etc.myblog.biz.impl.UserBizImpl;
import com.etc.myblog.dto.BlogDto;
import com.etc.myblog.entity.User;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1设置请求编码
		request.setCharacterEncoding("utf-8");
		//2 获取登录名和密码
		String name=request.getParameter("username");
		String pwd=request.getParameter("userpwd");
		//3 判断登录的用户名和密码在数据库中是否匹配
		UserBiz biz=new UserBizImpl();
		
		User user=biz.checkLogin(name, pwd);
		//4 如果匹配成功，将用户保存在request域中，转发到index.jsp页面，
		   //如果没有匹配的数据，则登录失败，重定向到login.jsp页面
		 if(user!=null)  {
			 //获取session对象
			 HttpSession session=request.getSession();
			 
			 //将值保存在会话域中
			 session.setAttribute("loginuser", user);
	      //重定向到查询当前用户博客的servlet中
		   response.sendRedirect("DoBlog?flag=getBlogByLoginUser");
		 
		 }
		 else{
			//设置响应格式和编码
			 response.setContentType("text/html;charset=utf-8");
			 //获取响应输出对象			
			 PrintWriter pw=response.getWriter();
			 //输出响应脚本
			 pw.print("<script>alert('用户名或者密码错误！');history.back();</script>");
		     pw.flush();
		     pw.close();
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
