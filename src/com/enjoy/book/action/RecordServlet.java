package com.enjoy.book.action;

import com.alibaba.fastjson.JSON;
import com.enjoy.book.bean.Member;
import com.enjoy.book.bean.Record;
import com.enjoy.book.bean.User;
import com.enjoy.book.biz.MemberBiz;
import com.enjoy.book.biz.RecordBiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/record.let")
public class RecordServlet extends HttpServlet {
    RecordBiz recordBiz = new RecordBiz();
    MemberBiz memberBiz = new MemberBiz();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * record.let?type=add&mid=1&ids=1_3_4  图书借阅
     * record.let?type=queryback&idn=xx   根据会员的身份证号查询会员的信息及借阅信息
     * record.let?type=back&mid=xx&ids=1_3_4 图书归还的功能
     * record.let?type=keep&id=xx    书籍续借
     * record.let?type=doajax&typeId=xx ajax查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        RecordBiz recordBiz = new RecordBiz();
        PrintWriter out = resp.getWriter();


        String type = req.getParameter("type");
        switch(type){
            case "add":
                //1、借阅用户编号
                long memberId = Long.parseLong(req.getParameter("mid"));
                //2、借阅的书籍编号
                String ids = req.getParameter("ids");
                String []strs = ids.split("_");
                List<Long> bookIds = new ArrayList<Long>();
                for(String s: strs){
                    long id = Long.parseLong(s);
                    bookIds.add(id);
                }
                //3、负责处理借阅的管理员编号
                long userId = user.getId();
                //4、调用biz层
                int count = recordBiz.add(memberId,bookIds,userId);
                if(count>0){
                    out.println("<script>alert('图书借阅成功');location.href='main.jsp';</script>");
                }
                else{
                    out.println("<script>alert('图书借阅失败');location.href='main.jsp';</script>");
                }
                break;
            //展示借阅记录
            case "queryback":
                //1、获取会员的身份证号(从return_list.jsp中获取idn-->idNumber)
                String idn = req.getParameter("idn");
                //2、获取会员对象和所有的未归还的记录
                Member member = memberBiz.getByIdNumber(idn);

                List<Record> records = recordBiz.getRecordsByMemberId(member.getId());
                //3、存在req中
                req.setAttribute("member",member);
                req.setAttribute("records",records);
                //4、转发
                req.getRequestDispatcher("return_list.jsp").forward(req,resp);
                break;
            //归还图书
            case "back":
                //1、拿到会员编号
                long memberId2 = Long.parseLong(req.getParameter("mid"));
                //2、借书记录
                String idStr = req.getParameter("ids");
                String []idStrs = idStr.split("_");
                List<Long> recordIds = new ArrayList<Long>();
                for(String s:idStrs){
                    recordIds.add(Long.parseLong(s));
                }
                //3、操作的用户编号
                long userId2 = user.getId();
                //4、调用biz
                int count2 = recordBiz.modify(memberId2,recordIds,userId2);
                //5、归还
                if(count2>0){
                    out.println("<script>alert('归还成功');location.href='main.jsp';</script>");
                }
                else{
                    out.println("<script>alert('归还失败');location.href='main.jsp';</script>");
                }
                break;
            case "keep":
                //将借阅时间改为当前时间
                long recordId = Long.parseLong(req.getParameter("id"));
                int count3 = recordBiz.modify(recordId);
                if(count3>0){
                    out.println("<script>alert('续借成功');location.href='main.jsp';</script>");
                }
                else{
                    out.println("<script>alert('续借失败');location.href='main.jsp';</script>");
                }

                break;

            default:
                resp.sendError(404,"请求的地址不存在");
        }
    }
}
