package com.enjoy.book.dao;

import com.enjoy.book.bean.Book;
import com.enjoy.book.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDao {
    QueryRunner runner = new QueryRunner();

    /**
     * 根据类型查询对应的书籍信息
     * @param typeId
     * @return
     * @throws SQLException
     */
    public List<Book> getBookByTypeId(long typeId) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from book where typeId=?";
        List<Book> books = runner.query(conn,sql,new BeanListHandler<Book>(Book.class),typeId);
        DBHelper.close(conn);
        return books;
    }

    /**
     *  添加书籍
     * @return
     */
    public int add(long typeId,String name,double price,String desc,String pic,String publish,String author,long stock,String address) throws SQLException{
        Connection conn = DBHelper.getConnection();
        String sql = "insert into book(typeId,`name`,price,`desc`,pic,publish,author,stock,address) value(?,?,?,?,?,?,?,?,?)";
        int count = runner.update(conn,sql,typeId,name,price,desc,pic,publish,author,stock,address);
        DBHelper.close(conn);
        return count;
    }

    /**
     *  修改书籍
     * @return
     */
    public int modify(long id,long typeId,String name,double price,String desc,String pic,String publish,String author,long stock,String address) throws SQLException{
        Connection conn = DBHelper.getConnection();
        String sql = "update book set typeId=?,`name`=?,price=?,`desc`=?,pic=?,publish=?,author=?,stock=?,address=? where id=?";
        int count = runner.update(conn,sql,typeId,name,price,desc,pic,publish,author,stock,address,id);
        DBHelper.close(conn);
        return count;
    }

    /**
     * 删除书籍
     * @param id
     * @return
     */
    public int remove(long id) throws SQLException{
        Connection conn = DBHelper.getConnection();
        String sql = "delete from book where id=?";
        int count = runner.update(conn,sql,id);
        DBHelper.close(conn);
        return count;
    }

    /**
     * 分页查询
     * @param pageIndex 当前页码,从一开始
     * @param pageSize  当前每页可以显示多少条信息
     * @sort  采用默认排序方式
     * @return  当前页的信息
     */
//    根据分页来获取  一片的数据
    public List<Book> getByPage(int pageIndex,int pageSize) throws SQLException{
        Connection conn = DBHelper.getConnection();
        String sql = "select * from book limit ?,?";
        int offset = (pageIndex-1)*pageSize;
        List<Book> books = runner.query(conn,sql,new BeanListHandler<Book>(Book.class),offset,pageSize);
        DBHelper.close(conn);
        return books;
//        这时books的type属性仍旧为null
    }
//    根据id来获取  单个数据
    public Book getById(long id) throws SQLException{
        Connection conn = DBHelper.getConnection();
        String sql = "select * from book where id=?";
        Book book = runner.query(conn,sql,new BeanHandler<Book>(Book.class),id);
        DBHelper.close(conn);
        //返回的book对象中type属性为空
        return book;
    }
//    查询多少数据(所有行数)
    public int getCount() throws SQLException{
        Connection conn = DBHelper.getConnection();
        String sql = "select count(id) from book";
        Number data = runner.query(conn,sql,new ScalarHandler<>());//此时为long类型
//        类型转换:long-->int
        int count = data.intValue();
        DBHelper.close(conn);
        return count;
    }

    /**
     * 根据书籍名称查询书籍信息
     * @param bookName
     * @return
     * @throws SQLException
     */
    public Book getByName(String bookName) throws SQLException{
        Connection conn = DBHelper.getConnection();
        String sql = "select * from book where name=?";
        Book book = runner.query(conn,sql,new BeanHandler<Book>(Book.class),bookName);
        DBHelper.close(conn);
        return book;
    }


    /**
     * 修改书籍的数量根据书籍编号进行修改
     * @param id
     * @param amount +1加一本 -1借出一本
     * @return
     */
    public int modify(long id,int amount) throws SQLException{
        Connection conn = DBHelper.getConnection();
        String sql = "update book set stock=stock + ? where id = ?";
        int count = runner.update(conn,sql,amount,id);
        DBHelper.close(conn);
        return count;
    }

    public static void main(String[] args){

        try {
            BookDao bookDao = new BookDao();
            int count = bookDao.modify(1,1);
            System.out.print(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
