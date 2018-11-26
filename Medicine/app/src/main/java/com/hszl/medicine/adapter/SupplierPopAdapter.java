package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.Stock;
import com.hszl.medicine.entity.Supplier;
import com.hszl.medicine.entity.SupplierType;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class SupplierPopAdapter extends BaseQuickAdapter <Supplier.ListBean,Viewholder>{

    public SupplierPopAdapter(int layoutResId, @Nullable List<Supplier.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, Supplier.ListBean item) {
        ImageView check =helper.getView(R.id.ivCheck);
        if (!item.isCheck())
            check.setVisibility(View.GONE);
        else
            check.setVisibility(View.VISIBLE);
        helper.setText(R.id.tvInfo,item.getName());
    }
}
