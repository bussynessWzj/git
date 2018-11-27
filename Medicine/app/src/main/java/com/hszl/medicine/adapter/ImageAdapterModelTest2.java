package com.hszl.medicine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hszl.medicine.R;
import com.hszl.medicine.entity.MainTest2;
import com.hszl.medicine.inerface.BaseAdapter;

import java.util.List;

public class ImageAdapterModelTest2 extends BaseAdapter<MainTest2> {

    Context context;
    public ImageAdapterModelTest2(Context context,List<MainTest2> list)
    {
        this.context=context;
        this.list=list;
    }

    @Override
    public View getView(ViewGroup parent, MainTest2 data, int pos) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_main_body,parent,false);
        ImageView img =view.findViewById(R.id.img);
        TextView tvContent=view.findViewById(R.id.tvContent);
        img.setImageResource(data.getResid());
        img.setBackgroundColor(Color.parseColor("#2da29a"));
        tvContent.setText(data.getStr1());
        return view;
    }
}
