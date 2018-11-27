package com.hszl.medicine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hszl.medicine.MainActivity;
import com.hszl.medicine.MainActivity1;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.SPUtils;
import com.hszl.medicine.entity.User;
import com.hszl.medicine.http.HttpInterface;
import com.hszl.medicine.utils.Constant;
import com.hszl.medicine.utils.DataUtils;
import com.hszl.medicine.utils.ImmersionUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;


import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    LinearLayout llTop;
    Button btnLogin;
    EditText etName,etPwd;
    String json="{\n" +
            "\t\"UserName\":\"admin\",\n" +
            "\t\"UserPwd\":\"1\"\n" +
            "}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_login);
        initView();
        hideBottom();
        ImmersionUtils.immersionTop(this,llTop);
        hideTop();
    }

    @Override
    public boolean validation()
    {
        if (DataUtils.isEmpty(etName.getText())||DataUtils.isEmpty(etPwd.getText()))
        {
            Toast.makeText(this,"请输入完整数据",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        llTop=findViewById(R.id.llTop);
        btnLogin=findViewById(R.id.btnLogin);
        etName=findViewById(R.id.etName);
        etPwd=findViewById(R.id.etPwd);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.btnLogin:
                if (validation())
                    login(etPwd.getText().toString(),etName.getText().toString());
                break;
        }
    }

    private void login(String pwd,String username)
    {
//        Gson gson=new Gson();
//        User user=new User();
//        user.setUserName(username);
//        user.setUserPwd(pwd);

//        String json=gson.toJson(user);
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl("http://10.0.3.16:8089/MobileApi/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        Map<String,Object> map=new HashMap<>();
        map.put("UserName",username);
        map.put("UserPwd",pwd);
        String json=DataUtils.toJson(map);
        HttpInterface service=Constant.retrofit.create(HttpInterface.class);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        Call<User> call=service.login(body);
        call.enqueue(new Callback<User>()
        {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user=response.body();
                if (user!=null)
                {
                    user.setLoginpwd(etPwd.getText().toString());
                    SPUtils.saveUser(user);
                    Intent intent=new Intent();
                    intent.setClass(LoginActivity.this,MainActivity1.class);
                    LoginActivity.this.startActivity(intent);
                    Log.e("user",user.getUsername());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("user",t.toString());
            }
        });
    }


    private void login1()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkGo.<String>post("http://10.0.3.16:8089/MobileApi/UserLogin")
                        .upJson(json)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                                Log.e("tag","success");
                            }

                            @Override
                            public void onError(com.lzy.okgo.model.Response<String> response) {
                                super.onError(response);
                                Log.e("tag","failed");
                            }
                        });
            }
        }).start();
    }
}
