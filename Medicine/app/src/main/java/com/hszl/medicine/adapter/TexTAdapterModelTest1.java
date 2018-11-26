package com.hszl.medicine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hszl.medicine.R;
import com.hszl.medicine.entity.MainTest;

import com.hszl.medicine.inerface.BaseAdapter;

import java.util.List;

public class TexTAdapterModelTest1 extends BaseAdapter<MainTest> {


    Context context;

    public TexTAdapterModelTest1(Context context,List<MainTest> list) {
        this.context = context;
        super.list=list;
    }

    @Override
    public View getView(ViewGroup parent, MainTest data, int pos) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_main_top,parent,false);
        TextView num =view.findViewById(R.id.tvNum);
        TextView tvContent =view.findViewById(R.id.tvContent);
        num.setText(data.getStr1());
        tvContent.setText(data.getStr2());
        return view;
    }
}
