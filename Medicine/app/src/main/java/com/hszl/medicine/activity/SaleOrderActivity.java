package com.hszl.medicine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.hszl.medicine.R;
import com.hszl.medicine.adapter.SaleOrderAdapter;
import com.hszl.medicine.entity.AAA;
import com.hszl.medicine.utils.ImmersionUtils;

import java.util.ArrayList;
import java.util.List;

public class SaleOrderActivity extends BaseActivity {

    Button btnSearch;
    EditText etSearch;
    RecyclerView rvInfo;
    LinearLayoutManager linearLayoutManager;
    SaleOrderAdapter adapter;
    List<AAA> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_order_manager);
        ImmersionUtils.immersionTop(this,rlTop);
        showTitle("销售订单");
        hideBottom();
        hideRightText();
        initView();
        for (int i=0;i<10;i++)
        {
            list.add(new AAA());
        }
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        btnSearch=findViewById(R.id.btnSearch);
        etSearch=findViewById(R.id.etSearch);
        rvInfo=findViewById(R.id.rvInfo);
        linearLayoutManager=new LinearLayoutManager(this);
        adapter=new SaleOrderAdapter(R.layout.item_sale_order,list);
        rvInfo.setLayoutManager(linearLayoutManager);
        rvInfo.setAdapter(adapter);
    }
}
