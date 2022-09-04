package com.etc.myblog.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 数据库访问通用类
 * 
 * @author Administrator
 *
 */
public final class DBUtil {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/myblog?serverTimezone=Asia/Shanghai";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	private DBUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取连接对象Connection
	 * 
	 * @return java.sql.Connection
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			// 2创建connection
			// 得到连接对象
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	/**
	 * 通用的增加，删除，修改的代码
	 * 
	 * @param sql    要执行的sql语句
	 * @param params 占位符的值，可以用数组来传递或者直接赋值
	 * @return int 受影响的行
	 */
	public static int exUpdate(String sql, Object... params) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = getConn();

			// 3创建一个PreparedStatement =>增加 删除 修改 完全一致，除了sql语句不同
			pstmt = conn.prepareStatement(sql);
			// 调用设置参数的方法
			setPstmt(pstmt, params);
			// 5 执行sql
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			closeAll(pstmt, conn, null);
		}

		return result;

	}

	/**
	 * 设置参数,补齐占位符
	 * 
	 * @param pstmt  PreparedStatement
	 * @param params 占位符的数据值列表(数组)
	 */
	private static void setPstmt(PreparedStatement pstmt, Object... params) {
		// 补齐占位符
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				try {
					pstmt.setObject(i + 1, params[i]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		System.out.println(pstmt);
	}

	/**
	 * 释放资源你的方法
	 * 
	 * @param pstmt PreparedStatement 对象
	 * @param conn  Connection 连接对象
	 * @param rs    ResultSet 结果集对象
	 */
	public static void closeAll(PreparedStatement pstmt, Connection conn, ResultSet rs) {
		// 6 释放资源
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通用的查询方法
	 * @param sql  查询sql语句
	 * @param cls  Class类型对象
	 * @param params  查询的占位符的值
	 * @return   List 查询返回对象集合
	 */
	public static List exQuery(String sql, Class cls, Object... params) {
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			System.out.print(conn);
			// 3创建一个PreparedStatement =>增加 删除 修改 完全一致，除了sql语句不同
			pstmt = conn.prepareStatement(sql);
			// 调用设置参数的方法
			setPstmt(pstmt, params);
			// 5 执行sql
			rs = pstmt.executeQuery();
			// 需要将resultset中的结果遍历出来
			while (rs.next()) {
				// 将rs中的指向的一行数据中的列的值取出来，将数据封装成一个java中的对象->放到一个方法中来
				Object obj = convert(rs, cls);
				// 将Obj并添加到List中来.
				list.add(obj);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放资源
			closeAll(pstmt, conn, rs);
		}

		return list;
	}

	/**
	 * 将rs集中的一行记录转换为Class->类型对象,并赋值
	 * 
	 * @param rs  结果集
	 * @param cls Class对象
	 * @return Object
	 */
	public static Object convert(ResultSet rs, Class cls) {

		// 将rs中的指向的一行数据中的列的值取出来，将数据封装成一个java中的对象->放到一个方法中来
		Object obj = null;
		try {
			obj = cls.newInstance();
			// 获取查询结果的列的数量
			ResultSetMetaData rsmd = rs.getMetaData();
//			System.out.println("rsmd: " + rsmd.getColumnCount());
			// 将这个ResultSet中的数据取出来
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				// 查询结果中显示的列名->
				String name = rsmd.getColumnLabel(i);
				// 拿到的查询的列的值
				Object value = rs.getObject(i);
				//输出这个value的"真实类型"是什么 ->时间 ->java.sql.Date
				//System.out.println(value.getClass().getName());
				// 将java.sql.Date ->实体类 java.time.LocalDate
				if(value instanceof java.sql.Date) {
					value = ((java.sql.Date)value).toLocalDate();
				}
				// obj=> obj.setxx方法
				// 第一个参数 对象名,"属性名"，属性的值
				BeanUtils.setProperty(obj, name, value);
			}

		} catch (InstantiationException | IllegalAccessException | SQLException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj;
	}

}
