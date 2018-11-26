package com.hszl.medicine.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.Stock;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class StockAapter extends BaseQuickAdapter<Stock.ListBean,Viewholder> {

    public StockAapter(int layoutResId, @Nullable List<Stock.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, Stock.ListBean item) {
        ImageView check =helper.getView(R.id.ivCheck);
        if (!item.isCheck())
            check.setVisibility(View.GONE);
        else
            check.setVisibility(View.VISIBLE);
        helper.setText(R.id.tvInfo,item.getName());
    }
}
