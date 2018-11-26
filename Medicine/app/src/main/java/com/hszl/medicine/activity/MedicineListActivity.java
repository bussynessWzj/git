package com.hszl.medicine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hszl.medicine.R;
import com.hszl.medicine.adapter.MedicineListAdapter;
import com.hszl.medicine.entity.MedicineList;
import com.hszl.medicine.utils.ImmersionUtils;

import java.util.ArrayList;
import java.util.List;

public class MedicineListActivity extends BaseActivity {
        RecyclerView recyclerView;
        LinearLayoutManager linearLayoutManager;
        MedicineListAdapter medicineListAdapter;
        List<MedicineList> list=new ArrayList<>();

    @Override
    public boolean validation() {
        return false;
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            ImmersionUtils.setTrans(this);
            super.onCreate(savedInstanceState);
            initLayout(R.layout.activity_medicine_list);
            ImmersionUtils.immersionTop(this,rlTop);
            hideRightText();
            showTitle("已添加药品");
            hideBottom();
            list.addAll ((List<MedicineList>) getIntent().getSerializableExtra("list"));
            recyclerView=findViewById(R.id.rvMedicine);
            linearLayoutManager=new LinearLayoutManager(this);
            medicineListAdapter=new MedicineListAdapter(R.layout.item_medicine_list,list);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(medicineListAdapter);
        }
}
