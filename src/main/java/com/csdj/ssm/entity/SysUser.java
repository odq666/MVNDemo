package com.csdj.ssm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SysUser {
    private Integer uid;
    private String uname;
    private String upwd;
    private String uimg;
    private int urole;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ucreateTime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg;
    }

    public int getUrole() {
        return urole;
    }

    public void setUrole(int urole) {
        this.urole = urole;
    }

    public Date getUcreateTime() {
        return ucreateTime;
    }

    public void setUcreateTime(Date ucreateTime) {
        this.ucreateTime = ucreateTime;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", uimg='" + uimg + '\'' +
                ", urole=" + urole +
                ", ucreateTime=" + ucreateTime +
                '}';
    }
}
