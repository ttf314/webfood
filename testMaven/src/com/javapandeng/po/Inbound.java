package com.javapandeng.po;

import java.io.Serializable;

public class Inbound implements Serializable {

    private Integer id;
    private Integer did;
    private Integer rmb;
    private String goods_id;
    private String goods_name;
    private Integer quantity;
    private Integer remaining;
    private String inbound_date;
    private String operator;
    private String detel;
    private Integer isDelete;

    public Integer getRmb() {
        return rmb;
    }

    public void setRmb(Integer rmb) {
        this.rmb = rmb;
    }

    public String getDetel() {
        return detel;
    }

    public void setDetel(String detel) {
        this.detel = detel;
    }

    public Inbound() {
        super();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return "Inbound{" +
                "id=" + id +
                ", goods_id=" + goods_id +
                ", goods_name='" + goods_name + '\'' +
                ", quantity=" + quantity +
                ", inbound_date='" + inbound_date + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getInbound_date() {
        return inbound_date;
    }

    public void setInbound_date(String inbound_date) {
        this.inbound_date = inbound_date;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
