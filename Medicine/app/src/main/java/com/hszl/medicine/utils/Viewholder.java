package com.hszl.medicine.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

public class Viewholder extends BaseViewHolder {

    public Viewholder(View view) {
        super(view);
    }

    /**
     * 用来缓存控件，优化加载
     * @param view  itemView的布局
     * @param id    itemView布局中需要缓存控件的id
     * @return  缓存后的控件（textView、imageView...等控件）
     */
    @SuppressWarnings("unchecked")
    public static View get(View view, int id) {
        // 获取itemView的ViewHolder对象，并将其转型为SparseArray<View>
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            // 如果viewHolder为空，就新建一个
            viewHolder = new SparseArray<View>();
            // 给view设置tag标签
            view.setTag(viewHolder);
        }
        // 根据控件的id获取itemView布局的控件
        View childView = viewHolder.get(id);
        if(childView == null){
            // 如果还没有缓存该控件，那么就根据itemView找到该控件
            childView = view.findViewById(id);
            // 缓存该控件
            viewHolder.put(id, childView);
        }
        // 返回缓存好的控件
        return childView;
    }
}
