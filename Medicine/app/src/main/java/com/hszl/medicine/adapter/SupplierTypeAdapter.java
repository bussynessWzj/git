package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.SupplierType;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class SupplierTypeAdapter extends BaseQuickAdapter<SupplierType.ListBean,Viewholder> {


    public SupplierTypeAdapter(int layoutResId, @Nullable List<SupplierType.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, SupplierType.ListBean item) {
        helper.setText(R.id.tvName,item.getName())
                .setText(R.id.tvCode,item.getCode());
        if (item.getStatus()==0)
            helper.setText(R.id.tvStatus,"不正常");
        else
            helper.setText(R.id.tvStatus,"正常");
    }
}
