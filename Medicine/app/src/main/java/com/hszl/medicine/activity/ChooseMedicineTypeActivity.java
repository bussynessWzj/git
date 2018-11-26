package com.hszl.medicine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.adapter.MedicineLevelAdapter;
import com.hszl.medicine.adapter.MedicineOneLevelAdapter;
import com.hszl.medicine.adapter.MedicineTwoLevelAdapter;
import com.hszl.medicine.entity.Medicine;
import com.hszl.medicine.entity.MedicineLevel;
import com.hszl.medicine.entity.MedicineOneLevel;
import com.hszl.medicine.entity.MedicineTwoLevel;
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

public class ChooseMedicineTypeActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    LinearLayout llTop;
    RecyclerView rvTypeOne,rvTypeTwo;
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManager1;
    List<MedicineLevel.ListBean> list1=new ArrayList<>();
    List<MedicineLevel.ListBean> list2=new ArrayList<>();
    MedicineLevelAdapter adapter1;
    MedicineLevelAdapter adapter2;
    MedicineLevel.ListBean bean;
    TextView tvBack,tvCommit;

    @Override
    public boolean validation() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_medicinetype);
        initView();
        ImmersionUtils.immersionTop(this,llTop);
        getMedicineOneLevel();

    }
    private void initView() {
        llTop=findViewById(R.id.llTop);
        rvTypeOne=findViewById(R.id.rvTypeOne);
        rvTypeTwo=findViewById(R.id.rvTypeTwo);
        tvBack=findViewById(R.id.tvBack);
        tvCommit=findViewById(R.id.tvCommit);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager1=new LinearLayoutManager(this);
        adapter1=new MedicineLevelAdapter(R.layout.item_medicine_twolevel,list1);
        adapter1.setOnItemClickListener(this);
        adapter2=new MedicineLevelAdapter(R.layout.item_medicine_twolevel,list2);
        adapter2.setOnItemClickListener(this);
        rvTypeOne.setLayoutManager(linearLayoutManager);
        rvTypeOne.setAdapter(adapter1);
        rvTypeTwo.setLayoutManager(linearLayoutManager1);
        rvTypeTwo.setAdapter(adapter2);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter==adapter1) {
            bean=list1.get(position);
            addView(list1.get(position).getName(), list1.get(position).getId());
            addSymbol();
            rvTypeOne.setVisibility(View.GONE);
            rvTypeTwo.setVisibility(View.VISIBLE);
            getMedicineTwoLevel(list1.get(position).getId());
        }else if (adapter==adapter2)
        {
            bean=list2.get(position);
            addView(list2.get(position).getName(), list2.get(position).getId());
        }
    }

    private void addView(String text,String tag)
    {
        TextView textView=new TextView(this);
        textView.setText(text);
        textView.setTag(tag);
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        llTop.addView(textView);
    }

    /**
     * 添加符号
     */
    private void addSymbol()
    {
        TextView textView=new TextView(this);
        textView.setText("<<<<<<");
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        llTop.addView(textView);
    }

    private void getMedicineOneLevel() {
        HttpInterface service = Constant.retrofit.create(HttpInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");
        retrofit2.Call<MedicineOneLevel> call = service.getMedicineOneLevel(body);
        call.enqueue(new Callback<MedicineOneLevel>() {
            @Override
            public void onResponse(retrofit2.Call<MedicineOneLevel> call, Response<MedicineOneLevel> response) {
                list1.clear();
                MedicineLevel level=response.body();
                list1.addAll(level.getList());
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<MedicineOneLevel> call, Throwable t) {
                Toast.makeText(ChooseMedicineTypeActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ChooseMedicineTypeActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.tvBack:
                this.finish();
                break;
            case R.id.tvCommit:
                Intent intent=new Intent();
                intent.putExtra("MedicineLevel",bean);
                this.setResult(RESULT_OK,intent);
                break;
        }
    }
}
