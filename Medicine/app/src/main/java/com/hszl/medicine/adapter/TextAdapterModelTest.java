package com.hszl.medicine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hszl.medicine.R;
import com.hszl.medicine.entity.MainTest;

import org.w3c.dom.Text;

import java.util.List;

public class TextAdapterModelTest extends AdapterModelTest {




    public TextAdapterModelTest(List<MainTest> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(ViewGroup parent, int position) {
         View view=LayoutInflater.from(context).inflate(R.layout.item_main_top,parent,false);
         TextView Num =view.findViewById(R.id.tvNum);
         TextView content=view.findViewById(R.id.tvContent);
         Num.setText("0");
         content.setText("今日订单");
        return view;
    }
}
