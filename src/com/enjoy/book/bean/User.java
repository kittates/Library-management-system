package com.enjoy.book.bean;


import java.io.Serializable;

/**
 * 保存用户的信息
 * 1.Serializable：序列化接口，可以使用io完成对象的读写
 * 2.私有的属性
 * 3.getter/setter
 * 4.默认的构造
 */
public class User implements Serializable {
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", state=" + state +
                '}';
    }

    //与mysql中的表的属性一一对应
    private long id;
    private String name;
    private String pwd;
    private long state;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

}
