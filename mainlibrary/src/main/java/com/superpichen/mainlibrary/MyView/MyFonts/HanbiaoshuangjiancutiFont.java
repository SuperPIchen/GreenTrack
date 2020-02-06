package com.superpichen.mainlibrary.MyView.MyFonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class HanbiaoshuangjiancutiFont extends TextView {
    public HanbiaoshuangjiancutiFont(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    //重写设置字体方法
    @Override
    public void setTypeface(Typeface tf)
    {
        tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/汉标双剑粗体.ttf");
        super.setTypeface(tf);
    }
}
