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
		//1 �����������
		request.setCharacterEncoding("utf-8");
		//2 ��ȡ���󴫵ݵĲ���
		String name=request.getParameter("username");
		String pwd=request.getParameter("userpwd");
		String job=request.getParameter("userjob");
		String info=request.getParameter("userinfo");
		//3 ����ҵ���߼���ķ�����ע���û�
		UserBiz biz=new UserBizImpl();
		User user=new User();
		user.setUser_name(name);
		user.setUser_pwd(pwd);
		user.setUser_job(job);
		user.setUser_info(info);
		//4 �ж��Ƿ�ע��ɹ����ɹ�����ת��login.jsp ,ʧ�ܾ���ת��register.jsp
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		if(biz.registerUser(user)){
			//ע��ɹ�,��ת��loginҳ��
			out.print("<script>alert('ע��ɹ�');location.href='login.jsp';</script>");	

			/*
		    //��ȡ�����ĵ�¼�û�
		     User user1=biz.checkLogin(name, pwd);
		     request.setAttribute("loginuser", user1);
		     //ת��index.jsp
		     request.getRequestDispatcher("index.jsp").forward(request, response);
		     */
		}else{
			//ע��ʧ��
			out.print("<script>alert('ע��ʧ��');history.back();</script>");	
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
