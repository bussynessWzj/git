package com.hszl.medicine.http;

import com.hszl.medicine.entity.DosageForm;
import com.hszl.medicine.entity.Medicine;
import com.hszl.medicine.entity.MedicineOneLevel;
import com.hszl.medicine.entity.MedicineTwoLevel;
import com.hszl.medicine.entity.Report;
import com.hszl.medicine.entity.Stock;
import com.hszl.medicine.entity.Supplier;
import com.hszl.medicine.entity.SupplierType;
import com.hszl.medicine.entity.Unit;
import com.hszl.medicine.entity.Update;
import com.hszl.medicine.entity.User;
import com.hszl.medicine.entity.WarehouseIn;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface HttpInterface {

     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("UserLogin")
     Call<User> login(@Body RequestBody route);

     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("GetBillNumber")
     Call<WarehouseIn>  getRKDH(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("GetSupplierInfos")
     Call<Supplier>  getSupplier(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("GetStorageCategorys")
     Call<Stock>  getStock(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("GetDrugInfos")
     Call<Medicine>  getMedicine(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("DrugIn")
     Call<Update>  commitStockIn(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("SaveStorageCategory")
     Call<Update>  updateStock(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("SaveSupplierInfo")
     Call<Update>  updateSupplier(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("GetSupplierCategorys")
     Call<SupplierType>  getSupplierType(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("SaveSupplierCategory")
     Call<Update>  updateSupplierType(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("GetFirstDrugCategorys")
     Call<MedicineOneLevel>  getMedicineOneLevel(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("GetTwoDrugCategorys")
     Call<MedicineTwoLevel>  getMedicineTwoLevel(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("SaveDrugCategory")
     Call<Update>  AddMedicineType(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("GetDictionaryByCode")
     Call<DosageForm>  getDosageForm(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("GetDictionaryByCode")
     Call<Unit>  getUnit(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("SaveDrugInfo")
     Call<Update>  saveMedicineInfo(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("UserUpdate")
     Call<Update>  UpdatePwd(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("DeleteDrugInfo")
     Call<Update>  deleteMedicineInfo(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("DeleteStorageCategory")
     Call<Update>  deleteStock(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("DeleteSupplierInfo")
     Call<Update>  deleteSupplierInfo(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("DeleteSupplierCategory")
     Call<Update>  deleteSupplierType(@Body RequestBody route);
     @Headers({"Content-Type: application/json","Accept: application/json"})
     @POST("GetDrugStatics")
     Call<Report> getReportData(@Body RequestBody route);

}
