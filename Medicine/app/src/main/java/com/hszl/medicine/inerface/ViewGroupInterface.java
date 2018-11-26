package com.hszl.medicine.inerface;

import android.view.View;
import android.view.ViewGroup;

public interface ViewGroupInterface {
    View getView(ViewGroup parent,int pos);
    int count();
}
