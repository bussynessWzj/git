package com.hszl.medicine.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.hszl.medicine.inerface.ExpenseEvent;

public class MyScrollView extends ScrollView {

    private boolean isScrollTop=false;

    private boolean isScrollBottom=false;

    private boolean isExpense=true;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        return true;
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return super.dispatchTouchEvent(ev);
//    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        Log.e("touchEvent1","进入ScrollView滑动监听事件");
        super.onScrollChanged(l, t, oldl, oldt);
        if (getScrollY()==0)
        {
            isScrollBottom=false;
            isScrollTop=true;
//            Log.e("touchEvent","滑动到顶部");
        }else if (getChildAt(0).getMeasuredHeight()-getMeasuredHeight()==getScrollY())
        {
//            Log.e("touchEvent","滑动到底部");
            isScrollTop=false;
            isScrollBottom=true;
        }else
        {
            isScrollBottom=false;
            isScrollTop=false;
//            Log.e("touchEvent","正在滑动");
        }
    }
    float lasty;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        Log.e("touchEvent3","进入scrollView消费事件");
        super.onTouchEvent(ev);
//        switch (ev.getAction())
//        {
//            case MotionEvent.ACTION_MOVE:
//                if (isScrollTop||isScrollBottom)
//                {
//                    Log.e("touchEvent","已经滑动到底部或者顶部了");
//                    return false;
//                }
//        }
//            return true;
        float scrollViewHeight = getMeasuredHeight();
        float childViewHeight = this.getChildAt(0).getMeasuredHeight();
        float ScrollY = getScrollY();
//        Log.e("touchEvent", "scrollView高度为" + scrollViewHeight + "子View的高度为" + childViewHeight);
//        Log.e("touchEvent", "可滑动距离为" + ScrollY);
//        if (y >= childViewHeight - scrollViewHeight) {
//            Log.e("touchEvent", "滑动到了底部");
//            return false;
//        }
        int y= (int) ev.getRawY();
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lasty=y;
                isExpense=true;
                Log.e("touchEvent2","按下坐标"+String.valueOf(y));
//                Log.e("touchEvent1","scrollView按下");
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.e("touchEvent3","进入滑动");
//                Log.e("touchEvent2","getY"+String.valueOf(ev.getY()));
//                Log.e("touchEvent2","y"+String.valueOf(lasty));
                float offsety=lasty-y;
                lasty=y;
                Log.e("touchEvent2","偏移量为"+String.valueOf(offsety));
//                Log.e("touchEvent1","scrollView移动");

//                break;
                if (isScrollBottom||isScrollTop)
                {
                    isExpense=false;
//                    Log.e("touchEvent3","到底部或者顶部了");
                }else
                {
//                    Log.e("touchEvent3","滑动中的"+String.valueOf(isExpense));
                    isExpense=true;
                }
                break;
            case MotionEvent.ACTION_UP:
//                Log.e("touchEvent1","scrollView抬起");
                break;
        }
//        Log.e("touchEvent3",String.valueOf(isExpense));
//        Log.e("touchEvent1","判断是否消费了");
        if (isExpense)
            return true;
        else
            return false;



//        return true;
        //        switch (ev.getAction())
        //        {
        //            case MotionEvent.ACTION_DOWN:
        //                Log.e("touchEvent","按下了scrollView");
        //                break;
        //            case MotionEvent.ACTION_MOVE:
        //                if (y>=childViewHeight-scrollViewHeight)
        //                {
        //                    Log.e("touchEvent","滑动到了底部");
        //                    return false;
        //                }
        //                Log.e("touchEvent","正在移动scrollView");
        //                break;
        //            case MotionEvent.ACTION_UP:
        //                Log.e("touchEvent","抬起了scrollView");
        //                break;
        //        }
    }

//    private boolean isCanScroll(float y)
//    {
//        float scrollViewHeight = getMeasuredHeight();
//        float childViewHeight = this.getChildAt(0).getMeasuredHeight();
//        float scrollY = getScrollY();
//        if (scrollY+scrollViewHeight+y>childViewHeight)
//        {
//
//        }
//    }
}
