package com.hszl.medicine.entity;

import java.io.Serializable;
import java.util.List;

public class MedicineLevel implements Serializable {

    private List<MedicineOneLevel.ListBean> list;

    public List<MedicineOneLevel.ListBean> getList() {
        return list;
    }

    public void setList(List<MedicineOneLevel.ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * id : 8dad2848-6ff2-448b-a511-1a4cc1db1b9c
         * name : 非处方药
         * parentID : 00000000-0000-0000-0000-000000000000
         * code : 2
         * status : 0
         * number : 8dad2848-6ff2-448b-a511-1a4cc1db1b9c
         * createDate : 0001-01-01T00:00:00
         * createUserID : 00000000-0000-0000-0000-000000000000
         * createUserName : null
         * updateDate : null
         * updateUserName : null
         * updateUserID : null
         * isDel : 0
         */

        private String id;
        private String name;
        private String parentID;
        private String code;
        private int status;
        private String number;
        private String createDate;
        private String createUserID;
        private Object createUserName;
        private Object updateDate;
        private Object updateUserName;
        private Object updateUserID;
        private int isDel;
        private boolean check=false;

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

        public String getParentID() {
            return parentID;
        }

        public void setParentID(String parentID) {
            this.parentID = parentID;
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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
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
