package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.Supplier;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class SupplierInfoAdapter extends BaseQuickAdapter<Supplier.ListBean,Viewholder> {


    public SupplierInfoAdapter(int layoutResId, @Nullable List<Supplier.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, Supplier.ListBean item) {
        helper.setText(R.id.tvName,item.getName())
                .setText(R.id.tvCode,item.getCode())
        .setText(R.id.tvType,item.getSupplierCategoryName())
        .setText(R.id.tvPhone,item.getPhone());
        if (item.getStatus()==0)
            helper.setText(R.id.tvStatus,"不正常");
        else
            helper.setText(R.id.tvStatus,"正常");
    }
}
