package com.enjoy.book.bean;

import java.io.Serializable;

/**
 * 书的实体类:注意外键
 * 1.DBUtil无法生成表以外的数据
 * 2.外键的实体对象没有数据，需要后期手动添加biz(业务去实现)
 */
public class Book implements Serializable {

    private long id;
    //外键号
    private long typeId;
    private String name;
    private double price;
    private String desc;
    private String pic;
    private String publish;
    private String author;
    private long stock;
    private String address;

    //外键对应的实体对象
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", desc='" + desc + '\'' +
                ", pic='" + pic + '\'' +
                ", publish='" + publish + '\'' +
                ", author='" + author + '\'' +
                ", stock=" + stock +
                ", address='" + address + '\'' +
                ", type=" + type +
                '}';
    }
}
