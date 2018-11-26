package com.hszl.medicine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.hszl.medicine.R;
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

public class SaveMedicineTypeActivity extends BaseActivity {

    EditText etName,etCode;
    SwitchView svStatus;
    Button btnSave;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        ImmersionUtils.immersionTop(this,rlTop);
        showTitle("更新药品类型");
        hideRightText();
        hideBottom();
        initLayout(R.layout.activity_update_supplier_type);
        initView();
        id=getIntent().getStringExtra("id");
    }

    private void initView() {
        etName=findViewById(R.id.etName);
        etCode=findViewById(R.id.etCode);
        svStatus=findViewById(R.id.svStatus);
        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
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
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.btnSave:
                if (!validation()) return;
                save();
                break;
        }
    }

    private void save()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("Code",etCode.getText().toString());
        map.put("Name",etName.getText().toString());
        map.put("ParentID",id);
        map.put("Status",svStatus.isOpened()?"1":"0");
        String json=DataUtils.toJson(map);
        HttpInterface service=Constant.retrofit.create(HttpInterface.class);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        Call<ResponseBody> call=service.AddMedicineType(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(SaveMedicineTypeActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SaveMedicineTypeActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
