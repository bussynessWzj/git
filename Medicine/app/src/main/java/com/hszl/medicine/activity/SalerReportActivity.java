package com.hszl.medicine.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.hszl.medicine.R;
import com.hszl.medicine.utils.ImmersionUtils;

public class SalerReportActivity extends BaseActivity {
    @Override
    public boolean validation() {
        return false;
    }

    TextView tvType,tvYear,tvMonth;
    BarChart barchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_time_report);
        ImmersionUtils.immersionTop(this,rlTop);
        initView();
    }

    private void initView() {
        tvType=findViewById(R.id.tvType);
        tvYear=findViewById(R.id.tvYear);
        tvMonth=findViewById(R.id.tvMonth);
        barchart=findViewById(R.id.barchart);
    }
}
