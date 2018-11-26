package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.Stock;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class StockInfoAdapter extends BaseQuickAdapter<Stock.ListBean,Viewholder> {

    public StockInfoAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, Stock.ListBean item) {
        helper.setText(R.id.tvName,item.getName())
                .setText(R.id.tvCode,item.getCode())
                .setText(R.id.tvAddress,item.getAddress())
                .setText(R.id.tvPerson,item.getContact())
                .setText(R.id.tvPhone,item.getTel());
    }
}
