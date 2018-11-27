package com.hszl.medicine.inerface;

import android.view.View;
import android.view.ViewGroup;

import com.hszl.medicine.utils.ViewGroupImpl;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> implements ViewGroupInterface,Observerable{

    protected List<Observer> observers=new ArrayList<>();

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

    public void notifyDataSetChange()
    {
        notifyObserver();
    }

    @Override
    public void registObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (!observers.isEmpty())
            observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).notifyDataSetChange();
        }
    }
}
