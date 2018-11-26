package com.hszl.medicine.activity;

import android.content.Intent;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.adapter.MedicineOneLevelAdapter;
import com.hszl.medicine.adapter.MedicineTwoLevelAdapter;
import com.hszl.medicine.entity.MedicineOneLevel;
import com.hszl.medicine.entity.MedicineTwoLevel;
import com.hszl.medicine.entity.WarehouseIn;
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
import retrofit2.Callback;
import retrofit2.Response;

public class MedicineTypeActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    FloatingActionButton fab;
    RecyclerView rvLevelOne, rvLevelTwo;
    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
    MedicineOneLevelAdapter adapter1;
    MedicineTwoLevelAdapter adapter2;
    List<MedicineOneLevel.ListBean> list1 = new ArrayList<>();
    List<MedicineTwoLevel.ListBean> list2 = new ArrayList<>();
    MedicineOneLevel.ListBean listBean;

    @Override
    public boolean validation() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_medicine_type);
        ImmersionUtils.immersionTop(this, rlTop);
        showTitle("药品类型");
        initView();
        hideBottom();
        hideRightText();
        getMedicineOneLevel();
    }

    private void initView() {
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        rvLevelOne = findViewById(R.id.rvLevelOne);
        rvLevelTwo = findViewById(R.id.rvLevelTwo);
        adapter1 = new MedicineOneLevelAdapter(R.layout.item_medicine_onelevel, list1);
        adapter2 = new MedicineTwoLevelAdapter(R.layout.item_medicine_twolevel, list2);
        rvLevelOne.setLayoutManager(linearLayoutManager1);
        rvLevelOne.setAdapter(adapter1);
        rvLevelTwo.setLayoutManager(linearLayoutManager2);
        rvLevelTwo.setAdapter(adapter2);
        adapter1.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent=new Intent();
        switch (v.getId()) {
            case R.id.fab:
                intent.setClass(this,SaveMedicineTypeActivity.class);
                intent.putExtra("id",listBean.getId());
                startActivity(intent);
                Toast.makeText(this, "点击了悬浮按钮", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        listBean = list1.get(position);
        for (int i = 0; i < list1.size(); i++) {
            if (i == position)
                list1.get(i).setCheck(true);
            else
                list1.get(i).setCheck(false);
        }
        adapter1.notifyDataSetChanged();
        getMedicineTwoLevel(listBean.getId());
    }


    private void getMedicineOneLevel() {
        HttpInterface service = Constant.retrofit.create(HttpInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");
        retrofit2.Call<MedicineOneLevel> call = service.getMedicineOneLevel(body);
        call.enqueue(new Callback<MedicineOneLevel>() {
            @Override
            public void onResponse(retrofit2.Call<MedicineOneLevel> call, Response<MedicineOneLevel> response) {
                list1.clear();
                MedicineOneLevel level=response.body();
                list1.addAll(level.getList());
                list1.get(0).setCheck(true);
                listBean=list1.get(0);
                adapter1.notifyDataSetChanged();
                getMedicineTwoLevel(list1.get(0).getId());
            }

            @Override
            public void onFailure(retrofit2.Call<MedicineOneLevel> call, Throwable t) {
                Toast.makeText(MedicineTypeActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMedicineTwoLevel(String id)
    {
        Map<String,Object> map=new HashMap<>();
        map.put("parentid",id);
        String json=DataUtils.toJson(map);
        HttpInterface service = Constant.retrofit.create(HttpInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofit2.Call<MedicineTwoLevel> call = service.getMedicineTwoLevel(body);
        call.enqueue(new Callback<MedicineTwoLevel>() {
            @Override
            public void onResponse(retrofit2.Call<MedicineTwoLevel> call, Response<MedicineTwoLevel> response) {
                list2.clear();
                MedicineTwoLevel level=response.body();
                list2.addAll(level.getList());
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<MedicineTwoLevel> call, Throwable t) {
                Toast.makeText(MedicineTypeActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
