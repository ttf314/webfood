package com.javapandeng.po;


import java.io.Serializable;

/**
 * 餐品
 */
public class Salaryandattendinfo implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 主键
     */
    private Integer itemid;

    /**
     * 主键
     */
    private Integer employeeid;
    private Integer rmb3;
    private String attendance_date;
    /**
     * 是否有效 0有效 1已删除
     */
    private Integer isDelete;

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

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public Integer getRmb3() {
        return rmb3;
    }

    public void setRmb3(Integer rmb3) {
        this.rmb3 = rmb3;
    }

    public String getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(String attendance_date) {
        this.attendance_date = attendance_date;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}

