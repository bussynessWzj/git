package com.hszl.medicine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hszl.medicine.R;
import com.hszl.medicine.entity.Supplier;
import com.hszl.medicine.entity.SupplierType;
import com.hszl.medicine.entity.Update;
import com.hszl.medicine.http.HttpInterface;
import com.hszl.medicine.utils.Constant;
import com.hszl.medicine.utils.DataUtils;
import com.hszl.medicine.utils.ImmersionUtils;

import java.util.HashMap;
import java.util.Map;

import ch.ielse.view.SwitchView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateSupplierTypeActivity extends BaseActivity implements SwitchView.OnStateChangedListener {

    String tag;
    SupplierType.ListBean listBean;
    EditText etName,etCode;
    SwitchView svStatus;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_update_supplier_type);
        ImmersionUtils.immersionTop(this,rlTop);
        initView();
        showTitle("更新供应商类型");
        hideRightText();
        hideBottom();
        tag=getIntent().getStringExtra("tag");
        if (tag.equals("add"))
        {

        }else if (tag.equals("update"))
        {
            listBean= (SupplierType.ListBean) getIntent().getSerializableExtra("SupplierType.ListBean");
            etName.setText(listBean.getName());
            etCode.setText(listBean.getCode());
            if (listBean.getStatus()==0)
                svStatus.setOpened(false);
            else
                svStatus.setOpened(true);
        }
    }

    @Override
    public boolean validation() {
        if (DataUtils.isEmpty(etName.getText())) {
            Toast.makeText(this, "请输入名称", Toast.LENGTH_SHORT).show();
            return false;
        } else if (DataUtils.isEmpty(etCode.getText()))
        {
            Toast.makeText(this,"请输入编码",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        etName=findViewById(R.id.etName);
        etCode=findViewById(R.id.etCode);
        svStatus=findViewById(R.id.svStatus);
        btnSave=findViewById(R.id.btnSave);
        svStatus.setOnStateChangedListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.btnSave:
                if (!validation()) return;
                updateSupplierType();
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

    private void updateSupplierType() {
        Map<String, Object> map = new HashMap<>();
        if (tag.equals("add"))
            map.put("keyValue", "");
        else
            map.put("keyValue", listBean.getId());
        map.put("Name", etName.getText().toString());
        map.put("Code", etCode.getText().toString());
        map.put("Status", svStatus.isOpened()?1:0);
        String json = DataUtils.toJson(map);
        HttpInterface service = Constant.retrofit.create(HttpInterface.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Call<Update> call = service.updateSupplierType(body);
        call.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(Call<Update> call, Response<Update> response) {
                Update update
                        =response.body();
                if (update.getSuccess()==1) {
                    Toast.makeText(UpdateSupplierTypeActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    UpdateSupplierTypeActivity.this.finish();
                }else
                {
                    Toast.makeText(UpdateSupplierTypeActivity.this, update.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Update> call, Throwable t) {
                Toast.makeText(UpdateSupplierTypeActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
