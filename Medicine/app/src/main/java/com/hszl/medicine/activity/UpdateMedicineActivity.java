package com.hszl.medicine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hszl.medicine.R;
import com.hszl.medicine.entity.MedicineOneLevel;
import com.hszl.medicine.utils.DataUtils;
import com.hszl.medicine.utils.ImmersionUtils;

import ch.ielse.view.SwitchView;

/**
 * 更新药品类型信息
 */
public class UpdateMedicineActivity extends BaseActivity {


    EditText etName,etCode;
    SwitchView svStatus;
    Button btnSave;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImmersionUtils.setTrans(this);
        super.onCreate(savedInstanceState);
        initLayout(R.layout.activity_update_medicine);
        showTitle("更新药品");
        hideRightText();
        ImmersionUtils.immersionTop(this,rlTop);
        hideBottom();
        id=getIntent().getStringExtra("id");
        initView();
    }

    private void initView() {
        etName=findViewById(R.id.etName);
        etCode=findViewById(R.id.etCode);
        svStatus=findViewById(R.id.svStatus);
        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }

    @Override
    public boolean validation() {
        if (DataUtils.isEmpty(etName.getText()))
        {
            Toast.makeText(this,"请输入名称",Toast.LENGTH_SHORT).show();
            return false;
        }else if (DataUtils.isEmpty(etCode.getText()))
        {
            Toast.makeText(this,"请输入编码",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.btnSave:
                if (!validation()) return;
                break;
        }
    }

    private void updateType(String id)
    {

    }
}
