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
		//执行跟blog有关的操作的servlet
		//1 设置请求编码
		request.setCharacterEncoding("utf-8");
		//2 获取请求标杆
		String flag=request.getParameter("flag");

		if("getBlogByLoginUser".equals(flag)) {
			//查询当前登录用户的博客
			BlogBiz biz2=new BlogBizImpl();

			//获取session中保存的用户
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("loginuser");

			if(user==null) {
				//设置响应类型和编码
				response.setContentType("text/html;charset=utf-8");
				//获取指定的输出对象
				PrintWriter pw=response.getWriter();
				pw.print("<script>alert('会话失效，请先登录');location.href='login.jsp';</script>");
				pw.flush();
				pw.close();
			}else {
				//要放入首页的博客数据，而且博客的作者必须是当前的登录用户
				//List<Blog>  list=  biz2.getBlogDataByUserId(user.getUser_id());

				
				Integer page = 1; //默认页数
				Integer size = 3; //默认大小
				
				if(request.getParameter("page") != null && request.getParameter("size") != null) {
					//非法数据捕获
					try {
						page = Integer.valueOf(request.getParameter("page"));
						size = Integer.valueOf(request.getParameter("size"));
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				//负数兜底
				if(page <= 0 || size <= 0) {
					page = 1; //默认页数
					size = 3; //默认大小
				}
				//求最大页数
				Integer AllBlogSize = biz2.queryBlogSizeByUserid(user.getUser_id());
				AllBlogSize = (AllBlogSize % size == 0)?(AllBlogSize / size):((AllBlogSize / size) + 1);
				//页面容错
				page = (page > AllBlogSize)?(AllBlogSize):(page);
				List<BlogDto> list = biz2.getBlogDataByPageAndSizeAndUserid(user.getUser_id(), page , size);
				//将值保存在请求域中
				request.setAttribute("list", list);	
				request.setAttribute("page", page);
				request.setAttribute("size", size);
				request.setAttribute("AllBlogSize", AllBlogSize);
				//转发index.jsp
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}


		}
		else if("addblogByLoginUser".equals(flag)) {
			//添加当前用户的博客
			BlogBizImpl blogBiz = new BlogBizImpl();
			//获取要添加博客的用户
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("loginuser");
			//判断用户是否为null
			//设置输出对象
			PrintWriter pw = null;
			//设置响应类型和编码
			response.setContentType("text/html;charset=utf-8");
			if(user == null) {
				pw = response.getWriter();
				//设置响应类型和编码
				response.setContentType("text/html;charset=utf-8");
				pw.print("<script>alert('会话失效，请从新登入');location.href='login.jsp'</script>");
				//关闭对象流
				pw.flush();
				pw.close();
			}else {
				//获取数据
				String blogtitle = request.getParameter("blogtitle");
				String blogtype = request.getParameter("blogtype");
				String blogcontent = request.getParameter("blogcontent");
				
				//给当前用户添加博客
				//创建一个博客对象
				Blog blog = new Blog();
				blog.setBlog_title(blogtitle);
				blog.setBlog_author(user);
				blog.setBlog_content(blogcontent);
				blog.setBlog_type(blogtype);
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
				String blog_time = format.format(date);
				blog.setBlog_time(blog_time);
				boolean addflag = blogBiz.addblogByLoginUser(user.getUser_id(),blog);
				if(addflag) {
					//插入成功
					pw = response.getWriter();
					//设置响应类型和编码
					response.setContentType("text/html;charset=utf-8");
					pw.print("<script>alert('添加成功');location.href='creatMyBlog.jsp';</script>");
				}else {
					//插入失败
					pw = response.getWriter();
					//设置响应类型和编码
					response.setContentType("text/html;charset=utf-8");
					pw.print("<script>alert('添加失败');location.href='creatMyBlog.jsp';</script>");
				}
				//关闭对象流
				pw.flush();
				pw.close();
			}
		}
		else if("showblogByid".equals(flag)) {
			//展示当前用户的一个博客
			//从session中获取用户对象
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("loginuser");	
			BlogBizImpl blogBiz = new BlogBizImpl();
			if(user == null) {
				//设置响应类型和编码
				response.setContentType("text/html;charset=utf-8");
				PrintWriter 	pw = response.getWriter();
			
				pw.print("<script>alert('会话失效，请从新登入');location.href='login.jsp';</script>");
				//关闭对象流
				pw.flush();
				pw.close();
			}else {
					//获取博客id
					String blogid = request.getParameter("blogid");
					//获取当前的博客对象
					Blog blog = blogBiz.getBlogDataByBlogid(Integer.parseInt(blogid));					
					if(blog == null) {
						//设置响应类型和编码
						response.setContentType("text/html;charset=utf-8");
						PrintWriter pw = response.getWriter();
						pw.print("<script>alert('博客获取失败');history.go(-1);</script>");
						//关闭对象流
						pw.flush();
						pw.close();
					}else {
						//将值保存在请求域中
						 //request.setCharacterEncoding("utf-8");
						request.setAttribute("blog", blog);	
						//转发博客对象到updateMyblog.jsp
						request.getRequestDispatcher("updateMyblog.jsp").forward(request, response);
						//跳转到updateMyblog.jsp
					}
			}
			
		}
		else if("updateblogByLoginUser".equals(flag)) {
			//修改当前用户的博客
			BlogBizImpl blogBiz = new BlogBizImpl();
			//获取要添加博客的用户
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("loginuser");
			//判断用户是否为null		
			if(user == null) {
				PrintWriter pw = response.getWriter();
				//设置响应类型和编码
				response.setContentType("text/html;charset=utf-8");
				pw.print("<script>alert('会话失效，请从新登入');location.href='login.jsp'</script>");
				//关闭对象流
				pw.flush();
				pw.close();
			}else {
				//获取数据
				String blogtitle = request.getParameter("blogtitle");
				String blogtype = request.getParameter("blogtype");
				String blogcontent = request.getParameter("blogcontent");
				String blog_id = request.getParameter("blog_id");
				//给当前用户添加博客
				//创建一个博客对象
				Blog blog = new Blog();
				blog.setBlog_title(blogtitle);
				blog.setBlog_author(user);
				blog.setBlog_content(blogcontent);
				blog.setBlog_type(blogtype);
				blog.setBlog_id(Integer.parseInt(blog_id));
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
				String blog_time = format.format(date);
				blog.setBlog_time(blog_time);
				boolean addflag = blogBiz.updateBlogByBlogId(blog);
				if(addflag) {
					//插入成功
					//设置响应类型和编码
					response.setContentType("text/html;charset=utf-8");
					PrintWriter pw = response.getWriter();
					pw.print("<script>alert('修改成功');location.href='DoBlog?flag=getBlogByLoginUser';</script>");
					//关闭对象流
					pw.flush();
					pw.close();
				}else {
					//插入失败
					//设置响应类型和编码
					response.setContentType("text/html;charset=utf-8");
					//设置响应类型和编码
					PrintWriter pw = response.getWriter();
					pw.print("<script>alert('修改失败');history.go(-1);</script>");
					//关闭对象流
					pw.flush();
					pw.close();
				}
			}
		}
		else if("deleteblogByLoginUser".equals(flag)) {
			//删除当前用户的博客

		}
		else if("loginout".equals(flag)) {
			//注销当前登录用户
			//获取session对象
			HttpSession session =request.getSession();
			session.invalidate();//设置session失效
			//设置响应类型和编码
			response.setContentType("text/html;charset=utf-8");
			//获取指定的输出对象
			PrintWriter pw=response.getWriter();
			pw.print("<script>alert('会话已经失效，请重新登录');location.href='login.jsp';</script>");
			pw.flush();
			pw.close();
		}
		else if("getDiaryByLoginUser".equals(flag)) {
			//查询当前登录用户的日记
			//获取session中保存的用户
			DiaryBizImpl biz = new DiaryBizImpl();
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("loginuser");
			//调用获取当前用户的业务逻辑，获取对应的日记
			if(user==null) {
				//设置响应类型和编码
				response.setContentType("text/html;charset=utf-8");
				//获取指定的输出对象
				PrintWriter pw=response.getWriter();
				pw.print("<script>alert('会话失效，请先登录');location.href='login.jsp';</script>");
				pw.flush();
				pw.close();
			}else {
				 List<Diary> list = biz.getDiaryByUserId(user.getUser_id());
				//将获取的日记保存在请求域中
				request.setAttribute("list", list);	
				//转发addMydiary.jsp页面中	 
				request.getRequestDispatcher("addMydiary.jsp").forward(request, response);
			}
		}
		else if("addDiaryByLoginUser".equals(flag)) {
			//添加当前用户的日记
			//获取session保存的用户
			DiaryBizImpl biz = new DiaryBizImpl();
			//获取指定的输出对象			
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("loginuser");
			if(user == null) {
				//设置响应类型和编码
				response.setContentType("text/html;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.print("<script>alert('登入失效，请先登录');location.href='login.jsp';</script>");
				pw.flush();
				pw.close();
			}else {
				//获取日记对象
				String diary_content = request.getParameter("diary_content");
				String diary_date = request.getParameter("diary_time");
				
				//获取时分秒
				Date time = new Date();
				DateFormat df = new SimpleDateFormat("HH:mm:ss");
				String format = df.format(time);
				//拼接时间
				String diary_time = "日期:"+diary_date +"  时间:"+ format;
				//创建日记对象
				Diary diary = new Diary();
				diary.setDiary_author(user);
				diary.setDiary_content(diary_content);
				diary.setDiary_time(diary_time);
				boolean addflag = biz.addDiaryByLoginUser(diary);
				
				if(addflag) {
					//设置响应类型和编码
					response.setContentType("text/html;charset=utf-8");
					//获取指定的输出对象
					PrintWriter pw = response.getWriter();
					pw.print("<script>alert('添加成功');location.href='DoBlog?flag=getDiaryByLoginUser';</script>");
					pw.flush();
					pw.close();
				}else if(!addflag){
					//设置响应类型和编码
					response.setContentType("text/html;charset=utf-8");
					//获取指定的输出对象
					PrintWriter pw = response.getWriter();
					pw.print("<script>alert('添加失败');location.href='DoBlog?flag=getDiaryByLoginUser';</script>");	
					pw.flush();
					pw.close();
				}
				
			}
			
		}
		else if("deletediaryByLoginUser".equals(flag)) {
			//删除用户的一篇日记
			//获取session保存的用户
			DiaryBizImpl biz = new DiaryBizImpl();
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("loginuser");
			try {
				//设置响应编码
				response.setContentType("text/html;charset=utf-8");
				//获取指定的输出对象
				PrintWriter pw = response.getWriter();
				if(user == null) {
					pw.print("<script>alert('会话过期，重新登入');location.href='login.jsp';</script>");
				}else {
					//获取日记的id
					String diaryid = request.getParameter("diary_id");
					//调用业务逻辑层删除
					boolean removeflag = biz.removeDiaryByLoginUser(Integer.parseInt(diaryid));
					if(removeflag) {
						//删除成功
						//跳转到查询日记servlet
						pw.print("<script>alert('删除成功');location.href='DoBlog?flag=getDiaryByLoginUser';</script>");
						pw.flush();
						pw.close();
					}else {
						pw.print("<script>alert('删除失败');location.href='DoBlog?flag=getDiaryByLoginUser';</script>");
						pw.flush();
						pw.close();
					}
				}
			}catch (Exception e) {
			}
		}
		else if("updateMydiary".equals(flag)) {
		  //修改日记内容
		  //获取session中的用户
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("loginuser");
			if(user == null) {
				//设置响应编码
				response.setContentType("text/html;charset=utf-8");
				//获取输出对象
				PrintWriter pw = response.getWriter();
				pw.print("<script>alert('会话过期，重新登入');location.href='login.jsp';</script>");
				pw.flush();
				pw.close();
			}else {
				DiaryBizImpl diaryBiz = new DiaryBizImpl();
				String diary_id = request.getParameter("diary_id");
				//System.out.println(diary_id);
				String diary_content = request.getParameter("diary_content");
				boolean updateflag = diaryBiz.updateDiaryByLoginUser(Integer.parseInt(diary_id), diary_content);
				if(updateflag) {
					//删除成功
					//跳转到查询日记servlet
					//设置响应编码
					response.setContentType("text/html;charset=utf-8");
					//获取输出对象
					PrintWriter pw = response.getWriter();
					pw.print("<script>alert('修改成功');location.href='DoBlog?flag=getDiaryByLoginUser';</script>");
					pw.flush();
					pw.close();
				}else {
					//设置响应编码
					response.setContentType("text/html;charset=utf-8");
					//获取输出对象
					PrintWriter pw = response.getWriter();
					pw.print("<script>alert('修改失败');location.href='DoBlog?flag=getDiaryByLoginUser';</script>");
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
