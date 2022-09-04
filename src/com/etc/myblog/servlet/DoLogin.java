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
		//1�����������
		request.setCharacterEncoding("utf-8");
		//2 ��ȡ��¼��������
		String name=request.getParameter("username");
		String pwd=request.getParameter("userpwd");
		//3 �жϵ�¼���û��������������ݿ����Ƿ�ƥ��
		UserBiz biz=new UserBizImpl();
		
		User user=biz.checkLogin(name, pwd);
		//4 ���ƥ��ɹ������û�������request���У�ת����index.jspҳ�棬
		   //���û��ƥ������ݣ����¼ʧ�ܣ��ض���login.jspҳ��
		 if(user!=null)  {
			 //��ȡsession����
			 HttpSession session=request.getSession();
			 
			 //��ֵ�����ڻỰ����
			 session.setAttribute("loginuser", user);
	      //�ض��򵽲�ѯ��ǰ�û����͵�servlet��
		   response.sendRedirect("DoBlog?flag=getBlogByLoginUser");
		 
		 }
		 else{
			//������Ӧ��ʽ�ͱ���
			 response.setContentType("text/html;charset=utf-8");
			 //��ȡ��Ӧ�������			
			 PrintWriter pw=response.getWriter();
			 //�����Ӧ�ű�
			 pw.print("<script>alert('�û��������������');history.back();</script>");
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
