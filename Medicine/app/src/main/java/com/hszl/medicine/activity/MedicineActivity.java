package com.hszl.medicine.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.MainActivity;
import com.hszl.medicine.R;
import com.hszl.medicine.adapter.MedicineAdapter;
import com.hszl.medicine.entity.Medicine;
import com.hszl.medicine.entity.MedicineList;
import com.hszl.medicine.entity.StockIn;
import com.hszl.medicine.entity.User;
import com.hszl.medicine.http.HttpInterface;
import com.hszl.medicine.utils.Constant;
import com.hszl.medicine.utils.DataUtils;
import com.hszl.medicine.utils.ImmersionUtils;
import com.hszl.medicine.view.MyImage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicineActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener,
        BaseQuickAdapter.RequestLoadMoreListener ,SwipeRefreshLayout.OnRefreshListener {

    StockIn stockIn;
    SwipeRefreshLayout srl;
    EditText etSearch;
    RecyclerView rvMedicine;
    MyImage imgShopCart;
    Button btnCommit;
    LinearLayoutManager linearLayoutManager;
    MedicineAdapter medicineAdapter;
    List<Medicine.ListBean> listBeans=new ArrayList<>();
    PopupWindow popupWindow;
    boolean isLoadMore=false;
    int pageSize=10;
    int pageNum=1;
    int circleNum=0;
    Medicine.ListBean bean=new Medicine.ListBean();
    List<MedicineList> medicineLists=new ArrayList<>();

    TextView tvPopTitle;
    EditText etBactch;
    EditText etProductionData;
    EditText etValidity;
    EditText etUnitPrice;
    EditText etNum;
    TextView etTotalPrice;
    TextView tvClose;
    Button btnCancel;
    Button btnSure;
    EditText etRetailPrice;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_medicine);
        ImmersionUtils.immersionTop(this,rlTop);
        stockIn= (StockIn) getIntent().getSerializableExtra("stockin");
        hideBottom();
        showTitle("选择药品");
        hideRightText();
        initView();
        getMedicine(pageSize,pageNum,etSearch.getText().toString());
    }

    private void initView() {
        btnSearch=findViewById(R.id.btnSearch);
        srl=findViewById(R.id.srl);
        etSearch=findViewById(R.id.etSearch);
        rvMedicine=findViewById(R.id.rvMedicine);
        imgShopCart=findViewById(R.id.imgShopCart);
        btnCommit=findViewById(R.id.btnCommit);
        linearLayoutManager=new LinearLayoutManager(this);
        medicineAdapter=new MedicineAdapter(R.layout.item_goods,listBeans);
        medicineAdapter.setOnLoadMoreListener(this,rvMedicine);
        rvMedicine.setLayoutManager(linearLayoutManager);
        rvMedicine.setAdapter(medicineAdapter);
        medicineAdapter.setOnItemChildClickListener(this);
        imgShopCart.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
        srl.setColorSchemeColors(Color.BLUE);
        srl.setOnRefreshListener(this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public boolean validation() {
        if (DataUtils.isEmpty(etBactch.getText()))
        {
            Toast.makeText(this,"请输入生产批次",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etProductionData.getText()))
        {
            Toast.makeText(this,"请选择生产日期",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etValidity.getText()))
        {
            Toast.makeText(this,"请选择有效期",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etUnitPrice.getText()))
        {
            Toast.makeText(this,"请输入单价",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etRetailPrice.getText()))
        {
            Toast.makeText(this,"请输入零售价",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etNum.getText()))
        {
            Toast.makeText(this,"请输入入库数量",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Calendar calendar=Calendar.getInstance();
        Intent intent=new Intent();
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.imgShopCart:
                intent.setClass(this,MedicineListActivity.class);
                intent.putExtra("list", (Serializable) medicineLists);
                startActivity(intent);
                break;
            case R.id.btnCommit:

                intent.putExtra("stockin",stockIn);
                setResult(RESULT_OK,intent);
                this.finish();
                break;
            case R.id.etProductionData:                            ;
                DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etProductionData.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
                dialog.show();
                break;
            case R.id.etValidity:
                DatePickerDialog dialog1=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etValidity.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
                dialog1.show();
                break;
            case R.id.tvClose:
                if (popupWindow!=null)
                    popupWindow.dismiss();
                break;
            case R.id.btnCancel:
                popupWindow.dismiss();
                break;
            case R.id.btnSure:
                if (!validation()) return;
                /**蛋疼的数据结构UML建模不行吗**/
                /**往上级页面的对象里赋值开始**/
                StockIn.ListBean listBean=new StockIn.ListBean();
                listBean.setDrugID(bean.getId());
                listBean.setProductionNumber(etBactch.getText().toString());
                listBean.setProductionDate(etProductionData.getText().toString());
                listBean.setValidityDate(etValidity.getText().toString());
                listBean.setDrugCount(etNum.getText().toString());
                listBean.setPrice(etUnitPrice.getText().toString());
                if (stockIn.getList()!=null)stockIn.getList().add(listBean);
                else {
                    List<StockIn.ListBean> listBean1 = new ArrayList<>();
                    stockIn.setList(listBean1);
                    stockIn.getList().add(listBean);
                }
                /**往上级页面赋值结束**/
                /**设置下级界面数据结构开始**/
                MedicineList ml=new MedicineList();
                ml.setName(bean.getDrugName());
                ml.setBactch(etBactch.getText().toString());
                ml.setBarcode(bean.getBarCode());
                ml.setCount(etNum.getText().toString());
                ml.setProductDate(etProductionData.getText().toString());
                ml.setValidity(etValidity.getText().toString());
                ml.setUnitPrice(etUnitPrice.getText().toString());
                medicineLists.add(ml);
                /**设置下级界面数据结构结束**/
                circleNum++;
                imgShopCart.setCircleText(String.valueOf(circleNum));
                popupWindow.dismiss();
                break;
            case R.id.tvLeft:
                setResult(100);
                this.finish();
                break;
            case R.id.btnSearch:
                getMedicine(pageSize,pageNum,etSearch.getText().toString());
                break;
        }
    }

    private PopupWindow creatPop(int resid, String title)
    {
        View view=LayoutInflater.from(this).inflate(R.layout.pop_item_medicine,null,false);
        PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.popwindow);
        btnSure=view.findViewById(R.id.btnSure);
        btnCancel=view.findViewById(R.id.btnCancel);
        tvClose=view.findViewById(R.id.tvClose);
        tvPopTitle=view.findViewById(R.id.tvPopTitle);
        etBactch=view.findViewById(R.id.etBactch);
        etProductionData=view.findViewById(R.id.etProductionData);
        etRetailPrice=view.findViewById(R.id.etRetailPrice);
        etValidity=view.findViewById(R.id.etValidity);
        etUnitPrice=view.findViewById(R.id.etUnitPrice);
        etNum=view.findViewById(R.id.etNum);
        etTotalPrice=view.findViewById(R.id.etTotalPrice);
        etProductionData.setOnClickListener(this);
        etValidity.setOnClickListener(this);
        tvClose.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        etUnitPrice.addTextChangedListener(textWatcher);
        etNum.addTextChangedListener(textWatcher1);
        return popupWindow;
    }

    private void showPop(PopupWindow popupWindow)
    {
        popupWindow.showAtLocation(findViewById(android.R.id.content),Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
    }

    /**
     * 获取药品信息 pagesize每页的行数pagenumber页数
     */
    private void getMedicine(int pageSize,int pageNum,String keyValue)
    {
        Map<String,Object> map=new HashMap<>();
        map.put("pagesize", String.valueOf(pageSize));
        map.put("pagenumber", String.valueOf(pageNum));
        map.put("keyValue",keyValue);
        map.put("drugtypeid","");
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
                    listBeans.clear();
                listBeans.addAll(medicine.getList());
                medicineAdapter.notifyDataSetChanged();
                if (srl.isRefreshing())
                    srl.setRefreshing(false);
                medicineAdapter.loadMoreEnd();
            }

            @Override
            public void onFailure(Call<Medicine> call, Throwable t) {
                if (srl.isRefreshing())
                    srl.setRefreshing(false);
                Log.e("user",t.toString());
            }
        });
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        String code=listBeans.get(position).getDrugCode();
        bean=listBeans.get(position);
        if (popupWindow==null)
            popupWindow=creatPop(R.layout.pop_item_medicine,code);
        if (!popupWindow.isShowing())
            showPop(popupWindow);
        tvPopTitle.setText(code);
    }

    @Override
    public void onLoadMoreRequested() {
        isLoadMore=true;
        pageNum++;
        getMedicine(pageSize,pageNum,etSearch.getText().toString());
    }

    @Override
    public void onRefresh() {
        isLoadMore=false;
        pageNum=1;
        getMedicine(pageSize,pageNum,etSearch.getText().toString());
    }

    TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (DataUtils.isEmpty(etUnitPrice.getText())||DataUtils.isEmpty(etNum.getText())) return;
            else
            {
                int unitPrice=Integer.valueOf(s.toString());
                int num=Integer.valueOf(etNum.getText().toString());
                etTotalPrice.setText(String.valueOf(unitPrice*num));
            }
        }
    };

    TextWatcher textWatcher1=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (DataUtils.isEmpty(etUnitPrice.getText())||DataUtils.isEmpty(etNum.getText())) return;
            else
            {
                int num =Integer.valueOf(s.toString());
                int unitPrice=Integer.valueOf(etUnitPrice.getText().toString());
                etTotalPrice.setText(String.valueOf(unitPrice*num));
            }
        }
    };
}
