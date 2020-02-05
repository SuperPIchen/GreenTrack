package com.superpichen.mainlibrary.Tools.JavaTools;

import android.content.Context;

public class Dip2px {
    private Context context;
    public Dip2px(Context context){
        this.context=context;
    }
    public int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
