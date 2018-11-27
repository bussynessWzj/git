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
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.Stock;
import com.hszl.medicine.entity.Update;
import com.hszl.medicine.http.HttpInterface;
import com.hszl.medicine.utils.Constant;
import com.hszl.medicine.utils.DataUtils;
import com.hszl.medicine.utils.DateUtils;
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

public class UpdateStockActivity extends BaseActivity implements SwitchView.OnStateChangedListener{

    String tag;
    Stock.ListBean listBean;
    EditText etName,etCode,etAddress,etPhoneNum,etContact;
    SwitchView svStatus;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_update_stock);
        ImmersionUtils.immersionTop(this,rlTop);
        hideBottom();
        showTitle("更新仓库信息");
        hideRightText();
        initView();
        tag=getIntent().getStringExtra("tag");
        if (tag.equals("add"))
        {

        }else if (tag.equals("update"))
        {
            listBean= (Stock.ListBean) getIntent().getSerializableExtra("Stock.ListBean");
            etName.setText(listBean.getName());
            etCode.setText(listBean.getCode());
            etAddress.setText(listBean.getAddress());
            etContact.setText(listBean.getContact());
            etPhoneNum.setText(listBean.getTel());
            if (listBean.getStatus()==0)
                svStatus.setOpened(false);
            else
                svStatus.setOpened(true);
        }
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
        }else if (DataUtils.isEmpty(etAddress.getText()))
        {
            Toast.makeText(this,"请输入地址",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etPhoneNum.getText()))
        {
            Toast.makeText(this,"请输入电话",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etContact.getText()))
        {
            Toast.makeText(this,"请输入联系人",Toast.LENGTH_SHORT).show();
            return false;
        }else if (!DataUtils.isPhoneNum(etPhoneNum.getText().toString()))
        {
            Toast.makeText(this,"手机号码输入不正确",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        etName=findViewById(R.id.etName);
        etCode=findViewById(R.id.etCode);
        etAddress=findViewById(R.id.etAddress);
        etPhoneNum=findViewById(R.id.etPhoneNum);
        etContact=findViewById(R.id.etContact);
        svStatus=findViewById(R.id.svStatus);
        btnSave=findViewById(R.id.btnSave);
        svStatus.setOnStateChangedListener(this);
        btnSave.setOnClickListener(this);
    }


    @Override
    public void toggleToOn(SwitchView view) {
        view.setOpened(true);
    }

    @Override
    public void toggleToOff(SwitchView view) {
        view.setOpened(false);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.btnSave:
                if (!validation()) return;
                updateStock();
                break;
        }
    }

    private void updateStock()
        {
            Map<String,Object> map=new HashMap<>();
            if (tag.equals("add"))map.put("keyValue","");
            else map.put("keyValue",listBean.getId());
            map.put("Name",etName.getText().toString());
            map.put("Code",etCode.getText().toString());
            map.put("Address",etAddress.getText().toString());
            map.put("Tel",etPhoneNum.getText().toString());
            map.put("Contact",etContact.getText().toString());
            map.put("Status",svStatus.isOpened()?1:0);
            String json=DataUtils.toJson(map);
            HttpInterface service=Constant.retrofit.create(HttpInterface.class);
            RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
            Call<Update> call=service.updateStock(body);
            call.enqueue(new Callback<Update>() {
                @Override
                public void onResponse(Call<Update> call, Response<Update> response) {
                    Update update
                            =response.body();
                    if (update.getSuccess()==1) {
                        Toast.makeText(UpdateStockActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        UpdateStockActivity.this.finish();
                    }else
                    {
                        Toast.makeText(UpdateStockActivity.this, update.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Update> call, Throwable t) {
                    Toast.makeText(UpdateStockActivity.this,"提交失败",Toast.LENGTH_SHORT).show();
                }
            });
    }
}
