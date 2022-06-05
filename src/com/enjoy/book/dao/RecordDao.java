package com.enjoy.book.dao;

import com.enjoy.book.bean.Record;
import com.enjoy.book.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RecordDao {
    QueryRunner runner = new QueryRunner();

    public List<Record> getRecordByBookId(long bookId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from record where bookId=?";
        List<Record> records = runner.query(conn,sql,new BeanListHandler<Record>(Record.class),bookId);
        DBHelper.close(conn);
        return records;
    }

    /**
     * 根据身份证号查询用户借阅信息
     * 包含子查询，效率低
     * @param idNum
     * @return
     */
    public List<Record> getRecordsByIdNum(String idNum) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from record where memberId = (select id from member where idNumber = ?);";
        List<Record> records = runner.query(conn,sql,new BeanListHandler<Record>(Record.class),idNum);
        DBHelper.close(conn);
        return records;
    }

    /**
     * 根据会员ID查询用户的借阅信息
     * @param memberId
     * @return
     * @throws SQLException
     */
    public List<Record> getRecordsByMemberId(long memberId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        //backDate is null：表示借了但没有归还
        String sql = "select * from record where memberId=? and backDate is null";
        List<Record> records = runner.query(conn,sql,new BeanListHandler<Record>(Record.class),memberId);
        DBHelper.close(conn);
        return records;
    }


    /**
     * 添加借阅记录
     * @param memberId
     * @param bookId
     * @param deposit
     * @param userId
     * @return
     * @throws SQLException
     */
    public int add(long memberId,long bookId,double deposit,long userId) throws SQLException {
        Connection conn =DBHelper.getConnection();
        // null表示暂未归还，不为空表示已经归还
        String sql = "insert into record values(null,?,?,CURRENT_DATE,null,?,?,'978-7-302-12260-9')";
        int count=runner.update(conn,sql,memberId,bookId,deposit,userId);
        DBHelper.close(conn);
        return count;
    }

    /**
     *更新归还信息记录表
     * @param deposit 押金  过期归还->押金保持不变 准时归还->清零
     * @param userId    管理员编号
     * @param id    记录编号
     * @return
     * @throws SQLException
     */
    public int modify(double deposit,long userId,long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "update record set backDate = CURRENT_DATE,deposit = ?,userId =? where id=?";
        int count = runner.update(conn,sql,deposit,userId,id);
        DBHelper.close(conn);
        return count;
    }


    /**
     * 续借功能
     * @param id :record表中的id
     * @return
     * @throws SQLException
     */
    public int modify(long id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "update record set rentDate = CURRENT_DATE where id=?";
        int count = runner.update(conn,sql,id);
        DBHelper.close(conn);
        return count;
    }

    public Record getById(long recordId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from record where id=?";
        Record records = runner.query(conn,sql,new BeanHandler<Record>(Record.class),recordId);
        DBHelper.close(conn);
        return records;
    }


}
