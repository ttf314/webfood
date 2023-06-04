package com.javapandeng.po;
import java.io.Serializable;

/**
 * 类目
 */
public class ItemCategory implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    private String pass;
    /**
     * 类目名称
     */
    private String name;

    /**
     * 父id
     */
    private Integer pid;

    /**
     * 是否已删除
     */
    private Integer isDelete;

    public ItemCategory(Integer id, String pass,String name, Integer pid, Integer isDelete) {
        this.id = id;
        this.pass = pass;
        this.name = name;
        this.pid = pid;
        this.isDelete = isDelete;
    }

    public ItemCategory() {
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    @Override
    public String toString() {
        return "ItemCategory{" +
                "id=" + id +
                ", pass='" + pass + '\'' +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", isDelete=" + isDelete +
                '}';
    }
}
