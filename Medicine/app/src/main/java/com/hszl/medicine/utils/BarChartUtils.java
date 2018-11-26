package com.hszl.medicine.utils;

import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.hszl.medicine.entity.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BarChartUtils {

    /**
     * 初始化圆柱状报表
     *
     * @param barChart 圆柱报表
     * @param x        X轴要显示的元素
     * @param maxY     Y轴的最大值
     * @param count    Y轴显示的标签个数
     */
    public static void initBarChart(BarChart barChart, List<String> x, float maxY, int count) {
        barChart.setBackgroundColor(Color.WHITE);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBarShadow(false);
        barChart.setHighlightFullBarEnabled(false);
        barChart.setDrawBorders(false);
        barChart.animateX(1000, Easing.Linear);
        barChart.animateY(1000, Easing.Linear);
        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);
        IAxisValueFormatter iAxisValueFormatter = new StringValueFormatter(x);
        //        barChart.setPinchZoom(true);
        /***设置X坐标轴***/
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //        xAxis.setGranularity(1f);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
//        xAxis.setAxisMinimum(0f);
        //        xAxis.setAxisMaximum(x.size());
        xAxis.setLabelCount(x.size());
        xAxis.setLabelRotationAngle(90f);
        xAxis.setCenterAxisLabels(false);//设置标签居中
        xAxis.setValueFormatter(iAxisValueFormatter);
        xAxis.setAvoidFirstLastClipping(false);
        /***设置Y坐标轴***/
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(maxY);
        yAxis.setLabelCount(count);
        barChart.getAxisRight().setEnabled(false);

        /**设置图例**/
        Legend legend = barChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);//图例水平居中
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//图例在图表上方
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//图例的方向为水平
        legend.setDrawInside(false);//绘制在chart的外侧
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);//图例中的文字方向

        legend.setForm(Legend.LegendForm.CIRCLE);//图例窗体的形状
        legend.setFormSize(0f);//图例窗体的大小
        legend.setTextSize(16f);//图例文字的大小

        legend.setDrawInside(false);

        barChart.setExtraBottomOffset(10);//距视图窗口底部的偏移，类似与paddingbottom
        barChart.setExtraTopOffset(30);//距视图窗口顶部的偏移，类似与paddingtop
        barChart.setFitBars(true);//使两侧的柱图完全显示
        barChart.animateX(1500);//数据显示动画，从左往右依次显示

    }

    /**
     * 设置柱形的
     *
     * @param barDataSet 圆柱的数据集
     * @param color      圆柱的颜色
     */
    public static void initBarDataSet(BarDataSet barDataSet, int color) {
        barDataSet.setColor(color);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15f);
        barDataSet.setDrawValues(true);
    }

    /**
     * 每个bardataset表示一个柱形的数据集
     *
     * @param list 为一个柱形设置一个数据源
     * @param name 图例的名字
     * @return 返回一个柱形
     */
    public static BarDataSet setBarDataSetResource(List<? extends Value> list, String name) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            BarEntry barEntry = new BarEntry(i, Float.parseFloat(list.get(i).getValue()));
            entries.add(barEntry);
        }
        BarDataSet barDataSet = new BarDataSet(entries, name);
        return barDataSet;
    }

    public void showBarChart() {

    }

    /**
     * 多圆柱
     *
     * @param colors
     * @param map
     * @return
     */
    public static BarData showBarChart(int[] colors, Map<String, List<? extends Value>> map) {
        float groupSpace = 0.3f;
        float barSpaece = 0.05f;
        ArrayList<IBarDataSet> barDataSets = new ArrayList<>();
        int i = 0;
        for (String str : map.keySet()
                ) {
            BarDataSet barDataSet = setBarDataSetResource(map.get(str), str);
            initBarDataSet(barDataSet, colors[i]);
            barDataSets.add(barDataSet);
            i++;
        }
        BarData barData = new BarData(barDataSets);
        float barWidth = (1 - groupSpace) / map.size() - barSpaece;
        barData.setBarWidth(barWidth);
        barData.groupBars(0, groupSpace, barSpaece);
        return barData;
    }

    public static BarData showBarChat(List<? extends Value> list, String name, int color) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            BarEntry barEntry = new BarEntry(i, Float.parseFloat(list.get(i).getValue()));
            entries.add(barEntry);
        }
        BarDataSet barDataSet = new BarDataSet(entries, name);
        initBarDataSet(barDataSet, color);
        BarData barData = new BarData(barDataSet);
        return barData;
    }
}
