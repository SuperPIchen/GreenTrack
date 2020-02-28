package com.superpichen.mainlibrary.MyView;

import android.content.Context;
import android.util.AttributeSet;

import com.dzaitsev.android.widget.RadarChartView;

public class MyRadarChartView extends RadarChartView {

    public MyRadarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }
}
