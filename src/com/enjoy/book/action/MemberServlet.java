package com.enjoy.book.action;

import com.alibaba.fastjson.JSON;
import com.enjoy.book.bean.Member;
import com.enjoy.book.bean.MemberType;
import com.enjoy.book.bean.Record;
import com.enjoy.book.biz.MemberBiz;
import com.enjoy.book.biz.MemberTypeBiz;
import com.enjoy.book.biz.RecordBiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/member.let")
public class MemberServlet extends HttpServlet {
    MemberBiz memberBiz = new MemberBiz();
    MemberTypeBiz memberTypeBiz = new MemberTypeBiz();
    RecordBiz recordBiz = new RecordBiz();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * /member.let?type=addpre 添加准备(MemberTypes)
     * /member.let?type=add
     * /member.let?type=modifypre&id=xx 修改准备(MemberTypes ,Member)
     * /member.let?type=modify 修改
     * /member.let?type=remove&id=xx 删除
     * /member.let?type=query
     * /member.let?type=modifyrecharge 充值
     * /member.let?type=doajax&idn=xx 通过ajax请求会员信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String type =req.getParameter("type");
        switch(type){
            case "addpre":
                //在添加会员时需要为其设置会员类型,这时就需要在这层获取数据库中的所有会员类型，存到-->req中，
                // 由left.jsp-->该业务层-->mem_add.jsp中
                List<MemberType> memberTypes = memberTypeBiz.getAll();

                req.setAttribute("memberTypes",memberTypes);
                req.getRequestDispatcher("mem_add.jsp").forward(req,resp);

                break;
            case "add":
                String name = req.getParameter("name");
                String pwd = req.getParameter("pwd");
                long memberTypeId = Long.parseLong(req.getParameter("memberType"));
                double balance = Double.parseDouble(req.getParameter("balance"));
                String tel = req.getParameter("tel");
                String idNumber = req.getParameter("idNumber");
                int count = memberBiz.add(name,pwd,memberTypeId,balance,tel,idNumber);

                if(count>0){
                    out.println("<script>alert('开卡成功');location.href='member.let?type=query';</script>");
                }
                else{
                    out.println("<script>alert('开卡失败');location.href='member.let?type=query';</script>");
                }
                break;
            case "modifypre":
                //获取id
                long id = Long.parseLong(req.getParameter("id"));
                Member member = memberBiz.getById(id);
                //获取类型数据
                List<MemberType> memberTypes2 = memberTypeBiz.getAll();
                req.setAttribute("member",member);
                req.setAttribute("memberTypes",memberTypes2);
                req.getRequestDispatcher("mem_modify.jsp").forward(req,resp);
                break;
            case "modify":
                long memberId = Long.parseLong(req.getParameter("id"));
                String name2 = req.getParameter("name");
                String pwd2 = req.getParameter("pwd");
                long memberTypeId2 = Long.parseLong(req.getParameter("memberType"));
                double balance2 = Double.parseDouble(req.getParameter("balance"));
                String tel2 = req.getParameter("tel");
                String idNumber2 = req.getParameter("idNumber");
                int count3 = memberBiz.modify(memberId,name2,pwd2,memberTypeId2,balance2,tel2,idNumber2);

                if(count3>0){
                    out.println("<script>alert('修改成功');location.href='member.let?type=query';</script>");
                }
                else{
                    out.println("<script>alert('修改失败');location.href='member.let?type=query';</script>");
                }
                break;
            case "remove":
                long memId = Long.parseLong(req.getParameter("id"));
                try{
                    int countMember = memberBiz.remove(memId);
                    if(countMember>0){
                        out.println("<script>alert('删除成功');location.href='member.let?type=query';</script>");
                    }
                    else{
                        out.println("<script>alert('删除失败');location.href='member.let?type=query';</script>");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    out.println("<script>alert('"+e.getMessage()+"');location.href='member.let?type=query';</script>");
                }

                break;
            case "query":
                List<Member> memberList = memberBiz.getAll();
                //将memberList存到req中,前台需要看的时候就从中获取
                req.setAttribute("memberList",memberList);
                req.getRequestDispatcher("mem_list.jsp").forward(req,resp);
                break;
            case "modifyrecharge":
                //身份证号作为唯一充值标识
                //获取身份证号和金额
                String idNumber3 = req.getParameter("idNumber");
                double amount = Double.parseDouble(req.getParameter("amount"));
                int count4 = memberBiz.modifyBalance(idNumber3,amount);
                if(count4>0){
                    out.println("<script>alert('充值成功');location.href='member.let?type=query';</script>");
                }
                else{
                    out.println("<script>alert('充值失败');location.href='member.let?type=query';</script>");
                }
                break;
            case "doajax":
                //1、获取身份证号
                String idNum = req.getParameter("idn");

                //2、获取member对象
                Member member1 = memberBiz.getByIdNumber(idNum);
                //2.1 修改member的可借数量 根据memberId来查看所有的该用户的借阅记录数量
                List<Record> records = recordBiz.getRecordsByMemberId(member1.getId());
                if(records.size()>0){
                    long size = member1.getType().getAmount()-records.size();
                    member1.getType().setAmount(size);
                }

                //3、member1-->json字符串,需要导入jar包:可将进行对象与json的互相转换
                String memberJson = JSON.toJSONString(member1);
                //4、相应客户端，out不能用println-->会自动换行
                out.print(memberJson);


                break;

            default:
                resp.sendError(404,"请求的地址不存在");
        }
    }
}
