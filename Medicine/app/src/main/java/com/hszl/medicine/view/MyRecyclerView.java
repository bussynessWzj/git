package com.hszl.medicine.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * recyclerView的item项包含ScrollView引起的滑动冲突解决
 */
public class MyRecyclerView extends RecyclerView {

    float lastY;
    boolean isScrollTop=false;
    boolean isScrollBottom=false;
    ScrollView view;


    public MyRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {

        boolean intercept=false;
        int y= (int) e.getY();
        int x= (int) e.getX();
        switch (e.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastY=y;
                view= (ScrollView) this.findChildViewUnder(x,y);
                return super.onInterceptTouchEvent(e);
            case MotionEvent.ACTION_MOVE:
                float offset=y-lastY;
                Log.e("偏移量", String.valueOf(offset));
                isScrollTopOrBottom(view);
                if (isScrollBottom&&offset<0)  //scrollView已经滑动到底部还要继续上拉则拦截事件 交给recyclerView去处理
                {
                    intercept=true;
                }else if (isScrollTop&&offset>0)   //scrollView已经滑动到顶部还要继续下拉则拦截事件  交给recyclerView处理
                {
                    intercept=true;
                }else    //scrollView可以滑动的情况下优先滚动scrollview 则recyclerView不拦截事件 让事件分发下去
                {
                    intercept=false;
                }
                break;
        }
        lastY=y;
         return intercept;
    }

    private void isScrollTopOrBottom(ScrollView view)
    {
        float childViewHeight=view.getChildAt(0).getMeasuredHeight();
        float scrollViewHeight=view.getMeasuredHeight();
        float scrollY=view.getScrollY();
        if (scrollY==0)
        {
            isScrollTop=true;
            isScrollBottom=false;
        }else if (scrollY+scrollViewHeight==childViewHeight)
        {
            isScrollBottom=true;
            isScrollTop=false;
        }else
        {
            isScrollTop=false;
            isScrollBottom=false;
        }
    }

}
