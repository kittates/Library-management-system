package com.enjoy.book.biz;

import com.enjoy.book.bean.Member;
import com.enjoy.book.bean.MemberType;
import com.enjoy.book.dao.MemberDao;
import com.enjoy.book.dao.MemberTypeDao;

import java.sql.SQLException;
import java.util.List;

public class MemberBiz {
    MemberDao memberDao = new MemberDao();
    MemberTypeDao typeDao = new MemberTypeDao();

    public int add(String name,String pwd,long typeId,double balance,String tel,String idNumber){
        int count =0;
        try {
            count = memberDao.add(name,pwd,typeId,balance,tel,idNumber);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public int modify(long id,String name,String pwd,long typeId,double balance,String tel,String idNumber){
        int count =0;
        try {
            count = memberDao.modify(id,name,pwd,typeId,balance,tel,idNumber);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public int remove(long id) throws Exception{
        //1、判断会员账号余额，大于0->不能删除，继续保存该会员用户
        Member member = memberDao.getById(id);
        if(member.getBalance()>0){
            throw new Exception("金额有剩余,删除失败");
        }
        //2、作为外键出现在record表中
        if(memberDao.exist(id)){
            throw new Exception("此会员有借书记录不能删除");
        }
        //3、可以删除
        int count=0;
        try {
            count = memberDao.remove(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public int modifyBalance(String idNumber,double amount){
        int count=0;
        try {
            count=memberDao.modifyBalance(idNumber,amount);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public List<Member> getAll(){
        List<Member> members=null;
        MemberTypeDao memberTypeDao = new MemberTypeDao();
        try {
            members = memberDao.getAll();
            //获取type值
            for(Member member: members){
                MemberType type = memberTypeDao.getById(member.getTypeId());
                member.setType(type);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return members;
    }
    public Member getById(long id){
        Member member = null;
        try {
            member= memberDao.getById(id);
            MemberType memberType = typeDao.getById(member.getTypeId());
            member.setType(memberType);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return member;
    }

    //根据用户身份证号查询用户信息
    public Member getByIdNumber(String idNumber) {
        Member member = null;
        try {
            member = memberDao.getByIdNumber(idNumber);
            MemberType memberType = typeDao.getById(member.getTypeId());
            member.setType(memberType);
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return member;
    }


}
