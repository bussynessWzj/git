package com.hszl.medicine;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Test extends Activity {

    ImageView tv;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        tv=findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                creatPop(R.layout.pop_item_stock);
//                tv.setBackgroundResource(R.drawable.one);
                tv.setImageResource(R.drawable.one);
                tv.setBackgroundColor(Color.RED);
            }
        });
    }

    private PopupWindow creatPop(int resid)
    {
        View view=LayoutInflater.from(this).inflate(R.layout.pop_item_stock,null,false);
        PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,300);
        //        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.RED));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.style.popwindow);
        //        rlBottom.setVisibility(View.GONE);
        popupWindow.showAtLocation(findViewById(android.R.id.content),Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
        return popupWindow;
    }
}
