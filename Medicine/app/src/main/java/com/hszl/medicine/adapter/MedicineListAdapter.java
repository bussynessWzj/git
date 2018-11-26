package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.MedicineList;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class MedicineListAdapter extends BaseQuickAdapter<MedicineList,Viewholder> {

    public MedicineListAdapter(int layoutResId, @Nullable List<MedicineList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, MedicineList item) {
        helper.setText(R.id.tvName,item.getName())
                .setText(R.id.tvBarcode,item.getBarcode())
                .setText(R.id.tvBactch,item.getBactch())
                .setText(R.id.tvProductDate,item.getProductDate())
                .setText(R.id.tvValidity,item.getValidity())
                .setText(R.id.tvCount,item.getCount())
                .setText(R.id.tvUnitPrice,item.getUnitPrice());
    }
}
