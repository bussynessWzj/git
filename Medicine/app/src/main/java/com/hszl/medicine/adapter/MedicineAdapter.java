package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.Medicine;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class MedicineAdapter extends BaseQuickAdapter<Medicine.ListBean,Viewholder> {

    public MedicineAdapter(int layoutResId, @Nullable List<Medicine.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, Medicine.ListBean item) {
        helper.setText(R.id.tvCode,item.getDrugCode())
                .setText(R.id.tvBarcode,item.getBarCode())
                .setText(R.id.tvName,item.getDrugName())
                .setText(R.id.tvSpecifications,item.getSpecs())
                .setText(R.id.tvUnit,item.getUnitName())
                .addOnClickListener(R.id.imgAdd);
    }
}
