package com.hszl.medicine.entity;

import java.io.Serializable;
import java.util.List;

public class DosageForm implements Serializable {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * id : f8b014cc-0cb0-49bd-8b33-d87631b12c76
         * parentID : 597457f0-fdc7-42c2-b2a3-8ba639455c2c
         * parentName : null
         * title : 颗粒剂
         * code : DosageForm1
         * dataType : 0
         * value : DosageForm1
         * note : &nbsp;
         * sort : 1
         * createUserID : ae05743e-a038-4356-8d9b-efdaf71ec78c
         * createDate : 2018-05-18T17:46:41.417
         */

        private String id;
        private String parentID;
        private Object parentName;
        private String title;
        private String code;
        private int dataType;
        private String value;
        private String note;
        private int sort;
        private String createUserID;
        private String createDate;
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

        public String getParentID() {
            return parentID;
        }

        public void setParentID(String parentID) {
            this.parentID = parentID;
        }

        public Object getParentName() {
            return parentName;
        }

        public void setParentName(Object parentName) {
            this.parentName = parentName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getDataType() {
            return dataType;
        }

        public void setDataType(int dataType) {
            this.dataType = dataType;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getCreateUserID() {
            return createUserID;
        }

        public void setCreateUserID(String createUserID) {
            this.createUserID = createUserID;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
