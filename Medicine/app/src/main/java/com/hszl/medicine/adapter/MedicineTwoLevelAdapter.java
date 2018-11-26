package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.MedicineTwoLevel;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class MedicineTwoLevelAdapter extends BaseQuickAdapter<MedicineTwoLevel.ListBean,Viewholder> {

    public MedicineTwoLevelAdapter(int layoutResId, @Nullable List<MedicineTwoLevel.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, MedicineTwoLevel.ListBean item) {
        helper.setText(R.id.tvName,item.getName());
        if (item.getStatus()==0)
        {
            helper.setText(R.id.tvStatus,"禁用");
        }else
        {
            helper.setText(R.id.tvStatus,"正常");
        }

    }
}
