package com.javapandeng.po;

import java.io.Serializable;

public class Item_warehouse implements Serializable {
    private Integer id;
    private String name;
    private Integer pid;
    private Integer isDelete;
    public Item_warehouse(Integer id,String name, Integer pid, Integer isDelete) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.isDelete = isDelete;
    }
    public Item_warehouse() {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.isDelete = isDelete;
    }
    @Override
    public String toString() {
        return "Item_warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", isDelete=" + isDelete +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
