package com.hszl.medicine.inerface;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter<T> implements ViewGroupInterface{

    protected List<T> list;

    @Override
    public View getView(ViewGroup parent, int pos) {
        return getView(parent,list.get(pos),pos);
    }

    @Override
    public int count() {
        return list.size();
    }

    public abstract View getView(ViewGroup parent,T data,int pos);
}
