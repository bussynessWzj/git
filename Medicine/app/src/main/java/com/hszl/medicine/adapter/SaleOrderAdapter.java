package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;
import android.widget.ScrollView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.AAA;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class SaleOrderAdapter extends BaseQuickAdapter<AAA,Viewholder> {

    public SaleOrderAdapter(int layoutResId, @Nullable List<AAA> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder viewholder, AAA aaa) {
        ScrollView sv =viewholder.getView(R.id.sv);
    }
}
