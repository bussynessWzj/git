package com.hszl.medicine.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hszl.medicine.R;
import com.hszl.medicine.entity.MainTest2;

import java.util.List;

public class ImageAdapterModelTest extends AdapterModelTest<MainTest2> {

    public ImageAdapterModelTest(List<MainTest2> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(ViewGroup parent, int position) {
       return   LayoutInflater.from(context).inflate(R.layout.item_main_body,parent,false);
    }
}
