package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.MedicineTwoLevel;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class CopyMedicineTwoLevelAdapter extends BaseQuickAdapter<MedicineTwoLevel.ListBean,Viewholder> {

    public CopyMedicineTwoLevelAdapter(int layoutResId, @Nullable List<MedicineTwoLevel.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, MedicineTwoLevel.ListBean item) {
        ImageView check =helper.getView(R.id.ivCheck);
        if (!item.isCheck())
            check.setVisibility(View.GONE);
        else
            check.setVisibility(View.VISIBLE);
        helper.setText(R.id.tvInfo,item.getName());
    }
}
