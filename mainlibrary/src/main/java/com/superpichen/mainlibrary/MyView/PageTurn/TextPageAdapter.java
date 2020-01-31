package com.superpichen.mainlibrary.MyView.PageTurn;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanli on 2018/3/23.
 * 一个纯文本翻页适配器
 */

public class TextPageAdapter extends BasePageAdapter {

    /**
     * 当前需要被翻页的文本
     */
    private String mText;

    /**
     * 被分割好的文本，每一个Text都可以在当前page正好显示
     */
    private List<String> mPageTextList;

    /**
     * 绘制文本的画笔
     */
    private TextPaint mTextPaint;

    /**
     * 当前adapter保存的page宽度
     */
    private int mPageWidth;

    public TextPageAdapter(Context context) {
        super(context);
        this.context=context;
        init();
    }

    Context context;
    public TextPageAdapter(Context context , String mText) {
        super(context);
        this.mText = mText;
        this.context=context;
        init();
    }

    private void init(){
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(CommonUtil.dp2px(mContext , 18));
        AssetManager mgr = context.getAssets();
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/仓耳今楷.ttf");
        mTextPaint.setColor(0xff5fb5d6);
        mTextPaint.setTypeface(tf);
        mPageTextList = new ArrayList<>();
    }

    @Override
    public int getPageCount() {
        return mPageTextList.size();
    }

    @Override
    public void onDraw(int position, Canvas canvas) {
        String pageText =mPageTextList.get(position);
        StaticLayout staticLayout = new StaticLayout(pageText , mTextPaint , mPageWidth-200 , Layout.Alignment.ALIGN_NORMAL , 1.0f , 0.5f , true);
        canvas.translate(40,180);
        staticLayout.draw(canvas);
    }

    @Override
    public void onPageLayoutChanged(int pageWidth, int pageHeight) {
        if(pageWidth > 0){
            mPageWidth = pageWidth;
            mPageTextList.clear();
            if(!TextUtils.isEmpty(mText)){
                StaticLayout staticLayout = new StaticLayout(mText , mTextPaint , pageWidth-200 , Layout.Alignment.ALIGN_NORMAL , 1.0f , 0.5f , true);
                int lineHeight = staticLayout.getLineBottom(0) - staticLayout.getLineTop(0);
                int pageMaxlineCount = pageHeight / (lineHeight+100);
                if(staticLayout.getLineCount() > pageMaxlineCount){
                    // 需要有多页
                    int maxPageTextNum = staticLayout.getLineEnd(pageMaxlineCount);
                    int beginIndex = 0;
                    while(beginIndex < mText.length()){
                        int endIndex = beginIndex + maxPageTextNum;
                        if(endIndex > mText.length()){
                            endIndex = mText.length();
                        }
                        String pageText = mText.substring(beginIndex , endIndex);
                        mPageTextList.add(pageText);
                        beginIndex = endIndex;
                    }
                }else{
                    // 只有一页
                    mPageTextList.add(mText);
                }
            }
        }
    }

    public void setText(String text){
        this.mText = text;
        notifyDataSetChanged();
    }
}
