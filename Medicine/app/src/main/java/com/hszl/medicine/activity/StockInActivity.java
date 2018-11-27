package com.hszl.medicine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hszl.medicine.MainActivity;
import com.hszl.medicine.R;
import com.hszl.medicine.adapter.StockAapter;
import com.hszl.medicine.adapter.SupplierAdapter;
import com.hszl.medicine.entity.SPUtils;
import com.hszl.medicine.entity.Stock;
import com.hszl.medicine.entity.StockIn;
import com.hszl.medicine.entity.Supplier;
import com.hszl.medicine.entity.Update;
import com.hszl.medicine.entity.User;
import com.hszl.medicine.entity.WarehouseIn;
import com.hszl.medicine.http.HttpInterface;
import com.hszl.medicine.utils.Constant;
import com.hszl.medicine.utils.DataUtils;
import com.hszl.medicine.utils.DateUtils;
import com.hszl.medicine.utils.ImmersionUtils;
import com.hszl.medicine.view.MoneyTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

public class StockInActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    TextView tvTime, tvRKDH, tvPerson, tvSupplier, tvStock, tvCount;
    EditText etRemark;
    MoneyTextView mtvMoney;
    ImageView imgAdd;
    Button btnCommit;
    RelativeLayout rlBottom;

    TextView tvPopTitle, tvSure;
    RecyclerView rvInfo;
    StockAapter stockAapter;
    SupplierAdapter supplierAdapter;
    User user;
    List<Supplier.ListBean> supplierListBeans = new ArrayList<>();
    List<Stock.ListBean> stockListBeans = new ArrayList<>();
    PopupWindow popupWindow;
    StockIn stockIn = new StockIn();
    public static final int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionUtils.setTrans(this);
        initLayout(R.layout.activity_stock_in);
        initView();
        ImmersionUtils.immersionTop(this, rlTop);
        user = SPUtils.getUser();
        getRKDH();
        showTitle("入库单录入");
        hideLeftImg();
        hideRightImg();
        showLeftText("返回");
        hideRightText();
        hideBottom();
        tvTime.setText(DateUtils.getDateBylong(System.currentTimeMillis()));
        supplierAdapter = new SupplierAdapter(R.layout.pop_stock_list_item, supplierListBeans);
        stockAapter = new StockAapter(R.layout.pop_stock_list_item, stockListBeans);

        tvPerson.setText(user.getUsername());
    }

    @Override
    public boolean validation() {
        if (DataUtils.isEmpty(tvSupplier.getText())) {
            Toast.makeText(this, "请选择供应商", Toast.LENGTH_SHORT).show();
            return false;
        } else if (DataUtils.isEmpty(tvStock.getText())) {
            Toast.makeText(this, "请选择仓库", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        tvTime = findViewById(R.id.tvTime);
        tvRKDH = findViewById(R.id.tvRKDH);
        tvPerson = findViewById(R.id.tvPerson);
        tvSupplier = findViewById(R.id.tvSupplier);
        tvStock = findViewById(R.id.tvStock);
        tvCount = findViewById(R.id.tvCount);
        etRemark = findViewById(R.id.etRemark);
        mtvMoney = findViewById(R.id.mtvMoney);
        imgAdd = findViewById(R.id.imgAdd);
        btnCommit = findViewById(R.id.btnCommit);
        rlBottom = findViewById(R.id.rlBottom);
        tvSupplier.setOnClickListener(this);
        tvStock.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tvSupplier:
                //先请求数据，初始化adapter然后调用下面的创建方法
                getSupplier(10, 1);
                popupWindow = creatPop(R.layout.pop_item_stock, "选择供应商", supplierAdapter);
                if (!popupWindow.isShowing()) {
                    showPop(popupWindow);
                }
                break;
            case R.id.tvStock:
                //先请求数据，初始化adapter然后调用下面的创建方法
                getStock(10, 1);
                popupWindow = creatPop(R.layout.pop_item_stock, "选择仓库", stockAapter);
                //                popupWindow=creatPop(R.layout.pop_item_stock,"选择供应商",supplierAdapter);
                if (!popupWindow.isShowing()) {
                    showPop(popupWindow);
                }
                break;
            case R.id.imgAdd:
                if (!validation())
                    return;
                //跳转选择购物车界面
                Intent intent = new Intent();
                intent.setClass(this, MedicineActivity.class);
                intent.putExtra("stockin", stockIn);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.btnCommit:
                commit(stockIn);
                break;
            case R.id.tvSure:
                if (popupWindow == null)
                    return;
                if (popupWindow.isShowing())
                    popupWindow.dismiss();
                break;
        }
    }

    private PopupWindow creatPop(int resid, String title, RecyclerView.Adapter adapter) {
        View view = LayoutInflater.from(this).inflate(resid, null, false);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, 500);
        tvPopTitle = view.findViewById(R.id.tvPopTitle);
        tvSure = view.findViewById(R.id.tvSure);
        rvInfo = view.findViewById(R.id.rvInfo);
        tvPopTitle.setText(title);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvInfo.setLayoutManager(linearLayoutManager);
        rvInfo.setAdapter(adapter);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.style.popwindow);
        tvSure.setOnClickListener(this);
        return popupWindow;
    }

    private void showPop(PopupWindow popupWindow) {
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, rlBottom.getMeasuredHeight());
    }


    private void getStock(int size, int num) {
        Map<String, Object> map = new HashMap<>();
        map.put("pagesize", String.valueOf(size));
        map.put("pagenumber", String.valueOf(num));
        map.put("keyValue", "");
        String json = DataUtils.toJson(map);
        HttpInterface service = Constant.retrofit.create(HttpInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofit2.Call<Stock> call = service.getStock(body);
        call.enqueue(new Callback<Stock>() {
            @Override
            public void onResponse(retrofit2.Call<Stock> call, Response<Stock> response) {
                stockListBeans.clear();
                Stock stock = response.body();
                stock.getList();
                check(stock);
                stockListBeans.addAll(stock.getList());
                stockAapter.setOnItemClickListener(StockInActivity.this);
                stockAapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<Stock> call, Throwable t) {
                Toast.makeText(StockInActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**
     * 请求供应商信息
     *
     * @param size 每页数量
     * @param num  页码
     */
    private void getSupplier(int size, int num) {
        Map<String, Object> map = new HashMap<>();
        map.put("pagesize", String.valueOf(size));
        map.put("pagenumber", String.valueOf(num));
        map.put("keyValue", "");
        String json = DataUtils.toJson(map);
        HttpInterface service = Constant.retrofit.create(HttpInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofit2.Call<Supplier> call = service.getSupplier(body);
        call.enqueue(new Callback<Supplier>() {
            @Override
            public void onResponse(retrofit2.Call<Supplier> call, Response<Supplier> response) {
                supplierListBeans.clear();
                Supplier supplier = response.body();
                supplier.getList();
                check(supplier);
                supplierListBeans.addAll(supplier.getList());
                supplierAdapter.setOnItemClickListener(StockInActivity.this);
                supplierAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<Supplier> call, Throwable t) {
                Toast.makeText(StockInActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 检查是否在请求的数据中有被选中的数据（有则更改数据源的check属性让列表适配器能选择显示是否显示选中图片）
     *
     * @param object 请求返回的实体对象
     */
    private void check(Object object) {

        if (object instanceof Supplier) {
            if (tvSupplier.getTag() == null)
                return;
            Supplier supplier = (Supplier) object;
            for (int i = 0; i < supplier.getList().size(); i++) {
                if (supplier.getList().get(i).getId().equals(tvSupplier.getTag().toString())) {
                    supplier.getList().get(i).setCheck(true);
                } else {
                    supplier.getList().get(i).setCheck(false);
                }
            }
        } else if (object instanceof Stock) {
            if (tvStock.getTag() == null)
                return;
            Stock stock = (Stock) object;
            for (int i = 0; i < stock.getList().size(); i++) {
                if (stock.getList().get(i).getId().equals(tvStock.getTag().toString())) {
                    stock.getList().get(i).setCheck(true);
                } else {
                    stock.getList().get(i).setCheck(false);
                }
            }
        }
    }


    /**
     * 获取入库单
     */
    private void getRKDH() {
        HttpInterface service = Constant.retrofit.create(HttpInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");
        retrofit2.Call<WarehouseIn> call = service.getRKDH(body);
        call.enqueue(new Callback<WarehouseIn>() {
            @Override
            public void onResponse(retrofit2.Call<WarehouseIn> call, Response<WarehouseIn> response) {
                WarehouseIn warehouseIn = response.body();
                tvRKDH.setText(warehouseIn.getCode());
            }

            @Override
            public void onFailure(retrofit2.Call<WarehouseIn> call, Throwable t) {
                Toast.makeText(StockInActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter instanceof SupplierAdapter) {
            for (int i = 0; i < supplierListBeans.size(); i++) {
                if (i == position) {
                    supplierListBeans.get(i).setCheck(true);
                    continue;
                }
                supplierListBeans.get(i).setCheck(false);
            }
            tvSupplier.setText(supplierListBeans.get(position).getName());
            stockIn.setSupplierInfoID(supplierListBeans.get(position).getId());
            tvSupplier.setTag(supplierListBeans.get(position).getId());
            supplierAdapter.notifyDataSetChanged();
        } else {
            for (int i = 0; i < stockListBeans.size(); i++) {
                if (i == position) {
                    stockListBeans.get(i).setCheck(true);
                    continue;
                }
                stockListBeans.get(i).setCheck(false);
            }
            tvStock.setText(stockListBeans.get(position).getName());
            stockIn.setStorageCategoryID(stockListBeans.get(position).getId());
            tvStock.setTag(stockListBeans.get(position).getId());
            stockAapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                stockIn = (StockIn) data.getSerializableExtra("stockin");
                tvCount.setText(String.valueOf(stockIn.getList().size()));
                mtvMoney.setText(String.valueOf(calcTotal(stockIn)), TextView.BufferType.NORMAL);
            }
        }
    }

    private int calcTotal(StockIn stockIn) {
        int totalPrice = 0;
        for (int i = 0; i < stockIn.getList().size(); i++) {
            totalPrice += Integer.valueOf(stockIn.getList().get(i).getDrugCount()) * Integer.valueOf(stockIn.getList().get(i).getPrice());
        }
        return totalPrice;
    }

    private void commit(StockIn stockIn) {
        stockIn.setCurrentUserID(user.getId());
        stockIn.setSupplierInfoID(tvSupplier.getTag().toString());
        stockIn.setStorageCategoryID(tvStock.getTag().toString());
        Gson gson = new Gson();
        String json = gson.toJson(stockIn);
        HttpInterface service = Constant.retrofit.create(HttpInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofit2.Call<Update> call = service.commitStockIn(body);
        call.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(retrofit2.Call<Update> call, Response<Update> response) {
                Update update
                        =response.body();
                if (update.getSuccess()==1) {
                    Toast.makeText(StockInActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(StockInActivity.this, update.getMsg(), Toast.LENGTH_SHORT).show();
                }
//
//                Toast.makeText(StockInActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(retrofit2.Call<Update> call, Throwable t) {
                Toast.makeText(StockInActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
