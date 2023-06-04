package com.javapandeng.po;


import java.io.Serializable;

/**
 * 餐品
 */
public class Employeeinfo implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 主键
     */
    private Integer itemid;

    /**
     * 名
     */
    private String employeename;
    /**
     * 脸图
     */
    private String faceurl;

    /**
     * 图
     */
    private String healthcertificateurl;

    /**
     * 手机
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    private Integer rmb;
    private Integer rmb2;
    private Integer rmb3;
    private String data1;
    private String data2;
    private String detel;
    /**
     * 是否有效 0有效 1已删除
     */
    private Integer isDelete;

    public Integer getRmb3() {
        return rmb3;
    }

    public void setRmb3(Integer rmb3) {
        this.rmb3 = rmb3;
    }

    public Integer getRmb2() {
        return rmb2;
    }

    public void setRmb2(Integer rmb2) {
        this.rmb2 = rmb2;
    }

    @Override
    public String toString() {
        return "Employeeinfo{" +
                "id=" + id +
                ", itemid=" + itemid +
                ", employeename='" + employeename + '\'' +
                ", faceurl='" + faceurl + '\'' +
                ", healthcertificateurl='" + healthcertificateurl + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", rmb=" + rmb +
                ", data1='" + data1 + '\'' +
                ", data2='" + data2 + '\'' +
                ", detel='" + detel + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }

    public String getDetel() {
        return detel;
    }

    public void setDetel(String detel) {
        this.detel = detel;
    }

    public Integer getRmb() {
        return rmb;
    }

    public void setRmb(Integer rmb) {
        this.rmb = rmb;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getFaceurl() {
        return faceurl;
    }

    public void setFaceurl(String faceurl) {
        this.faceurl = faceurl;
    }

    public String getHealthcertificateurl() {
        return healthcertificateurl;
    }

    public void setHealthcertificateurl(String healthcertificateurl) {
        this.healthcertificateurl = healthcertificateurl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}

