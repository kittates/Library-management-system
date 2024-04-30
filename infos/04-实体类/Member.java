package com.enjoy.book.bean;


import java.io.Serializable;

public class Member implements Serializable {

    private long id;
    private String name;
    private String pwd;
    private long typeId;
    private double balance;
    private java.sql.Date regdate;
    private String tel;
    private String idNumber;

    //外键
    private MemberType type;


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

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public java.sql.Date getRegdate() {
        return regdate;
    }

    public void setRegdate(java.sql.Date regdate) {
        this.regdate = regdate;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", type=" + type +
                ", balance='" + balance + '\'' +
                ", regdate=" + regdate +
                ", tel='" + tel + '\'' +
                ", idNumber='" + idNumber + '\'' +
                '}';
    }
}
