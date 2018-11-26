package com.hszl.medicine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hszl.medicine.R;

import java.util.List;

public abstract class AdapterModelTest<E>{

    List<E> list;
    Context context;

    public AdapterModelTest(List<E> list,Context context) {
        this.list=list;
        this.context=context;
    }

    public int getCount()
    {
        return list.size();
    }

    public Object getItem(int position)
    {
        return list.get(position);
    }

    /**
     *
     * @param parent parent 为自定义的viewgroup
     * @param position parent的条目
     * @param
     * @return
     */
    public abstract View getView(ViewGroup parent,int position);
}
