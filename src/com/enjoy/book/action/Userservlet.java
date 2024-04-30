package com.enjoy.book.action;

import com.enjoy.book.bean.User;
import com.enjoy.book.biz.UserBiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//用于配置servlet的虚拟地址(虚拟路径)(http)，与web的地址平行，把这个类映射到user.let中
@WebServlet("/user.let")
public class Userservlet extends HttpServlet {
    //构建UserBiz的对象
    UserBiz userBiz = new UserBiz();

    //处理get类型的请求:a地址栏的请求 b:超链接 c:<form method='get'> d:重定向
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * /user.let?type=login 登录  /:项目的根目录-->web文件夹
     * /user.let?type=exit  安全退出
     * /user.let?type=modifyPwd 修改密码    一共三条链接
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    //处理post类型的请求,<form method='post'>
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        将请求和相应的编码设置好，放置乱码(前端页面最怕乱码)
        req.setCharacterEncoding("utf-8");
//        请求的是一个HTML文件
        resp.setContentType("text/html;charset=utf-8");
        //相应流的一个输出对象，能够打印信息
        PrintWriter out = resp.getWriter();

        //1、判断用户请求的类型为login
        //  获取type参数的值
        String method = req.getParameter("type");
        switch(method){
            case "login":
                //2、从login.html中获取用户名、密码和验证码
                String name=req.getParameter("name");
                String pwd=req.getParameter("pwd");
                String userCode = req.getParameter("valcode");
                //2.1提取session中的验证码进行判断
                String code = session.getAttribute("code").toString();
                // 一般验证码是不需要区分大小写的
                // TODO: 2022/5/4 修改用户验证码输入错误的情况 
                if(!code.equalsIgnoreCase(userCode)){
                    out.print("<script>alert('验证码错误');location.href = 'login.html';</script>");

                }

                //3、调用UserBiz的getUser方法，根据用户名和密码获取对应的用户对象
                User user = userBiz.getUser(name,pwd);

                //4、判断用户对象是否为null
                // TODO: 2022/5/4 修改用户密码账号输入错误的情况 
                if(user==null){
                    //4.1如果是null表示用户名和密码不正确，提示错误，回到登录页面
                    out.print("<script>alert('用户名或密码不存在');location.href = 'login.html';</script>");

                }
                else{
                    //4.2返回非空，登陆成功，将用户对象保存到session中，提示登录成功后，将页面跳转到index.jsp
//                    用户信息保存到session中
                    session.setAttribute("user",user); //user-->Object
                    out.print("<script>alert('登录成功');location.href = 'index.jsp';</script>");
                }
                break;

            case "exit":
                //1、清除session
                session.invalidate();
                //2、跳转到login.html中(框架中需要回去，无法用页面返回) top.jsp->parent->index.jsp->login.html
                out.print("<script>parent.window.location.href = 'login.html';</script>");
                break;

            case "modifyPwd":
                //验证用户是否登录
                if (session.getAttribute("user") == null) {
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }

                //修改密码
                //1.获取用户输入的新的密码
                String newPwd = req.getParameter("newpwd");
                String newPwd2 = req.getParameter("newpwd2");
//                if(newPwd!=newPwd2){
//                    out.println("<script>alert('两次密码输入错误');parent.window.location.href='set_pwd.jsp';</script>");
//                }
//                else{
                    //2.获取用户的编号-session
                    long id = ((User) session.getAttribute("user")).getId();

                    //3.调用biz层方法
                    int count = userBiz.modifyPwd(id, newPwd);
                    //4.响应-参考exit
                    if (count > 0) {
                        out.println("<script>alert('密码修改成功');parent.window.location.href='login.html';</script>");
                    } else {
                        out.println("<script>alert('密码修改失败');parent.window.location.href='login.html';</script>");
                    }
//                }

                break;

//                //修改密码
//                //1、获取用户输入的新密码
//                String oldPwd = req.getParameter("pwd");
//                String newPwd = req.getParameter("newpwd");
//                String newPwd2 = req.getParameter("newpwd2");
//                String oldPwdSql = ((User)session.getAttribute("user")).getPwd();
//
//                //输入的旧密码不正确 或者 新输入的两次密码不相同 --> 修改失败
//                if(oldPwd!=oldPwdSql){
//                    out.println("<script>alert('原密码输入错误');parent.window.location.href = 'index.jsp';</script>");
//                }
//                else if(newPwd!=newPwd2){
//                    out.println("<script>alert('两次密码输入不同');parent.window.location.href = 'index.jsp';</script>");
//                }
//                else{
//                    //2、获取用户的编号 session
//                    long id = ((User)session.getAttribute("user")).getId();
//
//                    //3、调用biz层方法
//                    int count = userBiz.modifyPwd(id,newPwd);
//
//                    //4、响应-参考exit
//                    //密码成功修改后需要退出重新登录
//                    if(count>0){
//                        out.print("<script>alert('密码修改成功');parent.window.location.href = 'login.html';</script>");
//                    }
//                    else{
//                        out.println("<script>alert('密码修改失败');parent.window.location.href = 'index.jsp';</script>");
//                    }
//                }
        }

    }
}
