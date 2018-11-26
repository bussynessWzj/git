package com.hszl.medicine.entity;

import java.io.Serializable;
import java.util.List;

public class Supplier implements Serializable {

    /**
     * count : 2
     * list : [{"id":"6af573b3-fa27-4195-8292-5317a5729425","supplierCategoryID":"9a51ecf5-128e-4e4a-8eb4-7dc09a525a99","code":"GYS002","name":"贵林三精药业","address":"ZZZ","tel":"1234567890","phone":"13985274136","fax":"02712369874","email":"glsj@163.com","simpleSpell":"glsjyy","status":1,"supplierCategoryName":"药品供应商44","createDate":"2018-06-07T10:17:53.733","createUserID":"8b795d09-4da0-447d-bb67-838bc3abf7fa","createUserName":null,"updateDate":"2018-08-03T09:58:01.007","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"0d8132b3-6deb-465d-8eb7-8a0669bd5975","supplierCategoryID":"6f285fa4-3f02-4c7c-a61d-f60167f3726f","code":"GHS01","name":"哈药六厂","address":"XX省YY市ZZ大街PP号","tel":"028-12345678","phone":"12345678912","fax":"028-32165487","email":"3233dd","simpleSpell":"hylc","status":1,"supplierCategoryName":"药品供应商1","createDate":"2018-05-18T14:44:45.94","createUserID":"8b795d09-4da0-447d-bb67-838bc3abf7fa","createUserName":null,"updateDate":"2018-08-07T17:14:30.737","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0}]
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
         * id : 6af573b3-fa27-4195-8292-5317a5729425
         * supplierCategoryID : 9a51ecf5-128e-4e4a-8eb4-7dc09a525a99
         * code : GYS002
         * name : 贵林三精药业
         * address : ZZZ
         * tel : 1234567890
         * phone : 13985274136
         * fax : 02712369874
         * email : glsj@163.com
         * simpleSpell : glsjyy
         * status : 1
         * supplierCategoryName : 药品供应商44
         * createDate : 2018-06-07T10:17:53.733
         * createUserID : 8b795d09-4da0-447d-bb67-838bc3abf7fa
         * createUserName : null
         * updateDate : 2018-08-03T09:58:01.007
         * updateUserName : null
         * updateUserID : ae05743e-a038-4356-8d9b-efdaf71ec78c
         * isDel : 0
         */

        private String id;
        private String supplierCategoryID;
        private String code;
        private String name;
        private String address;
        private String tel;
        private String phone;
        private String fax;
        private String email;
        private String simpleSpell;
        private int status;
        private String supplierCategoryName;
        private String createDate;
        private String createUserID;
        private Object createUserName;
        private String updateDate;
        private Object updateUserName;
        private String updateUserID;
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

        public String getSupplierCategoryID() {
            return supplierCategoryID;
        }

        public void setSupplierCategoryID(String supplierCategoryID) {
            this.supplierCategoryID = supplierCategoryID;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSimpleSpell() {
            return simpleSpell;
        }

        public void setSimpleSpell(String simpleSpell) {
            this.simpleSpell = simpleSpell;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSupplierCategoryName() {
            return supplierCategoryName;
        }

        public void setSupplierCategoryName(String supplierCategoryName) {
            this.supplierCategoryName = supplierCategoryName;
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

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public Object getUpdateUserName() {
            return updateUserName;
        }

        public void setUpdateUserName(Object updateUserName) {
            this.updateUserName = updateUserName;
        }

        public String getUpdateUserID() {
            return updateUserID;
        }

        public void setUpdateUserID(String updateUserID) {
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
