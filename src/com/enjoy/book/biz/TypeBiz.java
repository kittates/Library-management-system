package com.enjoy.book.biz;

import com.enjoy.book.bean.*;
import com.enjoy.book.bean.*;
import com.enjoy.book.dao.BookDao;
import com.enjoy.book.dao.TypeDao;

import java.sql.SQLException;
import java.util.List;

public class TypeBiz {
    TypeDao typeDao = new TypeDao();
    public List<Type> getAll(){
        try {
            return typeDao.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public int add(String name,long parentId){
        int count=0;
        try{
            count = typeDao.add(name,parentId);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return count;
    }

    public int modify(long id,String name,long parentId){
        int count=0;
        try{
            count = typeDao.modify(id,name,parentId);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return count;
    }

    /**
     * 删除：
     * 表与表之间的关系：逻辑关系
     * type是book的一个外键
     * @param id
     * @return 0:删除失败 >0:成功成功 Exception：提示用户的信息
     */

    public int remove(long id) throws Exception {
        //如果有子项，是不能删除的
        BookDao bookDao = new BookDao();
        int count = 0;
        try{
            List<Book> books = bookDao.getBookByTypeId(id);
            if(books.size()>0){
                //不能删除，业务问题往外抛出
                throw new Exception("删除的类型有子信息，删除失败");
            }
            count = typeDao.remove(id);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return count;
    }

    public Type getById(long id){
        try {
            return typeDao.getById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
