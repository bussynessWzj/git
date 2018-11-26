package com.hszl.medicine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hszl.medicine.R;
import com.hszl.medicine.entity.SPUtils;
import com.hszl.medicine.entity.User;
import com.hszl.medicine.http.HttpInterface;
import com.hszl.medicine.utils.Constant;
import com.hszl.medicine.utils.DataUtils;
import com.hszl.medicine.utils.ImmersionUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePwdActivity extends BaseActivity {

    EditText etUpdatePwd;
    Button btnCommit;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_update_pwd);
        showTitle("修改个人信息");
        hideRightText();
        initView();
        user=SPUtils.getUser();
    }

    @Override
    public boolean validation() {
        if (DataUtils.isEmpty(etUpdatePwd.getText()))
        {
            Toast.makeText(this,"请输入新密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        etUpdatePwd=findViewById(R.id.etUpdatePwd);
        btnCommit=findViewById(R.id.btnCommit);
        btnCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.btnCommit:
                if (!validation()) return;
                updatePwd(etUpdatePwd.getText().toString());
                break;
        }
    }

    private void updatePwd(String pwd)
    {
        Map<String,Object> map=new HashMap<>();
        map.put("UserId",user.getId());
        map.put("OldPwd",user.getLoginpwd());
        map.put("NewPwd1",pwd);
        map.put("NewPwd2",pwd);
        String json=DataUtils.toJson(map);
        HttpInterface service=Constant.retrofit.create(HttpInterface.class);
        RequestBody body=RequestBody.create(MediaType.parse("application/json,charset=utf-8"),json);
        Call<ResponseBody> call=service.UpdatePwd(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(UpdatePwdActivity.this,"更新成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(UpdatePwdActivity.this,"更新失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
