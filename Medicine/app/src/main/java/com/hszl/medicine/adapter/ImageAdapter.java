package com.hszl.medicine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hszl.medicine.R;
import com.hszl.medicine.entity.MainTest2;
import com.hszl.medicine.inerface.Adapter;
import com.hszl.medicine.inerface.MyItemClickListener;

import java.util.List;

public class ImageAdapter implements Adapter ,View.OnClickListener {

    List<MainTest2> list;
    Context context;
    MyItemClickListener myItemClickListener;

    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    public ImageAdapter(List<MainTest2> list, Context context)
    {
        this.list=list;
        this.context=context;
    }

    @Override
    public void setView(final ViewGroup converView) {
        for (int i = 0; i < converView.getChildCount(); i++) {
            if (myItemClickListener!=null) {
                final int finalI = i;
                converView.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myItemClickListener.ItemClickListener(converView, finalI);
                    }
                });
            }
            ImageView img =converView.getChildAt(i).findViewById(R.id.img);
            TextView content=converView.getChildAt(i).findViewById(R.id.tvContent);
            img.setImageResource(list.get(i).getResid());
            img.setBackgroundColor(Color.parseColor("#2da29a"));
            content.setText(list.get(i).getStr1());
        }
        converView.requestLayout();
    }

    @Override
    public void onClick(View v) {

    }
}
