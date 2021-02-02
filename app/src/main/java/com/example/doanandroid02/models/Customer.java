package com.example.doanandroid02.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("id")
@Expose
public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("sdt")
    @Expose
    public String sdt;
    @SerializedName("dia_chi")
    @Expose
    public String dia_chi;
    @SerializedName("mail")
    @Expose
    public String mail;

    public Customer(int id,String name, String sdt, String dia_chi, String mail) {
        this.id = id;
        this.name = name;
        this.sdt = sdt;
        this.dia_chi = dia_chi;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}