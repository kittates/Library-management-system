package com.enjoy.book.biz;
import com.enjoy.book.bean.MemberType;
import com.enjoy.book.dao.MemberTypeDao;

import java.sql.SQLException;
import java.util.List;

public class MemberTypeBiz {
    MemberTypeDao dao = new MemberTypeDao();

    public List<MemberType> getAll(){
        try {
            return dao.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public MemberType getById(long id){
        try {
            return dao.getById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
