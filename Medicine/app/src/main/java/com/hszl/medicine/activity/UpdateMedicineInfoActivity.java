package com.hszl.medicine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.adapter.DosageFormAdapter;
import com.hszl.medicine.adapter.UnitAdapter;
import com.hszl.medicine.entity.DosageForm;
import com.hszl.medicine.entity.Medicine;
import com.hszl.medicine.entity.MedicineLevel;
import com.hszl.medicine.entity.Unit;
import com.hszl.medicine.entity.Update;
import com.hszl.medicine.http.HttpInterface;
import com.hszl.medicine.utils.Constant;
import com.hszl.medicine.utils.DataUtils;
import com.hszl.medicine.utils.ImmersionUtils;
import com.skateboard.zxinglib.CaptureActivity;

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

public class UpdateMedicineInfoActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    Medicine.ListBean bean;
    String tag;
    EditText etType,etCode,etBarcode,etName,etGenericNames,
            etSpecification,etDosageForm,etOrigin,etManufacturer,etApprovalNumber,etUnit,etJianPin;
    ImageView imgBarcode;
    SwitchView svStatus;
    Button btnSave;


    PopupWindow df_window,u_window;
    TextView tvPopTitle,tvSure;
    RecyclerView rvInfo;
    LinearLayoutManager df_linearLayoutManager,u_linearLayoutManager;
    DosageFormAdapter dosageFormAdapter;
    UnitAdapter unitAdapter;
    List<DosageForm.ListBean> list1=new ArrayList<>();
    List<Unit.ListBean> list2=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_update_medicine_info);
        ImmersionUtils.immersionTop(this,rlTop);
        showTitle("更新药品信息");
        hideRightText();
        hideBottom();
        initView();
        initDate();
    }

    @Override
    public boolean validation() {
        if (DataUtils.isEmpty(etType.getText()))
        {
            Toast.makeText(this,"请选择类型",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (DataUtils.isEmpty(etCode.getText()))
        {
            Toast.makeText(this,"请输入编码",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (DataUtils.isEmpty(etBarcode.getText()))
        {
            Toast.makeText(this,"请输入药品条码",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (DataUtils.isEmpty(etName.getText()))
        {
            Toast.makeText(this,"请输入药品名称",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (DataUtils.isEmpty(etGenericNames.getText()))
        {
            Toast.makeText(this,"请输入通用名称",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (DataUtils.isEmpty(etSpecification.getText()))
        {
            Toast.makeText(this,"请输入规格",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (DataUtils.isEmpty(etDosageForm.getText()))
        {
            Toast.makeText(this,"请选择剂型",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (DataUtils.isEmpty(etOrigin.getText()))
        {
            Toast.makeText(this,"请输入产地",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (DataUtils.isEmpty(etManufacturer.getText()))
        {
            Toast.makeText(this,"请输入生产厂家",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (DataUtils.isEmpty(etApprovalNumber.getText()))
        {
            Toast.makeText(this,"请输入批准文号",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (DataUtils.isEmpty(etUnit.getText()))
        {
            Toast.makeText(this,"请选择单位",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (DataUtils.isEmpty(etJianPin.getText()))
        {
            Toast.makeText(this,"请输入简拼",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void initView() {
        etType=findViewById(R.id.etType);
        etCode=findViewById(R.id.etCode);
        etBarcode=findViewById(R.id.etBarcode);
        etGenericNames=findViewById(R.id.etGenericNames);
        etSpecification=findViewById(R.id.etSpecification);
        etDosageForm=findViewById(R.id.etDosageForm);
        etOrigin=findViewById(R.id.etOrigin);
        etName=findViewById(R.id.etName);
        etManufacturer=findViewById(R.id.etManufacturer);
        etApprovalNumber=findViewById(R.id.etApprovalNumber);
        etUnit=findViewById(R.id.etUnit);
        etJianPin=findViewById(R.id.etJianPin);
        imgBarcode=findViewById(R.id.imgBarcode);
        svStatus=findViewById(R.id.svStatus);
        btnSave=findViewById(R.id.btnSave);
        imgBarcode.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        etDosageForm.setOnClickListener(this);
        etUnit.setOnClickListener(this);
        etType.setOnClickListener(this);
        df_linearLayoutManager=new LinearLayoutManager(this);
        u_linearLayoutManager=new LinearLayoutManager(this);
        dosageFormAdapter=new DosageFormAdapter(R.layout.pop_stock_list_item,list1);
        unitAdapter=new UnitAdapter(R.layout.pop_stock_list_item,list2);
        unitAdapter.setOnItemClickListener(this);
        dosageFormAdapter.setOnItemClickListener(this);
    }

    private void initDate() {
        tag=getIntent().getStringExtra("tag");
        if (tag.equals("add"))
        {
            etType.setText(getIntent().getStringExtra("type"));
            etType.setTag(getIntent().getStringExtra("typeid"));
        }else if (tag.equals("update"))
        {
            bean= (Medicine.ListBean) getIntent().getSerializableExtra("medicine");
            etType.setText(bean.getDrugCategoryName());
            etType.setTag(bean.getDrugCategoryID());
            etCode.setText(bean.getDrugCode());
            etBarcode.setText(bean.getBarCode());
            etName.setText(bean.getDrugName());
            etGenericNames.setText(bean.getCommonName());
            etSpecification.setText(bean.getSpecs());
            etDosageForm.setText(bean.getDosageFormName());
            etDosageForm.setTag(bean.getDosageForm());
            etOrigin.setText(bean.getOrigin());
            etManufacturer.setText(bean.getManufacturers());
            etApprovalNumber.setText(bean.getApprovedNumber());
            etUnit.setText(bean.getUnitName());
            etUnit.setTag(bean.getUnit());
            etJianPin.setText(bean.getSimpleSpell());
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent=new Intent();
        switch (v.getId())
        {
            case R.id.btnSave:
                saveMedicineInfo();
                break;
            case R.id.imgBarcode:
                intent.setClass(this,CaptureActivity.class);
                startActivityForResult(intent,1001);
                break;
            case R.id.etUnit:
                getUnit();
                break;
            case R.id.etDosageForm:
                getDosageForm();
                break;
            case R.id.tvSure:
                if (!validation())return;
                if (u_window!=null&&u_window.isShowing())
                    u_window.dismiss();
                if (df_window!=null&&df_window.isShowing())
                    df_window.dismiss();
                break;
            case R.id.etType:
                intent.setClass(this,ChooseMedicineTypeActivity.class);
                startActivityForResult(intent,1000);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1001&&resultCode==RESULT_OK)
        {
            String code=data.getStringExtra(CaptureActivity.KEY_DATA);
            etBarcode.setText(code);
        }else if (requestCode==1000&&resultCode==RESULT_OK)
        {
            MedicineLevel.ListBean medicineLevel = (MedicineLevel.ListBean) data.getSerializableExtra("MedicineLevel");
            etType.setText(medicineLevel.getName());
            etType.setTag(medicineLevel.getId());
        }
    }

    private PopupWindow creatPop(int resid, String title, RecyclerView.Adapter adapter)
    {
        View view=LayoutInflater.from(this).inflate(resid,null,false);
        PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,500);
        tvPopTitle=view.findViewById(R.id.tvPopTitle);
        tvSure=view.findViewById(R.id.tvSure);
        rvInfo=view.findViewById(R.id.rvInfo);
        tvPopTitle.setText(title);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvInfo.setLayoutManager(linearLayoutManager);
        rvInfo.setAdapter(adapter);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.style.popwindow);
        tvSure.setOnClickListener(this);
        return popupWindow;
    }

    private void showPop(PopupWindow popupWindow)
    {
        popupWindow.showAtLocation(findViewById(android.R.id.content),Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
    }

    private void getDosageForm()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("Code","DosageForm");
        String json=DataUtils.toJson(map);
        HttpInterface service=Constant.retrofit.create(HttpInterface.class);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        Call<DosageForm> call=service.getDosageForm(body);
        call.enqueue(new Callback<DosageForm>() {
            @Override
            public void onResponse(Call<DosageForm> call, Response<DosageForm> response) {
                if (df_window==null)
                    df_window=creatPop(R.layout.pop_item_stock,"选择单位",dosageFormAdapter);
                if (!df_window.isShowing())
                    showPop(df_window);
                DosageForm dosageForm=response.body();
                list1.clear();
                list1.addAll(dosageForm.getList());
                dosageFormAdapter.notifyDataSetChanged();
                UpdateMedicineInfoActivity.this.finish();
            }

            @Override
            public void onFailure(Call<DosageForm> call, Throwable t) {
                Toast.makeText(UpdateMedicineInfoActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUnit()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("Code","unit");
        String json=DataUtils.toJson(map);
        HttpInterface service=Constant.retrofit.create(HttpInterface.class);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        Call<Unit> call=service.getUnit(body);
        call.enqueue(new Callback<Unit>() {
            @Override
            public void onResponse(Call<Unit> call, Response<Unit> response) {
                if (u_window==null)
                    u_window=creatPop(R.layout.pop_item_stock,"选择单位",unitAdapter);
                if (!u_window.isShowing())
                    showPop(u_window);
                Unit unit=response.body();
                list2.clear();
                list2.addAll(unit.getList());
                unitAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Unit> call, Throwable t) {
                Toast.makeText(UpdateMedicineInfoActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter instanceof DosageFormAdapter) {
            etDosageForm.setText(list1.get(position).getTitle());
            etDosageForm.setTag(list1.get(position).getId());
            for (int i = 0; i < list1.size(); i++) {
                if (i == position) {
                    list1.get(i).setCheck(true);
                    continue;
                } else
                    list1.get(i).setCheck(false);
            }
            dosageFormAdapter.notifyDataSetChanged();
        } else if (adapter instanceof UnitAdapter) {
            etUnit.setText(list2.get(position).getTitle());
            etUnit.setTag(list2.get(position).getId());
            for (int i = 0; i < list2.size(); i++) {
                if (i == position) {
                    list2.get(i).setCheck(true);
                    continue;
                } else
                    list2.get(i).setCheck(false);
            }
            unitAdapter.notifyDataSetChanged();
        }
    }

    private void saveMedicineInfo()
    {
        Map<String,Object> map=new HashMap<>();
        if (tag.equals("add")) {
            map.put("keyValue", "");
        } else {
            map.put("keyValue", bean.getId());
        }
        map.put("DrugCategoryID",etType.getTag().toString());
        map.put("DrugCode",etCode.getText().toString());
        map.put("DrugName",etName.getText().toString());
        map.put("BarCode",etBarcode.getText().toString());
        map.put("CommonName",etGenericNames.getText().toString());
        map.put("Specs",etSpecification.getText().toString());
        map.put("DosageForm",etDosageForm.getTag().toString());
        map.put("Origin",etOrigin.getText().toString());
        map.put("Manufacturers",etManufacturer.getText().toString());
        map.put("ApprovedNumber",etApprovalNumber.getText().toString());
        map.put("Unit",etUnit.getTag().toString());
        map.put("Status",svStatus.isOpened()?1:0);
        String json=DataUtils.toJson(map);
        HttpInterface service=Constant.retrofit.create(HttpInterface.class);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        Call<Update> call=service.saveMedicineInfo(body);
        call.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(Call<Update> call, Response<Update> response) {
                Update update
                        =response.body();
                if (update.getSuccess()==1) {
                    Toast.makeText(UpdateMedicineInfoActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    UpdateMedicineInfoActivity.this.finish();
                }else
                {
                    Toast.makeText(UpdateMedicineInfoActivity.this, update.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Update> call, Throwable t) {
                Toast.makeText(UpdateMedicineInfoActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
