package com.hszl.medicine.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.hszl.medicine.inerface.Adapter;
import com.hszl.medicine.inerface.MyItemClickListener;
import com.hszl.medicine.inerface.Observer;
import com.hszl.medicine.inerface.ViewGroupInterface;
import com.hszl.medicine.inerface.ViewGroupItemClickListener;


/**
 * 可扩展为添加新数据时用老数据做对比不需要remove所有的数据
 */
public class ViewGroupImpl implements Observer {


   ViewGroupItemClickListener viewGroupItemClickListener;
   ViewGroup parent;
   ViewGroupInterface viewGroupInterface;



    public void setViewGroupItemClickListener(ViewGroupItemClickListener viewGroupItemClickListener) {
        this.viewGroupItemClickListener = viewGroupItemClickListener;
        setOnItemClickListener(parent,viewGroupItemClickListener);
    }

    public void addView(ViewGroup parent, ViewGroupInterface groupInterface, boolean removeViews)
    {
        if (parent==null&&groupInterface==null) return;
        this.parent=parent;
        this.viewGroupInterface=groupInterface;
        if (removeViews)
        {
            parent.removeAllViews();
        }
        for (int i=0;i<groupInterface.count();i++)
        {
            parent.addView(groupInterface.getView(parent,i));
        }
    }

    public void setOnItemClickListener(final ViewGroup parent, final ViewGroupItemClickListener viewGroupItemClickListener) {
        if (parent==null||viewGroupItemClickListener==null) return;
        for (int i = 0; i < parent.getChildCount(); i++) {
            final View view=parent.getChildAt(i);
            if (view!=null&&!view.isClickable())
            {
                final int finalI = i;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewGroupItemClickListener.onItemClickListener(parent,view, finalI);
                    }
                });
            }
        }
    }

    public ViewGroupImpl(com.hszl.medicine.inerface.BaseAdapter adapter) {
        this.viewGroupInterface = adapter;
        adapter.registObserver(this);
    }

    @Override
    public void notifyDataSetChange() {
        addView(parent,viewGroupInterface,true);
        setOnItemClickListener(parent,viewGroupItemClickListener);
    }

    @Override
    public void addNewData() {
        addView(parent,viewGroupInterface,false);
        setOnItemClickListener(parent,viewGroupItemClickListener);
    }
}


