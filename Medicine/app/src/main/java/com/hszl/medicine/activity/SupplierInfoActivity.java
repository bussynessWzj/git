package com.hszl.medicine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.hszl.medicine.adapter.StockInfoAdapter;
import com.hszl.medicine.adapter.SupplierInfoAdapter;
import com.hszl.medicine.entity.Medicine;
import com.hszl.medicine.entity.Stock;
import com.hszl.medicine.entity.Supplier;
import com.hszl.medicine.entity.SupplierType;
import com.hszl.medicine.entity.Update;
import com.hszl.medicine.http.HttpInterface;
import com.hszl.medicine.utils.Constant;
import com.hszl.medicine.utils.DataUtils;
import com.hszl.medicine.utils.ImmersionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupplierInfoActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener ,BaseQuickAdapter.RequestLoadMoreListener {

    Button btnSearch;
    FloatingActionButton fab;
    EditText etSearch;
    RecyclerView rvStockInfo;
    LinearLayoutManager linearLayoutManager;
    SupplierInfoAdapter adapter;
    List<Supplier.ListBean> list=new ArrayList<>();
    PopupWindow popupWindow;
    Supplier.ListBean listBean;
    TextView tvEditor;
    TextView tvDelete;
    TextView tvCancel;
    SwipeRefreshLayout srl;
    int pageSize=10;
    int pageNum=1;
    boolean isLoadMore=false;

    @Override
    public boolean validation() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_supplier_info);
        ImmersionUtils.immersionTop(this,rlTop);
        showTitle("供应商信息");
        hideRightText();
        initView();
        hideBottom();
        getSupplier(pageSize,pageNum,etSearch.getText().toString());
    }

    private void initView() {
        btnSearch=findViewById(R.id.btnSearch);
        srl=findViewById(R.id.srl);
        fab=findViewById(R.id.fab);
        etSearch=findViewById(R.id.etSearch);
        rvStockInfo=findViewById(R.id.rvStockInfo);
        fab.setOnClickListener(this);
        linearLayoutManager=new LinearLayoutManager(this);
        adapter=new SupplierInfoAdapter(R.layout.item_supplier_info,list);
        adapter.setOnItemClickListener(this);
        rvStockInfo.setAdapter(adapter);
        rvStockInfo.setLayoutManager(linearLayoutManager);
        adapter.setOnLoadMoreListener(this,rvStockInfo);
        srl.setColorSchemeColors(Color.BLUE);
        srl.setOnRefreshListener(this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent=new Intent();
        switch (v.getId())
        {
            case R.id.fab:
                intent.setClass(this,UpdateSupplierInfo.class);
                intent.putExtra("tag","add");
                startActivity(intent);
                break;
            case R.id.tvEditor:
                intent.setClass(this,UpdateSupplierInfo.class);
                intent.putExtra("tag","update");
                intent.putExtra("Supplier.ListBean",listBean);
                startActivity(intent);
                break;
            case R.id.tvDelete:
                deleteSupplierInfo(listBean);

                break;
            case R.id.tvCancel:
                if (popupWindow!=null)
                    popupWindow.dismiss();
                break;
            case R.id.btnSearch:
                getSupplier(pageSize,pageNum,etSearch.getText().toString());
                break;
        }
    }

    private PopupWindow creatPop(int resid)
    {
        View view=LayoutInflater.from(this).inflate(R.layout.pop_item_operation,null,false);
        PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,500);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.style.popwindow);
        tvEditor=view.findViewById(R.id.tvEditor);
        tvDelete=view.findViewById(R.id.tvDelete);
        tvCancel=view.findViewById(R.id.tvCancel);
        tvEditor.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        return popupWindow;
    }

    private void show(PopupWindow popupWindow)
    {
        popupWindow.showAtLocation(findViewById(android.R.id.content),Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        listBean=list.get(position);
        if (popupWindow==null)
            popupWindow=creatPop(R.layout.pop_item_operation);
        if (!popupWindow.isShowing())
            show(popupWindow);
    }

    private void getSupplier(int size,int num,String keyValue) {
        Map<String, Object> map = new HashMap<>();
        map.put("pagesize", String.valueOf(size));
        map.put("pagenumber", String.valueOf(num));
        map.put("keyValue", keyValue);
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
                list.addAll(supplier.getList());
                adapter.notifyDataSetChanged();
                if (srl.isRefreshing())
                    srl.setRefreshing(false);
                adapter.loadMoreEnd();
            }

            @Override
            public void onFailure(retrofit2.Call<Supplier> call, Throwable t) {
                if (srl.isRefreshing())
                    srl.setRefreshing(false);
                adapter.loadMoreEnd();
                Toast.makeText(SupplierInfoActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        isLoadMore=false;
        pageNum=1;
        getSupplier(pageSize,pageNum,etSearch.getText().toString());
    }

    @Override
    public void onLoadMoreRequested() {
        isLoadMore=true;
        pageNum++;
        getSupplier(pageSize,pageNum,etSearch.getText().toString());
    }

    private void deleteSupplierInfo(final Supplier.ListBean listBean)
    {
        Map<String,Object> map=new HashMap<>();
        map.put("keyValue",listBean.getId());
        String json=DataUtils.toJson(map);
        HttpInterface service=Constant.retrofit.create(HttpInterface.class);
        RequestBody body=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json);
        Call<Update> call=service.deleteSupplierInfo(body);
        call.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(Call<Update> call, Response<Update> response) {

                Update update
                        =response.body();
                if (update.getSuccess()==1) {
                    Toast.makeText(SupplierInfoActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    list.remove(listBean);
                    adapter.notifyDataSetChanged();
                }else
                {
                    Toast.makeText(SupplierInfoActivity.this, update.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Update> call, Throwable t) {
                Toast.makeText(SupplierInfoActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
