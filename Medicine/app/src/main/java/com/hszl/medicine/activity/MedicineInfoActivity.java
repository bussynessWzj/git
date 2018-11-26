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
import com.hszl.medicine.adapter.CopyMedicineAdapter;
import com.hszl.medicine.adapter.CopyMedicineOneLevelAdapter;
import com.hszl.medicine.adapter.CopyMedicineTwoLevelAdapter;
import com.hszl.medicine.entity.Medicine;
import com.hszl.medicine.entity.MedicineOneLevel;
import com.hszl.medicine.entity.MedicineTwoLevel;
import com.hszl.medicine.http.HttpInterface;
import com.hszl.medicine.utils.Constant;
import com.hszl.medicine.utils.DataUtils;
import com.hszl.medicine.utils.ImmersionUtils;

import java.io.Serializable;
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

public class MedicineInfoActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener ,
        SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {

    Button btnSearch;
    SwipeRefreshLayout srl;
    FloatingActionButton fab;
    EditText etSearch;
    RecyclerView rvStockInfo;
    LinearLayoutManager linearLayoutManager;
    CopyMedicineAdapter adapter;
    List<Medicine.ListBean> list=new ArrayList<>();
    TextView tvLevelOne,tvLevelTwo;
    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);

    List<MedicineOneLevel.ListBean> list1 = new ArrayList<>();
    List<MedicineTwoLevel.ListBean> list2 = new ArrayList<>();

    CopyMedicineOneLevelAdapter adapter1;
    CopyMedicineTwoLevelAdapter adapter2;
    TextView tvPopTitle;
    TextView tvSure;
    RecyclerView rvInfo;
    PopupWindow popupWindow;
    MedicineOneLevel.ListBean listBean1;
    MedicineTwoLevel.ListBean listBean2;
    Medicine.ListBean listBean;


    int pageSize=10;
    int pageNum=1;
    boolean isLoadMore=false;
    TextView tvEditor,tvDelete,tvCancel;
    PopupWindow editorWindow;

    private void initList()
    {
        MedicineOneLevel.ListBean bean1=new MedicineOneLevel.ListBean();
        bean1.setName("所有分类");
        list1.add(bean1);
        MedicineTwoLevel.ListBean bean2=new MedicineTwoLevel.ListBean();
        bean2.setName("所有子类");
        list2.add(bean2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_medicine_info);
        ImmersionUtils.immersionTop(this,rlTop);
        showTitle("药品信息");
        hideRightText();
        initView();
        hideBottom();
        getMedicine(pageSize,pageNum,"",etSearch.getText().toString());
    }

    @Override
    public boolean validation() {
        if (listBean1==null&&listBean2==null)
        {
            Toast.makeText(this,"请选择药品类型",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        btnSearch=findViewById(R.id.btnSearch);
        srl=findViewById(R.id.srl);
        fab=findViewById(R.id.fab);
        etSearch=findViewById(R.id.etSearch);
        rvStockInfo=findViewById(R.id.rvStockInfo);
        tvLevelOne=findViewById(R.id.tvLevelOne);
        tvLevelTwo=findViewById(R.id.tvLevelTwo);
        linearLayoutManager=new LinearLayoutManager(this);
        adapter=new CopyMedicineAdapter(R.layout.item_medicine,list);
        adapter.setOnItemClickListener(this);
        rvStockInfo.setLayoutManager(linearLayoutManager);
        rvStockInfo.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this);
        fab.setOnClickListener(this);
        srl.setColorSchemeColors(Color.BLUE);
        srl.setOnRefreshListener(this);
        adapter1=new CopyMedicineOneLevelAdapter(R.layout.pop_stock_list_item,list1);
        adapter2=new CopyMedicineTwoLevelAdapter(R.layout.pop_stock_list_item,list2);
        adapter1.setOnItemClickListener(this);
        adapter2.setOnItemClickListener(this);
        tvLevelOne.setOnClickListener(this);
        tvLevelTwo.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.fab:
                if (tvLevelTwo.getText().equals("所有子类"))
                    Toast.makeText(this,"请选择药品分类",Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent();
                    intent.setClass(this,UpdateMedicineInfoActivity.class);
                    intent.putExtra("type",tvLevelTwo.getText().toString());
                    intent.putExtra("typeid",tvLevelTwo.getTag().toString());
                    intent.putExtra("tag","add");
                    this.startActivity(intent);
                }
                break;
            case R.id.tvLevelOne:
                getMedicineOneLevel();
                break;//显示一级分类
            case R.id.tvLevelTwo:
                if (!validation())return;
                getMedicineTwoLevel(listBean1.getId());
                break;//显示二级分类
            case R.id.tvSure:
                if (popupWindow==null) return;
                if (popupWindow.isShowing())
                    popupWindow.dismiss();
                break;
            case R.id.tvCancel:
                if (editorWindow==null) return;
                if (editorWindow.isShowing())
                    editorWindow.dismiss();
                break;
            case R.id.tvDelete:
                //删除数据源并更新数据源
                deleteMedicine(listBean);
                break;
            case R.id.tvEditor:
                if (!validation()) return;
                Intent intent=new Intent();
                intent.setClass(this,UpdateMedicineInfoActivity.class);
                intent.putExtra("medicine",  listBean);
                intent.putExtra("tag","update");
                startActivity(intent);
                break;
            case R.id.btnSearch:
                if (listBean2!=null&&!DataUtils.isEmpty(listBean2.getId()))
                    getMedicine(pageSize,pageNum,listBean2.getId(),etSearch.getText().toString());
                else if (listBean1!=null&&!DataUtils.isEmpty(listBean1.getId()))
                    getMedicine(pageSize,pageNum,listBean1.getId(),etSearch.getText().toString());
                else
                    getMedicine(pageSize,pageNum,"",etSearch.getText().toString());
                break;
        }
    }

    private PopupWindow creatPopEditor(int resid,String title)
    {
        View view=LayoutInflater.from(this).inflate(resid,null,false);
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

    private PopupWindow creatPop(int resid, String title)
    {
        View view=LayoutInflater.from(this).inflate(resid,null,false);
        PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,500);
        //        tvPopTitle=view.findViewById(R.id.tvPopTitle);
        //        tvSure=view.findViewById(R.id.tvSure);
        //        rvInfo=view.findViewById(R.id.rvInfo);
        //        tvPopTitle.setText(title);
        //        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        //        rvInfo.setLayoutManager(linearLayoutManager);
        //        rvInfo.setAdapter(adapter);
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

    private void getMedicineOneLevel() {
        HttpInterface service = Constant.retrofit.create(HttpInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");
        retrofit2.Call<MedicineOneLevel> call = service.getMedicineOneLevel(body);
        call.enqueue(new Callback<MedicineOneLevel>() {
            @Override
            public void onResponse(retrofit2.Call<MedicineOneLevel> call, Response<MedicineOneLevel> response) {
                list1.clear();
                initList();
                MedicineOneLevel level=response.body();
                list1.addAll(level.getList());
                if (popupWindow==null)
                    popupWindow=creatPop(R.layout.pop_item_stock,"请选择一级分类");
                if (!popupWindow.isShowing())
                    show(popupWindow);
                rvInfo.setAdapter(adapter1);
                rvInfo.setLayoutManager(linearLayoutManager1);
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<MedicineOneLevel> call, Throwable t) {
                Toast.makeText(MedicineInfoActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
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
                initList();
                MedicineTwoLevel level=response.body();
                list2.addAll(level.getList());
                if (popupWindow==null)
                    popupWindow=creatPop(R.layout.pop_item_stock,"请选择二级分类");
                if (!popupWindow.isShowing())
                    show(popupWindow);
                rvInfo.setAdapter(adapter2);
                rvInfo.setLayoutManager(linearLayoutManager2);
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<MedicineTwoLevel> call, Throwable t) {
                Toast.makeText(MedicineInfoActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter instanceof CopyMedicineOneLevelAdapter)
        {
            listBean1=list1.get(position);
            for (int i = 0; i < list1.size(); i++) {
                if (i==position) {
                    list1.get(i).setCheck(true);
                    continue;
                }
                list1.get(i).setCheck(false);
            }
            tvLevelOne.setText(list1.get(position).getName());
            tvLevelOne.setTag(list1.get(position).getId());
            adapter1.notifyDataSetChanged();
            getMedicine(pageSize,pageNum,listBean1.getId(),etSearch.getText().toString());
        }else if (adapter instanceof CopyMedicineTwoLevelAdapter){
            listBean2=list2.get(position);
            for (int i = 0; i < list2.size(); i++) {
                if (i==position) {
                    list2.get(i).setCheck(true);
                    continue;
                }
                list2.get(i).setCheck(false);
            }
            tvLevelTwo.setText(list2.get(position).getName());
            tvLevelTwo.setTag(list2.get(position).getId());
            adapter2.notifyDataSetChanged();
            getMedicine(pageSize,pageNum,listBean2.getId(),etSearch.getText().toString());
        }else if (adapter instanceof CopyMedicineAdapter){
            listBean=list.get(position);
            if (editorWindow==null)
                editorWindow=creatPopEditor(R.layout.pop_item_operation,"");
            if (!editorWindow.isShowing())
                show(editorWindow);

        }
    }

    /**
     * 获取药品信息 pagesize每页的行数pagenumber页数
     */
    private void getMedicine(int pageSize,int pageNum,String id,String keyValue)
    {
        Map<String,Object> map=new HashMap<>();
        map.put("pagesize", String.valueOf(pageSize));
        map.put("pagenumber", String.valueOf(pageNum));
        map.put("keyValue",keyValue);
        map.put("drugtypeid",id);
        String json=DataUtils.toJson(map);
        HttpInterface service=Constant.retrofit.create(HttpInterface.class);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        Call<Medicine> call=service.getMedicine(body);
        call.enqueue(new Callback<Medicine>()
        {
            @Override
            public void onResponse(Call<Medicine> call, Response<Medicine> response) {
                Medicine medicine=response.body();
                if (!isLoadMore)
                    list.clear();
                list.addAll(medicine.getList());
                adapter.notifyDataSetChanged();
                if (srl.isRefreshing())
                    srl.setRefreshing(false);
                adapter.loadMoreEnd();
            }

            @Override
            public void onFailure(Call<Medicine> call, Throwable t) {
                if (srl.isRefreshing())
                    srl.setRefreshing(false);
                adapter.loadMoreEnd();
                Log.e("user",t.toString());
            }
        });
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        list.clear();
        if (listBean2!=null&&!DataUtils.isEmpty(listBean2.getId()))
            getMedicine(pageSize,pageNum,listBean2.getId(),etSearch.getText().toString());
        else if (listBean1!=null&&!DataUtils.isEmpty(listBean1.getId()))
            getMedicine(pageSize,pageNum,listBean1.getId(),etSearch.getText().toString());
        else
            getMedicine(pageSize,pageNum,"",etSearch.getText().toString());
    }

    @Override
    public void onLoadMoreRequested() {
        isLoadMore=true;
        pageNum++;
        if (listBean2!=null&&!DataUtils.isEmpty(listBean2.getId()))
            getMedicine(pageSize,pageNum,listBean2.getId(),etSearch.getText().toString());
        else if (listBean1!=null&&!DataUtils.isEmpty(listBean1.getId()))
            getMedicine(pageSize,pageNum,listBean1.getId(),etSearch.getText().toString());
        else
            getMedicine(pageSize,pageNum,"",etSearch.getText().toString());
    }

    private void deleteMedicine(final Medicine.ListBean listBean)
    {
        Map<String,Object> map=new HashMap<>();
        map.put("keyValue",listBean.getId());
        String json=DataUtils.toJson(map);
        HttpInterface service=Constant.retrofit.create(HttpInterface.class);
        RequestBody body=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json);
        Call<ResponseBody> call=service.deleteMedicineInfo(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                list.remove(listBean);
                adapter.notifyDataSetChanged();
                Toast.makeText(MedicineInfoActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
