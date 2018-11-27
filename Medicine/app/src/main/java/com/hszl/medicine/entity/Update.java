package com.hszl.medicine.entity;

import java.io.Serializable;

public class Update implements Serializable {
    private int success;
    private String msg;

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
}
