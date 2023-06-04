package com.javapandeng.po;

public class Evaluates {
    private int id;    // id
    private int uid;    // 用户uid
    private int did;    // 订单did
    private String vdedtel;    // 用户评价vdedtel
    private String times;
    private String mealname;
    private int isDelete;    //
    private String no;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getMealname() {
        return mealname;
    }

    public void setMealname(String mealname) {
        this.mealname = mealname;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getVdedtel() {
        return vdedtel;
    }

    public void setVdedtel(String vdedtel) {
        this.vdedtel = vdedtel;
    }
}
