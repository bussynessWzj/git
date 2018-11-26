

package com.hszl.medicine.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

import com.hszl.medicine.R;
import com.hszl.medicine.adapter.AdapterModelTest;
import com.hszl.medicine.adapter.TexTAdapterModelTest1;
import com.hszl.medicine.inerface.Adapter;
import com.hszl.medicine.inerface.MyItemClickListener;
import com.hszl.medicine.inerface.ViewGroupInterface;

import java.util.List;

/**
 * 可优化使用适配器模式加观察者模式来为自定义FixChildLableGroup增加数据源适配器来达到更改自定义控件内部子控件
 * 的数据源从而达到控件，数据分离，加入中间的适配器类来达到适配的目的
 */

public class ViewGroupTest extends ViewGroup {

    ViewGroupInterface viewGroupInterface;
    MyItemClickListener myItemClickListener;

    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    MeasureSpec mWidthMeasureSpec,mHeightMeasureSpec;

    int mCount = 0;
    int num = 0;
    float fixSize;
    TexTAdapterModelTest1 adapter;

    public ViewGroupTest(Context context) {
        this(context, null);
    }

    public ViewGroupTest(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewGroupTest(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ViewGroupTest(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FixChildLableGroup, defStyleAttr, 0);
        num = a.getInteger(R.styleable.FixChildLableGroup_num, -1);
        a.recycle();
    }

    //    @Override
    //    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    //        float size = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
    //        fixSize = size / num;
    //        int lineWidth = 0;//记录当前行宽
    //        int lineHeight = 0;//记录当前行高
    //        int maxWidth = 0;     //记录每行测量控件的最大宽度
    //        int maxHeight = 0;    //记录每行测量控件的最大高度
    //        int mWidth = 0;       //记录当前控件的宽（包括padding属性）
    //        int mHeight = 0;      //记录当前控件的高(包括padding属性)
    //        for (int i = 0; i < getChildCount(); i++) {
    //            View view = getChildAt(i);
    //            measureChild(view, widthMeasureSpec, heightMeasureSpec);
    //            mWidth = view.getMeasuredWidth() + view.getPaddingRight() + view.getPaddingLeft();
    //            mHeight = view.getMeasuredHeight() + view.getPaddingTop() + view.getPaddingBottom();
    //            mWidth = (int) Math.max(fixSize, mWidth);
    //            if (lineWidth + mWidth <= size)//不换行
    //            {
    //                //记录每行测量控件的最大高度当换行是行高增加最大高度
    //                maxHeight = Math.max(mHeight, maxHeight);
    //                //不换行，行宽增加
    //                lineWidth += mWidth;
    //            } else  //换行
    //            {
    //                //首先行高增加
    //                lineHeight += maxHeight;
    //                //每行测量控件的最大高度复位
    //                maxHeight = 0;
    //                //记录每行的最大宽度
    //                maxWidth = Math.max(maxWidth, lineWidth);
    //                //行宽复位
    //                lineWidth = 0;
    //                lineWidth += mWidth;//从换行的位置开始记录当前行宽
    //                maxHeight = mHeight; //从新记录新行的高度
    //            }
    //        }
    //        lineHeight += maxHeight;//记录总行高
    //        lineWidth = Math.max(lineWidth, maxWidth);
    //        setMeasuredDimension(lineWidth, lineHeight);
    //    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float widthSize = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();  //父布局建议的宽度
        //float heightSize = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight(); //父布局建议的高度
//        mCount = adapter == null ? 0 : adapter.getCount();
        int lineWidth = 0;//记录当前行宽
        int lineHeight = 0;//记录当前行高
        int maxWidth = 0;     //记录每行测量控件的最大宽度
        int maxHeight = 0;    //记录每行测量控件的最大高度
        int mWidth = 0;       //记录当前控件的宽（包括padding属性）
        int mHeight = 0;      //记录当前控件的高(包括padding属性)
        fixSize = widthSize / num;
        for (int i = 0; i < mCount; i++) {
            View view=getChildAt(i);
            measureChild(view,widthMeasureSpec,heightMeasureSpec);
            mWidth = view.getMeasuredWidth() + view.getPaddingRight() + view.getPaddingLeft();
            mHeight = view.getMeasuredHeight() + view.getPaddingTop() + view.getPaddingBottom();
            mWidth = (int) Math.max(fixSize, mWidth);
            if (lineWidth + mWidth <= widthSize)//不换行
            {
                //记录每行测量控件的最大高度当换行是行高增加最大高度
                maxHeight = Math.max(mHeight, maxHeight);
                //不换行，行宽增加
                lineWidth += mWidth;
            } else  //换行
            {
                //首先行高增加
                lineHeight += maxHeight;
                //每行测量控件的最大高度复位
                maxHeight = 0;
                //记录每行的最大宽度
                maxWidth = Math.max(maxWidth, lineWidth);
                //行宽复位
                lineWidth = 0;
                lineWidth += mWidth;//从换行的位置开始记录当前行宽
                maxHeight = mHeight; //从新记录新行的高度
            }
        }
        lineHeight += maxHeight;//记录总行高
        lineWidth = Math.max(lineWidth, maxWidth);
        setMeasuredDimension(lineWidth, lineHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int maxsize = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int startY = getPaddingTop();
        int startX = getPaddingLeft();
        int maxHeight = 0;
        for (int i = 0; i < mCount; i++) {
            View view=adapter.getView(this,i);
            //需要从新测量
            view.measure(getMeasuredWidth(),getMeasuredHeight());
            int count=this.getChildCount();
            int width = view.getMeasuredWidth() + view.getPaddingLeft() + view.getPaddingRight();
            int height = view.getMeasuredHeight() + view.getPaddingTop() + view.getPaddingBottom();
            width = (int) Math.max(width, fixSize);
            if (startX + width > maxsize) {
                startY = startY + maxHeight;//起始坐标top改变
                startX = getPaddingLeft();//起始坐标left回到paddingleft
            }
            maxHeight = Math.max(maxHeight, height);
            view.layout(startX, startY, startX + width, startY + height);
            startX = startX + width;//起始点坐标位置改变
        }
    }


    //    @Override
    //    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    //        int maxsize = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    //        int startY = getPaddingTop();
    //        int startX = getPaddingLeft();
    //        int maxHeight = 0;
    //        for (int i = 0; i < getChildCount(); i++) {
    //            View view = getChildAt(i);
    //            int width = view.getMeasuredWidth() + view.getPaddingLeft() + view.getPaddingRight();
    //            int height = view.getMeasuredHeight() + view.getPaddingTop() + view.getPaddingBottom();
    //            width = (int) Math.max(width, fixSize);
    //            if (startX + width > maxsize) {
    //                startY = startY + maxHeight;//起始坐标top改变
    //                startX = getPaddingLeft();//起始坐标left回到paddingleft
    //            }
    //            maxHeight = Math.max(maxHeight, height);
    //            view.layout(startX, startY, startX + width, startY + height);
    //            startX = startX + width;//起始点坐标位置改变
    //        }
    //    }

    public void setAdapter(TexTAdapterModelTest1 adapter) {
        this.adapter = adapter;
        requestLayout();//重新布局
    }


}
