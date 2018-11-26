package com.hszl.medicine.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hszl.medicine.inerface.Adapter;
import com.hszl.medicine.inerface.ViewGroupInterface;

public abstract class ViewGroupImpl{


    public static void addView(ViewGroup parent, ViewGroupInterface groupInterface, boolean removeViews)
    {
        if (parent==null&&groupInterface==null) return;
        if (removeViews)
        {
            parent.removeAllViews();
        }
        for (int i=0;i<groupInterface.count();i++)
        {
            parent.addView(groupInterface.getView(parent,i));
        }
    }
}


