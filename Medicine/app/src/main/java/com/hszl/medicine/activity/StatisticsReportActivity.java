package com.hszl.medicine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hszl.medicine.R;
import com.hszl.medicine.utils.ImmersionUtils;

public class StatisticsReportActivity extends BaseActivity {

    @Override
    public boolean validation() {
        return false;
    }

    TextView tvSalerReport, tvMedicineInventory, tvStockMedicine, tvReceiving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_statistics_report);
        ImmersionUtils.immersionTop(this, rlTop);
        initView();
        hideBottom();
        hideRightText();
        showTitle("报表");
    }

    private void initView() {
        tvSalerReport = findViewById(R.id.tvSalerReport);
        tvMedicineInventory = findViewById(R.id.tvMedicineInventory);
        tvStockMedicine = findViewById(R.id.tvStockMedicine);
        tvReceiving = findViewById(R.id.tvReceiving);
        tvSalerReport.setOnClickListener(this);
        tvMedicineInventory.setOnClickListener(this);
        tvStockMedicine.setOnClickListener(this);
        tvReceiving.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent=new Intent();
        switch (v.getId()) {
            case R.id.tvReceiving:
                intent.setClass(this,ReceivingReportActivity.class);
                startActivity(intent);
                break;
            case R.id.tvStockMedicine:
                intent.setClass(this,StockMedicineReportActivity.class);
                startActivity(intent);
                break;
            case R.id.tvMedicineInventory:
                intent.setClass(this,MedicineInventoryReportActivity.class);
                startActivity(intent);
                break;
            case R.id.tvSalerReport:
                intent.setClass(this,SalerReportActivity.class);
                startActivity(intent);
                break;
        }
    }
}
