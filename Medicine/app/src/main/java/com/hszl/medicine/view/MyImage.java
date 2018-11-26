package com.hszl.medicine.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.hszl.medicine.R;

/**
 * Created by asus-pc on 2018/3/26.
 */

public class MyImage extends AppCompatImageView {


    String circleText;

    public String getCircleText() {
        return circleText;
    }

    public void setCircleText(String circleText) {
        this.circleText = circleText;
        this.requestLayout();
    }

    public MyImage(Context context) {
        super(context);
    }

    public MyImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画外圆边框
        float bigcirclex = this.getMeasuredWidth() / 2;   //圆心x坐标
        float bigcircley = this.getMeasuredHeight() / 2;  //圆心y坐标
        float radus = Math.min(getMeasuredHeight(), getMeasuredWidth()) / 2; //圆半径
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        Bitmap bitmap=((BitmapDrawable)getResources().getDrawable(R.drawable.addnew)).getBitmap();
        canvas.drawBitmap(bitmap,100,100,null);
        canvas.drawCircle(bigcirclex, bigcircley, radus, paint);
        //计算消息小圆的半径
        double π = Math.PI;
        float smallcircley = (float) (bigcircley - radus * Math.sin(π / 4));  //小圆Y坐标
        float smallcirclex = (float) (bigcirclex + radus * Math.cos(π / 4));  //小圆X坐标
        float smallradus = 20;
        Paint paint1 = new Paint();
        paint1.setColor(Color.RED);
        canvas.drawCircle(smallcirclex, smallcircley, smallradus, paint1);
        Paint paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setTextSize(30);
        if (circleText==""||null==circleText) circleText="0";
        canvas.drawText(circleText, smallcirclex, smallcircley + 10, paint2);
    }
}
