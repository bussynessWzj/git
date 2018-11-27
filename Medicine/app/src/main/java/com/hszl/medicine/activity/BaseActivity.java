package com.hszl.medicine.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hszl.medicine.MainActivity1;
import com.hszl.medicine.R;

import java.lang.reflect.Field;

public class BaseActivity extends Activity implements View.OnClickListener ,RadioGroup.OnCheckedChangeListener {


    public RelativeLayout ll_decor;
    public RelativeLayout rlTop;
    public RadioGroup rg;
    TextView tvRight,tvLeft,tvTitle;
    ImageView imgRight,imgLeft;
    FrameLayout flContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_base);
        initialize();
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Build.VERSION.SDK_INT>=19)
        {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /**
     * 添加子布局
     * @param resid 子布局的id
     */
    public void initLayout(int resid)
    {
        View view =LayoutInflater.from(this).inflate(resid,flContent,false);
        flContent.addView(view);
    }

    private void initialize()
    {
        rlTop=findViewById(R.id.rlTop);
        rg=findViewById(R.id.rg);
        tvLeft=findViewById(R.id.tvLeft);
        tvRight=findViewById(R.id.tvRight);
        tvTitle=findViewById(R.id.tvTitle);
        imgLeft=findViewById(R.id.imgLeft);
        imgRight=findViewById(R.id.imgRight);
        flContent=findViewById(R.id.flContent);
        ll_decor=findViewById(R.id.ll_decor);
        imgLeft.setOnClickListener(this);
        imgRight.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);
    }

    /**
     * 设置顶部标题栏颜色
     * @param color
     */
    public void setBackGround(int color)
    {
        rlTop.setBackgroundColor(color);
    }

    /**
     * 隐藏顶部标题栏
     */
    public void hideTop()
    {
        if (rlTop.getVisibility()==View.VISIBLE)
        {
            rlTop.setVisibility(View.GONE);
        }
    }

    /**
     * 隐藏顶部导航栏
     */
    public void hideBottom()
    {
        if (rg.getVisibility()==View.VISIBLE)
        {
            rg.setVisibility(View.GONE);
        }
    }

    public void showBottom()
    {
        if (rg.getVisibility()==View.GONE)
        {
            rg.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置标题栏标题
     * @param title
     */
    public void showTitle(String title)
    {
        tvTitle.setText(title);
    }

    /**
     * 设置右边图片
     * @param resid
     */
    public void showRightImg(int resid)
    {
        if (imgRight.getVisibility()==View.GONE)
        {
            imgRight.setVisibility(View.VISIBLE);
        }
        imgRight.setBackgroundResource(resid);
    }

    /**
     * 隐藏右边图片
     */
    public void hideRightImg()
    {
        if (imgRight.getVisibility()==View.VISIBLE)
        {
            imgRight.setVisibility(View.GONE);
        }
    }

    /**
     * 设置左边图片
     * @param resid 图片资源id
     *
     */
    public void showLeftImg(int resid)
    {
        if (imgLeft.getVisibility()==View.GONE)
        {
            imgLeft.setVisibility(View.VISIBLE);
        }
        imgLeft.setBackgroundResource(resid);
    }

    /**
     * 隐藏左边图片
     */
    public void hideLeftImg()
    {
        if (imgLeft.getVisibility()==View.VISIBLE)
        {
            imgLeft.setVisibility(View.GONE);
        }
    }


    /**
     * 设置左边文字
     * @param str 文字内容
     */
    public void showLeftText(String str)
    {
        if (tvLeft.getVisibility()==View.GONE)
        {
            tvLeft.setVisibility(View.VISIBLE);
        }
        tvLeft.setText(str);
    }

    /**
     * 隐藏左边文字
     */
    public void hideLeftText()
    {
        if (tvLeft.getVisibility()==View.VISIBLE)
        {
            tvLeft.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右边文字
     * @param str 文字内容
     */
    public void showRightText(String str)
    {
        if (tvRight.getVisibility()==View.GONE)
        {
            tvRight.setVisibility(View.VISIBLE);
        }
        tvRight.setText(str);
    }

    /**
     * 隐藏右边文字
     */
    public void hideRightText()
    {
        if (tvRight.getVisibility()==View.VISIBLE)
        {
            tvRight.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tvLeft:
                this.finish();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int id=group.getCheckedRadioButtonId();
        Intent intent=new Intent();
        switch (id)
        {
            case R.id.btnMain:
                intent.setClass(this,MainActivity1.class);
                startActivity(intent);
                break;
            case R.id.btnMe:
                intent.setClass(this,MyActivity.class);
                startActivity(intent);
                break;
        }
    }

    public boolean validation()
    {
        return false;
    }
}
