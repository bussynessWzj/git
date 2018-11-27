package com.hszl.medicine.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hszl.medicine.R;
import com.hszl.medicine.entity.MainTest;
import com.hszl.medicine.inerface.Adapter;
import com.hszl.medicine.inerface.MyItemClickListener;

import java.util.List;

@Deprecated
public class TextAdapter implements Adapter {
    List<MainTest> list;
    MyItemClickListener myItemClickListener;

    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    public TextAdapter(List<MainTest> list)
    {
        this.list=list;
    }

    @Override
    public void setView(final ViewGroup converView) {
        for (int i = 0; i < converView.getChildCount(); i++) {
            final int finalI = i;
            if (myItemClickListener!=null) {
                converView.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myItemClickListener.ItemClickListener(converView, finalI);
                    }
                });
            }
            TextView num =converView.getChildAt(i).findViewById(R.id.tvNum);
            TextView content=converView.getChildAt(i).findViewById(R.id.tvContent);
            num.setText(list.get(i).getStr1());
            content.setText(list.get(i).getStr2());
        }
        converView.requestLayout();
    }

    public void notifyDataSetChange()
    {

    }
}
