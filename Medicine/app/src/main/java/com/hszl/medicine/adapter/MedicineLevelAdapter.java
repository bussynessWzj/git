package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.MedicineLevel;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class MedicineLevelAdapter extends BaseQuickAdapter<MedicineLevel.ListBean,Viewholder> {

    public MedicineLevelAdapter(int layoutResId, @Nullable List<MedicineLevel.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, MedicineLevel.ListBean item) {
        helper.setText(R.id.tvName,item.getName());
        TextView tvStatus=helper.getView(R.id.tvStatus);
        tvStatus.setVisibility(View.GONE);
    }
}
