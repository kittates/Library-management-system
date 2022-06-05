package com.enjoy.book.dao;

import com.enjoy.book.bean.Member;
import com.enjoy.book.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.management.Query;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class MemberDao {
    QueryRunner runner = new QueryRunner();

    /**
     * 添加会员
     * @param name
     * @param pwd
     * @param typeId
     * @param balance
     * @param tel
     * @param idNumber
     * @return
     * @throws SQLException
     */
    public int add(String name,String pwd,long typeId,double balance,String tel,String idNumber) throws SQLException {
        Connection conn = DBHelper.getConnection();
        // TODO: 2022/5/3 这里怎么会出现这种低级问题呢，我怎么这么傻，怎么把conn,sql参数忘了插入到update中呢！！！ md，浪费了这么多时间
        String sql = "insert into member(`name`,pwd,typeId,balance,regdate,tel,idNumber) values(?,?,?,?,CURRENT_DATE,?,?)";
        int count = runner.update(conn,sql,name,pwd,typeId,balance,tel,idNumber);
        DBHelper.close(conn);
        return count;
    }

    /**
     * 修改会员信息
     * @param name
     * @param pwd
     * @param typeId
     * @param balance
     * @param tel
     * @param idNumber
     * @return
     * @throws SQLException
     */

    public int modify(long id,String name,String pwd,long typeId,double balance,String tel,String idNumber) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "update member set `name`=?, pwd=?,typeId=?,balance= ?,tel=?,idNumber= ? where id=?";
        int count = runner.update(conn,sql,name,pwd,typeId,balance,tel,idNumber,id);
        DBHelper.close(conn);
        return count;
    }

    /**
     * 删除会员信息
     * @param id
     * @return
     * @throws SQLException
     */
    public int remove(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "delete from member where id=?";
        int count = runner.update(conn,sql,id);
        DBHelper.close(conn);
        return count;
    }

    /**
     * 用户充值
     * @param idNumber
     * @param amount
     * @return
     * @throws SQLException
     */
    public int modifyBalance(String idNumber,double amount) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "update member set balance=balance+? where idNumber=?";
        int count = runner.update(conn,sql,amount,idNumber);
        DBHelper.close(conn);
        return count;
    }


    /**
     * 修改押金 根据用户编号memberId
     * @param id
     * @param amount 归还+ 借出-
     * @return
     * @throws SQLException
     */
    public int modifyBalance(long id,double amount) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "update member set balance=balance+? where id=?";
        int count = runner.update(conn,sql,amount,id);
        DBHelper.close(conn);
        return count;
    }
    /**
     * 查询所有会员信息
     * @return
     * @throws SQLException
     */
    public List<Member> getAll() throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select id,`name`,pwd,typeId,balance,regdate,tel,idNumber from member";
        List<Member> members = runner.query(conn,sql,new BeanListHandler<Member>(Member.class));
        DBHelper.close(conn);
        return members;
    }

    /**
     * 根据id查询单个会员信息
     * @param id
     * @return
     * @throws SQLException
     */
    public Member getById(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select id,`name`,pwd,typeId,balance,regdate,tel,idNumber from member where id=?";
        Member member = runner.query(conn,sql,new BeanHandler<Member>(Member.class),id);
        DBHelper.close(conn);
        return member;
    }

    /**
     * 根据会员身份证号查询会员信息
     * @param idNumber
     * @return
     * @throws SQLException
     */
    public Member getByIdNumber(String idNumber) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select id,`name`,pwd,typeId,balance,regdate,tel,idNumber from member where idNumber=?";
        Member member = runner.query(conn,sql,new BeanHandler<Member>(Member.class),idNumber);
        DBHelper.close(conn);
        return member;
    }
    /**
     * 判断会员编号是否存在record中,即id是否作为外键出现在record表中
     * @param id
     * @return
     */
    public Boolean exist(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select count(*) from record where memberId=?";
        Number number = runner.query(conn,sql,new ScalarHandler<>(),id);//返回的是一个数字
        DBHelper.close(conn);
       return number.intValue()>0?true:false;
    }
}
