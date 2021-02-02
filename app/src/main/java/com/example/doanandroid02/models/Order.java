package com.example.doanandroid02.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order { @SerializedName("id")
@Expose
public int id;
    @SerializedName("customer_id")
    @Expose
    public int customer_id;
    @SerializedName("order_date")
    @Expose
    public String order_date;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("total")
    @Expose
    public Long total;
    @SerializedName("note")
    @Expose
    public String note;
    @SerializedName("user_id")
    @Expose
    public int user_id;

    public Order(int id, int customer_id, String order_date, String address, Long total, String note, int user_id) {
        this.id = id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.address = address;
        this.total = total;
        this.note = note;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", order_date='" + order_date + '\'' +
                ", address='" + address + '\'' +
                ", total=" + total +
                ", note='" + note + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}