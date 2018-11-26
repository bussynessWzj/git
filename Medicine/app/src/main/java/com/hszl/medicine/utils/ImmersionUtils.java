package com.hszl.medicine.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * 沉浸模式布局解决工具类
 */
public class ImmersionUtils {

    /**
     * 顶部沉浸式布局
     * @param mActivity 所需要布局的activity对象
     * @param topView   距离状态栏最近的视图
     *
     */
    public static void immersionTop(Activity mActivity, View topView) {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT)
        {
            return;
        }
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT_WATCH) {
            //这部分机型做沉浸式适配
        int statusHeight = getStatusBarHeight(mActivity);
        topView.setPadding(topView.getPaddingLeft(), topView.getPaddingTop() + statusHeight, topView.getPaddingRight(), topView.getPaddingBottom());
        //}
    }

   public static void immersionBottom(Activity mActivity,View bottomView,int styleColor)
   {
       if (Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT)
       {
           return;
       }
       mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
       //透明导航栏
       mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
       if (getNavigationHeight(mActivity)>0)
       {
           ViewGroup.LayoutParams layoutParams=bottomView.getLayoutParams();
           layoutParams.height+=getNavigationHeight(mActivity);
           Log.i("barry", "getNavigationHeight  " + getNavigationHeight(mActivity));
           bottomView.setLayoutParams(layoutParams);
           bottomView.setBackgroundColor(styleColor);
       }
   }


    private static int getStatusBarHeight(Activity mActivity) {
        int height=-1;
        try {
            Class<?> clazz=Class.forName("com.android.internal.R$dimen");
            Object  object=clazz.newInstance();
            String heightStr=clazz.getField("status_bar_height").get(object).toString();
            height = Integer.parseInt(heightStr);
            //dp--px
            height = mActivity.getResources().getDimensionPixelSize(height);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        return height;
    }

    private static int getNavigationHeight(Activity mActivity) {
        int height=-1;
        try {
            Class<?> clazz=Class.forName("com.android.internal.R$dimen");
            Object  object=clazz.newInstance();
            String heightStr=clazz.getField("navigation_bar_height").get(object).toString();
            height = Integer.parseInt(heightStr);
            //dp--px
            height = mActivity.getResources().getDimensionPixelSize(height);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        return height;
    }

    /**
     * 处理顶部导航栏在7.0以上有灰色遮罩的问题
     * @param mActivity
     */
    public static void setTrans(Activity mActivity)
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
        {
            try {
                Class clz=Class.forName("com.android.internal.policy.DecorView");
                Field field=clz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(mActivity.getWindow().getDecorView(),Color.TRANSPARENT);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
