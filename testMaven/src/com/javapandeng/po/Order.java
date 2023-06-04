package com.javapandeng.po;

import java.io.Serializable;

public class Order {


    private int id;
    private int uid;
    private int uids;
    private String date;
    private int count;
    private double nowrmb;
    private double price;
    private String detel;
    private String detel2;
    private String weizhi;
    private int isDelete;
    private String no;
    public int getUids() {
        return uids;
    }

    public String getDetel2() {
        return detel2;
    }

    public void setDetel2(String detel2) {
        this.detel2 = detel2;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setUids(int uids) {
        this.uids = uids;
    }

    public void setNowrmb(double nowrmb) {
        this.nowrmb = nowrmb;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getNowrmb() {
        return nowrmb;
    }

    public void setNowrmb(float nowrmb) {
        this.nowrmb = nowrmb;
    }

    public String getWeizhi() {
        return weizhi;
    }

    public void setWeizhi(String weizhi) {
        this.weizhi = weizhi;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDetel() {
        return detel;
    }

    public void setDetel(String detel) {
        this.detel = detel;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }




}