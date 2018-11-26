package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.DosageForm;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class DosageFormAdapter extends BaseQuickAdapter<DosageForm.ListBean,Viewholder> {

    public DosageFormAdapter(int layoutResId, @Nullable List<DosageForm.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, DosageForm.ListBean item) {
        helper.setText(R.id.tvInfo,item.getTitle());
        ImageView check =helper.getView(R.id.ivCheck);
        if (item.isCheck()) {
            check.setVisibility(View.VISIBLE);
        } else {
            check.setVisibility(View.GONE);
        }
    }
}
