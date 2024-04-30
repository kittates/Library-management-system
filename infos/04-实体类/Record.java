package com.enjoy.book.bean;


import java.io.Serializable;

public class Record implements Serializable {

    private long id;
    private long memberId;
    private long bookId;
    private java.sql.Date rentDate;
    private java.sql.Date backDate;
    private double deposit;
    private long userId;
    private String isbn;

    //外键
    private Member member;
    private Book book;
    private User user;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }


    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }


    public java.sql.Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(java.sql.Date rentDate) {
        this.rentDate = rentDate;
    }


    public java.sql.Date getBackDate() {
        return backDate;
    }

    public void setBackDate(java.sql.Date backDate) {
        this.backDate = backDate;
    }


    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", bookId=" + bookId +
                ", rentDate=" + rentDate +
                ", backDate=" + backDate +
                ", deposit=" + deposit +
                ", userId=" + userId +
                ", isbn='" + isbn + '\'' +
                ", member=" + member +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}
