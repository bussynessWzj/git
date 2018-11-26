package com.hszl.medicine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.SPUtils;
import com.hszl.medicine.entity.User;
import com.hszl.medicine.utils.ImmersionUtils;

public class MyActivity extends BaseActivity {

    User user;

    RelativeLayout tvUpdatePwd;
    Button btnBack;
    ImageView ivHead;
    TextView tvUserName,tvBirthday,tvPhone;

    @Override
    public boolean validation() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_me);
        initView();
    }

    private void initView() {
        tvUpdatePwd=findViewById(R.id.tvUpdatePwd);
        btnBack=findViewById(R.id.btnBack);
        ivHead=findViewById(R.id.ivHead);
        tvUserName=findViewById(R.id.tvUserName);
        tvBirthday=findViewById(R.id.tvBirthday);
        tvPhone=findViewById(R.id.tvPhone);
        user=SPUtils.getUser();
        tvUserName.setText(user.getUsername());
        tvBirthday.setText(user.getBirthday());
        tvPhone.setText(user.getMobilephone());
        Glide.with(this)
                .load(user.getImg())
                .into(ivHead);
        tvUpdatePwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent=new Intent();
        switch (v.getId())
        {
            case R.id.tvUpdatePwd:
                intent.setClass(this,UpdatePwdActivity.class);
                startActivity(intent);
                break;
        }
    }
}
