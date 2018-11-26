package com.hszl.medicine.entity;

import java.io.Serializable;
import java.util.List;

public class SupplierType implements Serializable {

    /**
     * count : 7
     * list : [{"id":"3d144e69-8e54-4536-b91e-87bc2497be2c","name":"测试","code":"101","status":1,"createDate":"2018-11-15T10:59:35.863","createUserID":"00000000-0000-0000-0000-000000000000","createUserName":null,"updateDate":null,"updateUserName":null,"updateUserID":null,"isDel":0},{"id":"b51e98fb-c5e7-4b83-b33e-55c098702ef8","name":"fds","code":"fds","status":1,"createDate":"2018-08-07T14:59:22.41","createUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","createUserName":null,"updateDate":null,"updateUserName":null,"updateUserID":null,"isDel":0},{"id":"ea60e1b1-20d5-4213-b343-7352a40220f1","name":"药品供应商5d","code":"10051dd","status":1,"createDate":"2018-05-18T14:02:20.137","createUserID":"8b795d09-4da0-447d-bb67-838bc3abf7fa","createUserName":null,"updateDate":"2018-08-03T14:43:43.11","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"9a51ecf5-128e-4e4a-8eb4-7dc09a525a99","name":"药品供应商44","code":"10041","status":1,"createDate":"2018-05-02T00:00:00","createUserID":"00000000-0000-0000-0000-000000000000","createUserName":null,"updateDate":"2018-08-10T11:04:19.943","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"0bc2276f-0bb5-479b-9a9f-92d1cd453c53","name":"药品供应商3","code":"1003","status":1,"createDate":"2018-05-02T00:00:00","createUserID":"00000000-0000-0000-0000-000000000000","createUserName":null,"updateDate":"2018-11-12T10:00:46.977","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"84ddf038-7ea8-43ee-b331-f37e79df0b16","name":"药品供应商2","code":"1002","status":1,"createDate":"2018-05-02T00:00:00","createUserID":"00000000-0000-0000-0000-000000000000","createUserName":null,"updateDate":null,"updateUserName":null,"updateUserID":null,"isDel":0},{"id":"6f285fa4-3f02-4c7c-a61d-f60167f3726f","name":"药品供应商1","code":"1001","status":1,"createDate":"2018-05-02T00:00:00","createUserID":"00000000-0000-0000-0000-000000000000","createUserName":null,"updateDate":"2018-11-12T10:00:42.357","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0}]
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
         * id : 3d144e69-8e54-4536-b91e-87bc2497be2c
         * name : 测试
         * code : 101
         * status : 1
         * createDate : 2018-11-15T10:59:35.863
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
