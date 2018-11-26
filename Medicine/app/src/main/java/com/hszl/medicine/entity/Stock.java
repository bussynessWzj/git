package com.hszl.medicine.entity;

import java.io.Serializable;
import java.util.List;

public class Stock implements Serializable {

    /**
     * count : 7
     * list : [{"id":"fcf62118-b9e2-401e-8d54-2ba739b5df08","name":"测试仓库","code":"101","status":0,"address":"我是真的爱你了！不能容忍的是一","contact":"侧咯的","tel":"13720113296","createDate":"2018-11-15T10:04:15.41","createUserID":"00000000-0000-0000-0000-000000000000","createUserName":null,"updateDate":null,"updateUserName":null,"updateUserID":null,"isDel":0},{"id":"d080dc48-2954-4e24-97b6-00fb9614640d","name":"qee","code":"qee","status":0,"address":"qwr","contact":"wet","tel":"qer","createDate":"2018-11-13T17:27:54.93","createUserID":"00000000-0000-0000-0000-000000000000","createUserName":null,"updateDate":null,"updateUserName":null,"updateUserID":null,"isDel":0},{"id":"2562cd16-f0d3-42b4-9e17-039b1e02e41d","name":"yhl","code":"61212","status":1,"address":"qwe","contact":"qwe","tel":"13720113277","createDate":"2018-11-13T16:44:43.47","createUserID":"00000000-0000-0000-0000-000000000000","createUserName":null,"updateDate":"2018-11-13T17:27:23.85","updateUserName":null,"updateUserID":null,"isDel":0},{"id":"ac6e3048-225d-45bb-8cd3-0708f57d9e97","name":"货柜3f","code":"1005yhl","status":1,"address":"12","contact":"1222","tel":"1211","createDate":"2018-05-17T09:54:00","createUserID":"99a516df-0eed-4085-b56e-f5b2f6d8679d","createUserName":null,"updateDate":"2018-11-13T17:27:36.593","updateUserName":null,"updateUserID":null,"isDel":0},{"id":"2d651083-a6a3-4d51-aebb-2e9d0dc5f909","name":"冷藏库","code":"1002","status":1,"address":"修改","contact":"修改","tel":"修改","createDate":"2018-05-17T09:54:00","createUserID":"99a516df-0eed-4085-b56e-f5b2f6d8679d","createUserName":null,"updateDate":"2018-11-13T17:24:18.653","updateUserName":null,"updateUserID":null,"isDel":0},{"id":"ecb01c7f-2471-4b0a-b23e-b4fc1eaa1730","name":"货柜2","code":"1004","status":0,"address":"ab","contact":"ddd","tel":"123","createDate":"2018-05-17T09:54:00","createUserID":"99a516df-0eed-4085-b56e-f5b2f6d8679d","createUserName":null,"updateDate":"2018-11-13T16:52:44.55","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"595b3ced-c217-4e57-878a-cf221e431ebf","name":"常温库","code":"1001","status":0,"address":"&nbsp;","contact":"&nbsp;","tel":"&nbsp;","createDate":"2018-05-17T09:54:00","createUserID":"99a516df-0eed-4085-b56e-f5b2f6d8679d","createUserName":null,"updateDate":"2018-05-18T14:20:05.56","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0}]
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * id : fcf62118-b9e2-401e-8d54-2ba739b5df08
         * name : 测试仓库
         * code : 101
         * status : 0
         * address : 我是真的爱你了！不能容忍的是一
         * contact : 侧咯的
         * tel : 13720113296
         * createDate : 2018-11-15T10:04:15.41
         * createUserID : 00000000-0000-0000-0000-000000000000
         * createUserName : null
         * updateDate : null
         * updateUserName : null
         * updateUserID : null
         * isDel : 0
         */

        private String id;
        private String name;
        private String code;
        private int status;
        private String address;
        private String contact;
        private String tel;
        private String createDate;
        private String createUserID;
        private Object createUserName;
        private Object updateDate;
        private Object updateUserName;
        private Object updateUserID;
        private int isDel;
        private boolean check;

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCreateUserID() {
            return createUserID;
        }

        public void setCreateUserID(String createUserID) {
            this.createUserID = createUserID;
        }

        public Object getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(Object createUserName) {
            this.createUserName = createUserName;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public Object getUpdateUserName() {
            return updateUserName;
        }

        public void setUpdateUserName(Object updateUserName) {
            this.updateUserName = updateUserName;
        }

        public Object getUpdateUserID() {
            return updateUserID;
        }

        public void setUpdateUserID(Object updateUserID) {
            this.updateUserID = updateUserID;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }
    }
}
