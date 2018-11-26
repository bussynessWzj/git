package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.Medicine;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class CopyMedicineAdapter extends BaseQuickAdapter<Medicine.ListBean,Viewholder> {

    public CopyMedicineAdapter(int layoutResId, @Nullable List<Medicine.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, Medicine.ListBean item) {
        helper.setText(R.id.tvTypeName,item.getDrugCategoryName())
                .setText(R.id.tvName,item.getDrugName())
                .setText(R.id.tvSpecifications,item.getSpecs())
                .setText(R.id.tvDosage,item.getDosageFormName())
                .setText(R.id.tvUnit,item.getUnitName());
    }
}
