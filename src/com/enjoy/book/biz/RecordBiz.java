package com.enjoy.book.biz;

import com.enjoy.book.bean.Book;
import com.enjoy.book.bean.Member;
import com.enjoy.book.bean.Record;
import com.enjoy.book.dao.BookDao;
import com.enjoy.book.dao.MemberDao;
import com.enjoy.book.dao.RecordDao;
import com.enjoy.book.util.DBHelper;
import com.enjoy.book.util.DateHelper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class RecordBiz {

    RecordDao recordDao = new RecordDao();
    BookDao bookDao = new BookDao();
    MemberDao memberDao = new MemberDao();
    MemberBiz memberBiz = new MemberBiz();

    /**
     * 根据用户身份证号查询用户信息
     * 因为包含子查询，所以效率会低一些
     * @param idNum
     * @return
     */
    public List<Record> getRecordsByIdNum(String idNum){
        List<Record> records = null;
        try {
            records = recordDao.getRecordsByIdNum(idNum);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return records;
    }

    /**
     * 根据用户会员编号查询用户信息
     * @param memberId
     * @return
     */
    public List<Record> getRecordsByMemberId(long memberId){
        List<Record> records = null;
        try {
            records = recordDao.getRecordsByMemberId(memberId);
            //归还涉及到record(BackDate) Book(stock) Member(balance),需要将Book Member与record关联,于是设置外键信息
            //1、外键信息：Member Book
            //Member member = memberDao.getById(memberId);  member对象不包括外键信息
            Member member = memberBiz.getById(memberId);
            for(Record record: records){
                long bookId = record.getBookId();
                Book book = bookDao.getById(bookId);
                record.setBook(book);
                record.setMember(member);
                //2、应还时间 借阅时间+keepDay
                long day = member.getType().getKeepDay();
                //时间计算
                java.sql.Date rentDate = record.getRentDate();
                java.sql.Date backDate = DateHelper.getNewDate(rentDate,day);
                record.setBackDate(backDate);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return records;
    }

    /**
     * 借阅需要实现的功能:
     * 1、借一本数：record表添加一行信息-->(recordDao,insert)
     * 2、这本书的数量-1 -->(bookDao,update)
     * 3、会员信息表中,更改交付押金后的余额(memberDao,update)
     * 全部成功 || 全部失败
     *前提：要保证在一个connection对象中 放在同一个事务中进行
     * @param memberId
     * @param bookIdList
     * @param userId
     * @return 0:失败 1：成功
     */
    public int add(long memberId,List<Long> bookIdList,long userId){

        //1、启动事务
        try {
            DBHelper.beginTransaction();
            //记录用户借阅的书籍所需的押金
            double total =0;
            //2、拿到借阅的书籍的编号
            for(long bookId:bookIdList){
                //书籍编号-->书籍对象-->调用价格-->算押金(deposit)-->(recordDao.insert、bookDao.update、memberDao.update)
//                书籍对象
                Book book = bookDao.getById(bookId);
//                调用价格
                double price = book.getPrice();
//                算押金(deposit)折扣为0.3
                double regPrice = price*0.3f;
                total+=regPrice;
//              recordDao.add、bookDao.modify、memberDao.modifyBalance
                recordDao.add(memberId,bookId,regPrice,userId);
                bookDao.modify(bookId,-1);

            }
            //更新余额
            memberDao.modifyBalance(memberId,0-total);

            DBHelper.commitTransaction();//事务提交:成功

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                DBHelper.rollbackTransaction();//事务的回滚:有异常就回滚

            } catch (SQLException e) {
                e.printStackTrace();

            }
            return 0;
        }
        //2、结束事务
        return 1;

    }


    /**
     * 归还功能
     * 开启事务
     * 1、record表：押金(deposit) 归还日期 操作员的编号
     * 2、book表：数的数量
     * 3、member表：书籍的费用退还给balance
     *成功：提交
     * 失败：回滚
     * @param memberId2
     * @param recordIds
     * @param userId
     * @return
     */
    public int modify(long memberId2, List<Long> recordIds, long userId) {
        //1、开始事务
        try {
            DBHelper.beginTransaction();
            Member member = memberBiz.getById(memberId2);
            double total=0;
            for(long recordId:recordIds){
                //通过recordId 获取记录对象：书
                Record record = recordDao.getById(recordId);

                //更改record押金:超出一天扣1元
                java.sql.Date backDate = DateHelper.getNewDate(record.getRentDate(),member.getType().getKeepDay());
                //系统当前时间
                java.util.Date currentDate = new java.util.Date();
                int day = 0 ;
                if(currentDate.after(backDate)){
                    //归还时间已经到期，计算超出押金(一天多扣除一元)
                    day = DateHelper.getSpan(currentDate,backDate);

                }
                //累加押金
                total+=record.getDeposit()- day;

                recordDao.modify(day,userId,recordId);
                //修改书籍 +1
                bookDao.modify(record.getBookId(),1);
            }
            //修改余额
            memberDao.modifyBalance(memberId2,total);


            DBHelper.commitTransaction();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                DBHelper.rollbackTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }
        return 1;
    }

    public int modify(long id){
        int count = 0;
        try {
            count = recordDao.modify(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }


}
