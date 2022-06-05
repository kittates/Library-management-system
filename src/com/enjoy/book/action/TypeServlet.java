package com.enjoy.book.action;

import com.enjoy.book.bean.Type;
import com.enjoy.book.biz.TypeBiz;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/type.let")
public class TypeServlet extends HttpServlet {
    TypeBiz typeBiz = new TypeBiz();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * /type.let?type=add   添加()
     * /type.let?type=modifypre 修改预备(req->转发->type_modify.jsp)
     * /type.let?type=modify    修改(表单)
     * /type.let?type=remove&id=xx  删除(获取删除的类型编号)  为什么通过这种方式传递:不是敏感信息，信息量不是很大
     * /type_list.jsp   查询(所有的类型数据:当项目运行时(监听器)，数据存放到application中)
     * servlet:作用域有几个
     * request:时间比较短，同一个请求中传输信息
     * session:同一个会话(用户)
     * application:同一个运行的项目，范围更大一些 生命周期更长
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、设置编码    防止乱码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //2、获取各种对象
        PrintWriter out = resp.getWriter();
        ServletContext application = req.getServletContext(); //后续对application的更改也要发生改变

        //3、根据用户的需求完成业务
        String type = req.getParameter("type");
        switch(type){
            case "add":
                add(req,resp,out,application);
                break;
            case "modifypre":
                modifiyPre(req,resp,out,application);
                break;
            case "modify":
                modify(req,resp,out,application);
                break;
            case "remove":
                remove(req,resp,out,application);
                break;
        }
    }

    private void remove(HttpServletRequest req, HttpServletResponse resp,PrintWriter out, ServletContext application) {
        //1、获取需要删除的ID
        Long id = Long.parseLong(req.getParameter("id"));

        //2、调用方法,biz异常
        //3、更新application
        try{
            int count = typeBiz.remove(id);
            if(count>0){
                List<Type> types = typeBiz.getAll();
                application.setAttribute("types",types);
//            添加成功后跳转到查询列表中去
                out.println("<script>alert('删除成功');location.href='type_list.jsp';</script>");
            }
            else{
                out.println("<script>alert('删除失败');location.href='type_list.jsp';</script>");
            }
        }catch(Exception e){
//            将错误信息打印出来
            out.println("<script>alert('"+e.getMessage()+"');location.href='type_list.jsp';</script>");
        }


        //4、提示结果
    }

    /**
     * type_modify.jsp -->type.let?type=modify -->type_list.jsp
     * @param req
     * @param resp
     * @param out
     * @param application
     */
    private void modify(HttpServletRequest req, HttpServletResponse resp,PrintWriter out, ServletContext application) {
        //1、获取表单中的数据(id:hidden,name,parentId)
        Long id = Long.parseLong(req.getParameter("typeId"));
        String name= req.getParameter("typeName");
        Long parentId = Long.parseLong(req.getParameter("parentType"));

        //2、调用biz的修改方法
        int count = typeBiz.modify(id,name,parentId);
        //3、更新application
        if(count>0){
//            得到最新的一个类型列表
            List<Type> types = typeBiz.getAll();
            application.setAttribute("types",types);
//            添加成功后跳转到查询列表中去
            out.println("<script>alert('修改成功');location.href='type_list.jsp';</script>");
        }else{
//            添加失败后重新返回到添加页面
            out.println("<script>alert('修改失败');location.href='type_list.jsp';</script>");
        }
        //4、提示信息
    }

    /**
     * type_list.jsp -->type.let?type=modifypre&id=xx -转发->type_modify.jsp 用重定向的话会带来新的请求
     * @param req
     * @param resp
     * @param out
     * @param application
     */
    private void modifiyPre(HttpServletRequest req, HttpServletResponse resp,PrintWriter out, ServletContext application) throws ServletException, IOException{
        //1、获取需要修改的type对象的id
        Long id = Long.parseLong(req.getParameter("id"));
        //2、根据id获取type对象(到数据库中查)
        Type type = typeBiz.getById(id);
        //3、把type存到req中，同一个功能，req，(session,application 太大了，不合适)
        req.setAttribute("type",type);
//        Dispatcher会进行页面的转发，转发到tyoe_modify.jsp,包含req、resp
        req.getRequestDispatcher("type_modify.jsp").forward(req,resp);
    }


    /**
     * type_add.jsp-->type.let?type=add-->ok-->type_list.jsp
     *                                    no-->type_add.jsp
     */
    private void add(HttpServletRequest req, HttpServletResponse resp,PrintWriter out, ServletContext application) {
        //1、从表单中获取名字和父类型
        String typename=req.getParameter("typeName");
        long parentId=Long.parseLong(req.getParameter("parentType"));

        //2、调用biz的添加方法
        int count = typeBiz.add(typename,parentId);
        //3、更新application
        if(count>0){
//            得到最新的一个类型列表
            List<Type> types = typeBiz.getAll();
            application.setAttribute("types",types);
//            添加成功后跳转到查询列表中去
            out.println("<script>alert('添加成功');location.href='type_list.jsp';</script>");
        }else{
//            添加失败后重新返回到添加页面
            out.println("<script>alert('添加失败');location.href='type_add.jsp';</script>");
        }
        //4、提示结果

    }
}
