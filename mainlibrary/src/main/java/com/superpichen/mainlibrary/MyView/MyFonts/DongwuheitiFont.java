package com.superpichen.mainlibrary.MyView.MyFonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class DongwuheitiFont extends TextView {
    public DongwuheitiFont(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    //重写设置字体方法
    @Override
    public void setTypeface(Typeface tf)
    {
        tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/动物黑体.ttf");
        super.setTypeface(tf);
    }
}
