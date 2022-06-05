package com.enjoy.book.biz;

import com.enjoy.book.bean.Book;
import com.enjoy.book.bean.Record;
import com.enjoy.book.bean.Type;
import com.enjoy.book.dao.BookDao;
import com.enjoy.book.dao.RecordDao;
import com.enjoy.book.dao.TypeDao;

import java.sql.SQLException;
import java.util.List;

public class BookBiz {
    BookDao bookDao = new BookDao();

    public List<Book> getBookByTypeId(long typeId) {
        try {
            return bookDao.getBookByTypeId(typeId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }
//    通过零散的方式去add
    public int add(long typeId,String name,double price,String desc,String pic,String publish,String author,long stock,String address) {
        int count = 0;
        try {
            count = bookDao.add(typeId,name,price,desc,pic,publish,author,stock,address);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
//    通过调用对象的方式去add 方法重载
    public int add(Book book){
        return add(book.getTypeId(),book.getName(),book.getPrice(),book.getDesc(),book.getPic(),book.getPublish(),book.getAuthor(),book.getStock(),book.getAddress());
    }

    public int modify(long id,long typeId,String name,double price,String desc,String pic,String publish,String author,long stock,String address) {
        int count = 0;
        try {
            count = bookDao.modify(id,typeId,name,price,desc,pic,publish,author,stock,address);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public int modify(Book book){
        int count=0;
        try {
            count = bookDao.modify(book.getId(), book.getTypeId(), book.getName(), book.getPrice(), book.getDesc(), book.getPic(), book.getPublish(), book.getAuthor(), book.getStock(), book.getAddress());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public int remove(long id) throws Exception {
        //1、判断id是否存在外键
        RecordDao recordDao = new RecordDao();


        int count = 0;
        try {
            List<Record> records = recordDao.getRecordByBookId(id);
            if(records.size()>0){
                throw new Exception("删除的书籍有子信息,不能删除!");
            }
            //2、删除
            count=bookDao.remove(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public List<Book> getByPage(int pageIndex,int pageSize) {
        TypeDao typeDao = new TypeDao();
        List<Book> books = null;
        try {
            books = bookDao.getByPage(pageIndex,pageSize);
//            处理type对象的外键设置的数据问题
            for(Book book :books){
//                获取typeId
                long typeId = book.getTypeId();
//                根据typeId找到对应的type对象
                    Type type = typeDao.getById(typeId);
//                设置给book.setType()
                book.setType(type);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    public Book getById(long id) {
        Book book = null;
        TypeDao typeDao = new TypeDao();
        try {
            //返回的对象中type属性为空
            book = bookDao.getById(id);
            long typeId = book.getTypeId();
            Type type = typeDao.getById(typeId);
            book.setType(type);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return book;
    }

    /**
     * 由行数算页数
     * @return 数据行
     */
    public int getPageCount(int pageSize) {
        int pageCount=0;
        try {
//            获取行数
            int rowCount = bookDao.getCount();
//            根据行数获取页数
            pageCount=(rowCount+1)/pageSize;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pageCount;
    }

    public Book getByName(String bookName){
        Book book = null;
        try {
            book = bookDao.getByName(bookName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return book;
    }
}
