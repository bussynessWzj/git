package com.hszl.medicine.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 上传给后台服务器的入库单对象
 */
public class StockIn implements Serializable {
    private String Code;
    private String SupplierInfoID;
    private String StorageCategoryID;
    private String Remark;
    private String CurrentUserID;
    private List<ListBean> List;

    public java.util.List<ListBean> getList() {
        return List;
    }

    public void setList(java.util.List<ListBean> list) {
        List = list;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getSupplierInfoID() {
        return SupplierInfoID;
    }

    public void setSupplierInfoID(String supplierInfoID) {
        SupplierInfoID = supplierInfoID;
    }

    public String getStorageCategoryID() {
        return StorageCategoryID;
    }

    public void setStorageCategoryID(String storageCategoryID) {
        StorageCategoryID = storageCategoryID;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getCurrentUserID() {
        return CurrentUserID;
    }

    public void setCurrentUserID(String currentUserID) {
        CurrentUserID = currentUserID;
    }

    public static class ListBean implements Serializable{
        private String DrugID;
        private String ProductionNumber;
        private String ProductionDate;
        private String ValidityDate;
        private String DrugCount;
        private String Price;
        private String Amount;

        public String getDrugID() {
            return DrugID;
        }

        public void setDrugID(String drugID) {
            DrugID = drugID;
        }

        public String getProductionNumber() {
            return ProductionNumber;
        }

        public void setProductionNumber(String productionNumber) {
            ProductionNumber = productionNumber;
        }

        public String getProductionDate() {
            return ProductionDate;
        }

        public void setProductionDate(String productionDate) {
            ProductionDate = productionDate;
        }

        public String getValidityDate() {
            return ValidityDate;
        }

        public void setValidityDate(String validityDate) {
            ValidityDate = validityDate;
        }

        public String getDrugCount() {
            return DrugCount;
        }

        public void setDrugCount(String drugCount) {
            DrugCount = drugCount;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String price) {
            Price = price;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String amount) {
            Amount = amount;
        }
    }


}
