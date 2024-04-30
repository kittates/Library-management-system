package com.enjoy.book.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * @author 凤姐
 * @desc 数据库的帮助类
 * 1.当前数据库的连接对象由连接池进行分配
 * 我们本次使用的连接池是c3p0
 * <p>
 * 2.当前数据库的帮助类可以完成事务处理
 */
public class DBHelper {

    //创建c3p0数据源对象
    static ComboPooledDataSource ds = new ComboPooledDataSource("mysql-book");
    //创建一个ThreadLocal对象，用于存放事务中需要用的连接对象(空)
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        //查找驱动(一个项目做一次)
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    /**
     * 获取连接
     *
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        //判断当前获取连接的方式是基于事务业务还是普通业务
        Connection conn = tl.get();
        //如果从threadLocal中能够获取连接对象，那么说明当前是事务的业务
        if (conn != null) {
            return conn;
        }
        //普通业务，直接从数据源中获取连接对象
        conn = ds.getConnection();
        return conn;
    }

    /**
     * 关闭方法
     *
     * @param conn
     * @param st
     * @param rs
     */
    public static void close(Connection conn, Statement st, ResultSet rs) throws SQLException {
        //如果是事务中的连接对象,我们随意不能关闭，需要使用事务的提交获取回滚来关闭连接对象
        Connection tlConn = tl.get();
        //是事务中的连接对象,我们什么也不做
        if (conn == tlConn) {
            return;
        }
        //不是事务的连接对象,我们能关闭
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }
        if (conn != null) {
            conn.close();
        }

    }

    public static void close(Connection conn) throws SQLException {
        //如果是事务中的连接对象,我们随意不能关闭，需要使用事务的提交获取回滚来关闭连接对象
        Connection tlConn = tl.get();
        //是事务中的连接对象,我们什么也不做
        if (tlConn == conn) {
            return;
        }
        //普通的情况
        if (conn != null) {
            conn.close();
        }

    }

    /**
     * 启动事务
     */
    public static void beginTransaction() throws SQLException {
        //事务已经启动
        Connection conn = tl.get();
        if (conn != null) {
            throw new RuntimeException("事务已启动");
        }
        //获取连接
        conn = getConnection();
        //存储到ThreadLocal中
        tl.set(conn);
        //设置手动提交
        conn.setAutoCommit(false);
    }

    /**
     * 提交事务
     */
    public static void commitTransaction() throws SQLException {
        //判断是否有未提交的事务
        Connection conn = tl.get();
        //没有事务
        if (conn == null) {
            throw new RuntimeException("没有事务,提交失败");
        }
        //有,提交
        conn.commit();
        //3.将ThreadLocal中的内容删除
        tl.remove();
        //关闭连接对象
        conn.close();
    }

    public static void rollbackTransaction() throws SQLException {
        //判断是否有未提交的事务
        Connection conn = tl.get();
        if (conn == null) {
            throw new RuntimeException("没有事务，回滚失败");
        }
        //有,回滚
        conn.rollback();
        //将ThreadLocal中的内容删除
        tl.remove();
        //关闭连接对象
        conn.close();
    }

    public static void main(String[] args) {
        //测试数据库的连接情况
        try {
            Connection connection = DBHelper.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
