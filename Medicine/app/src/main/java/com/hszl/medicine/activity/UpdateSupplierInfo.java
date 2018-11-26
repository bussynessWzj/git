package com.hszl.medicine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.adapter.SupplierAdapter;
import com.hszl.medicine.adapter.SupplierPopAdapter;
import com.hszl.medicine.entity.Stock;
import com.hszl.medicine.entity.Supplier;
import com.hszl.medicine.http.HttpInterface;
import com.hszl.medicine.utils.Constant;
import com.hszl.medicine.utils.DataUtils;
import com.hszl.medicine.utils.ImmersionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.ielse.view.SwitchView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateSupplierInfo extends BaseActivity implements SwitchView.OnStateChangedListener, SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.OnItemClickListener {

    String tag;
    Supplier.ListBean listBean;
    EditText etName, etCode, etSupplierType, etLandline, etPhoneNum, etFax, etEmail, etAddress;
    SwitchView svStatus;
    Button btnSave;
    PopupWindow popupWindow;

    TextView tvPopTitle, tvSure;
    RecyclerView rvInfo;
    LinearLayoutManager linearLayoutManager;
    SupplierPopAdapter adapter;
    List<Supplier.ListBean> list = new ArrayList<>();
    int pageSize = 10;
    int pageNum = 1;
    boolean isLoadMore = false;
    private SwipeRefreshLayout srl;
    Supplier.ListBean bean;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_update_supplier_info);
        ImmersionUtils.immersionTop(this, rlTop);
        showTitle("更新供应商信息");
        hideRightText();
        initView();
        hideBottom();
        tag=getIntent().getStringExtra("tag");
        if (tag.equals("add")) {

        } else if (tag.equals("update")) {
            listBean = (Supplier.ListBean) getIntent().getSerializableExtra("Supplier.ListBean");
            etName.setText(listBean.getName());
            etCode.setText(listBean.getCode());
            etAddress.setText(listBean.getAddress());
            etEmail.setText(listBean.getEmail());
            etFax.setText(listBean.getFax());
            etLandline.setText(listBean.getTel());
            etPhoneNum.setText(listBean.getPhone());
            etSupplierType.setText(listBean.getSupplierCategoryName());
            etSupplierType.setTag(listBean.getSupplierCategoryID());
            if (listBean.getStatus() == 0)
                svStatus.setOpened(false);
            else
                svStatus.setOpened(true);
        }
    }

    @Override
    public boolean validation() {
        if (DataUtils.isEmpty(etName.getText()))
        {
            Toast.makeText(this,"请输入名称",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etCode.getText()))
        {
            Toast.makeText(this,"请输入编码",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etSupplierType.getText()))
        {
            Toast.makeText(this,"请选择供应商类型",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etLandline.getText()))
        {
            Toast.makeText(this,"请输入电话",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etPhoneNum.getText()))
        {
            Toast.makeText(this,"请输入手机",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etFax.getText()))
        {
            Toast.makeText(this,"请输入传真",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etEmail.getText()))
        {
            Toast.makeText(this,"请输入邮件",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etAddress.getText()))
        {
            Toast.makeText(this,"请输入地址",Toast.LENGTH_SHORT).show();
            return false;
        }else if (!DataUtils.isPhoneNum(etPhoneNum.getText().toString()))
        {
            Toast.makeText(this,"手机号码输入不正确",Toast.LENGTH_SHORT).show();
            return false;
        }else if (!DataUtils.isEmail(etEmail.getText().toString()))
        {
            Toast.makeText(this,"邮箱输入不正确",Toast.LENGTH_SHORT).show();
            return false;
        }
            return true;
    }

    private void initView() {
        etName = findViewById(R.id.etName);
        etCode = findViewById(R.id.etCode);
        etSupplierType = findViewById(R.id.etSupplierType);
        etLandline = findViewById(R.id.etLandline);
        etPhoneNum = findViewById(R.id.etPhoneNum);
        etFax = findViewById(R.id.etFax);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        svStatus = findViewById(R.id.svStatus);
        btnSave = findViewById(R.id.btnSave);
        svStatus.setOnStateChangedListener(this);
        btnSave.setOnClickListener(this);
        etSupplierType.setOnClickListener(this);

        /**以下为pop框控件属性初始化**/
        adapter = new SupplierPopAdapter(R.layout.pop_stock_list_item, list);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnSave:
                if (!validation()) return;
                updateSupplier();
                break;
            case R.id.etSupplierType:
                getSupplier(pageSize,pageNum);

                break;
        }
    }

    @Override
    public void toggleToOn(SwitchView view) {
        view.setOpened(true);
    }

    @Override
    public void toggleToOff(SwitchView view) {
        view.setOpened(false);
    }

    private void updateSupplier() {
        Map<String, Object> map = new HashMap<>();
        if (tag.equals("add"))
            map.put("keyValue", "");
        else
            map.put("keyValue", listBean.getId());
        map.put("Name", etName.getText().toString());
        map.put("Code", etCode.getText().toString());
        map.put("SupplierCategoryID", etSupplierType.getTag().toString());
        map.put("Tel", etLandline.getText().toString());
        map.put("Address", etAddress.getText().toString());
        map.put("Fax", etFax.getText().toString());
        map.put("Email", etEmail.getText().toString());
        map.put("Phone", etPhoneNum.getText().toString());
        map.put("Status", svStatus.isOpened()?1:0);
        String json = DataUtils.toJson(map);
        HttpInterface service = Constant.retrofit.create(HttpInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Call<ResponseBody> call = service.updateSupplier(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(UpdateSupplierInfo.this, "提交成功", Toast.LENGTH_SHORT).show();
                UpdateSupplierInfo.this.finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(UpdateSupplierInfo.this, "提交失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

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
                Supplier supplier = response.body();
                if (!isLoadMore)
                    list.clear();
                check(supplier);
                list.addAll(supplier.getList());
                if (popupWindow == null)
                    popupWindow = creatPop(R.layout.pop_item_stock, "选择供应商类型");
                if (!popupWindow.isShowing())
                    show(popupWindow);
                adapter.notifyDataSetChanged();
                if (srl.isRefreshing())
                    srl.setRefreshing(false);
            }

            @Override
            public void onFailure(retrofit2.Call<Supplier> call, Throwable t) {
                if (srl.isRefreshing())
                    srl.setRefreshing(false);
                Toast.makeText(UpdateSupplierInfo.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private PopupWindow creatPop(int resid, String title) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_item_stock, null, false);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, 500);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.popwindow);
        srl = view.findViewById(R.id.srl);
        tvPopTitle = view.findViewById(R.id.tvPopTitle);
        tvSure = view.findViewById(R.id.tvSure);
        rvInfo = view.findViewById(R.id.rvInfo);
        rvInfo.setAdapter(adapter);
        rvInfo.setLayoutManager(linearLayoutManager);
        srl.setColorSchemeColors(Color.BLUE);
        srl.setOnRefreshListener(this);
        return popupWindow;
    }

    private void show(PopupWindow popupWindow) {
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onRefresh() {
        isLoadMore = false;
        pageNum = 1;
        getSupplier(pageSize, pageNum);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        bean = list.get(position);
        if (popupWindow == null)
            return;
        if (popupWindow.isShowing())
            popupWindow.dismiss();
        for (int i = 0; i < list.size(); i++) {
            if (i == position) {
                list.get(i).setCheck(true);
                continue;
            }
            list.get(i).setCheck(false);
        }
        etSupplierType.setText(list.get(position).getName());
        etSupplierType.setTag(list.get(position).getId());
        adapter.notifyDataSetChanged();


    }

    /**
     * 检查是否在请求的数据中有被选中的数据（有则更改数据源的check属性让列表适配器能选择显示是否显示选中图片）
     *
     * @param object 请求返回的实体对象
     */
    private void check(Object object) {

        if (object instanceof Supplier) {
            if (etSupplierType.getTag() == null)
                return;
            Supplier supplier = (Supplier) object;
            for (int i = 0; i < supplier.getList().size(); i++) {
                if (supplier.getList().get(i).getId().equals(etSupplierType.getTag().toString())) {
                    supplier.getList().get(i).setCheck(true);
                } else {
                    supplier.getList().get(i).setCheck(false);
                }
            }
        }
    }
}
