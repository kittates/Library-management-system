package com.enjoy.book.listener;

import com.enjoy.book.biz.TypeBiz;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.enjoy.book.bean.Type;

import java.util.List;

//设置监听器，程序启动时自动执行以下代码

@WebListener
public class TypeServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1、获取当前数据库中所有的类型信息
        TypeBiz biz = new TypeBiz();
        List<Type> types = biz.getAll();

        //2、获取application对象
        ServletContext application = servletContextEvent.getServletContext();

        //3、将信息存在application对象中 所有页面可以直接使用这个信息
        application.setAttribute("types",types);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
