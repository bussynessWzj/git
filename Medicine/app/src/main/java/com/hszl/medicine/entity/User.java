package com.hszl.medicine.entity;

import java.io.Serializable;

public class User implements Serializable {

    /**
     * success : 1
     * msg : 登录成功
     * id : ae05743e-a038-4356-8d9b-efdaf71ec78c
     * img : /Files/PhotoFile/ae05743e-a038-4356-8d9b-efdaf71ec78c.jpg
     * birthday : 1987-03-16
     * username : 超级管理员
     * mobilephone : 18988888888
     */

    private int success;
    private String msg;
    private String id;
    private String img;
    private String birthday;
    private String username;
    private String mobilephone;
    private String loginpwd;

    public String getLoginpwd() {
        return loginpwd;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
}
