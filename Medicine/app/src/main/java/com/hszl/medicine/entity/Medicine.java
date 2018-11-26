package com.hszl.medicine.entity;

import java.io.Serializable;
import java.util.List;

public class Medicine implements Serializable {

    /**
     * count : 62
     * list : [{"id":"b9167be9-0560-406a-98e2-4f7c57d4b43a","drugCategoryID":"c12ac9cd-8001-47b9-b0f1-01f940589ae3","drugCode":"1111","barCode":"11111","drugName":"11222","commonName":"2222","specs":"22","dosageForm":"fd35dc13-000f-45f5-bf1b-968670bd1606","origin":"222","manufacturers":"22","approvedNumber":"222","unit":"bfb5dc76-c4cf-4f12-98f5-490e840f78bd","usage":null,"simpleSpell":"222","status":0,"dosageFormName":"胶囊","unitName":"盒","drugCategoryName":"心脑血管用药","validityDate":null,"drugCount":0,"storageCategoryName":null,"productionNumber":null,"leaveDays":0,"createDate":"2018-11-14T13:46:13.277","createUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","createUserName":null,"updateDate":"2018-11-14T13:46:13.277","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"79e12c0b-96bd-4e96-9295-e747166935c2","drugCategoryID":"a869efe1-d570-4b12-a7bb-0590590e4dbf","drugCode":"1","barCode":"2","drugName":"3","commonName":"4","specs":"5","dosageForm":"266260c3-ebc9-47d4-b733-8a783ee37ea7","origin":"7","manufacturers":"8","approvedNumber":"9","unit":"bfb5dc76-c4cf-4f12-98f5-490e840f78bd","usage":null,"simpleSpell":"10","status":0,"dosageFormName":"胶囊剂","unitName":"盒","drugCategoryName":"抗过敏药","validityDate":null,"drugCount":0,"storageCategoryName":null,"productionNumber":null,"leaveDays":0,"createDate":"2018-08-03T14:27:02.12","createUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","createUserName":null,"updateDate":"2018-08-09T11:46:10.933","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"474ebc82-2065-4c80-ba5b-7e6c906fccc0","drugCategoryID":"47ddd2ab-588e-4f8f-9f37-f96650f61860","drugCode":"114001","barCode":"6930691258012","drugName":"少林风湿跌打膏","commonName":"&nbsp;","specs":"8cm*9.5cm","dosageForm":"8c506839-4645-4d7c-8ff0-d49333051b56","origin":"&nbsp;","manufacturers":"安阳中智药业","approvedNumber":"&nbsp;","unit":"bfb5dc76-c4cf-4f12-98f5-490e840f78bd","usage":"&nbsp;","simpleSpell":"slfsddg","status":0,"dosageFormName":"软膏剂","unitName":"盒","drugCategoryName":"外用药","validityDate":null,"drugCount":0,"storageCategoryName":null,"productionNumber":null,"leaveDays":0,"createDate":"2018-06-08T13:38:01.133","createUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","createUserName":null,"updateDate":"2018-06-12T09:29:57.29","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"d9b117aa-2a89-4d15-8c1f-e9f8aa2d3e73","drugCategoryID":"01cebc5b-b773-4834-9862-e3c14d38fb24","drugCode":"107001","barCode":"6928189600191","drugName":"安胎丸","commonName":"&nbsp;","specs":"6g*10","dosageForm":"266260c3-ebc9-47d4-b733-8a783ee37ea7","origin":"&nbsp;","manufacturers":"江西泽众制药","approvedNumber":"&nbsp;","unit":"bfb5dc76-c4cf-4f12-98f5-490e840f78bd","usage":"&nbsp;","simpleSpell":"atw","status":0,"dosageFormName":"胶囊剂","unitName":"盒","drugCategoryName":"妇科用药","validityDate":null,"drugCount":0,"storageCategoryName":null,"productionNumber":null,"leaveDays":0,"createDate":"2018-06-08T13:36:34.43","createUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","createUserName":null,"updateDate":"2018-06-08T13:36:34.413","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"0791491f-d4e2-4870-9724-e9ef309a40be","drugCategoryID":"57a05195-851e-4b53-8236-db81df46f710","drugCode":"116004","barCode":"116004","drugName":"鲜竹沥","commonName":"&nbsp;","specs":"10ml*6","dosageForm":"9ad2dafc-70f5-40b3-8667-5c9ed5eb85ab","origin":"&nbsp;","manufacturers":"江西泽众制药","approvedNumber":"&nbsp;","unit":"bfb5dc76-c4cf-4f12-98f5-490e840f78bd","usage":"&nbsp;","simpleSpell":"xzl","status":0,"dosageFormName":"口服液","unitName":"盒","drugCategoryName":"呼吸系统用药","validityDate":null,"drugCount":0,"storageCategoryName":null,"productionNumber":null,"leaveDays":0,"createDate":"2018-06-08T13:32:12.77","createUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","createUserName":null,"updateDate":"2018-06-08T13:32:12.737","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"a796ea70-93ba-4051-9c83-ffbc4addd3a6","drugCategoryID":"57a05195-851e-4b53-8236-db81df46f710","drugCode":"116002","barCode":"6916082513687","drugName":"镇咳糖浆","commonName":"&nbsp;","specs":"糖浆剂","dosageForm":"9ad2dafc-70f5-40b3-8667-5c9ed5eb85ab","origin":"&nbsp;","manufacturers":"江西广力药业","approvedNumber":"&nbsp;","unit":"bfb5dc76-c4cf-4f12-98f5-490e840f78bd","usage":"&nbsp;","simpleSpell":"zktj","status":0,"dosageFormName":"口服液","unitName":"盒","drugCategoryName":"呼吸系统用药","validityDate":null,"drugCount":0,"storageCategoryName":null,"productionNumber":null,"leaveDays":0,"createDate":"2018-06-08T13:26:59.707","createUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","createUserName":null,"updateDate":"2018-06-08T13:26:59.683","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"5caa4805-839d-475a-a948-26626c69c416","drugCategoryID":"d7bc188e-c0e4-491d-a7ad-d393ac4a3137","drugCode":"115002","barCode":"115002","drugName":"健脾八珍糕","commonName":"&nbsp;","specs":"250g","dosageForm":"9ad2dafc-70f5-40b3-8667-5c9ed5eb85ab","origin":"&nbsp;","manufacturers":"&nbsp;","approvedNumber":"&nbsp;","unit":"bfb5dc76-c4cf-4f12-98f5-490e840f78bd","usage":"&nbsp;","simpleSpell":"jpbzg","status":0,"dosageFormName":"口服液","unitName":"盒","drugCategoryName":"营养药","validityDate":null,"drugCount":0,"storageCategoryName":null,"productionNumber":null,"leaveDays":0,"createDate":"2018-06-08T13:25:37.417","createUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","createUserName":null,"updateDate":"2018-06-08T13:25:37.4","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"a61ad67b-53e3-4bec-a50f-b4b9b2ca99cd","drugCategoryID":"d7bc188e-c0e4-491d-a7ad-d393ac4a3137","drugCode":"115001","barCode":"6920442415825","drugName":"智杞颗粒","commonName":"&nbsp;","specs":"2g*12","dosageForm":"3c38a821-c51b-48d8-bc99-ba15f5586ae1","origin":"&nbsp;","manufacturers":"&nbsp;","approvedNumber":"&nbsp;","unit":"bfb5dc76-c4cf-4f12-98f5-490e840f78bd","usage":"&nbsp;","simpleSpell":"zqkl","status":0,"dosageFormName":"片剂","unitName":"盒","drugCategoryName":"营养药","validityDate":null,"drugCount":0,"storageCategoryName":null,"productionNumber":null,"leaveDays":0,"createDate":"2018-06-08T13:25:10.93","createUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","createUserName":null,"updateDate":"2018-06-08T13:25:10.917","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"4e655f87-87fc-4ca5-bf9a-f8f42c06352b","drugCategoryID":"5561be09-08f4-4df9-a177-c3d5880cb208","drugCode":"118001","barCode":"6903426235688","drugName":"麝香接骨胶囊","commonName":"&nbsp;","specs":"0.3g*40","dosageForm":"3c38a821-c51b-48d8-bc99-ba15f5586ae1","origin":"&nbsp;","manufacturers":"哈药集团世一堂制药","approvedNumber":"&nbsp;","unit":"bfb5dc76-c4cf-4f12-98f5-490e840f78bd","usage":"&nbsp;","simpleSpell":"sxjgjn","status":0,"dosageFormName":"片剂","unitName":"盒","drugCategoryName":"祛风湿用药","validityDate":null,"drugCount":0,"storageCategoryName":null,"productionNumber":null,"leaveDays":0,"createDate":"2018-06-08T13:20:29.247","createUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","createUserName":null,"updateDate":"2018-06-08T13:20:29.227","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0},{"id":"47158678-8f84-48eb-97a2-46e944c3cc37","drugCategoryID":"128a8c43-1cb0-483d-bc21-bdad4d715385","drugCode":"1120011","barCode":"6922154600179","drugName":"阿司咪唑片","commonName":"&nbsp;","specs":"3mg*30s","dosageForm":"266260c3-ebc9-47d4-b733-8a783ee37ea7","origin":"&nbsp;","manufacturers":"西安杨森制药有限公司","approvedNumber":"&nbsp;","unit":"bfb5dc76-c4cf-4f12-98f5-490e840f78bd","usage":"&nbsp;","simpleSpell":"asmzp","status":0,"dosageFormName":"胶囊剂","unitName":"盒","drugCategoryName":"抗过敏药","validityDate":null,"drugCount":0,"storageCategoryName":null,"productionNumber":null,"leaveDays":0,"createDate":"2018-06-08T13:19:36.547","createUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","createUserName":null,"updateDate":"2018-06-08T13:19:33.407","updateUserName":null,"updateUserID":"ae05743e-a038-4356-8d9b-efdaf71ec78c","isDel":0}]
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
         * id : b9167be9-0560-406a-98e2-4f7c57d4b43a
         * drugCategoryID : c12ac9cd-8001-47b9-b0f1-01f940589ae3
         * drugCode : 1111
         * barCode : 11111
         * drugName : 11222
         * commonName : 2222
         * specs : 22
         * dosageForm : fd35dc13-000f-45f5-bf1b-968670bd1606
         * origin : 222
         * manufacturers : 22
         * approvedNumber : 222
         * unit : bfb5dc76-c4cf-4f12-98f5-490e840f78bd
         * usage : null
         * simpleSpell : 222
         * status : 0
         * dosageFormName : 胶囊
         * unitName : 盒
         * drugCategoryName : 心脑血管用药
         * validityDate : null
         * drugCount : 0
         * storageCategoryName : null
         * productionNumber : null
         * leaveDays : 0
         * createDate : 2018-11-14T13:46:13.277
         * createUserID : ae05743e-a038-4356-8d9b-efdaf71ec78c
         * createUserName : null
         * updateDate : 2018-11-14T13:46:13.277
         * updateUserName : null
         * updateUserID : ae05743e-a038-4356-8d9b-efdaf71ec78c
         * isDel : 0
         */

        private String id;
        private String drugCategoryID;
        private String drugCode;
        private String barCode;
        private String drugName;
        private String commonName;
        private String specs;
        private String dosageForm;
        private String origin;
        private String manufacturers;
        private String approvedNumber;
        private String unit;
        private Object usage;
        private String simpleSpell;
        private int status;
        private String dosageFormName;
        private String unitName;
        private String drugCategoryName;
        private Object validityDate;
        private int drugCount;
        private Object storageCategoryName;
        private Object productionNumber;
        private int leaveDays;
        private String createDate;
        private String createUserID;
        private Object createUserName;
        private String updateDate;
        private Object updateUserName;
        private String updateUserID;
        private int isDel;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDrugCategoryID() {
            return drugCategoryID;
        }

        public void setDrugCategoryID(String drugCategoryID) {
            this.drugCategoryID = drugCategoryID;
        }

        public String getDrugCode() {
            return drugCode;
        }

        public void setDrugCode(String drugCode) {
            this.drugCode = drugCode;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getDrugName() {
            return drugName;
        }

        public void setDrugName(String drugName) {
            this.drugName = drugName;
        }

        public String getCommonName() {
            return commonName;
        }

        public void setCommonName(String commonName) {
            this.commonName = commonName;
        }

        public String getSpecs() {
            return specs;
        }

        public void setSpecs(String specs) {
            this.specs = specs;
        }

        public String getDosageForm() {
            return dosageForm;
        }

        public void setDosageForm(String dosageForm) {
            this.dosageForm = dosageForm;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getManufacturers() {
            return manufacturers;
        }

        public void setManufacturers(String manufacturers) {
            this.manufacturers = manufacturers;
        }

        public String getApprovedNumber() {
            return approvedNumber;
        }

        public void setApprovedNumber(String approvedNumber) {
            this.approvedNumber = approvedNumber;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Object getUsage() {
            return usage;
        }

        public void setUsage(Object usage) {
            this.usage = usage;
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

        public String getDosageFormName() {
            return dosageFormName;
        }

        public void setDosageFormName(String dosageFormName) {
            this.dosageFormName = dosageFormName;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getDrugCategoryName() {
            return drugCategoryName;
        }

        public void setDrugCategoryName(String drugCategoryName) {
            this.drugCategoryName = drugCategoryName;
        }

        public Object getValidityDate() {
            return validityDate;
        }

        public void setValidityDate(Object validityDate) {
            this.validityDate = validityDate;
        }

        public int getDrugCount() {
            return drugCount;
        }

        public void setDrugCount(int drugCount) {
            this.drugCount = drugCount;
        }

        public Object getStorageCategoryName() {
            return storageCategoryName;
        }

        public void setStorageCategoryName(Object storageCategoryName) {
            this.storageCategoryName = storageCategoryName;
        }

        public Object getProductionNumber() {
            return productionNumber;
        }

        public void setProductionNumber(Object productionNumber) {
            this.productionNumber = productionNumber;
        }

        public int getLeaveDays() {
            return leaveDays;
        }

        public void setLeaveDays(int leaveDays) {
            this.leaveDays = leaveDays;
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
