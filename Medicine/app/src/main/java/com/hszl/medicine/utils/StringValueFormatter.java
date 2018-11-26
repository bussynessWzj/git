package com.hszl.medicine.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

public class StringValueFormatter implements IAxisValueFormatter {

    List<String> strs;

    public List<String> getStrs() {
        return strs;
    }

    public void setStrs(List<String> strs) {
        this.strs = strs;
    }

    public StringValueFormatter(List<String> strs) {
        this.strs = strs;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if (value<0)
            return "";
        if (value>=strs.size())
            return "";
        return strs.get((int) value);
    }
}
