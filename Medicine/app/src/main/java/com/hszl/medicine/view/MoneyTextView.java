package com.hszl.medicine.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.hszl.medicine.R;

public class MoneyTextView extends AppCompatTextView {

    String unit;
    String text;
    float textSize;
    int textColor;
    boolean sameSize;

    public MoneyTextView(Context context) {
        this(context,null);
    }

    public MoneyTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MoneyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.MoneyTextView,defStyleAttr,0);
        unit=a.getString(R.styleable.MoneyTextView_unit);
        text=a.getString(R.styleable.MoneyTextView_android_text);
        textSize= a.getDimension(R.styleable.MoneyTextView_android_textSize,0.0f);
        textColor=a.getColor(R.styleable.MoneyTextView_android_textColor,0);
        sameSize=a.getBoolean(R.styleable.MoneyTextView_sameSize,true);
        a.recycle();
        setText(assignText(text));
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        text=assignText(text.toString());
        super.setText(text, type);

    }

    private String assignText(String text)
    {
        text= RemoveUnit(text);
        if (text.contains("."))
        {
            if(text.split("\\.")[1].length()>=2)
            {
                return unit+text;
            }else if (text.split("\\.")[1].length()==1)
            {
                return unit+text+"0";
            }else
            {
                return unit+text+"00";
            }
        }else
        {
            return unit+text+".00";
        }
    }

    private String RemoveUnit(String text)
    {
        try {
            Integer.valueOf(text.substring(0,1));
            return text;
        }catch (Exception e)
        {
            return text.substring(1);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        String text=getText().toString();
        Log.e("text",text);
        Rect rect=new Rect();
        Paint paint =getPaint();
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.getTextBounds(text,0,text.length(),rect);
//        float space=rect.width()/text.length();
        float width=getMeasuredWidth();
        float height=getMeasuredHeight();
        float startX=width/2-rect.width()/2;
        float startY=height/2+rect.height()/2;
        canvas.drawText(text.split("\\.")[0]+".",startX,startY,paint);
        float size=paint.getTextSize();
        Rect rect1=new Rect();
        paint.getTextBounds(text.split("\\.")[0]+".",0,text.split("\\.")[0].length()+1,rect1);
        if (!sameSize)paint.setTextSize(size-17);
        Rect rect2=new Rect();
        paint.getTextBounds(text.split("\\.")[1],0,text.split("\\.")[1].length(),rect2);
        float space=calculate(startX,rect1,rect2);
        canvas.drawText(text.split("\\.")[1],startX+rect1.width()+space,startY,paint);
    }

    /**
     * 计算字符串分割之后后面字符串起始点距离前面字符串结束点的距离
     * @param startX
     * @param before
     * @param after
     */
    private float calculate(float startX,Rect before,Rect after)
    {
        float space=getMeasuredWidth()-startX*2-before.width()-after.width();
        return space;
    }

}
