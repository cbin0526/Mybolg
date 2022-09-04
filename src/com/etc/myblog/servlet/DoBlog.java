package com.etc.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.myblog.biz.BlogBiz;
import com.etc.myblog.biz.impl.BlogBizImpl;
import com.etc.myblog.biz.impl.DiaryBizImpl;
import com.etc.myblog.dto.BlogDto;
import com.etc.myblog.entity.Blog;
import com.etc.myblog.entity.Diary;
import com.etc.myblog.entity.User;

/**
 * Servlet implementation class DoBlog
 */
@WebServlet("/DoBlog")
public class DoBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoBlog() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ִ�и�blog�йصĲ�����servlet
		//1 �����������
		request.setCharacterEncoding("utf-8");
		//2 ��ȡ������
		String flag=request.getParameter("flag");

		if("getBlogByLoginUser".equals(flag)) {
			//��ѯ��ǰ��¼�û��Ĳ���
			BlogBiz biz2=new BlogBizImpl();

			//��ȡsession�б�����û�
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("loginuser");

			if(user==null) {
				//������Ӧ���ͺͱ���
				response.setContentType("text/html;charset=utf-8");
				//��ȡָ�����������
				PrintWriter pw=response.getWriter();
				pw.print("<script>alert('�ỰʧЧ�����ȵ�¼');location.href='login.jsp';</script>");
				pw.flush();
				pw.close();
			}else {
				//Ҫ������ҳ�Ĳ������ݣ����Ҳ��͵����߱����ǵ�ǰ�ĵ�¼�û�
				//List<Blog>  list=  biz2.getBlogDataByUserId(user.getUser_id());

				
				Integer page = 1; //Ĭ��ҳ��
				Integer size = 3; //Ĭ�ϴ�С
				
				if(request.getParameter("page") != null && request.getParameter("size") != null) {
					//�Ƿ����ݲ���
					try {
						page = Integer.valueOf(request.getParameter("page"));
						size = Integer.valueOf(request.getParameter("size"));
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				//��������
				if(page <= 0 || size <= 0) {
					page = 1; //Ĭ��ҳ��
					size = 3; //Ĭ�ϴ�С
				}
				//�����ҳ��
				Integer AllBlogSize = biz2.queryBlogSizeByUserid(user.getUser_id());
				AllBlogSize = (AllBlogSize % size == 0)?(AllBlogSize / size):((AllBlogSize / size) + 1);
				//ҳ���ݴ�
				page = (page > AllBlogSize)?(AllBlogSize):(page);
				List<BlogDto> list = biz2.getBlogDataByPageAndSizeAndUserid(user.getUser_id(), page , size);
				//��ֵ��������������
				request.setAttribute("list", list);	
				request.setAttribute("page", page);
				request.setAttribute("size", size);
				request.setAttribute("AllBlogSize", AllBlogSize);
				//ת��index.jsp
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}


		}
		else if("addblogByLoginUser".equals(flag)) {
			//��ӵ�ǰ�û��Ĳ���
			BlogBizImpl blogBiz = new BlogBizImpl();
			//��ȡҪ��Ӳ��͵��û�
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("loginuser");
			//�ж��û��Ƿ�Ϊnull
			//�����������
			PrintWriter pw = null;
			//������Ӧ���ͺͱ���
			response.setContentType("text/html;charset=utf-8");
			if(user == null) {
				pw = response.getWriter();
				//������Ӧ���ͺͱ���
				response.setContentType("text/html;charset=utf-8");
				pw.print("<script>alert('�ỰʧЧ������µ���');location.href='login.jsp'</script>");
				//�رն�����
				pw.flush();
				pw.close();
			}else {
				//��ȡ����
				String blogtitle = request.getParameter("blogtitle");
				String blogtype = request.getParameter("blogtype");
				String blogcontent = request.getParameter("blogcontent");
				
				//����ǰ�û���Ӳ���
				//����һ�����Ͷ���
				Blog blog = new Blog();
				blog.setBlog_title(blogtitle);
				blog.setBlog_author(user);
				blog.setBlog_content(blogcontent);
				blog.setBlog_type(blogtype);
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
				String blog_time = format.format(date);
				blog.setBlog_time(blog_time);
				boolean addflag = blogBiz.addblogByLoginUser(user.getUser_id(),blog);
				if(addflag) {
					//����ɹ�
					pw = response.getWriter();
					//������Ӧ���ͺͱ���
					response.setContentType("text/html;charset=utf-8");
					pw.print("<script>alert('��ӳɹ�');location.href='creatMyBlog.jsp';</script>");
				}else {
					//����ʧ��
					pw = response.getWriter();
					//������Ӧ���ͺͱ���
					response.setContentType("text/html;charset=utf-8");
					pw.print("<script>alert('���ʧ��');location.href='creatMyBlog.jsp';</script>");
				}
				//�رն�����
				pw.flush();
				pw.close();
			}
		}
		else if("showblogByid".equals(flag)) {
			//չʾ��ǰ�û���һ������
			//��session�л�ȡ�û�����
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("loginuser");	
			BlogBizImpl blogBiz = new BlogBizImpl();
			if(user == null) {
				//������Ӧ���ͺͱ���
				response.setContentType("text/html;charset=utf-8");
				PrintWriter 	pw = response.getWriter();
			
				pw.print("<script>alert('�ỰʧЧ������µ���');location.href='login.jsp';</script>");
				//�رն�����
				pw.flush();
				pw.close();
			}else {
					//��ȡ����id
					String blogid = request.getParameter("blogid");
					//��ȡ��ǰ�Ĳ��Ͷ���
					Blog blog = blogBiz.getBlogDataByBlogid(Integer.parseInt(blogid));					
					if(blog == null) {
						//������Ӧ���ͺͱ���
						response.setContentType("text/html;charset=utf-8");
						PrintWriter pw = response.getWriter();
						pw.print("<script>alert('���ͻ�ȡʧ��');history.go(-1);</script>");
						//�رն�����
						pw.flush();
						pw.close();
					}else {
						//��ֵ��������������
						 //request.setCharacterEncoding("utf-8");
						request.setAttribute("blog", blog);	
						//ת�����Ͷ���updateMyblog.jsp
						request.getRequestDispatcher("updateMyblog.jsp").forward(request, response);
						//��ת��updateMyblog.jsp
					}
			}
			
		}
		else if("updateblogByLoginUser".equals(flag)) {
			//�޸ĵ�ǰ�û��Ĳ���
			BlogBizImpl blogBiz = new BlogBizImpl();
			//��ȡҪ��Ӳ��͵��û�
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("loginuser");
			//�ж��û��Ƿ�Ϊnull		
			if(user == null) {
				PrintWriter pw = response.getWriter();
				//������Ӧ���ͺͱ���
				response.setContentType("text/html;charset=utf-8");
				pw.print("<script>alert('�ỰʧЧ������µ���');location.href='login.jsp'</script>");
				//�رն�����
				pw.flush();
				pw.close();
			}else {
				//��ȡ����
				String blogtitle = request.getParameter("blogtitle");
				String blogtype = request.getParameter("blogtype");
				String blogcontent = request.getParameter("blogcontent");
				String blog_id = request.getParameter("blog_id");
				//����ǰ�û���Ӳ���
				//����һ�����Ͷ���
				Blog blog = new Blog();
				blog.setBlog_title(blogtitle);
				blog.setBlog_author(user);
				blog.setBlog_content(blogcontent);
				blog.setBlog_type(blogtype);
				blog.setBlog_id(Integer.parseInt(blog_id));
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
				String blog_time = format.format(date);
				blog.setBlog_time(blog_time);
				boolean addflag = blogBiz.updateBlogByBlogId(blog);
				if(addflag) {
					//����ɹ�
					//������Ӧ���ͺͱ���
					response.setContentType("text/html;charset=utf-8");
					PrintWriter pw = response.getWriter();
					pw.print("<script>alert('�޸ĳɹ�');location.href='DoBlog?flag=getBlogByLoginUser';</script>");
					//�رն�����
					pw.flush();
					pw.close();
				}else {
					//����ʧ��
					//������Ӧ���ͺͱ���
					response.setContentType("text/html;charset=utf-8");
					//������Ӧ���ͺͱ���
					PrintWriter pw = response.getWriter();
					pw.print("<script>alert('�޸�ʧ��');history.go(-1);</script>");
					//�رն�����
					pw.flush();
					pw.close();
				}
			}
		}
		else if("deleteblogByLoginUser".equals(flag)) {
			//ɾ����ǰ�û��Ĳ���

		}
		else if("loginout".equals(flag)) {
			//ע����ǰ��¼�û�
			//��ȡsession����
			HttpSession session =request.getSession();
			session.invalidate();//����sessionʧЧ
			//������Ӧ���ͺͱ���
			response.setContentType("text/html;charset=utf-8");
			//��ȡָ�����������
			PrintWriter pw=response.getWriter();
			pw.print("<script>alert('�Ự�Ѿ�ʧЧ�������µ�¼');location.href='login.jsp';</script>");
			pw.flush();
			pw.close();
		}
		else if("getDiaryByLoginUser".equals(flag)) {
			//��ѯ��ǰ��¼�û����ռ�
			//��ȡsession�б�����û�
			DiaryBizImpl biz = new DiaryBizImpl();
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("loginuser");
			//���û�ȡ��ǰ�û���ҵ���߼�����ȡ��Ӧ���ռ�
			if(user==null) {
				//������Ӧ���ͺͱ���
				response.setContentType("text/html;charset=utf-8");
				//��ȡָ�����������
				PrintWriter pw=response.getWriter();
				pw.print("<script>alert('�ỰʧЧ�����ȵ�¼');location.href='login.jsp';</script>");
				pw.flush();
				pw.close();
			}else {
				 List<Diary> list = biz.getDiaryByUserId(user.getUser_id());
				//����ȡ���ռǱ�������������
				request.setAttribute("list", list);	
				//ת��addMydiary.jspҳ����	 
				request.getRequestDispatcher("addMydiary.jsp").forward(request, response);
			}
		}
		else if("addDiaryByLoginUser".equals(flag)) {
			//��ӵ�ǰ�û����ռ�
			//��ȡsession������û�
			DiaryBizImpl biz = new DiaryBizImpl();
			//��ȡָ�����������			
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("loginuser");
			if(user == null) {
				//������Ӧ���ͺͱ���
				response.setContentType("text/html;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.print("<script>alert('����ʧЧ�����ȵ�¼');location.href='login.jsp';</script>");
				pw.flush();
				pw.close();
			}else {
				//��ȡ�ռǶ���
				String diary_content = request.getParameter("diary_content");
				String diary_date = request.getParameter("diary_time");
				
				//��ȡʱ����
				Date time = new Date();
				DateFormat df = new SimpleDateFormat("HH:mm:ss");
				String format = df.format(time);
				//ƴ��ʱ��
				String diary_time = "����:"+diary_date +"  ʱ��:"+ format;
				//�����ռǶ���
				Diary diary = new Diary();
				diary.setDiary_author(user);
				diary.setDiary_content(diary_content);
				diary.setDiary_time(diary_time);
				boolean addflag = biz.addDiaryByLoginUser(diary);
				
				if(addflag) {
					//������Ӧ���ͺͱ���
					response.setContentType("text/html;charset=utf-8");
					//��ȡָ�����������
					PrintWriter pw = response.getWriter();
					pw.print("<script>alert('��ӳɹ�');location.href='DoBlog?flag=getDiaryByLoginUser';</script>");
					pw.flush();
					pw.close();
				}else if(!addflag){
					//������Ӧ���ͺͱ���
					response.setContentType("text/html;charset=utf-8");
					//��ȡָ�����������
					PrintWriter pw = response.getWriter();
					pw.print("<script>alert('���ʧ��');location.href='DoBlog?flag=getDiaryByLoginUser';</script>");	
					pw.flush();
					pw.close();
				}
				
			}
			
		}
		else if("deletediaryByLoginUser".equals(flag)) {
			//ɾ���û���һƪ�ռ�
			//��ȡsession������û�
			DiaryBizImpl biz = new DiaryBizImpl();
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("loginuser");
			try {
				//������Ӧ����
				response.setContentType("text/html;charset=utf-8");
				//��ȡָ�����������
				PrintWriter pw = response.getWriter();
				if(user == null) {
					pw.print("<script>alert('�Ự���ڣ����µ���');location.href='login.jsp';</script>");
				}else {
					//��ȡ�ռǵ�id
					String diaryid = request.getParameter("diary_id");
					//����ҵ���߼���ɾ��
					boolean removeflag = biz.removeDiaryByLoginUser(Integer.parseInt(diaryid));
					if(removeflag) {
						//ɾ���ɹ�
						//��ת����ѯ�ռ�servlet
						pw.print("<script>alert('ɾ���ɹ�');location.href='DoBlog?flag=getDiaryByLoginUser';</script>");
						pw.flush();
						pw.close();
					}else {
						pw.print("<script>alert('ɾ��ʧ��');location.href='DoBlog?flag=getDiaryByLoginUser';</script>");
						pw.flush();
						pw.close();
					}
				}
			}catch (Exception e) {
			}
		}
		else if("updateMydiary".equals(flag)) {
		  //�޸��ռ�����
		  //��ȡsession�е��û�
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("loginuser");
			if(user == null) {
				//������Ӧ����
				response.setContentType("text/html;charset=utf-8");
				//��ȡ�������
				PrintWriter pw = response.getWriter();
				pw.print("<script>alert('�Ự���ڣ����µ���');location.href='login.jsp';</script>");
				pw.flush();
				pw.close();
			}else {
				DiaryBizImpl diaryBiz = new DiaryBizImpl();
				String diary_id = request.getParameter("diary_id");
				//System.out.println(diary_id);
				String diary_content = request.getParameter("diary_content");
				boolean updateflag = diaryBiz.updateDiaryByLoginUser(Integer.parseInt(diary_id), diary_content);
				if(updateflag) {
					//ɾ���ɹ�
					//��ת����ѯ�ռ�servlet
					//������Ӧ����
					response.setContentType("text/html;charset=utf-8");
					//��ȡ�������
					PrintWriter pw = response.getWriter();
					pw.print("<script>alert('�޸ĳɹ�');location.href='DoBlog?flag=getDiaryByLoginUser';</script>");
					pw.flush();
					pw.close();
				}else {
					//������Ӧ����
					response.setContentType("text/html;charset=utf-8");
					//��ȡ�������
					PrintWriter pw = response.getWriter();
					pw.print("<script>alert('�޸�ʧ��');location.href='DoBlog?flag=getDiaryByLoginUser';</script>");
					pw.flush();
					pw.close();
				}
			}
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
