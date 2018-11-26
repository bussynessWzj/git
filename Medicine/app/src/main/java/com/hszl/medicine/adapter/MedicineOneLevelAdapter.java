package com.hszl.medicine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hszl.medicine.R;
import com.hszl.medicine.entity.MedicineOneLevel;
import com.hszl.medicine.utils.Viewholder;

import java.util.List;

public class MedicineOneLevelAdapter extends BaseQuickAdapter<MedicineOneLevel.ListBean,Viewholder> {

    public MedicineOneLevelAdapter(int layoutResId, @Nullable List<MedicineOneLevel.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(Viewholder helper, MedicineOneLevel.ListBean item) {
        helper.setText(R.id.tvName,item.getName());
        LinearLayout ll=helper.getView(R.id.ll);
        TextView tvName=helper.getView(R.id.tvName);
        View view=helper.getView(R.id.view);
        if (item.isCheck()) {
            view.setVisibility(View.VISIBLE);
            tvName.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            ll.setBackground(mContext.getResources().getDrawable(R.drawable.noborder));
        }
        else {
            view.setVisibility(View.GONE);
            tvName.setTextColor(mContext.getResources().getColor(R.color.white));
            ll.setBackground(mContext.getResources().getDrawable(R.drawable.border));
        }
    }
}
