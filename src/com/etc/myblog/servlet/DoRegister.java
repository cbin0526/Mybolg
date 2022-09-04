package com.etc.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.myblog.biz.UserBiz;
import com.etc.myblog.biz.impl.UserBizImpl;
import com.etc.myblog.entity.User;

/**
 * Servlet implementation class DoRegister
 */
@WebServlet("/DoRegister")
public class DoRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 设置请求编码
		request.setCharacterEncoding("utf-8");
		//2 获取请求传递的参数
		String name=request.getParameter("username");
		String pwd=request.getParameter("userpwd");
		String job=request.getParameter("userjob");
		String info=request.getParameter("userinfo");
		//3 调用业务逻辑层的方法，注册用户
		UserBiz biz=new UserBizImpl();
		User user=new User();
		user.setUser_name(name);
		user.setUser_pwd(pwd);
		user.setUser_job(job);
		user.setUser_info(info);
		//4 判断是否注册成功，成功就跳转到login.jsp ,失败就跳转到register.jsp
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		if(biz.registerUser(user)){
			//注册成功,跳转到login页面
			out.print("<script>alert('注册成功');location.href='login.jsp';</script>");	

			/*
		    //获取完整的登录用户
		     User user1=biz.checkLogin(name, pwd);
		     request.setAttribute("loginuser", user1);
		     //转发index.jsp
		     request.getRequestDispatcher("index.jsp").forward(request, response);
		     */
		}else{
			//注册失败
			out.print("<script>alert('注册失败');history.back();</script>");	
		}
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
