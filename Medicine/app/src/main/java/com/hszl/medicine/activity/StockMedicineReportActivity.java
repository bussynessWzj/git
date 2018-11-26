package com.hszl.medicine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.hszl.medicine.R;
import com.hszl.medicine.adapter.CopyMedicineOneLevelAdapter;
import com.hszl.medicine.entity.Medicine;
import com.hszl.medicine.entity.MedicineLevel;
import com.hszl.medicine.entity.MedicineOneLevel;
import com.hszl.medicine.entity.MedicineTwoLevel;
import com.hszl.medicine.entity.Report;
import com.hszl.medicine.entity.Value;
import com.hszl.medicine.entity.Y;
import com.hszl.medicine.http.HttpInterface;
import com.hszl.medicine.utils.BarChartUtils;
import com.hszl.medicine.utils.Constant;
import com.hszl.medicine.utils.DataUtils;
import com.hszl.medicine.utils.ImmersionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Callback;
import retrofit2.Response;

public class StockMedicineReportActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    
    TextView tvPopTitle,tvSure;
    RecyclerView rvInfo;
    List<MedicineLevel.ListBean> list=new ArrayList<>();
    CopyMedicineOneLevelAdapter adapter1;
    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

    Report report;
    List<String> x=new ArrayList<>();
    List<Y> y=new ArrayList<>();
    float maxY;

    @Override
    public boolean validation() {
        return false;
    }

    TextView tvMedicineType;
    BarChart barchart;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_stock_medicine_report);
        ImmersionUtils.immersionTop(this,rlTop);
        initView();
        initData();
        hideBottom();
        hideRightText();
        showTitle("仓库药品报表");
        getReportData("");
    }

    private void initData() {
        MedicineLevel.ListBean level=new MedicineLevel.ListBean();
        level.setCheck(true);
        level.setName("全部");
    }

    private void initView() {
        tvMedicineType=findViewById(R.id.tvMedicineType);
        barchart=findViewById(R.id.barchart);
        tvMedicineType.setOnClickListener(this);
        adapter1=new CopyMedicineOneLevelAdapter(R.layout.pop_stock_list_item,list);
        if (popupWindow==null)
            popupWindow=creatPop(R.layout.pop_item_stock,"请选择药品分类");
        rvInfo.setLayoutManager(linearLayoutManager);
        rvInfo.setAdapter(adapter1);
        adapter1.setOnItemClickListener(this);
    }
    
    

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.tvMedicineType:
                getMedicineOneLevel();
                break;
            case R.id.tvSure:
                if (popupWindow==null) return;
                if (popupWindow.isShowing())
                    popupWindow.dismiss();
                break;
        }
    }

    private void getMedicineOneLevel() {
        HttpInterface service = Constant.retrofit.create(HttpInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");
        retrofit2.Call<MedicineOneLevel> call = service.getMedicineOneLevel(body);
        call.enqueue(new Callback<MedicineOneLevel>() {
            @Override
            public void onResponse(retrofit2.Call<MedicineOneLevel> call, Response<MedicineOneLevel> response) {
                list.clear();
                MedicineOneLevel level=response.body();
                initData();
                if (!popupWindow.isShowing())
                    show(popupWindow);
                list.addAll(level.getList());
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<MedicineOneLevel> call, Throwable t) {
                Toast.makeText(StockMedicineReportActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getReportData(String id)
    {
        final Map<String,Object> map=new HashMap<>();
        map.put("typeid",id);
        String json=DataUtils.toJson(map);
        HttpInterface service = Constant.retrofit.create(HttpInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofit2.Call<Report> call = service.getReportData(body);
        call.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(retrofit2.Call<Report> call, Response<Report> response) {
                report=response.body();
                initDataResource(report);
                BarChartUtils.initBarChart(barchart,x,maxY,10);
                BarData barData =BarChartUtils.showBarChat(y,"库存数量",StockMedicineReportActivity.this.getResources().getColor(R.color.colorAccent));
//                BarDataSet barDataSet =BarChartUtils.setBarDataSetResource(y,"库存数量");
//                BarChartUtils.initBarDataSet(barDataSet,StockMedicineReportActivity.this.getResources().getColor(R.color.colorAccent));
                barchart.setData(barData);
//                Map<String,List<? extends Value>> map1=new HashMap<>();
//                map1.put("库存数量",y);
//                BarChartUtils.showBarChat(new int[]{R.color.colorAccent},map1);
            }

            @Override
            public void onFailure(retrofit2.Call<Report> call, Throwable t) {
                Toast.makeText(StockMedicineReportActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private PopupWindow creatPop(int resid, String title)
    {
        View view=LayoutInflater.from(this).inflate(resid,null,false);
        PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,500);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.style.popwindow);
        tvPopTitle=view.findViewById(R.id.tvPopTitle);
        tvSure=view.findViewById(R.id.tvSure);
        rvInfo=view.findViewById(R.id.rvInfo);
        tvSure.setOnClickListener(this);
        //        rlBottom.setVisibility(View.GONE);

        return popupWindow;
    }

    private void show(PopupWindow popupWindow)
    {
        popupWindow.showAtLocation(findViewById(android.R.id.content),Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        for (int i = 0; i < list.size(); i++) {
            if (i==position)
            {
                list.get(position).setCheck(true);
                continue;
            }
            list.get(i).setCheck(false);
        }
        getReportData(list.get(position).getId());
        tvMedicineType.setText(list.get(position).getName());
        tvMedicineType.setTag(list.get(position).getId());
    }

    /**
     * "[['非处方药',150.00],['中药饮片',0.00],['中药材',0.00],['医疗器材',0.00],['处方药',79.00]]"
     * @param report 解析该类型的数据源并初始化X,Y的list
     */
    private void initDataResource(Report report)
    {
        x.clear();
        y.clear();
        String newstr;
        newstr=report.getList().replaceAll("\\[","");
        newstr=newstr.replaceAll("]","");
        newstr=newstr.replaceAll("'","");
        String[] newstrs=newstr.split(",");
        for (int i = 0; i <newstrs.length ; i++) {
            if (i%2==0)
            {
                x.add(newstrs[i]);

            }else
            {
                float temp=Float.valueOf(newstrs[i]);
                maxY = Math.max(maxY, temp);
                Y y = new Y();
                y.setValue(newstrs[i]);
                this.y.add(y);
            }
        }
//        for (String str:newstrs
//                ) {
//          try {
//              float temp=Float.valueOf(str);
//              maxY=Math.max(maxY,temp);
//              Y y=new Y();
//              y.setValue(str);
//              this.y.add(y);
//          }catch (NumberFormatException ex)
//          {
//              x.add(str);
//          }
//        }
    }
}
