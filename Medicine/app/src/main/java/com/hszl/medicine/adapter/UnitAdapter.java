package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.Unit;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class UnitAdapter extends BaseQuickAdapter<Unit.ListBean,Viewholder> {

    public UnitAdapter(int layoutResId, @Nullable List<Unit.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, Unit.ListBean item) {
        helper.setText(R.id.tvInfo,item.getTitle());
        ImageView check =helper.getView(R.id.ivCheck);
        if (item.isCheck()) {
            check.setVisibility(View.VISIBLE);
        } else {
            check.setVisibility(View.GONE);
        }
    }
}
